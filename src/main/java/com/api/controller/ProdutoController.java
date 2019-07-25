/**
 * Edmilson.Reis
 */
package com.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ProdutoDTO;
import com.api.exception.BusinessException;
import com.api.response.BaseResponse;
import com.api.service.ProdutoService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listByFilter(@RequestParam(required = false) String query,
			@RequestParam(required = false) String status) {

		List<ProdutoDTO> list = this.produtoService.listByFilter(query, status);
		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		ProdutoDTO produto = this.produtoService.findById(id);
		return ResponseEntity.ok(produto);

	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody ProdutoDTO produto) {
		BaseResponse response = new BaseResponse();

		try {

			System.out.println(produto);

			// if (produto.getId() != null) {
			// produto = this.produtoService.update(produto);
			// } else {
			// produto = this.produtoService.insert(produto);
			// }
			//
			// response.setEntity(produto);

			response.setMessage("Registro alterado com sucesso.");
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody ProdutoDTO request) {

		ProdutoDTO produto = this.produtoService.update(request);

		return ResponseEntity.ok(produto);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		BaseResponse response = new BaseResponse();

		try {
			produtoService.excluir(id);

			response.setMessage("Produto excluido com sucesso.");
		} catch (Exception e) {

			response.setMessage(e.getMessage());
			e.printStackTrace();
		}

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/categoriaList", method = RequestMethod.GET)
	public ResponseEntity<?> categoriaList() {
		BaseResponse response = new BaseResponse();

		try {
			CacheControl cache = CacheControl.maxAge(10800, TimeUnit.SECONDS);
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(response);
		} catch (BusinessException e) {

			response.setMessage(e.getMessage());
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
