package bdbt_bada_project.SpringApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/index", "/css/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers("/resources/static/**").permitAll()
                        .requestMatchers("/main").authenticated()
                        .requestMatchers("/main_admin").hasRole("ADMIN")
                        .requestMatchers("/main_user").hasRole("USER")
                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/main")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/index")
                        .permitAll());


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("jerzy@gmail.com")
                .password("userPass")
                .roles("USER")
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminPass")
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }
}