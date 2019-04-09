package com.example.android.tel_unewsportal;

public class Modelberita {
    String judul;
    int gambar;
    String berita;

    public Modelberita(String judul, int gambar, String berita) {
        this.judul = judul;
        this.gambar = gambar;
        this.berita = berita;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getBerita() {
        return berita;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }
}
