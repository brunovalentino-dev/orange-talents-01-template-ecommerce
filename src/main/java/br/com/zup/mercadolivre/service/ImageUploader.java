package br.com.zup.mercadolivre.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

	public Set<String> envia(List<MultipartFile> imagens);
	
}
