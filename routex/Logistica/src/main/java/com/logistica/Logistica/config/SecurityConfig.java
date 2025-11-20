// src/main/java/com/logistica/Logistica/config/SecurityConfig.java
package com.logistica.Logistica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define um codificador de senha seguro (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Define usuários em memória para teste
    @Bean
    public UserDetailsService userDetailsService() {
        // Usuário de exemplo: login=usuario, senha=senha123
        UserDetails user = User.builder()
            .username("usuario")
            .password(passwordEncoder().encode("senha123")) // Senha codificada
            .roles("USER")
            .build();
        
        // Usuário admin de exemplo
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123")) // Senha codificada
            .roles("ADMIN", "USER")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // 3. Configura a cadeia de filtros de segurança (Regras de acesso)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Define as regras de autorização
            .authorizeHttpRequests(authorize -> authorize
                // PERMITE acesso à página de login (/login) e recursos estáticos SEM AUTENTICAÇÃO.
                // Esta permissão explícita resolve o loop de redirecionamento.
                .requestMatchers("/css/**", "/js/**", "/images/**", "/login").permitAll()
                
                // Qualquer outra requisição deve ser autenticada
                .anyRequest().authenticated() 
            )
            
            // Configura o formulário de login
            .formLogin(form -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/destinatarios", true) 
                .permitAll() // <<< Essencial: Permite que todos vejam o formulário.
            )
            
            // Configura o logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
            
        return http.build();
    }
}