package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;



import com.example.demo.userPackage.UserRepo;
import com.example.demo.userPackage.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	            .csrf()
	            .ignoringAntMatchers("/upload","/download","/delete","/photo","/api/**","/h2console/**")
	            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            .and()
	            .authorizeRequests()
	            .antMatchers("/login", "/css/**","/js/**","/static/**","/register").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .failureUrl("/login?error=true")
	            .defaultSuccessUrl("/home")
	            .permitAll()
	            .and()
	            .logout()
	            .logoutSuccessUrl("/loginout")
	            .permitAll()
	            .and().csrf().ignoringAntMatchers("/h2console/**")
	            .and().headers().frameOptions().sameOrigin()
	            .and()
	            .httpBasic();
	}

    
    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("SELECT username, password,'true' as enabled FROM user_credentials WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' FROM user_credentials WHERE username = ?")
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//    	return NoOpPasswordEncoder.getInstance();
//    }
//    
    

}