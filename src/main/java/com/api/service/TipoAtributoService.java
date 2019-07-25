package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.dto.OptionDTO;
import com.api.dto.TipoAtributoDTO;
import com.api.entity.Atributo;
import com.api.entity.TipoAtributo;
import com.api.repository.TipoAtributoRepository;

@Service
@EnableTransactionManagement
public class TipoAtributoService {

	@Autowired
	private TipoAtributoRepository tipoAtributoRepository;

	public List<TipoAtributoDTO> findAll() {
		List<TipoAtributo> list = this.tipoAtributoRepository.findAll();
		List<TipoAtributoDTO> listTipoAtributo = new ArrayList<>(0);

		if (list != null && !list.isEmpty()) {
			for (TipoAtributo tipoAtributo : list) {
				TipoAtributoDTO tipoAtributoDTO = new TipoAtributoDTO(tipoAtributo.getId());
				tipoAtributoDTO.setDescricao(tipoAtributo.getDescricao());
				for (Atributo atributo : tipoAtributo.getListAtributo()) {
					tipoAtributoDTO.getListAtributo().add(new OptionDTO(atributo.getId(), atributo.getDescricao()));
				}
				listTipoAtributo.add(tipoAtributoDTO);
			}
		}

		return listTipoAtributo;
	}

	public TipoAtributoDTO findById(Long id) {
		TipoAtributo tipoAtributo = this.tipoAtributoRepository.findById(id).orElse(new TipoAtributo());

		TipoAtributoDTO tipoAtributoDTO = new TipoAtributoDTO(tipoAtributo.getId());
		for (Atributo atributo : tipoAtributo.getListAtributo()) {
			tipoAtributoDTO.getListAtributo().add(new OptionDTO(atributo.getId(), atributo.getDescricao()));
		}

		return tipoAtributoDTO;
	}

}
