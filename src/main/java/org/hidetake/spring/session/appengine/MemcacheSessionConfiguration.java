package org.hidetake.spring.session.appengine;

import com.google.appengine.api.memcache.MemcacheServiceFactory;
import lombok.ToString;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
@CommonsLog
@ToString
public class MemcacheSessionConfiguration implements ImportAware {
    private int maxInactiveIntervalInSeconds;

    public void setImportMetadata(AnnotationMetadata importMetadata) {
        val map = importMetadata.getAnnotationAttributes(EnableMemcacheHttpSession.class.getName());
        val attributes = AnnotationAttributes.fromMap(map);
        maxInactiveIntervalInSeconds = attributes.getNumber("maxInactiveIntervalInSeconds");
        if (log.isInfoEnabled()) {
            log.info(String.format("Initializing %s", this));
        }
    }

    @Bean
    public MemcacheSessionRepository memcacheSessionRepository() {
        val memcacheService = MemcacheServiceFactory.getMemcacheService();
        return new MemcacheSessionRepository(memcacheService, maxInactiveIntervalInSeconds);
    }
}
