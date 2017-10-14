package org.hidetake.spring.session.appengine;

import lombok.Data;
import org.springframework.session.ExpiringSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
public class MemcacheSession implements ExpiringSession, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id = UUID.randomUUID().toString();
    private final Map<String, Object> attributes = new HashMap<>();
    private long creationTime = System.currentTimeMillis();
    private long lastAccessedTime = creationTime;
    private int maxInactiveIntervalInSeconds;

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String attributeName) {
        return (T) attributes.get(attributeName);
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    @Override
    public void removeAttribute(String attributeName) {
        attributes.remove(attributeName);
    }
}
