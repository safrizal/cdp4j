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

import static java.util.Arrays.asList;

import java.util.List;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.Cookie;

public class SendKeys {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();
        Session session = factory.create();

        session.getCommand().getNetwork().enable();
        List<Cookie> cookies = session.getCommand().getNetwork().getCookies(asList("https://demo.webfolder.io"));
        for (Cookie cookie : cookies) {
            session.getCommand().getNetwork().deleteCookie(cookie.getName(), "https://demo.webfolder.io");
        }
        session.navigate("https://demo.webfolder.io");
        session.waitDocumentReady();
        session.enableNetworkLog();
        session.focus("#txt-password");
        session.sendEnter();
        session.waitDocumentReady();
        session.wait(1000);
        session.focus("#txt-search");
        session.sendKeys("snoopy");
        session.wait(500);
        session.sendDownArrow();
        session.sendEnter();
        session.sendEsc();
        session.wait(500);
        session.click("a[data-image]");
        session.wait(2000);
        session.click("#btn-logout");
        session.wait(1000);

        factory.close();
    }
}
