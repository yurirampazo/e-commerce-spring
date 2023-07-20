package br.com.dominio.projetoecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
public class SMTPEmailService extends AbstractEmailService {
  private final MailSender mailSender;

  public SMTPEmailService(MailSender mailSender) {
    this.mailSender = mailSender;
  }


  @Override
  public void sendEmail(SimpleMailMessage msg) {
    try {
      log.info("Enviando email...");
      mailSender.send(msg);
      log.info("Email enviado!");
    } catch (Exception e) {
      log.info("E-mail não enviado! \nMotivo: " + e.getCause().getMessage());
      e.getMessage();
    }
  }
}
