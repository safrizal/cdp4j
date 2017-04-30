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
package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkResponseReceived;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.Response;

public class NetworkResponse {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();

        try (Session session = factory.create()) {
            session.getCommand().getNetwork().enable();
            session.navigate("http://cnn.com");
            session.addEventListener((e, d) -> {
                if (NetworkResponseReceived.equals(e)) {
                    ResponseReceived rr = (ResponseReceived) d;
                    Response response = rr.getResponse();
                    GetResponseBodyResult rb = session.getCommand().getNetwork().getResponseBody(rr.getRequestId());
                    String body = rb.getBody();
                    System.out.println("----------------------------------------");
                    System.out.println("URL       : " + response.getUrl());
                    System.out.println("Status    : HTTP " + response.getStatus().intValue() + " " + response.getStatusText());
                    System.out.println("Mime Type : " + response.getMimeType());
                    System.out.println("Content   : " + body.substring(0, body.length() > 1024 ? 1024 : body.length()));
                }
            });
            session.waitDocumentReady();
        }
    }
}
