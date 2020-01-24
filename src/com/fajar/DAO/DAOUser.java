package com.fajar.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fajar.Model.User;
import com.fajar.include.Koneksi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DAOUser {

	Connection koneksi;
	ResultSet rs;
	PreparedStatement pst;
	Statement stm;

	public DAOUser() {

	}

	public boolean tambahUser(User u) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "INSERT INTO `perpustkaan`.`pengguna` (`nama`, `username`, `password`, `admin`) VALUES (?,?,?,?)";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);

		pst.setString(1, u.getNama());
		pst.setString(2, u.getUsername());
		pst.setString(3, u.getKatasandi());
		pst.setInt(4, u.getAdmin());

		boolean userTersimpan = pst.executeUpdate() > 0;
		pst.close();
		koneksi.close();
		return userTersimpan;
	}

	public boolean masuk(String username, String katasandi) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "SELECT COUNT(*) AS rowcount FROM pengguna WHERE username=? AND password=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, username);
		pst.setString(2, katasandi);
		rs = pst.executeQuery();
		rs.next();
		int count = rs.getInt("rowcount");
		boolean masuk = count  == 1;

		rs.close();
		pst.close();
		koneksi.close();
		return masuk;
	}

	public boolean hapusUser(User u) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "DELETE FROM pengguna WHERE idpengguna=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, u.getId());
		boolean dataTerhapus = pst.executeUpdate() > 0;
		pst.close();
		koneksi.close();
		return dataTerhapus;
	}

	public User dapatkanUser(int id) throws SQLException {
		koneksi = Koneksi.connect();
		User u = null;
		String sql = "SELECT * FROM pengguna WHERE idpengguna=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		while (rs.next()) {
			String username = rs.getString("username");
			String nama = rs.getString("nama");
			int admin = rs.getInt("admin");
			String katasandi = rs.getString("password");

			u = new User(id, username, nama, katasandi, admin);
		}

		rs.close();
		pst.close();
		koneksi.close();
		return u;
	}
	
	public User cariUser(String col, String nilai) throws SQLException{
		koneksi = Koneksi.connect();
		User u = null;
		String sql = "SELECT * FROM pengguna WHERE "+col+"='"+nilai+"' ";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		//pst.setInt(1, id);
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("idpengguna");
			String username = rs.getString("username");
			String nama = rs.getString("nama");
			int admin = rs.getInt("admin");
			String katasandi = rs.getString("password");

			u = new User(id, username, nama, katasandi, admin);
		}

		rs.close();
		pst.close();
		koneksi.close();
		return u;
	}

	public List<User> dapatkaSemuaUser() throws SQLException {

		koneksi = Koneksi.connect();
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM pengguna";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("idpengguna");
			String username = rs.getString("username");
			String nama = rs.getString("nama");
			int admin = rs.getInt("admin");
			String katasandi = rs.getString("password");

			User u = new User(id, username, nama, katasandi, admin);
			users.add(u);
		}

		rs.close();
		pst.close();
		koneksi.close();

		return users;
	}

	public boolean perbaruiUser(User u) throws SQLException {
		koneksi = Koneksi.connect();
		String sql = "UPDATE `perpustkaan`.`pengguna` SET `nama`=?, `password`=? WHERE `idpengguna`=?";
		pst = (PreparedStatement) koneksi.prepareStatement(sql);
		pst.setString(1, u.getNama());
		pst.setString(2, u.getKatasandi());
		pst.setInt(3, u.getId());

		boolean dataDiperbarui = pst.executeUpdate() > 0;

		pst.close();
		koneksi.close();
		return dataDiperbarui;

	}

}
