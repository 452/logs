package com.github.logs.logs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("log")
public class LogController {

	@GetMapping(value = "fuse/zip", produces = "application/zip")
	public void getFuseLog(HttpServletResponse response) throws IOException {
		ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
		ArrayList<File> files = new ArrayList<>(2);
		files.add(new File("/var/log/tetra/backend/fuse.log"));
		for (File file : files) {
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			FileInputStream fileInputStream = new FileInputStream(file);
			IOUtils.copy(fileInputStream, zipOutputStream);
			fileInputStream.close();
			zipOutputStream.closeEntry();
		}
		zipOutputStream.close();
	}

}