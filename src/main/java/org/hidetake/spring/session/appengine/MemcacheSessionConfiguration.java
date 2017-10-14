package org.hidetake.spring.session.appengine;

import com.google.appengine.api.memcache.MemcacheServiceFactory;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.session.web.http.SessionRepositoryFilter;

import javax.servlet.ServletContext;

@Configuration
public class MemcacheSessionConfiguration {
    private int maxInactiveIntervalInSeconds;

    public void setImportMetadata(AnnotationMetadata importMetadata) {
        val map = importMetadata.getAnnotationAttributes(EnableSpringSessionMemcache.class.getName());
        val attributes = AnnotationAttributes.fromMap(map);
        maxInactiveIntervalInSeconds = attributes.getNumber("maxInactiveIntervalInSeconds");
    }

    @Bean
    public MemcacheSessionRepository memcacheSessionRepository() {
        return new MemcacheSessionRepository(MemcacheServiceFactory.getMemcacheService(), maxInactiveIntervalInSeconds);
    }

    @Bean
    public SessionRepositoryFilter<MemcacheSession> springSessionRepositoryFilter(ServletContext servletContext) {
        val filter = new SessionRepositoryFilter<MemcacheSession>(memcacheSessionRepository());
        filter.setServletContext(servletContext);
        return filter;
    }
}
