package com.fajar.Model;

public class User {

	private int id;
	private String username;
	private String nama;
	private String katasandi;
	private int admin;
	private String gambar;
	
	public User(){
		
	}
	
	public User(int id, String username, String nama, String katasandi, int admin) {
		super();
		this.id = id;
		this.username = username;
		this.nama = nama;
		this.katasandi = katasandi;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getKatasandi() {
		return katasandi;
	}
	public void setKatasandi(String katasandi) {
		this.katasandi = katasandi;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getGambar() {
		return gambar;
	}
	public void setGambar(String gambar) {
		this.gambar = gambar;
	}
	@Override
	public String toString() {
		return "Pengguna [id=" + id + ", username=" + username + ", nama="
				+ nama + ", admin=" + admin + ", gambar=" + gambar + "]";
	}
	
	
}
