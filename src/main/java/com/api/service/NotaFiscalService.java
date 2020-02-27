package com.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.api.dto.FileDTO;
import com.api.dto.NotaFiscalDTO;
import com.api.entity.NotaFiscal;
import com.api.entity.ScFile;
import com.api.exception.BusinessException;
import com.api.repository.FileRepository;
import com.api.repository.NotaFiscalRepository;

@Service
@EnableTransactionManagement
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Autowired
	private FileRepository fileRepository;

	@Transactional(rollbackFor = { Exception.class })
	public void insert(NotaFiscalDTO notaDto) throws BusinessException {

		NotaFiscal nota = new NotaFiscal();
		BeanUtils.copyProperties(nota, notaDto, "listFile");

		nota = this.notaFiscalRepository.save(nota);

		for (FileDTO dto : notaDto.getListFile()) {

			ScFile file = new ScFile();
			BeanUtils.copyProperties(file, dto);

			file.setIdPai(nota.getId());
			this.fileRepository.save(file);
		}
	}

}
