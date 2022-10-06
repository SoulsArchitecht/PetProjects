package com.soulsarch.PasswordManager.config;

import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    private final UserDetailsService userDetailsService;

*//*    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(
                    "User '" + username + "' not found");

        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/shutdownContext").access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll")
                //.antMatchers("/", "/**").access("hasRole('User')")
                //.antMatchers(("/login")).access("permitAll()")

                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .csrf()
                .disable()
                //.ignoringAntMatchers("/h2-console/**")

                //.and()
                .headers()
                .frameOptions()
                .sameOrigin()

                //.and()
                //.build();
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }*/


    /*    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() *//*временное отключение защиты*//*
            .authorizeRequests()
            //.antMatchers("/auth/login").permitAll()
            .antMatchers("/**").permitAll()
           //for h2 db access via localhost
            .and()
            .authorizeRequests().antMatchers("/h2-console/**").permitAll()
            .anyRequest()
            .authenticated()
            //.antMatchers("/**").hasAuthority(Permission.URLINFORMATION_READ.getPermission())

            //.anyRequest()
            //.authenticated()

            //for h2 db access via localhost
            .and()
            .headers().frameOptions().disable()
            .and()
            .csrf().ignoringAntMatchers("/h2-console/**")
            .and()
            .cors().disable()
            //.and()
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

    }*/


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

/*    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }*/

/*    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }*/

/*    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }*/
}
