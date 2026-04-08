package tugas_pbo.tugas2;

public class BangunRuang extends BangunDatar {
    private double tinggi;

    // Constructor
    public BangunRuang(double panjang, double lebar, double tinggi) {
        // Memanggil constructor dari class BangunDatar
        super(panjang, lebar);
        this.tinggi = tinggi;
    }

    // Method untuk menghitung volume
    public double hitungVolume() {
        // Menggunakan kembali method hitungLuas dari class induk
        return hitungLuas() * tinggi;
    }
}
