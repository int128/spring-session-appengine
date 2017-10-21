package example;

import org.hidetake.spring.session.appengine.EnableMemcacheHttpSession;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableMemcacheHttpSession(maxInactiveIntervalInSeconds = 3)  // for testing timeout
public class FixtureApp extends SpringBootServletInitializer {
}
