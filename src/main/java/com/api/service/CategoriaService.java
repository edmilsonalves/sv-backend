package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.dto.OptionDTO;
import com.api.entity.Categoria;
import com.api.entity.NotaFiscal;
import com.api.enums.ErrorCode;
import com.api.exception.BusinessException;
import com.api.repository.CategoriaRepository;
import com.api.repository.NotaFiscalRepository;
import com.api.util.SUtils;

@Service
@EnableTransactionManagement
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private NotaFiscalRepository produtoRepository;

	public List<OptionDTO> findAll() {
		List<Categoria> list = this.categoriaRepository.findAll();
		List<OptionDTO> categorias = new ArrayList<>(0);
		list.forEach(categoria -> {
			OptionDTO dto = new OptionDTO(categoria.getId(), categoria.getDescricao());
			categorias.add(dto);
		});
		return categorias;
	}

	public OptionDTO findById(Long id) {
		Categoria categoria = categoriaRepository.findById(id).orElse(new Categoria());
		return new OptionDTO(categoria.getId(), categoria.getDescricao());
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void delete(Long idCategoria) {

		List<NotaFiscal> produtoList = this.produtoRepository.findByIdCategoria(idCategoria);

		if (!SUtils.isNullOrEmpty(produtoList)) {
			throw new BusinessException(ErrorCode.SIS_4);
		}

		this.categoriaRepository.deleteById(idCategoria);
	}

}
