package com.api.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.dto.ImagemDTO;
import com.api.payload.UploadFileResponse;
import com.api.service.FileStorageServiceV2;

@RestController
public class FileControllerV2 {

	private static final Logger logger = LoggerFactory.getLogger(FileControllerV2.class);

	@Autowired
	private FileStorageServiceV2 fileStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		Long idEmpresa = 40L;
		Long idProduto = 35L;

		String fileName = fileStorageService.storeFile(file, idEmpresa, idProduto);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		Long idEmpresa = 40L;
		Long idProduto = 35L;

		this.fileStorageService.removerImagensProduto(idEmpresa, idProduto);

		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<List<ImagemDTO>> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Long idEmpresa = 40L;
		Long idProduto = 35L;

		List<ImagemDTO> files = fileStorageService.findByProdutoId(idEmpresa, idProduto, fileName);

		return ResponseEntity.ok().body(files);
	}

}
