package com.sensiblemetrics.api.sqoola.common.handler;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@EqualsAndHashCode
@ToString
public class SessionCountListener implements HttpSessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger();

    @Override
    public void sessionCreated(final HttpSessionEvent se) {
        this.sessionCount.incrementAndGet();
        setActiveSessionCount(se);
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent se) {
        this.sessionCount.decrementAndGet();
        setActiveSessionCount(se);
    }

    private void setActiveSessionCount(final HttpSessionEvent se) {
        se.getSession().getServletContext().setAttribute("activeSessions", this.sessionCount.get());
        System.out.println("Total Active Session: " + this.sessionCount.get());
    }
}
