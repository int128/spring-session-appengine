package org.hidetake.spring.session.appengine.test

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class FixtureController {
    @Autowired
    SessionContext context

    @GetMapping('/value')
    int get() {
        log.debug("context=$context")
        context.value
    }

    @GetMapping('/increment')
    int increment() {
        log.debug("context=$context")
        context.value = context.value + 1
    }
}
