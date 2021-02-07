package br.com.zup.mercadolivre.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.service.EmailSender;

@Component
@Primary
public class EmailSenderFake implements EmailSender {

	@Override
	public void send(String body, String subject, String nameFrom, String from, String to) {
		System.out.println("=> Novo email <=");
		System.out.println("================");
		System.out.println(body);
		System.out.println(subject);
		System.out.println(nameFrom);
		System.out.println(from);
		System.out.println(to);
	}

}
