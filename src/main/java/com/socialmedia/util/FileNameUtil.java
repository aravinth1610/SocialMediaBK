package com.socialmedia.util;

import java.sql.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileNameUtil {

	public String createFileName(MultipartFile multipartFile, String userId) {
		String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		Integer fileDotIndex = originalFileName.lastIndexOf('.');
		String fileExtension = originalFileName.substring(fileDotIndex);
		return userId.concat("_" + new Date(System.currentTimeMillis()) + fileExtension);
	}

}
