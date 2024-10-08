package com.aravind.SpringSecurity.config;

//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;


// TELUSKO CODE
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//
//        security.csrf(customizer -> customizer.disable());
//        security.authorizeRequests(request -> request.anyRequest().authenticated());
//        // security.formLogin(Customizer.withDefaults());
//        security.httpBasic(Customizer.withDefaults());
//        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
////        Customizer<CsrfConfigurer<HttpSecurity>> csrfConfigurerCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
////            @Override
////            public void customize(CsrfConfigurer<HttpSecurity> customizer) {
////                customizer.disable();
////
////
////            };
//
//
////        security.csrf(csrfConfigurerCustomizer);
//        return security.build();
//    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder()
////                .username("arun")
////                .password("arun")
////                .roles("USER")
////
////                .build();
////
////        return new InMemoryUserDetailsManager(user1);
////    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        return provider;
//    }
//}

// EMBARK-X CODE

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}aravind")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }

}



