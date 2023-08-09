package com.gui.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

@Autowired
    private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> {
                req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                req.requestMatchers(HttpMethod.POST, "/usuario").permitAll();
                req.requestMatchers( "/usuario/**").hasRole("ADMIN");
                req.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll();
                req.anyRequest().authenticated();
            })
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

/*
 * @Configuration
 *
 * @EnableWebSecurity
 * public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
 *
 * @Autowired
 * private AutenticacaoService usuarioService;
 *
 * @Override
 * protected void configure(HttpSecurity http) throws Exception {
 * http
 * .httpBasic()
 * .and()
 * .csrf().disable()
 * .authorizeRequests()
 * .antMatchers("/css/**", "/js/**", "/webjars/**", "/images/**", "/static/**",
 * "/resources/**").permitAll()
 * .antMatchers("/carrinho/**").permitAll()
 * .antMatchers("/index", "/").permitAll()
 * .antMatchers("/cadastro/usuario").permitAll()
 * .antMatchers("/finalizar").hasRole("USER")
 * .antMatchers("/pagamento").hasRole("USER")
 * .antMatchers("/tipoProduto").hasRole("USER")
 * .antMatchers("/finalizar").hasRole("ADMIN")
 * .antMatchers("/pagamento").hasRole("ADMIN")
 * .antMatchers("/tipoProduto").hasRole("ADMIN")
 * .antMatchers("/cadastro").hasRole("ADMIN")
 * .anyRequest().authenticated()
 * .and()
 * .formLogin()
 * .failureUrl("/login-error")
 * .loginPage("/login")
 * .permitAll()
 * .and()
 * .logout()
 * .logoutUrl("/logout")
 * .permitAll()
 * .invalidateHttpSession(true)
 * .logoutSuccessUrl("/");
 * }
 *
 * @Override
 * public void configure(WebSecurity web) throws Exception {
 * web
 * .ignoring()
 * .antMatchers("/resources/**", "/css/**", "/images/**",
 * "/webjars/**","/js/**","/static/**");
 * }
 *
 * @Override
 * protected void configure(AuthenticationManagerBuilder auth) throws Exception
 * {
 * auth.authenticationProvider(authProvider());
 * }
 *
 * @Bean
 * public DaoAuthenticationProvider authProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * authProvider.setUserDetailsService(usuarioService);
 * authProvider.setPasswordEncoder(passwordEncoder());
 * return authProvider;
 * }
 *
 * @Bean
 * public PasswordEncoder passwordEncoder() {
 *
 * return new BCryptPasswordEncoder();
 * }
 * }
 */