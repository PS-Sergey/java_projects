package com.opencode.questionare.configs;

import com.opencode.questionare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/questionnaire/**").authenticated()
                .antMatchers("/questionnaire/createApplication").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/questionnaire/updateApplication/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/questionnaire/delApplication/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/questionnaire/updateForms/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/questionnaire/users").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/questionnaire/userForms/**").hasAnyRole("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", true)
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
