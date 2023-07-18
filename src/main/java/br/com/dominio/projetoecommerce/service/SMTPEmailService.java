package br.com.dominio.projetoecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService {

  private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

  @Autowired
  private MailSender mailSender;

  @Override
  public void sendEmail(SimpleMailMessage msg) {
    try {
      LOG.info("Enviando email...");
      mailSender.send(msg);
      LOG.info("Email enviado!");
    } catch (Exception e) {
      LOG.info("E-mail não enviado! \nMotivo: " + e.getCause().getMessage());
      e.getMessage();
    }
  }
}
