package tugas_pbo.tugas2;

public class Main {
    public static void main(String[] args) {
        double p = 10;
        double l = 5;
        double t = 4;

        BangunDatar balok = new BangunDatar(p, l);
        BangunRuang kapu = new BangunRuang(p, l, t);

        System.out.println("=== Hasil Pengujian ===");
        System.out.println("Panjang    : " + p);
        System.out.println("Lebar      : " + l);
        System.out.println("Tinggi     : " + t);
        System.out.println("-----------------------");
        System.out.println("Luas Alas parent  : " + balok.hitungLuas());
        System.out.println("Luas Alas child  : " + kapu.hitungLuas());
        System.out.println("Volume parent : " + kapu.hitungVolume());
        System.out.println("Volume child : " + kapu.hitungVolume2());
    }
}