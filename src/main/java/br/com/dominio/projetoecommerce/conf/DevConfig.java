package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.service.DBService;
import br.com.dominio.projetoecommerce.service.EmailService;
import br.com.dominio.projetoecommerce.service.SMTPEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class DevConfig {

  private final DBService dbService;
  private final MailSender mailSender;


  @Value("${spring.jpa.hibernate.ddl-auto}")
  private final String strategy;

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
    return new SMTPEmailService(mailSender);
  }
}
