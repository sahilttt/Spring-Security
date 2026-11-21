package com.securityexample.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**")
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about", "/contact").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/balance", "/admin", "/transfer").authenticated()
                        .requestMatchers("/api/employees", "/api/employees/**").hasRole("ADMIN")
                        .requestMatchers("/api/users", "/api/users/**").authenticated()
                        .requestMatchers("/api/orders", "/api/orders/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }
    @Bean
    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("Admin")
                .roles("ADMIN").password(passwordEncoder.encode("sifra")).build();
        UserDetails user = User.withUsername("Mohammad")
                .roles("USER").password(passwordEncoder.encode("sifra123")).build();
        return new InMemoryUserDetailsManager(user,admin);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

