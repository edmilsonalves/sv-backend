/**
 * Edmilson.Reis
 */
package com.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.TipoAtributoDTO;
import com.api.service.TipoAtributoService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/tipo-atributo")
public class TipoAtributoController {

	@Autowired
	private TipoAtributoService tipoAtributoService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {

		List<TipoAtributoDTO> list = this.tipoAtributoService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}
}
