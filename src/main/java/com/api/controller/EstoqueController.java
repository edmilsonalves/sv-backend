/**
 * Edmilson.Reis
 */
package com.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.EstoqueDTO;
import com.api.service.EstoqueService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		EstoqueDTO estoque = this.estoqueService.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(estoque);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findByIdProduto(@RequestParam(required = false) Long idProduto) {

		EstoqueDTO estoque = this.estoqueService.findByIdProduto(idProduto);

		return ResponseEntity.status(HttpStatus.OK).body(estoque);
	}
}
