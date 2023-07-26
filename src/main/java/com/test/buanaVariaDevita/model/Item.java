package com.test.buanaVariaDevita.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item implements Serializable {
	@Id
    private String id;
    private String nama;
    private String deskripsi;
    private BigDecimal harga;
    private Double stok;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public BigDecimal getHarga() {
		return harga;
	}
	public void setHarga(BigDecimal harga) {
		this.harga = harga;
	}
	public Double getStok() {
		return stok;
	}
	public void setStok(Double stok) {
		this.stok = stok;
	}
    
}
