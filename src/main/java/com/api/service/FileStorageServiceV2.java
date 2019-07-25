package com.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ImagemDTO;
import com.api.exception.FileStorageException;
import com.api.exception.MyFileNotFoundException;
import com.api.property.FileStorageProperties;

@Service
public class FileStorageServiceV2 {

	private final Path fileStorageLocation;

	@Autowired
	private ImagemService imagemService;

	@Autowired
	public FileStorageServiceV2(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file, final Long idEmpresa, final Long idProduto) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			String pathPrincipal = this.fileStorageLocation.toString();
			String pathEmpresaProduto = pathPrincipal + "\\" + idEmpresa + "\\" + idProduto;
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(pathEmpresaProduto);

			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Files.copy(file.getInputStream(), targetLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public void removerImagensProduto(final Long idEmpresa, Long idProduto) {
		String pathPrincipal = this.fileStorageLocation.toString();
		String pathEmpresaProduto = pathPrincipal + "\\" + idEmpresa + "\\" + idProduto;
		File baseDir = new File(pathEmpresaProduto);
		if (baseDir.exists()) {
			remover(baseDir);
			baseDir.mkdirs();
		} else {
			baseDir.mkdirs();
		}
	}

	public void remover(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; ++i) {
				remover(files[i]);
			}
		}
		f.delete();
	}

	public List<ImagemDTO> findByProdutoId(final Long idEmpresa, Long idProduto, String fileName) {

		List<ImagemDTO> imagens = this.imagemService.findByIdProduto(idProduto);
		try {
			for (ImagemDTO imagem : imagens) {
				Path filePath = this.fileStorageLocation.resolve("\\tmp\\img-tmp\\40\\35\\primeiro pedaço.jpeg").normalize();
				Resource resource = new UrlResource(filePath.toUri());

				String imageBase64 = "data:" + imagem.getType() + ";base64," + encoder(resource.getFile());
				imagem.setFile(imageBase64);
			}
		} catch (IOException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}

		return imagens;

	}

	public static String encoder(File file) {
		String base64File = "";
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a file from file system
			byte fileData[] = new byte[(int) file.length()];
			imageInFile.read(fileData);
			base64File = Base64.getEncoder().encodeToString(fileData);
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the file " + ioe);
		}
		return base64File;
	}
}
