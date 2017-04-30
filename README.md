cdp4j - Chrome Remote Debugging Protocol for Java
=================================================

[![License](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)

cdp4j is a web-automation library for Java. It can be used for automating the use of web pages and for testing web pages.
It use Google Chrome Remote Debugging Protocol to automate Chrome & Chromium based browsers.

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
[Pre built jar file](https://webfolder.io/cdp4j-1.0.0.jar) (722 KB).
[source jar file](https://webfolder.io/cdp4j-1.0.0-sources.jar) (495 KB).


Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java & Chrome/Chromium available.

Headless Mode
-------------
Ui4j can be run in "headless" mode using with [Headless Chrome](https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md).

Javadoc
-------
[cdp4j api](https://webfolder.io/cdp4j/index.html)

Logging
-------
Simple logger for Java (SLF4J) is supported.

Usage Examples
--------------
```java
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class HelloWorld {

    public static void main(String[] args) {
        SessionFactory factory = new Launcher().launch();

        try (Session session = factory.create()) {
            session.navigate("https://webfolder.io");
            session.waitDocumentReady();
            DOM dom = session.getCommand().getDOM();
            Integer root = dom.getDocument().getNodeId();
            String content = dom.getOuterHTML(root);
            System.out.println(content);
        }

        factory.close();
    }
}
```

Building cdp4j
--------------
mvn install

Getting help and getting involved
---------------------------------
cdp4j is a free software but you must buy support package for bug & improvment report. Unfortunately there is no free support.
Please [contact us](https://webfolder.io/support) for support packages & pricing.
