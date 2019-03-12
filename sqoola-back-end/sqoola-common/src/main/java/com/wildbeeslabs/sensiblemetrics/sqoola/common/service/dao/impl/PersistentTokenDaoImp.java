package com.wildbeeslabs.sensiblemetrics.sqoola.common.service.dao.impl;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.PersistentLogins;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Repository("persistentTokenRepository")
@Transactional
public class PersistentTokenDaoImp implements PersistentTokenRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        final PersistentLogins logins = new PersistentLogins();
        logins.setUsername(token.getUsername());
        logins.setSeries(token.getSeries());
        logins.setToken(token.getTokenValue());
        logins.setLastUsed(token.getDate());
        sessionFactory.getCurrentSession().save(logins);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins logins = sessionFactory.getCurrentSession()
            .get(PersistentLogins.class, seriesId);

        if (Objects.nonNull(logins)) {
            return new PersistentRememberMeToken(logins.getUsername(), logins.getSeries(), logins.getToken(), logins.getLastUsed());
        }

        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        sessionFactory.getCurrentSession().createQuery("delete from PersistentLogins where username=:userName")
            .setParameter("userName", username).executeUpdate();
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        final Session session = sessionFactory.getCurrentSession();
        PersistentLogins logins = session.get(PersistentLogins.class, series);
        logins.setToken(tokenValue);
        logins.setLastUsed(lastUsed);
    }

}
