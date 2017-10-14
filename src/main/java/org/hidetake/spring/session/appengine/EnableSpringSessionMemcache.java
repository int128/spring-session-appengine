package org.hidetake.spring.session.appengine;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MemcacheSessionConfiguration.class)
@Documented
@Configuration
public @interface EnableSpringSessionMemcache {
    int maxInactiveIntervalInSeconds() default 1800;
}
