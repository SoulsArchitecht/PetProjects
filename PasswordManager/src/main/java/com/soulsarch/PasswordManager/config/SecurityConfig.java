package com.soulsarch.PasswordManager.config;

import com.soulsarch.PasswordManager.model.Permission;
import com.soulsarch.PasswordManager.model.Role;
import com.soulsarch.PasswordManager.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    /*    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() /*временное отключение защиты*/
            .authorizeRequests()
            //.antMatchers("/auth/login").permitAll()
            .antMatchers("/").permitAll()
            .anyRequest()
            .authenticated()
            //.antMatchers(HttpMethod.GET, "/**").hasAuthority(Permission.URLINFORMATION_WRITE.getPermission())
            //for h2 db access via localhost
            //.and()
            //.authorizeRequests().antMatchers("/h2-console/**").permitAll()
            //.anyRequest()
            //.authenticated()

            //for h2 db access via localhost
            //.and()
            //.headers().frameOptions().disable()
            //.and()
            //.csrf().ignoringAntMatchers("/h2-console/**")
            //.and()
            //.cors().disable()
            .and()
            .formLogin()
            .loginPage("/auth/login").permitAll()
            .defaultSuccessUrl("/auth/success")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/auth/login");
            //.httpBasic();

    }


    /*    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(
                userDetails
        );


    }*/


/*    //for inmemory security
    @Bean
    protected UserDetailsService userDetailsService() {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
                        .build(),
                User.builder()
                        .username("moderator")
                        .password(passwordEncoder().encode("moderator"))
                        .authorities(Role.MODERATOR.getAuthorities())
                        .build()
        );
    }*/

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

/*    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
