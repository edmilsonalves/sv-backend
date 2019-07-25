/**
 * Edmilson.Reis
 */
package com.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ProductDTO;
import com.api.exception.BusinessException;
import com.api.response.BaseResponse;
import com.api.service.ProdutoService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam(required = false) String query,
			@RequestParam(required = false) boolean incluirInativos) {

		List<ProductDTO> list = new ArrayList<>(0);
		ProductDTO dto = new ProductDTO();
		dto.set_id(1L);
		dto.setProd_name("prod1");
		dto.setProd_price(123);
		dto.setUpdated_at(new Date());

		list.add(dto);

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

}
