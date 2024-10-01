package com.presente.confeitaria.configuration;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebConfig {


    @Deprecated
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable)
                .cors().and().authorizeHttpRequests(authorize ->
                        authorize.
                        requestMatchers(HttpMethod.POST, "/register").permitAll().
                                anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
        return security.build();

    }
           @Bean
            public Filter jwtAuthorizationFilter() {
                return jwtAuthorizationFilter();
            }


}

