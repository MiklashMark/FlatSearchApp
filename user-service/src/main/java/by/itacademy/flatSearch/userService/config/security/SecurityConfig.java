package by.itacademy.flatSearch.userService.config.security;

import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.controller.filters.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter filter) throws Exception {
        httpSecurity = httpSecurity.cors().and().csrf().disable();

        // Set session management to stateless
        httpSecurity = httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        httpSecurity = httpSecurity.exceptionHandling()
                .authenticationEntryPoint((request, response, ex) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(ErrorMessages.AUTHENTICATION_ERROR.getMessage());
                })
                .accessDeniedHandler((request, response, ex) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(ErrorMessages.ACCESS_ERROR.getMessage());
                })
                .and();

        httpSecurity.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users/registration").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/verification").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/me").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/users").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/{uuid}").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/{uuid}/dt_update/{dt_update}").hasAnyRole("ADMIN")
        );


        // Add JWT token filter
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

