package br.com.jean.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.jean.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
