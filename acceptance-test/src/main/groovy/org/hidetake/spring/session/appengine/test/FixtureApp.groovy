package org.hidetake.spring.session.appengine.test

import org.hidetake.spring.session.appengine.EnableMemcacheHttpSession
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
@EnableMemcacheHttpSession
class FixtureApp extends SpringBootServletInitializer {
}
