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

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.CountDownLatch;

import com.google.gson.JsonElement;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.CommandException;

class WSContext {

    private CountDownLatch latch = new CountDownLatch(1);

    private JsonElement data;

    private CommandException error;

    private static final int WS_TIMEOUT = 10; // 10 seconds

    void await() {
        try {
            latch.await(WS_TIMEOUT, SECONDS);
        } catch (InterruptedException e) {
            throw new CdpException(e);
        }
    }

    void setData(final JsonElement data) {
        this.data = data;
        latch.countDown();
    }

    JsonElement getData() {
        return data;
    }

    void setError(CommandException error) {
        this.error = error;
        latch.countDown();
    }

    CommandException getError() {
        return error;
    }
}
