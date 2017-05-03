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

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class TestSession {

    private static CdpAppender appender;

    private static SessionFactory factory;

    private static Session session;

    private static LoggerContext loggerContext;

    private static String os = System.getProperty("os.name").toLowerCase();

    @BeforeClass
    @SuppressWarnings("unchecked")
    public static void init() {
        loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        appender = new CdpAppender();
        appender.setContext(loggerContext);
        appender.start();
        appender.setName(CdpAppender.class.getName());

        Logger logger = loggerContext.getLogger("cdp4j.flow");
        logger.addAppender((Appender<ILoggingEvent>) appender);

        List<String> arguments = new ArrayList<>();
        if (os.indexOf("nix") >= 0) {
            arguments.add("--headless");
            arguments.add("--disable-gpu");
        }

        factory = new Launcher().launch();

        session = factory.create();

        session.enableConsoleLog();

        URL url = TestSession.class.getResource("/session-test.html");
        session.navigate(url.toString());
    }

    @AfterClass
    public static void dispose() {
        appender.stop();
        loggerContext.stop();
        factory.close();
    }

    @Test
    public void test() {
        session.activate();
        session.getTitle();

        session.getValue("[type=text]");
        session.setValue("[type=text]", "bar");
        session.getValue("[type=text]");

        session.setAttribute("[type=text]", "my-attr", "my-value");
        session.getAttribute("[type=text]", "my-attr");

        session.isChecked("#myradio");
        session.setChecked("#myradio", false);
        session.isChecked("#myradio");

        session.click("#mybutton");

        session.focus("[type=text]");

        session.selectInputText("[type=text]");
        session.sendBackspace();
        session.sendKeys("hello");
        session.getProperty("[type=text]", "value");

        session.stop();
        session.reload();
        session.waitDocumentReady();

        assertTrue(session.isDomReady());

        session.evaluate("2 + 2");

        session.getSelectedIndex("#single-select");
        session.setSelectedIndex("#single-select", 1);
        session.getSelectedIndex("#single-select");
        session.clearOptions("#single-%s", "select");
        session.getSelectedIndex("#single-select");

        List<Option> options = session.getOptions("#multi-select");
        assertEquals(2, options.size());

        session.setSelectedOptions("#multi-%s", Arrays.asList(1), "select");

        options = session.getOptions("#multi-%s", "select");

        assertEquals(2, options.size());
        assertFalse(options.get(0).isSelected());
        assertTrue(options.get(1).isSelected());

        List<String> expecteds = emptyList();
        try {
            expecteds = readAllLines(get("src/test/resources/TestSession-console.txt"));
        } catch (IOException e) {
            throw new CdpException(e);
        }

        session.installSizzle();
        assertTrue(session.useSizzle());

        session.getProperty("p:contains('%s')", "innerHTML", "hello");
        session.setProperty("//p", "innerHTML", "hi");
        session.getProperty("p:contains('hi')", "innerHTML");

        session.setDisabled("[type=text]", true);
        session.isDisabled("[type=text]");

        try {
            session.focus("[type=text]");
        } catch (CdpException e) {
            assertEquals("Element is not focusable", e.getMessage());
        }

        session.setUserAgent("my browser");
        session.evaluate("navigator.userAgent");

        assertTrue(session.getQueryString().isEmpty());

        session.callFunction("myfunc");

        session.close();

        assertFalse(session.isConnected());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> actuals = appender.getLogEntries();

        List<String> failedExpecteds = new ArrayList<>();
        List<String>   failedActuals = new ArrayList<>();

        for (int i = 0; i < actuals.size(); i++) {
            String expected = i < expecteds.size() ? expecteds.get(i) : null;
            String   actual = actuals.get(i);
            String methodName = "";
            if (expected != null && expected.startsWith("invoked(")) {
                methodName = expected.substring("invoked(".length(), expected.length() - 1);
            }

            boolean ok = expected != null && expected.equals(actual) || actual.startsWith(methodName);
            if (ok) {
                System.out.println("[    OK    ] " + actual);
            } else {
                System.out.println("[  FAILED  ] " + actual);
                System.out.println("[ EXPECTED ] " + expected);
            }
            if ( ! ok ) {
                failedExpecteds.add(expected);
                failedActuals.add(actual);
            }
        }

        assertArrayEquals(failedExpecteds.toArray(new String[] { }), failedActuals.toArray(new String[] { }));

        assertEquals(expecteds.size(), actuals.size());
    }
}
