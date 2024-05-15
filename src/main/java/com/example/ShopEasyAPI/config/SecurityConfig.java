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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection for simplicity
        http.csrf(csrf -> csrf.disable())
                // Set up access rules
                .authorizeHttpRequests(authorize -> authorize
                        // Allow both users and admins to access product-related URLs
                        .requestMatchers("/api/products/**").hasAnyRole("USER", "ADMIN")
                        // Only admins can add new products
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                        // Only users can access order-related URLs
                        .requestMatchers("/api/orders/**").hasRole("USER")
                        // Any other request must be made by a logged-in user
                        .anyRequest().authenticated()
                )
                // Use basic authentication (simple username and password)
                .httpBasic(withDefaults());
        // Finalize the configuration
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Define a user with the role "USER"
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        // Define an admin with the role "ADMIN"
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        // Store the users in memory
        return new InMemoryUserDetailsManager(user, admin);
    }
}
