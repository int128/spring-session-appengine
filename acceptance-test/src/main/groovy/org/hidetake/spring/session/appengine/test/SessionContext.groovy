package org.hidetake.spring.session.appengine.test

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope

@SessionScope
@Component
class SessionContext implements Serializable {
    int value
}
