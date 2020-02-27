package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.entity.ScFile;
import com.api.repository.FileRepository;

@Service
@EnableTransactionManagement
public class FileService {

	@Autowired
	private FileRepository imagemRepository;

	public List<ScFile> findByIdPai(final Long idProduto) {

		return this.imagemRepository.findAll();
	}


}
