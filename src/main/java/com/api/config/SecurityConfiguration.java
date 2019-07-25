//package com.api.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
///**
// *
// * @author edmilson.reis
// *
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserDetailsService usuarioDetailsService;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////		http.csrf().disable().authorizeRequests().antMatchers("/*/clientes*", "/*/produtos*")
////				.hasAnyAuthority("USUARIO", "ADMIN").antMatchers("/*/financeiro*").hasAuthority("ADMIN")
////				.antMatchers("/plain/rest/**", "/plain/assets/**", "/plain/cadastrar-se.html",
////						"/plain/recupera-acesso.html")
////				.permitAll().anyRequest().authenticated().and().formLogin()
////				.failureUrl("/plain/login.html?auth=loginInvalido").defaultSuccessUrl("/page/dashboard.html", true)
////				.loginPage("/plain/login.html").permitAll().and().logout().permitAll().and().exceptionHandling()
////				.accessDeniedPage("/page/acesso-negado.html");
//	}
//
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
////		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
////		auth.userDetailsService(usuarioDetailsService).passwordEncoder(shaPasswordEncoder);
//	}
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final CorsConfiguration configuration = new CorsConfiguration();
//
//		configuration.setAllowedOrigins(Collections.singletonList("*"));
//		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//		// setAllowCredentials(true) is important, otherwise:
//		// The value of the 'Access-Control-Allow-Origin' header in the response must
//		// not be the wildcard '*' when the request's credentials mode is 'include'.
//		configuration.setAllowCredentials(true);
//		// setAllowedHeaders is important! Without it, OPTIONS preflight request
//		// will fail with 403 Invalid CORS request
//		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//
//}
