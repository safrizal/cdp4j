cdp4j - Chrome Remote Debugging Protocol for Java
=================================================

[![License](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)

cdp4j is a web-automation library for Java. It can be used for automating the use of web pages and for testing web pages.
It use Google Chrome Remote Debugging Protocol to automate Chrome & Chromium based browsers.

Features
--------
* Full page screen capture
* Evaluate javascript
* Invoke JavaScript function
* Supports native CSS selector engine
* Supports [Sizzle](http://sizzlejs.com/) selector engine
* Supports Xpath queries
* Incognito Browsing (private tab)
* Trigger Mouse events (click etc...)
* Send keys (text, tab, enter etc...)
* Redirect Browser's log entries (javascript, network, storage etc...) to Java
* Intercept Network
* Upload file programmatically without third party solutions (does not requires AWT Robot etc.)
* get & set Element properties
* Supports Headless Chrome/Chromium
* Navigate back, forward, stop, reload
* clear cache, clear cookies, list cookies
* set & get values of form elements

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
[cdp4j-1.0.0.jar](https://webfolder.io/download/cdp4j-1.0.0.jar) (722 KB).

[cdp4j-1.0.0-sources.jar](https://webfolder.io/download/cdp4j-1.0.0-sources.jar) (495 KB).

Integration with Maven
----------------------
1. Download jar and sources file.

2. install the jar into your local Maven repository as follows:

```bash
mvn install:install-file -Dfile=cdp4j-1.0.0-sources.jar -DgroupId=io.webfolder -DartifactId=cdp4j -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true
```

3. install source jar into your local Maven repository as follows:
```bash
mvn install:install-file -Dfile=cdp4j-1.0.0-sources.jar -DgroupId=io.webfolder -DartifactId=cdp4j -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true -Dclassifier=sources
```

4. Add the following dependency to `pom.xml` file:

```xml
<dependency>
    <groupId>io.webfolder</groupId>
    <artifactId>cdp4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java & Chrome/Chromium available.

Headless Mode
-------------
Ui4j can be run in "headless" mode using with [Headless Chrome](https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md).

JavaDoc
-------
[cdp4j api](https://webfolder.io/cdp4j/javadoc/index.html)

Logging
-------
Simple logger for Java (SLF4J) is supported.

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
```java

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
---------------------------------
cdp4j is a free software but you must **buy** support package for bug & improvment reports.
Please [contact us](https://webfolder.io/support) for support packages & pricing.
