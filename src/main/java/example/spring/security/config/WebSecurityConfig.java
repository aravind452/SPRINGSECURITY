package example.spring.security.config;


import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    // In Spring Boot, the Security Filter Chain is a concept in Spring Security that
    // defines a series of filters responsible for handling security concerns,
    // such as authentication, authorization, and other security mechanisms,
    // in the request/response lifecycle.
    // It acts as a chain of filters that intercept HTTP requests and
    // apply security-related logic before the requests reach the application.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                //.csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());


        return httpSecurity.build();

    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userDetails = User.withUsername("aravind")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails userDetails1 = User.withUsername("arun")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails1, userDetails);


    }

}
