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

import com.wildbeeslabs.api.rest.common.handler.BaseAccessDeniedHandler;
import com.wildbeeslabs.api.rest.common.handler.BaseAuthenticationSuccessHandler;
import com.wildbeeslabs.api.rest.common.security.BaseAuthenticationEntryPoint;
import com.wildbeeslabs.api.rest.common.filter.AutoLoginFilter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *
 * Application Web Security Configuration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Configuration("subscriptionSecurityConfiguration")
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@ImportResource({ "classpath:spring-security.config.xml" })
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private IAuthenticationService authService;
    @Autowired
    private AutoLoginFilter autoLoginFilter;

    @Autowired
    public void configAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("user123").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("admin123").roles("ADMIN").build());
        manager.createUser(User.withUsername("dba").password("dba123").roles("ADMIN", "DBA").build());
        manager.createUser(User.withUsername("epadmin").password("epadmin123").roles("EPADMIN").build());
        return manager;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user123").roles("USER")
                .and()
                .withUser("admin").password("admin123").roles("ADMIN")
                .and()
                .withUser("dba").password("dba123").roles("ADMIN", "DBA")
                .and()
                .withUser("epadmin").password("epadmin123").roles("EPADMIN");
    }

    @Override
    public void configure(final WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/acceptCookies")
                .antMatchers("/assets/**")
                .antMatchers("/static/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/webjars/**")
                .antMatchers("/wro/**")
                .antMatchers("**/frag/**")
                .antMatchers("**/fragment/**")
                .antMatchers("**/script/beginner/**");
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        RequestMatcher matcher = new RequestHeaderRequestMatcher("X-Requested-With");

        httpSecurity.addFilter(autoLoginFilter)
                .addFilterBefore(new RememberMeAuthenticationFilter(autoLoginFilter.getAuthenticationManager(), rememberMeService()), AutoLoginFilter.class)
                .addFilterBefore(new CharacterEncodingFilter(StandardCharsets.UTF_8.displayName(), Boolean.TRUE), CsrfFilter.class);
        //                .addFilterBefore(new TransactionIdRequestFilter(), AutoLoginFilter.class)
        //                .exceptionHandling().defaultAuthenticationEntryPointFor(new Http401TimeoutEntryPoint(), matcher)

        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().csrfTokenRepository(csrfTokenRepository()).requireCsrfProtectionMatcher(createCSRFMatcher()).and();

        httpSecurity.authorizeRequests()
                //.httpBasic().realmName("REST API")
                //                .exceptionHandling()
                //                .accessDeniedHandler(accessDeniedHandler)
                //                .authenticationEntryPoint(authEntryPoint())
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "DB")
                .antMatchers("/api/data/**").hasAnyRole("USER")
                .antMatchers("/api/login**", "/api/login/update**", "/api/auth/**").permitAll()
                .antMatchers("/api/data/**").authenticated()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .defaultAuthenticationEntryPointFor(authEntryPoint(), matcher)
                .accessDeniedHandler(accessDeniedHandler())
                .accessDeniedPage("/denied")
                .and().formLogin()
                .successHandler(savedRequestAwareAuthenticationSuccessHandler())//authSuccessHandler
                .failureHandler(authFailureHandler())
                .loginPage("/api/login")
                .failureUrl("/api/login?error")
                .loginProcessingUrl("/api/auth/login_check")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/login?logout");
        httpSecurity.headers().cacheControl().disable();
        //.and().headers().frameOptions().disable()
        httpSecurity.rememberMe().rememberMeServices(rememberMeService()).key("token");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
//        CassandraTokenRepository db = new CassandraTokenRepository(persistanceTokenDao);
//        return db;
    }

    @Bean
    public BaseAuthenticationSuccessHandler authSuccessHandler() {
        return new BaseAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }

    @Bean
    public CookieCsrfTokenRepository csrfTokenRepository() {
        return new CookieCsrfTokenRepository();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new BaseAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authEntryPoint() {
        return new BaseAuthenticationEntryPoint();
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);//BCryptPasswordEncoder(31);
    }

    @Bean
    public AbstractRememberMeServices rememberMeService() {
        PersistentTokenBasedRememberMeServices rememberMeService = new PersistentTokenBasedRememberMeServices("token", authService, persistentTokenRepository());
        rememberMeService.setCookieName("rm-token");
        rememberMeService.setAlwaysRemember(Boolean.TRUE);
        rememberMeService.setSeriesLength(64);
        rememberMeService.setTokenLength(64);
        //rememberMeService.setUseSecureCookie(Boolean.TRUE);
        rememberMeService.setTokenValiditySeconds(1209600);
        return rememberMeService;
    }

    private RequestMatcher createCSRFMatcher() {
        return new RequestMatcher() {
            private final Pattern allowedMethods = Pattern.compile("^GET$");

            private final AntPathRequestMatcher[] requestMatchers = {new AntPathRequestMatcher("/admin/**"), new AntPathRequestMatcher("/api/**")};

            @Override
            public boolean matches(HttpServletRequest request) {
                if (allowedMethods.matcher(request.getMethod()).matches()) {
                    return false;
                }
                for (AntPathRequestMatcher rm : requestMatchers) {
                    if (rm.matches(request)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }
}
