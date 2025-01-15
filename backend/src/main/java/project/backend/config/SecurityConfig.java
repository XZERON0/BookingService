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

@Configuration
@EnableWebSecurity
public class SecurityConfig {
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
                .requestMatchers("user/current").authenticated()
                .anyRequest().anonymous()   
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
    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
    // @Bean
    // public UserDetailsService users()
    // {
    //     UserDetails admin = User.builder()
    //     .username("username").password("password").roles("ADMIN").build();
        
    //     UserDetails user = User.builder().username("username").password("password").roles("USER").build();
    //     return new InMemoryUserDetailsManager(admin, user);
    // }
}
