package com.compasso.academia.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.compasso.academia.service.UsuarioDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private static CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void WebSecurityConfig(CustomSuccessHandler customSuccessHandler) {
        SecurityConfig.customSuccessHandler = customSuccessHandler;
    }

	@Bean
	public UsuarioDetailsService usuarioDetailsService() {
		return new UsuarioDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()               	
                	.antMatchers("/home","/sobre","/cadastro","/contato").permitAll()
                	.antMatchers(HttpMethod.POST, "/submeter_registro").permitAll()
                    .antMatchers("/personal/**").hasAuthority("PERSONAL")
                    .antMatchers("/dashboard/**").authenticated()                
                    .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .loginProcessingUrl("/login")
                    .successHandler(customSuccessHandler)
                    .failureUrl("/403")
                    .permitAll()
                    .and()
                .logout()
                .deleteCookies("JSESSIONID")
    			.logoutSuccessUrl("/home")
    			.and()
    			.exceptionHandling().accessDeniedPage("/403")
    			;           
        }
        
     // Config static files
        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/css/**","/js/**","/img/**");
        }
}

