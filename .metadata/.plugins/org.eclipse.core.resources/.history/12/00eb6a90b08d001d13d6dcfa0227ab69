package com.kronsoft.project.config;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.kronsoft.project.config.authentication.CustomUserDetailsService;
import com.kronsoft.project.config.authentication.UserAuthneticationFailureHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new CustomUserDetailsService();
		
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
		
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
        
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
		
        auth.authenticationProvider(authenticationProvider());
        
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeHttpRequests()
		    .antMatchers("/user/register").permitAll()
			.antMatchers("/products/**", "/stocks/**", "/user/**").authenticated()
		.and().formLogin()
			.successHandler(new AuthenticationSuccessHandler() {

				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					
					System.out.println("User " + authentication.getName() + " has logged in: " + LocalDateTime.now());
				}
				
			})
			.failureHandler(new UserAuthneticationFailureHandler())
		.and().logout()
			.logoutSuccessHandler(new LogoutSuccessHandler() {
			 
            @Override
            public void onLogoutSuccess(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
				
				System.out.println("User " + authentication.getName() + " has logged out: " + LocalDateTime.now());

            }
        }).deleteCookies("JSESSIONID")
			.clearAuthentication(true)
			.permitAll();
		
	}
	
}
