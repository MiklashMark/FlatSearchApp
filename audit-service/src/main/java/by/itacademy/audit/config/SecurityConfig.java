package by.itacademy.audit.config;

import by.itacademy.audit.controller.filter.JwtFilter;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter filter) throws Exception  {
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
                .requestMatchers(HttpMethod.GET, "/api/v1/audit/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/audit/{uuid}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/audit/**").hasRole("SYSTEM")
                .requestMatchers(HttpMethod.POST, "/api/v1/report/{type}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/report").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/report/{uuid}/export").hasRole("ADMIN")
                .requestMatchers(HttpMethod.HEAD, "/api/v1/report/{uuid}/export").hasRole("ADMIN")
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