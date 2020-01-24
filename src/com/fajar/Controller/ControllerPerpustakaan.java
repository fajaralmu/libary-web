package com.fajar.Controller;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fajar.DAO.DAOBuku;
import com.fajar.DAO.DAOPost;
import com.fajar.DAO.DAOUser;
import com.fajar.Model.Buku;
import com.fajar.Model.Post;
import com.fajar.Model.User;
import com.fajar.include.Koneksi;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ControllerPerpustakaan
 */
//@WebServlet("/websaya")
@MultipartConfig
public class ControllerPerpustakaan extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOBuku dob = new DAOBuku();
	private DAOUser dou = new DAOUser();
	private DAOPost dop = new DAOPost();
	private final static Logger LOGGER = Logger
			.getLogger(ControllerPerpustakaan.class.getCanonicalName());

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 100 * 1024;
	private int maxMemSize = 8 * 1024;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String tindakan = request.getServletPath();

		try {
			switch (tindakan) {
			case "/baru":
				tampilkanFormBaru(request, response);
				break;
			case "/tambah":
				tambahBuku(request, response);
				break;
			case "/cariBuku":
				cariBuku(request, response);
				break;
			case "/editBuku":
				tampilkanFormEditBuku(request, response);
				break;
			case "/perbarui":
				editBuku(request, response);
				break;
			case "/hapus":
				hapusBuku(request, response);
				break;
			case "/daftarBuku":
				daftarBuku(request, response);
				break;
			case "/buku":
				detailBuku(request, response);
				break;
			case "/registrasi":
				halamanRegistrasi(request, response);
				break;
			case "/cekUsername":
				cekUsername(request, response);
				break;
			case "/prosesRegistrasi":
				registrasiPengguna(request, response);
				break;
			case "/masuk":
				masuk(request, response);
				break;
			case "/editUser":
				tampilkanFormEditUser(request, response);
				break;
			case "/prosesEditUser":
				prosesEditUser(request, response);
				break;
			case "/beranda":
				tampilkanBeranda(request, response);
				break;
			case "/keluar":
				userKeluar(request, response);
				break;
			case "/post":
				halamanPosting(request, response);
				break;
			case "/simpaneditpost":
				simpanEditPosting(request, response);
				break;
			case "/postingBaru":
				postBaru(request, response);
				break;
			case "/myblog":
				halamanBlog(request, response);
				break;
			case "/catatan":
				catatanBlog(request, response);
				break;
			case "/dapatkannamapengguna":
				dapatkannama(request, response);
				break;
			case "/upload":
				upload(request, response);
				break;
			case "/uploadpage":
				halamanupload(request, response);
				break;
			case "/error":
				redirectError(request, response);
				break;
			case "/":
			case "/home":
				// System.out.println(System.getProperty("catalina.home").toString());
				halamanDepan(request, response);

				break;
			// default:
			// break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	// ////////////////////////////UPLOAD////////////////////////////////////////

	private void dapatkannama(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("idpengguna").toString());
		String namaUser = dou.dapatkanUser(id).getNama();
		response.setContentType("text/plain");
		response.getWriter().write(namaUser);
		System.out.println("nama: "+namaUser+" id: "+id);
	}

	private void halamanupload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("upload.jsp");
		dispatcher.forward(request, response);

	}

	protected void upload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		isMultipart = ServletFileUpload.isMultipartContent(request);
		java.io.PrintWriter out = response.getWriter();

		if (!isMultipart) {
			response.setContentType("text/plain");
			response.getWriter().write("gagal");
			System.out.println("bukan multipart");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					// out.println("Uploaded Filename: " + fileName + "<br>");
					response.setContentType("text/plain");
					System.out.println("oke");
					response.getWriter().write(fileName);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// /////////////////////////////////////BLOG/////////////////////

	private void simpanEditPosting(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			ServletException {
		// TODO Auto-generated method stub
		if (sessionUser(request, response)) {
			if (!request.getParameter("judul").isEmpty()
					&& !request.getParameter("konten").isEmpty()
					&& !request.getParameter("katakunci").isEmpty()
					&& !request.getParameter("kategori").isEmpty()) {

				String judul = request.getParameter("judul");
				String konten = request.getParameter("konten");
				int idkategori = Integer.parseInt(request
						.getParameter("kategori"));
				String katakunci = request.getParameter("katakunci");
				int idpengguna = (int) request.getSession().getAttribute("id");
				Post p = new Post(judul, konten, idkategori, idpengguna, null,
						katakunci, 1);
				p.setIdpost(Integer.parseInt(request.getParameter("idpost")));
				if (dop.editPost(p)) {
					response.setContentType("text/plain");
					response.getWriter().write("oke");
				} else {
					response.setContentType("text/plain");
					response.getWriter().write("gagal");
				}
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("tidaklengkap");
			}

		} else {
			response.sendRedirect("home");
		}

	}

	private void catatanBlog(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			SQLException {
		// TODO Auto-generated method stub
		if (request.getParameterMap().containsKey("action")) {
			String action = request.getParameter("action");
			if (action.equals("baca")) {
				if (request.getParameterMap().containsKey("id")) {
					int intid = Integer.parseInt(request.getParameter("id"));
					Post p = dop.dapatkanPost(intid);
					String nama = dou.dapatkanUser(p.getIdpengguna()).getNama();
					request.setAttribute("post", p);
					request.setAttribute("penulis", nama);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("blogread.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("catatan");
				}
			} else {
				response.sendRedirect("catatan");
			}
		}else if (request.getParameterMap().containsKey("halaman")) {
			int postperhalaman = 5;
			int totalPost = dop.jumlahPost();

			int jumlahhalaman = (int) Math.ceil((double) totalPost
					/ postperhalaman);
			response.setContentType("text/plain");
			response.getWriter().write(Integer.toString(jumlahhalaman));
			System.out.println("TOTAL PAGE : "+Integer.toString(jumlahhalaman));
		} else if (request.getParameterMap().containsKey("tampil")) {
			List<Post> daftarPostTotal = dop.daftarPost();
			int hal = Integer.parseInt(request.getParameter("tampil").toString());
			int postperhalaman = 5;
			int totalPost = dop.jumlahPost();

			int jumlahhalaman = (int) Math.ceil((double) totalPost
					/ postperhalaman);
			List<Post> daftarPost = new ArrayList<>();
			int pos = 0;
			for (int i = hal * postperhalaman - postperhalaman; i < (totalPost
					- hal * postperhalaman <= hal * postperhalaman ? totalPost
					: hal * postperhalaman); i++) {
				pos++;
				daftarPost.add(daftarPostTotal.get(i));
				if(pos>=5) break;
			}
			String json = new Gson().toJson(daftarPost);
			System.out.println("post yg tampil: " + daftarPost.size());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
//			response.setContentType("text/plain");
//			response.getWriter().write(Integer.toString(halamantampil));
//			System.out.println("PAGE TO SHOW : "+Integer.toString(halamantampil));
		}else{
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("cloneblogpage.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void halamanPosting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		if (sessionUser(request, response)) {
			if (request.getParameter("action").equals("baru")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("blogform.jsp");
				dispatcher.forward(request, response);
			} else if (request.getParameter("action").equals("daftarposting")) {
				// System.out.println(request.getSession().getAttribute("id"));
				List<Post> p = dop.dapatkanPost("idpengguna", request
						.getSession().getAttribute("id").toString());
				request.setAttribute("daftarPost", p);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("bloglist.jsp");
				dispatcher.forward(request, response);
			} else if (request.getParameter("action").equals("hapus")
					&& !request.getParameter("id").isEmpty()) {
				System.out.println("OKE");
				if (!request.getParameter("id").equals(
						request.getSession().getAttribute("id"))) {
					Post p = new Post();
					p.setIdpost(Integer.parseInt(request.getParameter("id")
							.toString()));

					if (dop.hapusPost(p))
						response.sendRedirect("post?action=daftarposting");
					else {
						System.out.println("gagal menghapus");
						response.sendRedirect("home");
					}
				} else {
					response.sendRedirect("home");
				}
			} else if (request.getParameter("action").equals("edit")
					&& !request.getParameter("id").isEmpty()) {
				System.out.println("OKE");
				if (!request.getParameter("id").equals(
						request.getSession().getAttribute("id"))) {
					Post p = dop.dapatkanPost((int) Integer.parseInt(request
							.getParameter("id")));
					System.out.println(p.toString());
					request.setAttribute("post", p);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("blogeditform.jsp");
					dispatcher.forward(request, response);
				} else {

					response.sendRedirect("beranda");
				}
			} else {
				response.sendRedirect("beranda");
			}
		} else {
			response.sendRedirect("home");
		}

	}

	private void postBaru(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			ServletException {
		// TODO Auto-generated method stub
		if (sessionUser(request, response)) {
			if (!request.getParameter("judul").isEmpty()
					&& !request.getParameter("konten").isEmpty()
					&& !request.getParameter("katakunci").isEmpty()
					&& !request.getParameter("kategori").isEmpty()) {

				String judul = request.getParameter("judul");
				String konten = request.getParameter("konten");
				int idkategori = Integer.parseInt(request
						.getParameter("kategori"));
				String katakunci = request.getParameter("katakunci");
				int idpengguna = (int) request.getSession().getAttribute("id");
				Post p = new Post(judul, konten, idkategori, idpengguna, null,
						katakunci, 1);
				if (dop.tambahPost(p)) {
					response.setContentType("text/plain");
					response.getWriter().write("oke");
				} else {
					response.setContentType("text/plain");
					response.getWriter().write("gagal");
				}
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("tidaklengkap");
			}
		} else {
			response.sendRedirect("home");
		}
	}

	private void halamanBlog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if (request.getParameterMap().containsKey("action")) {
			if (action.equals("baca")) {
				if (request.getParameterMap().containsKey("id")) {
					int intid = Integer.parseInt(request.getParameter("id"));
					Post p = dop.dapatkanPost(intid);
					String nama = dou.dapatkanUser(p.getIdpengguna()).getNama();
					request.setAttribute("post", p);
					request.setAttribute("penulis", nama);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("blogread.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("myblog");
				}
			} else {
				response.sendRedirect("myblog");
			}
		} else if (request.getParameterMap().containsKey("hal")) {
			if (!request.getParameter("hal").isEmpty()) {
				List<Post> daftarPostTotal = dop.daftarPost();
				int hal = Integer.parseInt(request.getParameter("hal")
						.toString());
				int postperhalaman = 5;
				int totalPost = dop.jumlahPost();

				int jumlahhalaman = (int) Math.ceil((double) totalPost
						/ postperhalaman);
				List<Post> daftarPost = new ArrayList<>();
				for (int i = hal * postperhalaman - postperhalaman; i < (totalPost
						- hal * postperhalaman <= hal * postperhalaman ? totalPost
						: hal * postperhalaman); i++) {
					daftarPost.add(daftarPostTotal.get(i));
				}

				System.out.println("total post: " + totalPost
						+ ", jumlah halaman:" + jumlahhalaman);
				request.setAttribute("totalhalaman", jumlahhalaman);
				request.setAttribute("DaftarPost", daftarPost);

				RequestDispatcher dispatcher = request
						.getRequestDispatcher("blogpage.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("myblog?hal=1");
			}
		} else {
			response.sendRedirect("myblog?hal=1");
		}
	}

	// //////////////////////////////////LIBRARY APP/////////////////

	private void detailBuku(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		if (request.getParameter("id").isEmpty()) {
			response.sendRedirect("home");
		} else {
			Buku b = dob.dapatkanBuku(id);
			if (b != null) {
				request.setAttribute("buku", b);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("detailbuku.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("home");
			}
		}
	}

	// //////////////////////////////////USER STUFF//////////////////

	private void cekUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		User u = dou.cariUser("username", request.getParameter("usernameData"));
		if (u != null) {
			response.setContentType("text/plain");
			response.getWriter().write("sudahada");
		} else {
			response.setContentType("text/plain");
			response.getWriter().write("oke");
		}
	}

	private void userKeluar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession(false).setAttribute("username", null);
		request.getSession(false).setAttribute("katasandi", null);
		request.getSession(false).setAttribute("id", null);
		response.sendRedirect("masuk");

	}

	private void prosesEditUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		if (!sessionUser(request, response)) {
			System.out.println("nosession");
			response.getWriter().write("nosession");

		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			int sessionId = Integer.parseInt(request.getSession()
					.getAttribute("id").toString());
			String username = request.getParameter("username");
			String nama = request.getParameter("nama");
			String katasandi = request.getParameter("katasandi");
			if (id == sessionId) {
				User u = new User(id, username, nama, katasandi, 0);
				System.out.println(u.toString());
				if (dou.perbaruiUser(u)) {
					response.getWriter().write("editok");

				} else {
					response.getWriter().write("editfailed");

				}
			} else {
				response.getWriter().write("missmatchId");

			}
		}

	}

	private void halamanDepan(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("lib.jsp");
		dispatcher.forward(request, response);

	}

	// ////////////////////////////////////VALIDASI
	// SESSION/////////////////////////

	public boolean sessionUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		return request.getSession().getAttribute("id") != null
				&& request.getSession().getAttribute("username") != null
				&& request.getSession().getAttribute("katasandi") != null;
	}

	// ////////////////////////////////////USER////////////////////////////////////

	private void tampilkanBeranda(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		String page = null;
		String username = request.getSession().getAttribute("username")
				.toString();
		if (!sessionUser(request, response)) {
			page = "lib.jsp";

		} else {
			request.setAttribute("id", dou.cariUser("username", username)
					.getId());
			request.setAttribute("nama", dou.cariUser("username", username)
					.getNama());
			page = "berandaUser.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	private void tampilkanFormEditUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// TODO Auto-generated method stub
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");

		} else {

			int id = Integer.parseInt(request.getParameter("iduser"));
			User u = dou.dapatkanUser(id);
			if (id != Integer.parseInt(request.getSession().getAttribute("id")
					.toString())) {
				response.sendRedirect("home");
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("registrasi.jsp");
				request.setAttribute("user", u);
				dispatcher.forward(request, response);
			}
		}
	}

	private void masuk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		if (sessionUser(request, response)) {
			response.sendRedirect("beranda");
		} else {
			RequestDispatcher dispatcher = null;
			if (!(request.getParameter("username") == null)
					&& !(request.getParameter("katasandi") == null)) {
				String username = request.getParameter("username");
				String katasandi = request.getParameter("katasandi");
				if (dou.masuk(username, katasandi)) {
					String nama = dou.cariUser("username", username).getNama();
					int id = dou.cariUser("username", username).getId();
					request.setAttribute("nama", nama);
					HttpSession session = request.getSession(true);
					session.setAttribute("id", id);
					session.setAttribute("username", username);
					session.setAttribute("katasandi", katasandi);

					response.sendRedirect("beranda");
				} else {
					String msg = "kata sandi atau username tidak cocok";
					request.setAttribute("gagalmasuk", msg);
					dispatcher = request
							.getRequestDispatcher("halamanmasuk.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				dispatcher = request.getRequestDispatcher("halamanmasuk.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

	private void redirectError(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("error.jsp");
		dispatcher.forward(request, response);
	}

	private void registrasiPengguna(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		if (sessionUser(request, response)) {
			response.sendRedirect("beranda");
		} else {
			if (!(request.getParameter("username") == null)
					&& !(request.getParameter("nama") == null)
					&& !(request.getParameter("katasandi") == null)) {

				User u = new User(0, request.getParameter("username"),
						request.getParameter("nama"),
						request.getParameter("katasandi"), 0);
				if (dou.tambahUser(u)) {
					response.sendRedirect("masuk");
				} else {
					response.sendRedirect("error");
				}

			}
		}

	}

	private void halamanRegistrasi(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {

		if (sessionUser(request, response)) {
			response.sendRedirect("beranda");
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("registrasi.jsp");
			dispatcher.forward(request, response);
		}
	}

	// /////////////////////CRUD BUKU/////////////////////////

	private void cariBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		String katakunci = request.getParameter("katakunci");
		String filter = request.getParameter("filter");

		List<Buku> daftarBuku = dob.dapatkanBuku(filter, katakunci);
		if (daftarBuku.size() != 0) {
			String json = new Gson().toJson(daftarBuku);
			System.out.println(json + daftarBuku.size());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else if (daftarBuku.size() == 0) {
			System.out.println("jumlah list" + daftarBuku.size());
			System.out.println("not found");
			response.setContentType("text/plain");
			response.getWriter().write("notfound");
		}

	}

	private void daftarBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		List<Buku> daftarBuku = dob.daftarBuku();
		request.setAttribute("DaftarBuku", daftarBuku);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("daftarbuku.jsp");
		dispatcher.forward(request, response);
	}

	private void hapusBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");
		} else {
			int id = Integer.parseInt(request.getParameter("idbuku"));
			Buku buku = new Buku();
			buku.setId(id);
			if (dob.hapusBuku(buku))
				response.sendRedirect("daftarBuku");
			else
				response.sendRedirect("error");
		}
	}

	private void editBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");

		} else {
			int id = Integer.parseInt(request.getParameter("idbuku"));
			String kodebuku = request.getParameter("kodebuku");
			String judul = request.getParameter("judul");
			String penulis = request.getParameter("penulis");
			String penerbit = request.getParameter("penerbit");
			int harga = Integer.parseInt(request.getParameter("harga"));
			int stok = Integer.parseInt(request.getParameter("stok"));

			Buku buku = new Buku(kodebuku, judul, penulis, penerbit, harga,
					stok);
			buku.setId(id);
			if (dob.editBuku(buku))
				response.sendRedirect("daftarBuku");
			else
				response.sendRedirect("error");
		}
	}

	private void tambahBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		// int id = Integer.parseInt(request.getParameter("idbuku"));
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");
		}
		String kodebuku = request.getParameter("kodebuku");
		String judul = request.getParameter("judul");
		String penulis = request.getParameter("penulis");
		String penerbit = request.getParameter("penerbit");
		int harga = Integer.parseInt(request.getParameter("harga"));
		int stok = Integer.parseInt(request.getParameter("stok"));

		Buku buku = new Buku(kodebuku, judul, penulis, penerbit, harga, stok);
		// buku.setId(id);
		if (dob.tambahBuku(buku))
			response.sendRedirect("daftarBuku");
		else
			response.sendRedirect("error");

	}

	private void tampilkanFormBaru(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("formulirbuku.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void tampilkanFormEditBuku(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		if (!sessionUser(request, response)) {
			response.sendRedirect("home");
		} else {
			int id = Integer.parseInt(request.getParameter("idbuku"));
			Buku b = dob.dapatkanBuku(id);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("formulirbuku.jsp");
			request.setAttribute("buku", b);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
