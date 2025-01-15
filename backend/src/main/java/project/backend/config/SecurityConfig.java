package project.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(auth -> auth
                // .requestMatchers("/service/**", "/user/register").permitAll()
                // .requestMatchers("user/current").authenticated()
                .anyRequest().permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //         .withUser("alter")
    //         .password("alter123")
    //         .roles("USER");
    // }
    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
