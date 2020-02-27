package com.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaFiscalDTO {

	private Long id;

	private Long idCategoria;

	private String descricao;

	private List<FileDTO> listFile;

}
