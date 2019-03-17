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
package com.ubs.network.api.gateway.core.configuration;

import com.google.common.base.Preconditions;
import com.wildbeeslabs.api.rest.common.utils.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * HTTP Client REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Service("httpClientService")
@Transactional
public class HttpClientServiceImpl implements IHttpClientService {

    private static final class ResponseUtils {

        private ResponseUtils() {
        }

        public static void closeResponse(final CloseableHttpResponse response) throws IOException {
            if (Objects.isNull(response)) {
                return;
            }
            try {
                final HttpEntity entity = response.getEntity();
                if (Objects.nonNull(entity)) {
//                    InputStream instream = entities.getContent();
//                    instream.close();
                    entity.getContent().close();
                }
            } finally {
                response.close();
            }
        }

        public static void closeClient(final CloseableHttpClient client) throws IOException {
            if (Objects.isNull(client)) {
                return;
            }
            client.close();
        }
    }

    @Autowired
    private Logger loggerMapper;
    @Autowired
    private ResourceUtils resourceUtils;

    /**
     * Default connection request timeout (milliseconds)
     */
    private static final int DEFAULT_REQUEST_TIMEOUT = 1000;
    /**
     * DEfault connection timeout (milliseconds)
     */
    private static final int DEFAULT_CONNECTION_TIMEOUT = 1000;
    /**
     * Default socket timeout (milliseconds)
     */
    private static final int DEFAULT_SOCKET_TIMEOUT = 1000;
    /**
     * Default redirect HTTP methods
     */
    private static final String[] DEFAULT_REDIRECT_HTTP_METHODS = new String[]{HttpGet.METHOD_NAME, HttpPost.METHOD_NAME, HttpHead.METHOD_NAME};

    @SuppressWarnings("null")
    public HttpResponse sendRequest(final HttpUriRequest request) throws IOException {
        CloseableHttpClient client = getHttpClient();
        return this.sendRequest(client, request, null);
    }

    @SuppressWarnings("null")
    public HttpResponse sendRequest(final HttpUriRequest request, final HttpContext context) throws IOException {
        CloseableHttpClient client = getHttpClient();
        return this.sendRequest(client, request, context);
    }

    @SuppressWarnings("null")
    public HttpResponse sendRequest(@NotNull final CloseableHttpClient client, @NotNull final HttpUriRequest request, @Nullable final HttpContext context) throws IOException {
        CloseableHttpResponse response = Objects.isNull(context) ? client.execute(request) : client.execute(request, context);
        try {
            checkResponseStatusCode(response);
        } catch (HttpException ex) {
            getLogger().error("ERROR: HTTP remote service exception, request={}, headers={}, message={}", request.getRequestLine(), StringUtils.join(request.getAllHeaders(), ", "), ex.getMessage());
        } finally {
            ResponseUtils.closeResponse(response);
            ResponseUtils.closeClient(client);
        }
        return response;
        /*CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        final HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);*/
    }

    @SuppressWarnings("null")
    public HttpResponse sendRequestWithCookies(final HttpUriRequest request, @Nullable final List<? extends BasicClientCookie> cookieList) throws IOException {
        BasicCookieStore cookieStore = new BasicCookieStore();
        if (Objects.nonNull(cookieList)) {
            cookieList.forEach((cookie) -> {
                cookieStore.addCookie(cookie);
            });
        }
//        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
//        cookie.setDomain(".github.com");
//        cookie.setPath("/");
//request.addHeader(HttpHeaders.ACCEPT, "application/xml");
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return this.sendRequest(request, localContext);
    }

    @SuppressWarnings("null")
    public HttpUriRequest configureHttpGetRequest(final String url) {
        final RequestBuilder getRequst = RequestBuilder.get().setUri(url);
        return getRequst.build();
    }

    @SuppressWarnings("null")
    public HttpUriRequest configureHttpPostRequest(final String url, @Nullable final Map<String, String> paramsMap) {
        final RequestBuilder postRequst = RequestBuilder.post().setUri(url);
        if (Objects.nonNull(paramsMap)) {
            List<NameValuePair> params = new ArrayList<>();
            paramsMap.entrySet().forEach((entry) -> {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            });
            postRequst.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        return postRequst.build();
    }

    @SuppressWarnings("null")
    public void configureHttpHeaders(final HttpUriRequest request, @Nullable final List<? extends Header> headerList) {
        if (Objects.nonNull(headerList)) {
            headerList.stream().filter(Objects::nonNull).forEach((header) -> {
                request.addHeader(header);
            });
        }
        /*String auth = DEFAULT_USER + ":" + DEFAULT_PASS;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);*/
    }

    public CloseableHttpClient configureHttpClient(@Nullable final RequestConfig requestConfig, @Nullable final List<? extends Header> headerList, @Nullable final RedirectStrategy redirectStrategy, @Nullable final CredentialsProvider credentialsProvider, @Nullable final SSLContext sslContext, @Nullable final HttpRoutePlanner proxyRoutePlanner) {
        HttpClientBuilder client = HttpClientBuilder.create();
        if (Objects.nonNull(requestConfig)) {
            client.setDefaultRequestConfig(requestConfig);
        } else {
            client.setDefaultRequestConfig(getDefaultRequestConfig());
        }
        if (Objects.nonNull(sslContext)) {
            client.setSSLContext(sslContext);
            client.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }
        if (Objects.nonNull(headerList)) {
            client.setDefaultHeaders(headerList);
        }
        if (Objects.nonNull(proxyRoutePlanner)) {
            client.setRoutePlanner(proxyRoutePlanner);
        }
        if (Objects.nonNull(credentialsProvider)) {
            client.setDefaultCredentialsProvider(credentialsProvider);
        }
        if (Objects.nonNull(redirectStrategy)) {
            client.setRedirectStrategy(redirectStrategy);
        } else {
            client.disableRedirectHandling();
        }
        return client.build();
    }

    public String getResponseMimeType(final HttpResponse response) {
        return ContentType.getOrDefault(response.getEntity()).getMimeType();
    }

    public Header[] getResponseHeadersByName(final HttpResponse response, final String headerName) {
        return response.getHeaders(headerName);
    }

    public String getResponseBody(final CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    private void checkResponseStatusCode(final CloseableHttpResponse response) throws HttpException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (!Objects.equals(HttpStatus.SC_OK, statusCode)) {
            String message = getResource().formatMessage("error.http.incorrect.response.status", statusCode, HttpStatus.SC_OK);
            getLogger().error(message);
            throw new HttpException(message);
        }
    }

    public CloseableHttpClient getHttpsClient() {
        return configureHttpClient(getDefaultRequestConfig(), null, null, null, getDefaultSslContext(), null);
    }

    public CloseableHttpClient getHttpProxyClient(final String host, final int port) {
        return configureHttpClient(getDefaultRequestConfig(), null, null, null, null, getProxyRoutePlanner(new HttpHost(host, port)));
    }

    public CloseableHttpClient getHttpClient() {
        return configureHttpClient(getDefaultRequestConfig(), null, null, null, null, null);
    }

    public CloseableHttpClient getHttpClientWithDefaultRedirect() {
        return configureHttpClient(getDefaultRequestConfig(), null, getDefaultRedirectStrategy(), null, null, null);
    }

    public CloseableHttpClient getHttpClientWithRedirect() {
        return configureHttpClient(getDefaultRequestConfig(), null, getLaxRedirectStrategy(), null, null, null);
    }

    public CloseableHttpClient getHttpClientWithBasicAuth(final String username, final String password, @Nullable final AuthScope authScope) {
        return configureHttpClient(getDefaultRequestConfig(), null, null, getBasicAuthProvider(username, password, authScope), null, null);
    }

    private RequestConfig getDefaultRequestConfig() {
        return this.getRequestConfig(DEFAULT_REQUEST_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    private RequestConfig getRequestConfig(int requestTimeout, int connectionTimeout, int socketTimeout) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(requestTimeout).setConnectTimeout(connectionTimeout).setSocketTimeout(socketTimeout).build();
        return requestConfig;
    }

    private RedirectStrategy getLaxRedirectStrategy() {
        final RedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        return redirectStrategy;
    }

    private RedirectStrategy getDefaultRedirectStrategy(@Nullable final String... redirectMethods) {

        final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() {

            private final String[] defaultRedirectMethods = Objects.nonNull(redirectMethods) ? redirectMethods : DEFAULT_REDIRECT_HTTP_METHODS;

            @Override
            protected boolean isRedirectable(final String method) {
                return Arrays.stream(defaultRedirectMethods).anyMatch(m -> m.equalsIgnoreCase(method));
            }
        };
        return redirectStrategy;
    }

    private SSLContext getDefaultSslContext() {
        try {
            return this.getSslContext(null, null);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | CertificateException | IOException ex) {
            getLogger().error("ERROR: cannot set valid ssl context with file={}, message={}", null, ex.getMessage());
        }
        return null;
    }

    private SSLContext getSslContext(final File file, final char[] password) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(file, password, (certificate, authType) -> true).build();
        return sslContext;
    }

    private HttpRoutePlanner getProxyRoutePlanner(final HttpHost httpHost) {
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(httpHost);
        return routePlanner;
    }

    private CredentialsProvider getBasicAuthProvider(final String username, final String password, @Nullable final AuthScope authScope) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        provider.setCredentials(Objects.isNull(authScope) ? AuthScope.ANY : authScope, credentials);
        return provider;
    }

    public String expandSingleLevel(final String url) throws IOException {
        HttpHead request = new HttpHead(url);
        try {
            CloseableHttpClient client = getHttpClient();
            HttpResponse httpResponse = client.execute(request);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 301 && statusCode != 302) {
                return url;
            }
            Header[] headers = getResponseHeadersByName(httpResponse, HttpHeaders.LOCATION);
            Preconditions.checkState(headers.length == 1);
            return headers[0].getValue();
        } catch (IllegalArgumentException ex) {
            getLogger().error("ERROR: incorrect single level url={}, message={}", url, ex.getMessage());
        } finally {
            request.releaseConnection();
        }
        return null;
    }

//    public String expandSafe(final String urlArg) throws IOException {
//        String originalUrl = urlArg;
//        String newUrl = expandSingleLevelSafe(originalUrl).getRight();
//        List<String> alreadyVisited = Lists.newArrayList(originalUrl, newUrl);
//        while (!Objects.equals(originalUrl, newUrl)) {
//            originalUrl = newUrl;
//            Pair<Integer, String> statusAndUrl = expandSingleLevelSafe(originalUrl);
//            newUrl = statusAndUrl.getRight();
//            boolean isRedirect = statusAndUrl.getLeft() == 301 || statusAndUrl.getLeft() == 302;
//            if (isRedirect && alreadyVisited.contains(newUrl)) {
//                getLogger().error("ERROR: redirecting in loop, url={}", newUrl);
//                throw new IllegalStateException("Likely a redirect loop");
//            }
//            alreadyVisited.add(newUrl);
//        }
//        return newUrl;
//    }
    protected ResourceUtils getResource() {
        return this.resourceUtils;
    }

    protected Logger getLogger() {
        return loggerMapper;
    }
}
/*
HttpPost post = new HttpPost("http://echo.200please.com");
File file = new File(textFileName);
String message = "This is a multipart post";
MultipartEntityBuilder builder = MultipartEntityBuilder.create();
builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
builder.addBinaryBody("upfile", file, ContentType.DEFAULT_BINARY, textFileName);
builder.addTextBody("text", message, ContentType.DEFAULT_BINARY);
HttpEntity entities = builder.build();
post.setEntity(entities);
HttpResponse response = client.execute(post);


HttpPost post = new HttpPost("http://echo.200please.com");
InputStream inputStream = new FileInputStream(zipFileName);
File file = new File(imageFileName);
String message = "This is a multipart post";
MultipartEntityBuilder builder = MultipartEntityBuilder.create();         
builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
builder.addBinaryBody
  ("upfile", file, ContentType.DEFAULT_BINARY, imageFileName);
builder.addBinaryBody
  ("upstream", inputStream, ContentType.create("application/zip"), zipFileName);
builder.addTextBody("text", message, ContentType.TEXT_PLAIN);
HttpEntity entities = builder.build();
post.setEntity(entities);
HttpResponse response = client.execute(post);

HttpPost post = new HttpPost("http://echo.200please.com");
String message = "This is a multipart post";
byte[] bytes = "binary code".getBytes(); 
MultipartEntityBuilder builder = MultipartEntityBuilder.create();
builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
builder.addBinaryBody("upfile", bytes, ContentType.DEFAULT_BINARY, textFileName);
builder.addTextBody("text", message, ContentType.TEXT_PLAIN);
HttpEntity entities = builder.build();
post.setEntity(entities);
HttpResponse response = client.execute(post);
 */

 /*
request.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 Firefox/26.0");
httpPost.setHeader("Content-Type", "application/xml");
StringEntity xmlEntity = new StringEntity(xmlBody);
httpPost.setEntity(xmlEntity);
 */

 /*
HttpHost proxy = new HttpHost("localhost", 8090);
DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

//Client credentials
CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
credentialsProvider.setCredentials(new AuthScope(proxy), 
  new UsernamePasswordCredentials("username_admin", "secret_password"));
 
// Create AuthCache instance
AuthCache authCache = new BasicAuthCache();
 
BasicScheme basicAuth = new BasicScheme();
authCache.put(proxy, basicAuth);
HttpClientContext context = HttpClientContext.create();
context.setCredentialsProvider(credentialsProvider);
context.setAuthCache(authCache);
 
HttpClient httpclient = HttpClients.custom()
  .setRoutePlanner(routePlanner)
  .setDefaultCredentialsProvider(credentialsProvider)
  .build();

HttpGet httpGet = new HttpGet("http://localhost:8089/private");
HttpResponse response = httpclient.execute(httpGet, context);
 */
