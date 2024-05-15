package com.example.ShopEasyAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Turn off CSRF protection for simplicity
                .authorizeRequests() // Start setting up access rules
                .antMatchers("/api/products/**").hasAnyRole("USER", "ADMIN") // Allow both users and admins to access product-related URLs
                .antMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN") // Only admins can add new products
                .antMatchers("/api/orders/**").hasRole("USER") // Only users can access order-related URLs
                .anyRequest().authenticated() // Any other request must be made by a logged-in user
                .and()
                .httpBasic(); // Use basic authentication (simple username and password)
        return http.build(); // Finalize the configuration
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}