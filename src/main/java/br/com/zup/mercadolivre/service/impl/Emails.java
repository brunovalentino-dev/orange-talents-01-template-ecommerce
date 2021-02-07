package br.com.zup.mercadolivre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.PerguntaProduto;
import br.com.zup.mercadolivre.service.EmailSender;

@Service
public class Emails {

	@Autowired
	private EmailSender emailSender;

	public void enviarNovaPergunta(PerguntaProduto pergunta) {
		emailSender.send("<html>...</html>",
				"Nova pergunta", 
				pergunta.getUsuario().getUsername(), 
				"cliente@email.com",
				pergunta.getProduto().getUsuario().getUsername());
	}
		
}
