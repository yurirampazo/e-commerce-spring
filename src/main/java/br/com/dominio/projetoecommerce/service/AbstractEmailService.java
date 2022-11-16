package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.model.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

  @Value("${default.sender}")
  private String sender;

  @Override
  public void sendOrderConfirmationEmail(Pedido obj) {
    SimpleMailMessage sm = this.prepareSimpleMailMessageFromPedido(obj);
    sendEmail(sm);
  }

  protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
    SimpleMailMessage sm = new SimpleMailMessage();
    sm.setTo(obj.getCliente().getEmail());
    sm.setFrom(sender);
    sm.setSubject("Pedido CONFIRMADO! Código: " + obj.getId());
    sm.setSentDate(new Date(System.currentTimeMillis()));
    sm.setText(obj.toString());
    return sm;
  }
}
