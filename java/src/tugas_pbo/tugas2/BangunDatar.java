package tugas_pbo.tugas2;

public class BangunDatar {
    // Enkapsulasi: atribut privat
    private double panjang;
    private double lebar;

    // Constructor
    public BangunDatar(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    // Method untuk menghitung luas
    public double hitungLuas() {
        return panjang * lebar;
    }

    // Getter (diperlukan agar anak class bisa mengakses nilai jika diperlukan)
    public double getPanjang() {
        return panjang;
    }

    public double getLebar() {
        return lebar;
    }
}