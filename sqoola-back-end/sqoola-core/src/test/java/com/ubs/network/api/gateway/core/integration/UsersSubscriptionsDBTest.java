/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ubs.network.api.gateway.core.integration;

import com.wildbeeslabs.api.rest.common.model.UserActivityStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.common.utils.DateUtils;
import com.wildbeeslabs.api.rest.common.utils.HibernateUtils;

import java.math.BigDecimal;

//import org.hibernate.HibernateException;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * User/UserSubOrder/Subscription DB Entity Test client
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class UsersSubscriptionsDBTest {

    private static void init() {
        final Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Subscription groupAdmin = new Subscription();
        groupAdmin.setExpiredAt(DateUtils.strToDate("2017-05-28 00:00:00+0300"));
        //groupAdmin.setExpiredAt("2017-05-28 00:00:00");
        groupAdmin.setTitle("Administrator Group");
        groupAdmin.setStatus(new SubscriptionStatusInfo());//SubscriptionStatusInfo.SubscriptionStatusType.PREMIUM

        Subscription groupGuest = new Subscription();
        groupGuest.setExpiredAt(DateUtils.strToDate("2017-05-28 00:00:00+0300"));
        //groupGuest.setExpiredAt("2017-05-28 00:00:00");
        groupGuest.setTitle("Guest Group");
        groupGuest.setStatus(new SubscriptionStatusInfo());

        User user1 = new User();
        user1.setLogin("Tom");
        user1.setRating(new BigDecimal(24.3));
        UserActivityStatusInfo status = new UserActivityStatusInfo();
        status.setStatusType(UserActivityStatusInfo.StatusType.CHALLENGING);
        user1.setStatus(status);

        User user2 = new User();
        user2.setLogin("Mary");
        user2.setRating(new BigDecimal(43.3));
        status.setStatusType(UserActivityStatusInfo.StatusType.CHALLENGING);
        user2.setStatus(status);

        UserSubOrder userSubOrder1 = new UserSubOrder();
        //userSubOrder1.setCreatedAt("2017-05-28 00:00:00+0300");
        userSubOrder1.setExpiredAt(null);
        userSubOrder1.setSubscription(groupAdmin);
        userSubOrder1.setUser(user1);

        UserSubOrder userSubOrder2 = new UserSubOrder();
        //userSubOrder2.setCreatedAt("2017-05-28 00:00:00+0300");
        userSubOrder2.setExpiredAt(null);
        userSubOrder2.setSubscription(groupGuest);
        userSubOrder2.setUser(user1);

        UserSubOrder userSubOrder3 = new UserSubOrder();
        //userSubOrder3.setCreatedAt("2017-05-28 00:00:00+0300");
        userSubOrder3.setExpiredAt(null);
        userSubOrder3.setSubscription(groupAdmin);
        userSubOrder3.setUser(user2);

        user1.addSubOrder(userSubOrder1);
        user1.addSubOrder(userSubOrder2);
        user2.addSubOrder(userSubOrder3);

        groupAdmin.addUserOrder(userSubOrder1);
        groupAdmin.addUserOrder(userSubOrder3);
        groupGuest.addUserOrder(userSubOrder2);

        session.save(groupAdmin);
        session.save(groupGuest);
        session.save(user1);
        session.save(user2);
        session.save(userSubOrder1);
        session.save(userSubOrder2);
        session.save(userSubOrder3);

        session.getTransaction().commit();
        HibernateUtils.shutdown();
    }

    public static void main(String[] args) {
        /*Configuration configuration = new Configuration().configure();

        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
        //init();
    }
}
