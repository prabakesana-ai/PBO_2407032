package tugas_pbo.tugas4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String nama;
    private double harga;

    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Encapsulation
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    // Method untuk menampilkan menu tunggal
    public void tampilkanMenu() {
        System.out.println(nama + " - Rp" + harga);
    }

    // Method hitung total default
    public double hitungTotal() {
        return harga;
    }

    // Data Master Menu (Statis) untuk dipilih nanti
    public static List<Menu> getDaftarMenu() {
        List<Menu> daftar = new ArrayList<>();
        daftar.add(new Menu("Nasi Goreng Special", 20000));
        daftar.add(new Menu("Mie Ayam Bakso", 15000));
        daftar.add(new Menu("Es Teh Manis", 5000));
        daftar.add(new Menu("Ayam Penyet", 18000));
        return daftar;
    }
}