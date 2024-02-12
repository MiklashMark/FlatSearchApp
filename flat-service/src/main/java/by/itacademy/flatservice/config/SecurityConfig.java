package by.itacademy.flatservice.config;

import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.flatservice.contoller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter filter) throws Exception {
        // Enable CORS and disable CSRF
        httpSecurity = httpSecurity.cors().and().csrf().disable();

        // Set session management to stateless
        httpSecurity = httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        httpSecurity = httpSecurity
                .exceptionHandling()
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
                .requestMatchers(HttpMethod.GET, "/api/v1/flats/**").authenticated()
                .anyRequest().authenticated()
        );


        // Add JWT token filter
        httpSecurity.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return httpSecurity.build();
    }
}