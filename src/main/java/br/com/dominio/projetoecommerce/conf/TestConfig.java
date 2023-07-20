package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.service.DBService;
import br.com.dominio.projetoecommerce.service.EmailService;
import br.com.dominio.projetoecommerce.service.MockEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

  private final DBService dbService;

  @Bean
  public boolean instanciarBancodeDados() {
    dbService.insanciarBancoTeste();
    return true;
  }

  @Bean
  public EmailService emailService() {
    return new MockEmailService();
  }
}
