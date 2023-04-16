package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	            .csrf()
	            .ignoringAntMatchers("/upload","/download","/delete","/photo","/api/**")
	            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            .and()
	            .authorizeRequests()
	            .antMatchers("/login", "/css/**","/js/**","/h2console/**","/static/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .permitAll()
	            .and()
	            .logout()
	            .permitAll()
	            .and().csrf().ignoringAntMatchers("/h2console/**")
	            .and().headers().frameOptions().sameOrigin()
	            .and()
	            .httpBasic();
	}

//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http
//            .csrf()
//            .ignoringAntMatchers("/upload","/download","/delete")
//            .and()
//            .authorizeRequests()
//            .antMatchers("/login", "/css/**", "/js/**", "/h2console/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/home", true)
//            .permitAll()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/login")
//            .permitAll()
//            .and()
//            .headers()
//            .frameOptions()
//            .sameOrigin()
//            .and()
//            .csrf().ignoringAntMatchers("/h2console/**")
//            .and()
//            .headers()
//            .frameOptions()
//            .sameOrigin();
//}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**");
    }

}