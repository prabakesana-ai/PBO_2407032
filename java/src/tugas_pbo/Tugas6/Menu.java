package Tugas6;

public class Menu {
    private String nama;
    private double harga;

    // Overloading Constructor
    public Menu() {}

    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Enkapsulasi: Semua atribut private
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    // Method detail menu (Poin 2a)
    public void detailMenu() {
        System.out.println("Menu: " + nama + " | Harga: Rp" + harga);
    }
}