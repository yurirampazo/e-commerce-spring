package br.com.dominio.projetoecommerce.security;

import br.com.dominio.projetoecommerce.domain.dto.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  private JWTUtil jwtUtil;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      log.info("Received Credentials to be analyzed...");
      CredentialsDTO creds = new ObjectMapper()
            .readValue(req.getInputStream(), CredentialsDTO.class);
      log.info("Username: {}", creds.getEmail());
      log.info("Password: {}", creds.getPassword());

      UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());

      return authenticationManager.authenticate(authToken);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) {

    User user = ((User) auth.getPrincipal());
    String token = jwtUtil.generateAccessToken(req, user);
    log.info("SUCCESSFUL AUTHENTICATION PROCESS!");
    res.addHeader("Authorization", "Bearer " + token);
    res.addHeader("access-control-expose-headers", "Authorization");
  }

  private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
          throws IOException {
      response.setStatus(401);
      response.setContentType("application/json");
      response.getWriter().append(json());
    }

    private String json() {
      long date = new Date().getTime();
      return "{\"timestamp\": " + date + ", "
            + "\"status\": 401, "
            + "\"error\": \"Unauthorized\", "
            + "\"message\": \"Invalid credentials\", "
            + "\"path\": \"/login\"}";
    }
  }
}