package com.CarHub.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

  private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests().requestMatchers("/api/v1/auth/signUp/*","/api/v1/auth/login").permitAll()
//                .requestMatchers("/api/v1/auth/car/**").hasAnyRole("MANAGER", "ADMIN")
//                .requestMatchers("/api/v1/auth/**").hasRole("ADMIN")
//                .requestMatchers("/api/v1/car/add").hasAnyRole("ADMIN","MANAGER","USER")
//                .anyRequest().authenticated();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        return http.build();
    }



}
