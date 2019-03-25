//package com.sensiblemetrics.api.sqoola.common.client;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.Jwts;
//
//@Component
//public class KeycloakTokenParser {
//    private static final String TOKEN_PREFIX = "Bearer ";
//    private static final String SESSION_STATE = "session_state";
//
//    @SuppressWarnings("rawtypes")
//    public String getIdentityId(final String keycloakToken) throws JsonProcessingException, IOException {
//        Jwt<Header, Claims> jwt = getJwt(keycloakToken);
//        return jwt.getBody().getSubject();
//    }
//
//    @SuppressWarnings("rawtypes")
//    public String getSessionState(final String keycloakToken) throws JsonProcessingException, IOException {
//       Jwt<Header, Claims> jwt = getJwt(keycloakToken);
//       return jwt.getBody().get(SESSION_STATE).toString();
//    }
//
//    @SuppressWarnings("rawtypes")
//    private Jwt<Header, Claims> getJwt(final String keycloakToken) {
//        String jwt = keycloakToken.replaceFirst(TOKEN_PREFIX, "");
//        String tokenWithoutSignature = getJWSWithoutSignature(jwt);
//        return Jwts.parser().parseClaimsJwt(tokenWithoutSignature);
//    }
//
//    /**
//     * @see <a href=
//     *      "https://github.com/jwtk/jjwt/issues/67#issuecomment-182527735">Ability
//     *      to inspect body of signed JWT</a>
//     */
//    private String getJWSWithoutSignature(final String token) {
//        int i = token.lastIndexOf('.');
//        String withoutSignature = token.substring(0, i + 1);
//        return withoutSignature;
//    }
//
//}
