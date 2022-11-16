package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.service.DBService;
import br.com.dominio.projetoecommerce.service.EmailService;
import br.com.dominio.projetoecommerce.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

  @Autowired
  private DBService dbService;

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
