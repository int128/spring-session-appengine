package org.hidetake.spring.session.appengine;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.session.SessionRepository;

@CommonsLog
@Data
public class MemcacheSessionRepository implements SessionRepository<MemcacheSession> {
    private final MemcacheService memcacheService;
    private final int maxInactiveIntervalInSeconds;

    @Override
    public MemcacheSession createSession() {
        val session = new MemcacheSession();
        session.setMaxInactiveIntervalInSeconds(maxInactiveIntervalInSeconds);
        if (log.isDebugEnabled()) {
            log.debug("Created session " + session);
        }
        return session;
    }

    @Override
    public void save(MemcacheSession session) {
        memcacheService.put(session.getId(), session,
                Expiration.byDeltaSeconds(session.getMaxInactiveIntervalInSeconds()));
        if (log.isDebugEnabled()) {
            log.debug("Saved session " + session);
        }
    }

    @Override
    public MemcacheSession getSession(String id) {
        val object = memcacheService.get(id);
        if (object instanceof MemcacheSession) {
            val session = (MemcacheSession) object;
            session.setLastAccessedTime(System.currentTimeMillis());
            if (log.isDebugEnabled()) {
                log.debug("Found session " + session);
            }
            return session;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Could not find session id=" + id);
            }
            return null;
        }
    }

    @Override
    public void delete(String id) {
        memcacheService.delete(id);
        if (log.isDebugEnabled()) {
            log.debug("Deleted session id=" + id);
        }
    }
}
