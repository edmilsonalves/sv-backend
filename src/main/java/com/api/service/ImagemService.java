package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.dto.ImagemDTO;
import com.api.entity.Imagem;
import com.api.repository.ImagemRepository;

@Service
@EnableTransactionManagement
public class ImagemService {

	@Autowired
	private ImagemRepository imagemRepository;

	public List<ImagemDTO> findByIdProduto(final Long idProduto) {
		List<Imagem> imagens = this.imagemRepository.findByIdProduto(idProduto);

		return convertToDtoList(imagens);
	}

	private List<ImagemDTO> convertToDtoList(List<Imagem> entities) {

		if (entities == null) {
			return null;
		}

		List<ImagemDTO> list = new ArrayList<>(0);

		entities.forEach(entity -> {
			ImagemDTO dto = new ImagemDTO();
			BeanUtils.copyProperties(entity, dto);
			
			Base64 codec = new Base64();
			
			String base64 = codec.encodeAsString(entity.getFile());
			String file = "data:"+entity.getType()+";base64,"+base64;
			dto.setFile(file);

			list.add(dto);
		});

		return list;
		
	}

}
