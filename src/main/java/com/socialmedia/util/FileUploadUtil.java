package com.socialmedia.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {

	public void saveNewFile(String uploadDirectory, String fileName, MultipartFile multipartFile) throws IOException {

		Path path = Paths.get(uploadDirectory);

		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

		try {
			InputStream inputStream = multipartFile.getInputStream();
			Path filePath = path.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save file");
		}

	}

}
