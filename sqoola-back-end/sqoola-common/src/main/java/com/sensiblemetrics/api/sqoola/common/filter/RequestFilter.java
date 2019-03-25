package com.sensiblemetrics.api.sqoola.common.filter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Request filter implementation {@link Filter}
 */
@Component
public class RequestFilter extends GenericFilterBean {
    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String REQUEST_ID_MDC_KEY = "req_id";
    private static final String IDENTITY_ID_MDC_KEY = "identity_id";
    private static final String UNKNOWN_IDENTITY_ID = "Unknown";

//    @Autowired
//    KeycloakTokenParser keycloakTokenParser;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String requestId = req.getHeader(REQUEST_ID_HEADER);
            requestId = (StringUtils.isBlank(requestId)) ? generateRequestId() : requestId;

            String keycloakToken = req.getHeader(AUTHORIZATION_HEADER);
            String identityId = getIdentityId(keycloakToken);

            MDC.put(REQUEST_ID_MDC_KEY, requestId);
            MDC.put(IDENTITY_ID_MDC_KEY, identityId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private String getIdentityId(final String keycloakToken) {
        if (StringUtils.isBlank(keycloakToken)) {
            return UNKNOWN_IDENTITY_ID;
        }
        try {
            //return keycloakTokenParser.getIdentityId(keycloakToken);
        } catch (Exception e) {
        }
        return UNKNOWN_IDENTITY_ID;
    }

    private String generateRequestId() {
        return RandomStringUtils.random(16, true, true).toLowerCase();
    }

}
