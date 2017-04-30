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

import java.util.HashMap;
import java.util.Map;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class GoogleTranslate {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();

        Session session = factory
                            .create()
                            .installSizzle();

        Network network = session.getCommand().getNetwork();
        network.enable();
        session.wait(1000);

        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept-Language", "en-US,en;q=1");
        headers.put("Cookie", "");
        network.setExtraHTTPHeaders(headers);
        session.wait(500);

        session.navigate("https://translate.google.co.uk");
        session.waitDocumentReady();

        String appName = session.getText("#gt-appname");
        if ( ! appName.equals("Translate") ) {
            session.clearCookies();
            session.wait(1000);
            network.setExtraHTTPHeaders(headers);
            session.reload();
        }

        session.click("#gt-sl-gms")
            .wait(500)
            .click("div:contains('English'):last")
            .wait(500)
            .click("#gt-tl-gms")
            .wait(500)
            .click("div:contains('Estonian'):last")
            .wait(500)
            .focus("textarea:first")
            .wait(500)
            .sendKeys("hello world")
            .wait(500);

        System.out.println(session.getText("#gt-res-dir-ctr"));

        factory.close();
    }
}
