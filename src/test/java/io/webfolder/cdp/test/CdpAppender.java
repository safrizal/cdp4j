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
package io.webfolder.cdp.test;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

@SuppressWarnings("rawtypes")
public class CdpAppender extends AppenderBase {

    private List<String> logEntries = new CopyOnWriteArrayList<>();

    private List<String> ignoredMethods = asList(
        "wait"
    );

    @Override
    protected void append(Object eventObject) {
        if (eventObject == null) {
            return;
        }
        if ( ! eventObject.getClass().isAssignableFrom(LoggingEvent.class) ) {
            return;
        }
        LoggingEvent event = (LoggingEvent) eventObject;
        String logEntry = event.getFormattedMessage();
        if (logEntry == null || logEntry.isEmpty()) {
            return;
        }
        int end = logEntry.indexOf("(");
        if (end > 0) {
            String methodName = logEntry.substring(0, end);
            if (ignoredMethods.contains(methodName)) {
                return;
            }
        }
        logEntries.add(logEntry);
    }

    public void clearLogEntries() {
        logEntries.clear();
    }

    public List<String> getLogEntries() {
        return logEntries;
    }
}
