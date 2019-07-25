/**
 * Edmilson.Reis
 */
package com.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ImagemDTO;
import com.api.service.ImagemService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/imagens")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam(required = false) String query,
			@RequestParam(required = true) Long idProduto) {

		List<ImagemDTO> list = this.imagemService.findByIdProduto(idProduto);

		return ResponseEntity.ok(list);

	}

}
