package com.soulsarch.PasswordManager.config;

import com.soulsarch.PasswordManager.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() /*временное отключение защиты*/
            .authorizeRequests()
            .antMatchers("/", "/api/init").permitAll()
            .antMatchers(HttpMethod.GET,"/").hasAnyRole(Role.USER.toString(), Role.MODERATOR.toString())
            .antMatchers(HttpMethod.GET,"/api/post/search*").hasAnyRole(Role.MODERATOR.toString())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .httpBasic();

    }

    @Bean
    protected UserDetailsService userDetailsService() {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles(Role.USER.toString())
                        .build(),
                User.builder()
                        .username("moderator")
                        .password(passwordEncoder().encode("moderator"))
                        .roles(Role.MODERATOR.toString())
                        .build()
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}
