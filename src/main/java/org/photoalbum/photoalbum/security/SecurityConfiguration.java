package org.photoalbum.photoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
        @Bean
        PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        @Bean
        UserDetailsService userDetailsService(){
            return new DatabaseUserDetailsService();
        }

        @Bean
        DaoAuthenticationProvider daoAuthenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userDetailsService());
            return daoAuthenticationProvider;
        }
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests()
                    .requestMatchers("/api/**", "/files/**").permitAll()
                    .requestMatchers("/**").hasAnyAuthority("ADMIN")
                            .and().logout()
                            .and().formLogin();
            http.csrf().disable();
            return http.build();
        }
}
