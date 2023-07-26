package com.gui.infra;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSaver {

	/*
	 * @Value signifia que pode ser setado no application.properties pela key
	 * app.upload.dir, se não tiver será dado o valor padrão "user.home" que está em
	 * uma variável de ambiente de todo OS. No caso é usado o diretório deste
	 * projeto
	 */

	@Value("${upload.dir.location=${user.dir}}")
	public String uploadDir;

	public String write(MultipartFile file) {

		try {
			Path copyLocation = Paths.get(uploadDir + File.separator + "src" + File.separator + "main" + File.separator
					+ "resources" + File.separator + "static" + File.separator + "images" + File.separator
					+ StringUtils.cleanPath(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			return File.separator + "images" + File.separator + file.getOriginalFilename();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Não foi possível salva o arquivo: " + file.getOriginalFilename() + ". Favor tentar outra vez!");
		}
	}
}