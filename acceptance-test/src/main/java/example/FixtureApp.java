package example;

import org.hidetake.spring.session.appengine.EnableMemcacheHttpSession;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableMemcacheHttpSession
public class FixtureApp extends SpringBootServletInitializer {
}
