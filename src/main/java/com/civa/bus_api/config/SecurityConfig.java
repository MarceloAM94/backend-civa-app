package com.civa.bus_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // desactivar CSRF con method reference
                .csrf(AbstractHttpConfigurer::disable)

                .cors(Customizer.withDefaults())

                // todos los endpoints requieren autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                // habilitar Basic Auth con la sintaxis moderna
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}