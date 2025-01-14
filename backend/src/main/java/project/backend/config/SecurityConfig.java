package project.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static PasswordEncoder instance;

    // Использование Singleton для PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (instance == null) {
            instance = new BCryptPasswordEncoder();
        }
        return instance;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/service/**", "/user/register").permitAll()
                .requestMatchers("user/current").authenticated()
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("alter")
            .password("alter123")
            .roles("USER");
    }
}
