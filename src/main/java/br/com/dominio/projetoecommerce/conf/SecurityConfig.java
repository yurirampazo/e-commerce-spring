package br.com.dominio.projetoecommerce.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {

  @Autowired
  private Environment environment;

  private static final String[] PUBLIC_MATCHERS = {
        "/h2-console/**"
  };

  private static final String[] PUBLIC_MATCHERS_GET = {
        "/produtos/**",
        "/categorias/**"
  };
  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
      http.headers().frameOptions().disable();
    }
    http.cors().and().csrf().disable();
    http.authorizeRequests()
          .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
          .antMatchers(PUBLIC_MATCHERS).permitAll()
          .anyRequest().authenticated();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

}
