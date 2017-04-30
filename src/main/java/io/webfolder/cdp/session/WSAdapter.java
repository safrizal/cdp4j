/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.session;

import static java.lang.Double.parseDouble;
import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.exception.CommandException;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.logger.CdpLogger;

class WSAdapter extends WebSocketAdapter {

    private final Map<String, Events> events = listEvents();

    private final Gson gson;

    private final Map<Integer, WSContext> contextList;

    private final List<EventListener<?>> eventListeners;

    private final Executor executor;

    private final CdpLogger log;

    private Session session;

    private static class TerminateSession implements Runnable {

        private final Session session;

        private final JsonObject object;

        public TerminateSession(final Session session, final JsonObject object) {
            this.session = session;
            this.object = object;
        }

        @Override
        public void run() {
            if (session != null) {
                session.close();
                session.terminate(
                        object.get("params")
                        .getAsJsonObject()
                        .get("reason").getAsString());
            }
        }
    }

    WSAdapter(
            final Gson gson,
            final Map<Integer, WSContext> contextList,
            final List<EventListener<?>> eventListeners,
            final Executor executor,
            final CdpLogger log) {
        this.gson           = gson;
        this.contextList    = contextList;
        this.eventListeners = eventListeners;
        this.executor       = executor;
        this.log            = log; 
    }

    protected Map<String, Events> listEvents() {
        Map<String, Events> map = new HashMap<>();
        for (Events next : Events.values()) {
            map.put(next.domain + "." + next.name, next);
        }
        return unmodifiableMap(map);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void onTextMessage(
                            final WebSocket websocket,
                            final String data) throws Exception {
        executor.execute(() -> {
            log.debug(data);
            JsonElement  json = gson.fromJson(data, JsonElement.class);
            JsonObject object = json.getAsJsonObject();
            JsonElement idElement = object.get("id");
            if (idElement != null) {
                String id = idElement.getAsString();
                if (id != null) {
                    int valId = (int) parseDouble(id);
                    WSContext context = contextList.get(valId);
                    if (context != null) {
                        JsonObject error = object.getAsJsonObject("error");
                        if (error != null) {
                            int code = (int) error.getAsJsonPrimitive("code").getAsDouble();
                            String message = error.getAsJsonPrimitive("message").getAsString();
                            context.setError(new CommandException(code, message));
                        } else {
                            context.setData(json);
                        }
                    }
                }
            } else {
                JsonElement method = object.get("method");
                if (method != null && method.isJsonPrimitive()) {
                    String eventName = method.getAsString();
                    if ("Inspector.detached".equals(eventName) && session != null) {
                        if (session != null) {
                            Thread thread = new Thread(new TerminateSession(session, object));
                            thread.setName("cdp4j-terminate");
                            thread.setDaemon(true);
                            thread.start();
                            session = null;
                        }
                    } else {
                        Events event = events.get(eventName);
                        if (event != null) {
                            JsonElement params = object.get("params");
                            Object value = gson.fromJson(params, event.klass);
                            for (EventListener next : eventListeners) {
                                executor.execute(() -> {
                                    next.onEvent(event, value);
                                });
                            }
                        }
                    }
                }
            }
        });
    }

    void setSession(final Session session) {
        this.session = session;
    }
}
