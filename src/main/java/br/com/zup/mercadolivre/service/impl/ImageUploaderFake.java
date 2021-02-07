package br.com.zup.mercadolivre.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.mercadolivre.service.ImageUploader;

@Component
public class ImageUploaderFake implements ImageUploader {

	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream()
					  .map(imagem -> "http://imagebucket.io/" + imagem.getOriginalFilename())
					  .collect(Collectors.toSet());
	}
	
}
