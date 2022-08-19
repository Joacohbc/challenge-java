package com.alkemy.challengejava.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alkemy.challengejava.auth.filter.JwtRequestFilter;
import com.alkemy.challengejava.auth.service.UserDetailsCustomSerivce;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCustomSerivce userDetailsCustomSerivce;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsCustomSerivce;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsCustomSerivce);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*").permitAll() // Le digo que permita cualquier peticion a los
                                                                        // endpoint que matcheen con /aut/* */
                .anyRequest().authenticated() // Al resto que les pida autenticarse
                .and().exceptionHandling() // Permitir manejo de erroe
                .and().sessionManagement() // Y permitir el manejo de sessiones
                // STATELESS sigmifica que pedira autentificacion cada vez que se haga una
                // peticion a cualquier endpoint
                // Predeterminadamente, es STATEFUL es decir que recuerdo en su contexto si es
                // que te logueaste anteriormente
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
