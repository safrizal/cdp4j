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
package io.webfolder.cdp.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CdpSlf4jLogger implements CdpLogger {

    private final Logger log;

    public CdpSlf4jLogger(final String name) {
        log = LoggerFactory.getLogger(name);
    }

    @Override
    public void info(final String message, final Object ...args) {
        if (log.isInfoEnabled()) {
            log.info(message, args);
        }
    }

    @Override
    public void debug(final String message, final Object ...args) {
        if (log.isDebugEnabled()) {
            log.debug(message, args);
        }
    }

    @Override
    public void error(final String message, final Object ...args) {
        if (log.isErrorEnabled()) {
            log.error(message, args);
        }
    }

    @Override
    public void warning(final String message, final Object ...args) {
        if (log.isWarnEnabled()) {
            log.warn(message, args);
        }
    }

    @Override
    public void error(String message, Throwable t) {
        if (log.isErrorEnabled()) {
            log.error(message, t);
        }
    }
}
