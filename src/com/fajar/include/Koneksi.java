package com.fajar.include;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;


public class Koneksi {

	public static Connection connect() {
		Connection conn = null;
		// Statement stm = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/perpustkaan", "root",
					"fajaralm");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

}
