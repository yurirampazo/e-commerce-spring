package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.service.DBService;
import br.com.dominio.projetoecommerce.service.EmailService;
import br.com.dominio.projetoecommerce.service.MockEmailService;
import br.com.dominio.projetoecommerce.service.SMTPEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBService dbService;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String strategy;

  @Bean
  public boolean instanciarBancodeDados() {

    if (!"create".equals(strategy)) {
      return false;
    }

    dbService.insanciarBancoTeste();
    return true;
  }
  @Bean
  public EmailService emailService() {
    return new SMTPEmailService();
  }
}
