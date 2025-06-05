package com.nnk.springboot.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .authorizeHttpRequests(auth -> auth
                               .requestMatchers("/**","/actuator/**","/bibList/**", "/curvePoint/**", "/rating/**", "/roleName/**","/trade/**","/user/**","/webjars/**").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(login -> login
                       .loginPage("/app/login")
                       .loginProcessingUrl("/j_spring_security_check")
                       .defaultSuccessUrl("/", true)
                       .usernameParameter("username")
                       .passwordParameter("password")
                       .permitAll()
               )
               .logout(logout -> logout
                       .logoutUrl("/app-logout")
                       .logoutSuccessUrl("/login?logout")
                       .invalidateHttpSession(true)
                       .deleteCookies("JSESSIONID")
               );
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/actuator/health","/bibList/**", "/curvePoint/**", "/rating/**",
//                                "/roleName/**","/trade/**","/user/**","/webjars/**").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/j_spring_security_check")
//                        .defaultSuccessUrl("/home", true)
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .permitAll());

        return http.build();
    }
}
