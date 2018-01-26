package com.github.logs.logs.ui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
//@SpringUI(path = "/*")
//@Theme("valo")
public class LogsUI extends UI {

	private static final long serialVersionUID = 1L;
	@Value("${name}")
	private String basePath;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		Button fuseLogDownloadButton = new Button("Download fuse log");
		FileDownloader fuseLogDownloader = new FileDownloader(new FileResource(new File(basePath + "fuse.log")));
		fuseLogDownloader.extend(fuseLogDownloadButton);
		layout.addComponents(fuseLogDownloadButton);
		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "logsUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = LogsUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 1L;
	}

}
