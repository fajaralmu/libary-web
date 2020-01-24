package com.fajar.Model;

import org.jsoup.Jsoup;

public class Post {

	private int idpost;
	private String judul;
	private String konten;
	private int idkategori;
	private int idpengguna;
	private String date;
	private String gambar;
	private String katakunci;
	private int tipe;
	private String kontenfreehtml;
	
	
	public Post(){
		
	}
	
	
	public Post(String judul, String konten, int idkategori,
			int idpengguna, String date, String katakunci, int tipe) {
		super();
		
		this.judul = judul;
		this.konten = konten;
		this.idkategori = idkategori;
		this.idpengguna = idpengguna;
		this.date = date;
		this.katakunci = katakunci;
		this.tipe = tipe;
		kontenfreehtml = Jsoup.parse(this.konten).text();
	}
	
	
	public String getKontenfreehtml() {
		return kontenfreehtml;
	}


	public void setKontenfreehtml(String kontenfreehtml) {
		this.kontenfreehtml = kontenfreehtml;
	}


	public int getIdpost() {
		return idpost;
	}
	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getKonten() {
		return konten;
	}
	public void setKonten(String konten) {
		this.konten = konten;
	}
	public int getIdkategori() {
		return idkategori;
	}
	public void setIdkategori(int idkategori) {
		this.idkategori = idkategori;
	}
	public int getIdpengguna() {
		return idpengguna;
	}
	public void setIdpengguna(int idpengguna) {
		this.idpengguna = idpengguna;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGambar() {
		return gambar;
	}
	public void setGambar(String gambar) {
		this.gambar = gambar;
	}
	public String getKatakunci() {
		return katakunci;
	}
	public void setKatakunci(String katakunci) {
		this.katakunci = katakunci;
	}
	public int getTipe() {
		return tipe;
	}
	public void setTipe(int tipe) {
		this.tipe = tipe;
	}
	@Override
	public String toString() {
		return "Post [idpost=" + idpost + ", judul=" + judul + ", konten="
				+ konten + ", idkategori=" + idkategori + ", idpengguna="
				+ idpengguna + ", date=" + date + ", gambar=" + gambar
				+ ", katakunci=" + katakunci + ", tipe=" + tipe + "]";
	}
	
	
	
}
