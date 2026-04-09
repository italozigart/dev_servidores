package br.com.fatec.catalogo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/produtos").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/produtos/novo", "/produtos/editar/**", "/produtos/deletar/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/produtos", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/produtos")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = org.springframework.security.core.userdetails.User.builder()
                .username("admin")
                .password("{noop}123456")
                .roles("ADMIN")
                .build();

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username("aluno")
                .password("{noop}123456")
                .roles("USER")
                .build();

        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user, admin);
    }
}