/**
 * Edmilson.Reis
 */
package com.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.NotaFiscalDTO;
import com.api.service.NotaFiscalService;

/**
 * @author edmilson.reis
 *
 */
@RestController
@RequestMapping("/api/v1/nota-fiscal")
public class NotaFiscalController {

	@Autowired
	private NotaFiscalService notaFiscalService;

	@Autowired
	HttpServletRequest request;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody NotaFiscalDTO request) {

		this.notaFiscalService.insert(request);

		return ResponseEntity.ok().build();
	}

}
