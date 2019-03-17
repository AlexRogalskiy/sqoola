/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
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

import java.util.List;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;

//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
//import org.springframework.oxm.xstream.XStreamMarshaller;
/**
 *
 * Application Web MVC Configuration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Configuration("subscriptionWebConfiguration")
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

//    @Override
//    public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {
//        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.indentOutput(true)
//                .dateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"));
//        messageConverters.add(new MappingJackson2HttpMessageConverter(builder.build()));
//        messageConverters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true)
//                .build()));
//
//        messageConverters.add(createXmlHttpMessageConverter());
//        // messageConverters.add(new MappingJackson2HttpMessageConverter());
//
//        messageConverters.add(new ProtobufHttpMessageConverter());
//        messageConverters.add(new KryoHttpMessageConverter());
//        messageConverters.add(new StringHttpMessageConverter());
//        super.configureMessageConverters(messageConverters);
//    }
//
//    private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
//        final MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
//
//        final XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
//        xmlConverter.setMarshaller(xstreamMarshaller);
//        xmlConverter.setUnmarshaller(xstreamMarshaller);
//
//        return xmlConverter;
////    }
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("application-local.properties"), new ClassPathResource("application-prod.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    /**
     * Get internationalization of messages
     *
     * @return internalization message source
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/templates/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }
    
    @Bean
    public View jsonTemplate() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }
    
    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/").setCachePeriod(31556926);
        }
        if (!registry.hasMappingForPattern("/css/**")) {
            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(31556926);
        }
        if (!registry.hasMappingForPattern("/js/**")) {
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(31556926);
        }
        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/").setCachePeriod(31556926);
        }
        if (!registry.hasMappingForPattern("/fonts/**")) {
            registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(31556926);
        }
        if (!registry.hasMappingForPattern("/pdf/**")) {
            registry.addResourceHandler("/pdf/**").addResourceLocations("classpath:/static/pdf/").setCachePeriod(31556926);
        }
        /*registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/font-awesome/**").addResourceLocations("classpath:/static/font-awesome/").setCachePeriod(31556926);
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/pdf/**").addResourceLocations("classpath:/static/pdf/").setCachePeriod(31556926);
        //set Upload Path
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + env.getProperty("upload.path") + File.separatorChar);*/
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/api/login").setViewName("login");
//        registry.addViewController("/api/logout").setViewName("logout");
//        registry.addViewController("/api/login/update").setViewName("update");
//        registry.addViewController("/api/admin").setViewName("admin");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
////        registry.addViewController("/subscriptionapp/home.html").setViewName("subscriptionapp/");
////        registry.addViewController("/subscriptionapp/info/subscriptionapp.html").setViewName("subscriptionapp/info");
//    }
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, 20));
        resolver.setPageParameterName("page.page");
        resolver.setSizeParameterName("page.size");
        argumentResolvers.add(resolver);
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

//    @Bean
//    public GlobalException handlerExceptionResolver() {
//        GlobalException handler = new GlobalException();
//        return handler;
//    }
//    @Override
//    public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {
//        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//    }
//
//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new CharacterEncodingFilter());
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new CharacterEncodingFilter());
//        return filterRegistrationBean;
//    }
//
//    private static class CharacterEncodingFilter implements Filter {
//
//        protected String encoding;
//
//        public void init(FilterConfig filterConfig) throws ServletException {
//            encoding = "UTF-8";
//        }
//
//        @Override
//        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            request.setCharacterEncoding(encoding);
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//        public void destroy() {
//            encoding = null;
//        }
//    }
//
//    @Bean
//    public TomcatEmbeddedServletContainerFactory tomcatFactory() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
//                tomcat.enableNaming();
//                return super.getTomcatEmbeddedServletContainer(tomcat);
//            }
//        };
//            tomcat.addConnectorCustomizers ( 
//                (TomcatConnectorCustomizer) connector -> {
//                    if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
//                    // -1 means unlimited, accept bytes
//                    ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
//                }
//            }
//            );
//        return tomcat;
//    }
//
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//        return (ConfigurableEmbeddedServletContainer container) -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                container.setPort(8888);
//                container.setContextPath("/mkyong");
//    protocol  = "HTTP/1.1"
//    connectionTimeout  = "20000"
//    redirectPort  = "8443"
//                TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = (TomcatEmbeddedServletContainerFactory) container;
//                tomcatEmbeddedServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {
//                            @Override
//                            public void customize(Context context) {
//                                StandardManager m = new StandardManager();
//                                m.setPathname(env.getProperty("session.persistence.path"));
//                                context.setManager(m);
//                                
//                            }
//                        });
//            }
//        };
//    }
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("5MB");
//        factory.setMaxRequestSize("5MB");
//        return factory.createMultipartConfig();
//    }
}
