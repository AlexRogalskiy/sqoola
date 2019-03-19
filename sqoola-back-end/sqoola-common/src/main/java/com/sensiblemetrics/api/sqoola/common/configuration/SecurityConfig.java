/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common.configuration;

import com.sensiblemetrics.api.sqoola.common.security.SecurityAccessDeniedHandler;
import com.sensiblemetrics.api.sqoola.common.security.SecurityAuditorAwareHandler;
import com.sensiblemetrics.api.sqoola.common.security.SecurityAuthenticationEntryPoint;
import com.sensiblemetrics.api.sqoola.common.security.SecurityAuthenticationSuccessHandler;
import com.sensiblemetrics.api.sqoola.common.service.AuthUserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Web security configuration {@link WebSecurityConfigurerAdapter}
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
//@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@ImportResource({ "classpath:spring-security.config.xml" })
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserDaoService authUserService;

    @Autowired
    @Qualifier("persistentTokenRepository")
    private PersistentTokenRepository persistentTokenRepository;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
            User.withUsername("user")
                .password("user123")
                .roles("USER")
                .authorities("PERM_READ", "PERM_WRITE")
                .build()
        );
        userDetailsManager.createUser(
            User.withUsername("manager")
                .password("manager123")
                .roles("MANAGER")
                .authorities("PERM_READ", "PERM_WRITE", "PERM_MANAGE")
                .build()
        );
        return userDetailsManager;
    }

    @Autowired
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //.anyRequest().authenticated()
        //.usernameParameter("ssid").passwordParameter("password")
        //.and().exceptionHandling().accessDeniedPage("/denied")
        //                .antMatchers("/api/**").authenticated()
        //                .and().formLogin().loginPage("/api/login")
        //                .successHandler(authenticationSuccessHandler)
        //                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        //.and().logout();

        http.httpBasic().realmName("REST API").and()
            .exceptionHandling().and()
            //.accessDeniedHandler(authenticationAccessDeniedHandler())
            //.authenticationEntryPoint(restAuthenticationEntryPoint)
            .authorizeRequests()
            .antMatchers("/v1/**", "/v2/**", "/swagger-ui/**", "/api-docs/**").permitAll()
            .antMatchers("/api/**").hasAnyRole("USER", "MANAGER").and()
//            .antMatchers(HttpMethod.GET, "/api/order").hasRole("USER")
//            .antMatchers(HttpMethod.POST, "/api/order").hasRole("USER")
//            .antMatchers(HttpMethod.PUT, "/api/order").hasRole("USER")
//            .antMatchers(HttpMethod.DELETE, "/api/order").hasRole("MANAGER")
//            .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("MANAGER").and()
            .headers()
            .cacheControl()
            .disable()
            .and()
            .csrf()
            .disable();

//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
//            .and().httpBasic().realmName("MY APP REALM")
//            .authenticationEntryPoint(authenticationEntryPoint());

//        http.authorizeRequests()
//            .antMatchers("/app/secure/**").hasAnyRole("ADMIN","USER")
//            .and().formLogin()  //login configuration
//            .loginPage("/app/login")
//            .loginProcessingUrl("/app-login")
//            .usernameParameter("app_username")
//            .passwordParameter("app_password")
//            .defaultSuccessUrl("/app/secure/article-details")
//            .and().logout()    //logout configuration
//            .logoutUrl("/app-logout")
//            .logoutSuccessUrl("/app/login")
//            .and().exceptionHandling() //exception handling configuration
//            .accessDeniedPage("/app/error");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
//            .and()
//            .authorizeRequests().antMatchers("/login**").permitAll()
//            .and()
//            .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
//            .and()
//            .logout().logoutSuccessUrl("/login").permitAll()
//            .and()
//            .rememberMe().rememberMeParameter("remember-me")
//            .tokenRepository(persistentTokenRepository).userDetailsService(authUserService)
//            .and()
//            .csrf().disable();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder());
    }

    //    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(dataSource);
//        return db;
////        CassandraTokenRepository db = new CassandraTokenRepository(persistanceTokenDao);
////        return db;
//    }

    //    @Bean
//    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
//        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
//        auth.setTargetUrlParameter("targetUrl");
//        return auth;
//    }
//
//    @Bean
//    public CookieCsrfTokenRepository csrfTokenRepository() {
//        return new CookieCsrfTokenRepository();
//    }
//
////    @Bean
////    public AbstractRememberMeServices rememberMeServices() {
////        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices("token", authService, persistentTokenRepository());
////        rememberMeServices.setCookieName("remember-me-token");
////        rememberMeServices.setAlwaysRemember(true);
////        rememberMeServices.setTokenValiditySeconds(1209600);
////        return rememberMeServices;
////    }
//    private RequestMatcher createCSRFMatcher() {
//        return new RequestMatcher() {
//            private final Pattern allowedMethods = Pattern.compile("^GET$");
//
//            private final AntPathRequestMatcher[] requestMatchers = {new AntPathRequestMatcher("/script/**"), new AntPathRequestMatcher("/page/external/client/**"), new AntPathRequestMatcher("/page/token")};
//
//            @Override
//            public boolean matches(HttpServletRequest request) {
//                if (allowedMethods.matcher(request.getMethod()).matches()) {
//                    return false;
//                }
//                for (AntPathRequestMatcher rm : requestMatchers) {
//                    if (rm.matches(request)) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//        };
//    }

    @Bean
    public AuditorAware<UserDetails> auditorAware() {
        return new SecurityAuditorAwareHandler();
    }

    @Bean
    public SecurityAccessDeniedHandler authenticationAccessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    @Bean
    public SecurityAuthenticationEntryPoint authenticationEntryPoint() {
        return new SecurityAuthenticationEntryPoint();
    }

    @Bean
    public SecurityAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SecurityAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
