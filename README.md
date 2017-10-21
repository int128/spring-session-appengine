# Spring Session Integration for App Engine [![CircleCI](https://circleci.com/gh/int128/spring-session-appengine.svg?style=shield)](https://circleci.com/gh/int128/spring-session-appengine) [![Gradle Status](https://gradleupdate.appspot.com/int128/spring-session-appengine/status.svg?branch=master)](https://gradleupdate.appspot.com/int128/spring-session-appengine/status)

A Spring Session integration library for App Engine Java Standard Environment. 


## Getting Started

See [example app](example-app) for more details.

```groovy
// build.gradle
compile 'org.hidetake:spring-session-appengine:0.9.0'
```

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.hidetake</groupId>
    <artifactId>spring-session-appengine</artifactId>
    <version>0.9.0</version>
</dependency>
```


## Caveat

Spring Session does not work properly on some case.
Unfortunately there is no workaround at this time.

```java
public class FixtureController {
    // This works perfectly.
    @ResponseBody
    String index() {
        return "0";
    }

    // This does not work because "Set-Cookie" header did not send.
    @ResponseBody
    int index() {
        return 0;
    }
}
```


## Contributions

This is an open source software licensed under the Apache License Version 2.0.
Feel free to open issues or pull requests.

### Release

CircleCI builds the plugin continuously.
It also publishes an artifact if a tag is pushed and following variables are set.

Environment Variable        | Value
----------------------------|------
`$BINTRAY_USER`             | Bintray user name
`$BINTRAY_KEY`              | Bintray key
