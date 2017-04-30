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

import java.util.logging.Logger;

public class CdpLoggerFactory {

    private final CdpLoggerType loggerType;

    private final Logger log = Logger.getLogger(CdpLoggerFactory.class.getName());

    private static CdpLogger NULL_LOGGER = new CdpLogger() {

        @Override
        public void info(String message, Object ...args) { }

        @Override
        public void debug(String message, Object ...args) { }

        @Override
        public void error(String message, Object ...args) { }

        @Override
        public void warning(String message, Object ...args) { }

        @Override
        public void error(String message, Throwable t) { }
    };

    public CdpLoggerFactory(final CdpLoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public CdpLogger getLogger(String name) {
        try {
            switch (loggerType) {
                case Slf4j: return new CdpSlf4jLogger(name);
                default   : return NULL_LOGGER;
            }
        } catch (Throwable e) {
            log.warning(e.getMessage());
            return NULL_LOGGER;
        }
    }
}
