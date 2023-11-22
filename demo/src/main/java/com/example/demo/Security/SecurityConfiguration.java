package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /*
     * @description
     * Aplicar Filtros Para Las Rutas
     * Privadas Y Publicas
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // ? CSRF => CROSS SITE REQUEST FORGERY
                .authorizeHttpRequests(authRequests -> authRequests // ? USAR FORMA LAMBDA
                        .requestMatchers("/auth/**").permitAll() // ! RUTAS PUBLICAS
                        .anyRequest().authenticated()) // ! RUTAS PRIVADAS
                .formLogin(withDefaults()) // ! GENERAR UNA PAGINA DE LOGIN POR DEFAULT SI NO ESTA AUTENTICADO
                .build();
    }
}
