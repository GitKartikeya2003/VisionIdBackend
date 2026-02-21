package com.example.VisionIdBackend.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(customCsrfRequest -> customCsrfRequest.disable());
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());

        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        // UserDetails user = User.withDefaultPasswordEncoder().username("user").password("1234").roles("USER").build(); ##hardcoded
//
//        return new InMemoryUserDetailsManager();
//
//    }
//
//
//}
