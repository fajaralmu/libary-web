package com.fajar.Model;

public class Buku {
	
	private int id;
	private String kode;
	private String judul;
	private String penulis;
	private String penerbit;
	private int harga;
	private int stok;
	
	public Buku(){
		
	}
	
	public Buku(String kode, String judul, String penulis,
			String penerbit, int harga, int stok) {
		super();
		this.kode = kode;
		this.judul = judul;
		this.penulis = penulis;
		this.penerbit = penerbit;
		this.harga = harga;
		this.stok = stok;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getPenulis() {
		return penulis;
	}
	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}
	public String getPenerbit() {
		return penerbit;
	}
	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}

	@Override
	public String toString() {
		return "Buku [id=" + id + ", kode=" + kode + ", judul=" + judul
				+ ", penulis=" + penulis + ", penerbit=" + penerbit
				+ ", harga=" + harga + ", stok=" + stok + "]";
	}
	

}
