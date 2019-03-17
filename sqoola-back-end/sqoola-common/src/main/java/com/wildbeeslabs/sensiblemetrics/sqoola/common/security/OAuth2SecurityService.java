//package com.wildbeeslabs.sensiblemetrics.sqoola.common.security;
//
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class OAuth2SecurityService {
//
//    public ResourceOwnerPasswordResourceDetails createHeaders(final String username, final String password) {
//        this.createHeaders(username, password, Arrays.asList("read", "write"));
//    }
//
//    public ResourceOwnerPasswordResourceDetails createHeaders(final String username, final String password, final List<String> scopes) {
//        final ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
//        resourceDetails.setGrantType("password");
//        resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
//        resourceDetails.setClientId("quickpolliOSClient");
//        resourceDetails.setClientSecret("top_secret");
//        // Set scopes
//        resourceDetails.setScope(scopes);
//
//        // Resource Owner details
//        resourceDetails.setUsername(username);
//        resourceDetails.setPassword(password);
//        return resourceDetails;
//    }
//}
