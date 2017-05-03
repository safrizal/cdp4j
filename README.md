cdp4j - Chrome DevTools Protocol for Java
=================================================

[![License](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)
[![Travis](https://img.shields.io/travis/webfolderio/cdp4j.svg?label=linux)](https://travis-ci.org/webfolderio/cdp4j) [![AppVeyor](https://img.shields.io/appveyor/ci/WebFolder/cdp4j.svg?label=windows)](https://ci.appveyor.com/project/WebFolder/cdp4j)

cdp4j is a web-automation library for Java. It can be used for automating the use of web pages and for testing web pages.
It use Google Chrome Remote DevTools Protocol to automate Chrome/Chromium based browsers.

Features
--------
* Supports full capabilities of the Chrome Remote DevTools Protocol ([tip-of-tree](https://chromedevtools.github.io/debugger-protocol-viewer/tot/))
* Evaluate JavaScript
* Invoke JavaScript function
* Supports native CSS selector engine
* Supports [Sizzle](http://sizzlejs.com/) selector engine
* Supports Xpath queries
* Incognito Browsing (private tab)
* Full page screen capture
* Trigger Mouse events (click etc...)
* Send keys (text, tab, enter etc...)
* Redirect log entries (javascript, network, storage etc...) from browser to slf4j
* Intercept Network (request & response)
* Upload file programmatically without third party solutions (does not requires AWT Robot etc...)
* get & set Element properties
* Supports Headless Chrome/Chromium
* Navigate back, forward, stop, reload
* clear cache, clear cookies, list cookies
* set & get values of form elements
* Supports event handling 

Supported Java Versions
-----------------------

Oracle & OpenJDK Java 8.

Both the JRE and the JDK are suitable for use with this library.

Licensing
---------

cdp4j is released under the terms of the MIT License (MIT).
You are free to use cdp4j or any of its constituent parts in any other project (even commercial projects) so long as its copyright headers are left intact.

Stability
---------
This library is suitable for use in production systems.

Download
--------
[cdp4j-1.0.1.jar](https://webfolder.io/download/cdp4j-1.0.1.jar) - 723 KB

[cdp4j-1.0.1-sources.jar](https://webfolder.io/download/cdp4j-1.0.1-sources.jar) - 496 KB

Integration with Maven
----------------------
1. Download jar and sources file.

2. install the jar into your local Maven repository as follows:

```bash
mvn install:install-file -Dfile=cdp4j-1.0.1.jar -DgroupId=io.webfolder -DartifactId=cdp4j -Dversion=1.0.1 -Dpackaging=jar -DgeneratePom=true
```

3. install source jar into your local Maven repository as follows:
```bash
mvn install:install-file -Dfile=cdp4j-1.0.1-sources.jar -DgroupId=io.webfolder -DartifactId=cdp4j -Dversion=1.0.1 -Dpackaging=jar -DgeneratePom=true -Dclassifier=sources
```

4. Add the following dependency to `pom.xml` file:

```xml
<dependency>
    <groupId>io.webfolder</groupId>
    <artifactId>cdp4j</artifactId>
    <version>1.0.1</version>
</dependency>
```

Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java & Chrome/Chromium available.

Headless Mode
-------------
cdp4j can be run in "headless" mode using with [Headless Chrome](https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md).

### Install Chrome on Ubuntu

```bash
# https://askubuntu.com/questions/79280/how-to-install-chrome-browser-properly-via-command-line
sudo apt-get install libxss1 libappindicator1 libindicator7
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo dpkg -i google-chrome*.deb # Might show "errors", fixed by next line
sudo apt-get install -f
```

Test headless Chrome

```bash
google-chrome --headless --remote-debugging-port=9222 --disable-gpu
```

JavaDoc
-------
[cdp4j api](https://webfolder.io/cdp4j/javadoc/index.html)

Logging
-------
Simple logger for Java (SLF4J) is supported.

Design Principles
-----------------
* Avoid external dependencies as much as possible.
* Support only Chrome/Chromium based browsers.
* Supports full capabilities of the Chrome Remote DevTools Protocol.
* Keep the API simple.

CSS Selector Engine
-------------------
cdp4j use W3C selector engine which is default selector engine of Chrome/Chromium. Alternatively [Sizzle](http://http://sizzlejs.com) selector engine might be used.
Sizzle is the css selector engine of JQuery and it supports extra selectors like _:has(div)_, _:text_, _contains(text)_ etc.
Check the [Sizzle.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Sizzle.java) for using sizzle with cdp4j.


Usage Examples
--------------

### Print text content with cdp4j

```java
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class HelloWorld {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();

        try (Session session = factory.create()) {
            session.navigate("https://webfolder.io");
            session.waitDocumentReady();
            String content = (String) session.getProperty("//body", "outerText");
            System.out.println(content);
        }

        factory.close();
    }
}
```

### Full page screen capture with cdp4j

```java
import static java.awt.Desktop.getDesktop;
import static java.awt.Desktop.isDesktopSupported;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.write;

import java.io.IOException;
import java.nio.file.Path;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Screenshot {

    public static void main(String[] args) throws IOException, InterruptedException {
        SessionFactory factory = new Launcher().launch();

        Path file = createTempFile("screenshot", ".png");

        try (Session session = factory.create()) {
            session.navigate("https://news.ycombinator.com");
            session.waitDocumentReady();
            byte[] data = session.captureScreenshot();
            write(file, data);
        }

        if (isDesktopSupported()) {
            getDesktop().open(file.toFile());
        }

        factory.close();
    }
}
```

Samples
-------
[Attributes.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Attributes.java)

[Bing.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Bing.java)

[BingTranslator.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/BingTranslator.java)

[CheckBox.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/CheckBox.java)

[ExecuteJavascript.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/ExecuteJavascript.java)

[GoogleTranslate.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/GoogleTranslate.java)

[HelloWorld.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/HelloWorld.java)

[IncognitoBrowsing.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/IncognitoBrowsing.java)

[Logging.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Logging.java)

[MultiSelect.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/MultiSelect.java)

[NetworkResponse.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/NetworkResponse.java)

[Screenshot.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Screenshot.java)

[Select.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Select.java)

[SendKeys.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/SendKeys.java)

[SharedSession.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/SharedSession.java)

[Sizzle.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Sizzle.java)

[UserAgent.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/UserAgent.java)

[WaitUntil.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/WaitUntil.java)

[XPathSelector.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/XPathSelector.java)

Building cdp4j
--------------
`mvn install`

Getting Help
--------------------------------------------------------------------------------------------

![WebFolder](https://raw.githubusercontent.com/webfolderio/cdp4j/master/images/logo.png)

cdp4j is an MIT licensed open source project and completely free to use. However, the amount of effort needed to maintain and develop new features for the project is not sustainable without proper financial backing. You can support cdp4j development by 
**buying** support package. Please [contact us](https://webfolder.io/support) for support packages & pricing.

