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
import com.wildbeeslabs.api.rest.subscription.model.User;
import java.math.BigDecimal;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Subscription REST Application Test client
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class SubscriptionRestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080/newsletterSub/api";

    private static HttpHeaders getHeaders(final String username, final String password) {
        String auth = username + ":" + password;
        String encodedCredential = new String(Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8"))));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredential);
        return headers;
    }

    /* GET */
    private static void listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(getHeaders("user", "user123"));
        //List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI + "/users", List.class);
        ResponseEntity<List> responseEntity = restTemplate.exchange(REST_SERVICE_URI + "/users", HttpMethod.GET, requestEntity, List.class, 1);
        List<User> userList = responseEntity.getBody();

        if (userList != null) {
            userList.forEach((map) -> {
                System.out.println("User : id=" + map.getId() + ", Name=" + map.getLogin() + ", Age=" + map.getContact().getAge() + ", raing=" + map.getRating() + ", status=" + map.getStatus());
            });
        } else {
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    private static void getUser() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
        System.out.println(user);
    }

    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();

        User user = new User();
        //user.setAge(12);
        user.setLogin("Sarah");
        user.setRating(new BigDecimal(24.3));
        UserActivityStatusInfo status = new UserActivityStatusInfo();
        status.setStatusType(UserActivityStatusInfo.StatusType.CHALLENGING);
        user.setStatus(status);

        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user", user, User.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();

        User user = new User();
        user.setLogin("Tomy");
        UserActivityStatusInfo status = new UserActivityStatusInfo();
        status.setStatusType(UserActivityStatusInfo.StatusType.CHALLENGING);
        user.setStatus(status);

        restTemplate.put(REST_SERVICE_URI + "/user/1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/user/3");
    }

    /* DELETE */
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/user/");
    }

    public static void main(String args[]) {
        listAllUsers();
        /*getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        deleteAllUsers();
        listAllUsers();*/
    }
}
