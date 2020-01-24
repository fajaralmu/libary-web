package com.fajar.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fajar.Model.Buku;
import com.fajar.Model.Post;
import com.fajar.include.Koneksi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DAOPost {

	Connection koneksi;
	ResultSet rs;
	PreparedStatement pst;
	Statement stm;

	public DAOPost(){
		
	}
	
	public boolean tambahPost(Post p) throws SQLException{
		koneksi = Koneksi.connect();
		String sql = "INSERT INTO `perpustkaan`.`post` (`judulpost`, `konten`, `idkategori`, `katakunci`, `gambar`, `idpengguna`, `tanggal`,  `tipe`) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, p.getJudul());
		pst.setString(2, p.getKonten());
		pst.setInt(3, p.getIdkategori());
		pst.setString(4, p.getKatakunci());
		pst.setString(5, p.getGambar());
		pst.setInt(6, p.getIdpengguna());
		pst.setString(7, p.getDate());
		pst.setInt(8, p.getTipe());
		
		boolean datamasuk = pst.executeUpdate() > 0;
		pst.close();
		koneksi.close();
		return datamasuk;
		
	}
	
	public boolean editPost(Post p)  throws SQLException{
		koneksi = Koneksi.connect();
		String sql = "UPDATE `perpustkaan`.`post` SET  `judulpost`=?, `konten`=?, `idkategori`=?, `katakunci`=?, `gambar`=?, `idpengguna`=?, `tanggal`=?, `tipe`=? WHERE `idpost`=?";

		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, p.getJudul());
		pst.setString(2, p.getKonten());
		pst.setInt(3, p.getIdkategori());
		pst.setString(4, p.getKatakunci());
		pst.setString(5, p.getGambar());
		pst.setInt(6, p.getIdpengguna());
		pst.setString(7, p.getDate());
		pst.setInt(8, p.getTipe());
		pst.setInt(9, p.getIdpost());

		boolean dataDiperbarui = pst.executeUpdate() > 0;

		pst.close();
		koneksi.close();
		return dataDiperbarui;
		
	}
	
	public List<Post> daftarPost() throws SQLException {
		koneksi = Koneksi.connect();
		List<Post> daftarPost = new ArrayList<>();
		String sql = "SELECT * FROM post ORDER BY idpost DESC";
		stm = (Statement) koneksi.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("idpost");
			String judul = rs.getString("judulpost");
			String konten = rs.getString("konten");
			int idkategori = rs.getInt("idkategori");
			int idpengguna = rs.getInt("idpengguna");
			String date = rs.getString("tanggal");
			String katakunci = rs.getString("katakunci");
			int tipe = rs.getInt("tipe");
			
			Post p = new Post(judul, konten, idkategori, idpengguna, date, katakunci, tipe);
			p.setIdpost(id);
			daftarPost.add(p);
		}
		rs.close();
		stm.close();
		koneksi.close();
		return daftarPost;
	}
	
	public Post dapatkanPost(int id) throws SQLException {
		koneksi = Koneksi.connect();
		Post p = null;
		String sql = "SELECT * FROM post WHERE idpost=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		while (rs.next()) {
			
			String judul = rs.getString("judulpost");
			String konten = rs.getString("konten");
			int idkategori = rs.getInt("idkategori");
			int idpengguna = rs.getInt("idpengguna");
			String date = rs.getString("tanggal");
			String katakunci = rs.getString("katakunci");
			int tipe = rs.getInt("tipe");
			
			p = new Post(judul, konten, idkategori, idpengguna, date, katakunci, tipe);
			p.setIdpost(id);
			
		}
		rs.close();
		pst.close();
		koneksi.close();
		return p;
	}
	
	public boolean hapusPost(Post p) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "DELETE FROM post WHERE idpost=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, p.getIdpost());
		boolean dataTerhapus = pst.executeUpdate() > 0;
		pst.close();
		koneksi.close();
		return dataTerhapus;
	}
	
	public List<Post> cariPost(String col, String nilai) throws SQLException {
		List<Post> daftarPost= new ArrayList<>();
		koneksi = Koneksi.connect();
		
		String sql = "SELECT * FROM post WHERE "+col+" LIKE '%"+nilai+"%'";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("idpost");
			String judul = rs.getString("judulpost");
			String konten = rs.getString("konten");
			int idkategori = rs.getInt("idkategori");
			int idpengguna = rs.getInt("idpengguna");
			String date = rs.getString("tanggal");
			String katakunci = rs.getString("katakunci");
			int tipe = rs.getInt("tipe");
			
			Post p = new Post(judul, konten, idkategori, idpengguna, date, katakunci, tipe);
			p.setIdpost(id);
			daftarPost.add(p);
		}
		rs.close();
		pst.close();
		koneksi.close();
		return daftarPost;
	}
	
	public List<Post> dapatkanPost(String col, String nilai) throws SQLException {
		List<Post> daftarPost= new ArrayList<>();
		koneksi = Koneksi.connect();
		
		String sql = "SELECT * FROM post WHERE "+col+" = '"+nilai+"' ORDER BY idpost DESC";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("idpost");
			String judul = rs.getString("judulpost");
			String konten = rs.getString("konten");
			int idkategori = rs.getInt("idkategori");
			int idpengguna = rs.getInt("idpengguna");
			String date = rs.getString("tanggal");
			String katakunci = rs.getString("katakunci");
			int tipe = rs.getInt("tipe");
			
			Post p = new Post(judul, konten, idkategori, idpengguna, date, katakunci, tipe);
			p.setIdpost(id);
			daftarPost.add(p);
		}
		rs.close();
		pst.close();
		koneksi.close();
		return daftarPost;
	}
	
	public int jumlahPost() throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "SELECT COUNT(*) AS rowcount FROM post";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		rs = pst.executeQuery();
		rs.next();
		int count = rs.getInt("rowcount");
		rs.close();
		pst.close();
		koneksi.close();
		return count;
	}
	
}
