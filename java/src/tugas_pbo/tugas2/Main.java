package tugas_pbo.tugas2;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi data sesuai soal
        double p = 10;
        double l = 5;
        double t = 4;

        // Membuat objek BangunRuang
        BangunRuang balok = new BangunRuang(p, l, t);

        // Menampilkan output
        System.out.println("=== Hasil Pengujian ===");
        System.out.println("Panjang    : " + p);
        System.out.println("Lebar      : " + l);
        System.out.println("Tinggi     : " + t);
        System.out.println("-----------------------");
        System.out.println("Luas Alas  : " + balok.hitungLuas());
        System.out.println("Volume     : " + balok.hitungVolume());
    }
}