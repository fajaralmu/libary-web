package com.fajar.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fajar.Model.Buku;
import com.fajar.include.Koneksi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DAOBuku {

	Connection koneksi;
	ResultSet rs;
	PreparedStatement pst;
	Statement stm;

	public boolean adabuku = false;
	
	
	public DAOBuku() {

	}

	public boolean tambahBuku(Buku buku) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "INSERT INTO `perpustkaan`.`buku` ( `kodebuku`, `judul`, `stok`, `harga`, `penulis`, `penerbit`) VALUES (?,?,?,?,?,?)";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, buku.getKode());
		pst.setString(2, buku.getJudul());
		pst.setInt(3, buku.getStok());
		pst.setInt(4, buku.getHarga());
		pst.setString(5, buku.getPenulis());
		pst.setString(6, buku.getPenerbit());

		boolean datamasuk = pst.executeUpdate() > 0;
		pst.close();

		koneksi.close();
		return datamasuk;
	}

	public List<Buku> daftarBuku() throws SQLException {
		koneksi = Koneksi.connect();
		List<Buku> daftarBuku = new ArrayList<>();
		String sql = "SELECT * FROM buku";
		stm = (Statement) koneksi.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("idbuku");
			String kode = rs.getString("kodebuku");
			String judul = rs.getString("judul");
			int harga = rs.getInt("harga");
			int stok = rs.getInt("stok");
			String penulis = rs.getString("penulis");
			String penerbit = rs.getString("penerbit");

			Buku buku = new Buku(kode, judul, penulis, penerbit, harga, stok);
			buku.setId(id);
			daftarBuku.add(buku);
		}
		rs.close();
		stm.close();
		koneksi.close();
		return daftarBuku;
	}

	public boolean hapusBuku(Buku buku) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "DELETE FROM buku WHERE idbuku=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, buku.getId());
		boolean dataTerhapus = pst.executeUpdate() > 0;
		pst.close();
		koneksi.close();
		return dataTerhapus;
	}

	public boolean editBuku(Buku buku) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "UPDATE `perpustkaan`.`buku` SET `kodebuku`=?, `judul`=?, `stok`=?, `harga`=?, `penulis`=?, `penerbit`=? WHERE `idbuku`=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, buku.getKode());
		pst.setString(2, buku.getJudul());
		pst.setInt(3, buku.getStok());
		pst.setInt(4, buku.getHarga());
		pst.setString(5, buku.getPenulis());
		pst.setString(6, buku.getPenerbit());
		pst.setInt(7, buku.getId());

		boolean dataDiperbarui = pst.executeUpdate() > 0;

		pst.close();
		koneksi.close();
		return dataDiperbarui;
	}

	public Buku dapatkanBuku(int id) throws SQLException {

		koneksi = Koneksi.connect();
		Buku buku = null;
		String sql = "SELECT * FROM buku where idbuku=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		while (rs.next()) {

			String kode = rs.getString("kodebuku");
			String judul = rs.getString("judul");
			int harga = rs.getInt("harga");
			int stok = rs.getInt("stok");
			String penulis = rs.getString("penulis");
			String penerbit = rs.getString("penerbit");

			buku = new Buku(kode, judul, penulis, penerbit, harga, stok);
			buku.setId(id);

		}
		rs.close();
		pst.close();
		koneksi.close();
		return buku;
	}
	
	public List <Buku> dapatkanBuku(String col, String nilai) throws SQLException {

		koneksi = Koneksi.connect();
		List<Buku> daftarBuku = new ArrayList<>();
		String sql = "SELECT * FROM buku where "+col+" LIKE '%"+nilai+"%'";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		System.out.println(sql);
		rs = pst.executeQuery();
		while (rs.next()) {

			int id = rs.getInt("idbuku");
			String kode = rs.getString("kodebuku");
			String judul = rs.getString("judul");
			int harga = rs.getInt("harga");
			int stok = rs.getInt("stok");
			String penulis = rs.getString("penulis");
			String penerbit = rs.getString("penerbit");

			Buku buku = new Buku(kode, judul, penulis, penerbit, harga, stok);
			
			buku.setId(id);
			
			daftarBuku.add(buku);

		}
		rs.close();
		pst.close();
		koneksi.close();
		return daftarBuku;
	}
}
