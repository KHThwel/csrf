package com.example.csrftokenweb.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.NoOpAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.formLogin(c -> c.defaultSuccessUrl("/main",true));
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();


    }


    @Bean
    public UserDetailsService uds(){
        var uds =new InMemoryUserDetailsManager();
        var u1 = User.withUsername("mary")
                .password("12345")
                .authorities("READ")
                .build();
        uds.createUser(u1);
        return uds;

    }
}
