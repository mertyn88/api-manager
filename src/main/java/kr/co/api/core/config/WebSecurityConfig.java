package kr.co.api.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
      //  http.authorizeRequests()
       //         .antMatchers("/", "/test", "/login").permitAll()
       //         .antMatchers("/test").hasRole("USER")
        //        .anyRequest().authenticated()
             //   .and()
             //   .formLogin().loginPage("/login").permitAll()
             //   .and()
             //   .logout();
        ;
    }
}