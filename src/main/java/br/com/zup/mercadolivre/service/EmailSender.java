package br.com.zup.mercadolivre.service;

public interface EmailSender {

	void send(String body, String subject, String nameFrom, String from, String to);
	
}
