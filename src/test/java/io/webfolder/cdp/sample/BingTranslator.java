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

public class BingTranslator {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();

        List<String> words = asList("hello", "world");

        Session session = factory
                            .create()
                            .navigate("https://www.bing.com/translator")
                            .waitDocumentReady()
                            .enableConsoleLog()
                            .enableDetailLog()
                            .enableNetworkLog();

        session.click(".sourceText #LS_LangList") // click source language
                .wait(2000)
                .click(".sourceText td[value='en']") // choose English
                .wait(2000)
                .click(".destinationText #LS_LangList") // click destination language
                .wait(2000)
                .click(".destinationText td[value='et']") // choose Estonian
                .wait(2000);

        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            String text = session
                                .focus(".sourceText .srcTextarea")
                                .selectInputText(".sourceText .srcTextarea")
                                .sendBackspace()
                                .sendKeys(word)
                                .wait(1000)
                                .getText("#destText");

            builder.append(text).append(" ");
        }

        System.out.println(builder.toString());

        factory.close();
    }
}
