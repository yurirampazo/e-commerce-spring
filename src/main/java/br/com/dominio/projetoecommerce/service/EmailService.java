package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);
}
