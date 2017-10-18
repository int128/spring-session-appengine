package org.hidetake.spring.session.appengine;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.session.SessionRepository;

@CommonsLog
@RequiredArgsConstructor
public class MemcacheSessionRepository implements SessionRepository<MemcacheSession> {
    private final MemcacheService memcacheService;
    private final int maxInactiveIntervalInSeconds;

    @Override
    public MemcacheSession createSession() {
        val session = new MemcacheSession(maxInactiveIntervalInSeconds);
        if (log.isDebugEnabled()) {
            log.debug(String.format("createSession() = %s", session));
        }
        return session;
    }

    @Override
    public void save(MemcacheSession session) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("save(%s)", session));
        }
        memcacheService.put(session.getId(), session, Expiration.byDeltaSeconds(session.getMaxInactiveIntervalInSeconds()));
    }

    @Override
    public MemcacheSession getSession(String id) {
        val object = memcacheService.get(id);
        if (object instanceof MemcacheSession) {
            val session = (MemcacheSession) object;
            session.setLastAccessedTime(System.currentTimeMillis());
            if (log.isDebugEnabled()) {
                log.debug(String.format("getSession(%s) = %s", id, session));
            }
            return session;
        } else {
            if (log.isDebugEnabled()) {
                log.debug(String.format("getSession(%s) = %s", id, object));
            }
            return null;
        }
    }

    @Override
    public void delete(String id) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("delete(%s)", id));
        }
        memcacheService.delete(id);
    }
}
