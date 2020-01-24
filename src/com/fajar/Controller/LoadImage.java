package com.fajar.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet("/image/*")
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String imgpath;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadImage() {
        super();
        // TODO Auto-generated constructor stub
        imgpath = System.getProperty("catalina.home") + File.separator
				+ "images";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String requestedImage = request.getPathInfo();
		if (requestedImage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		File gambar = new File(imgpath, URLDecoder.decode(requestedImage,
				"UTF-8"));

		if (!gambar.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String contentType = getServletContext().getMimeType(gambar.getName());
		
		if(contentType == null || !contentType.startsWith("image")){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(gambar.length()));
		
		Files.copy(gambar.toPath(), response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
