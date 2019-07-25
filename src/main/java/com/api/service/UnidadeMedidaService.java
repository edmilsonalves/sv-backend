package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.dto.OptionDTO;
import com.api.entity.UnidadeMedida;
import com.api.repository.UnidadeMedidaRepository;

@Service
@EnableTransactionManagement
public class UnidadeMedidaService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public List<OptionDTO> findAll() {
		List<UnidadeMedida> list = this.unidadeMedidaRepository.findAll();
		List<OptionDTO> dtoList = new ArrayList<>(0);
		list.forEach(entity -> {
			OptionDTO dto = new OptionDTO(entity.getId(), entity.getDescricao());
			dtoList.add(dto);
		});
		return dtoList;
	}

	public OptionDTO findById(Long id) {
		UnidadeMedida entity = this.unidadeMedidaRepository.findById(id).orElse(new UnidadeMedida());
		return new OptionDTO(entity.getId(), entity.getDescricao());
	}

	public UnidadeMedida save(UnidadeMedida unidadeMedida) {
		return this.unidadeMedidaRepository.save(unidadeMedida);
	}

}
