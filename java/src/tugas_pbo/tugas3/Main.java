package tugas_pbo.tugas3;

public class Main {
    public static void main(String[] args) {
        // Menggunakan satu tipe variabel yang sama (Polymorphism)
        BangunDatar bd;

        // Cetak Bangun Datar Dasar
        bd = new BangunDatar(3.0, 4.0);
        bd.tampilkan();
        System.out.println();

        // Cetak Persegi
        bd = new Persegi(5.0);
        bd.tampilkan();
        System.out.println();

        // Cetak Persegi Panjang
        bd = new PersegiPanjang(6.0, 4.0);
        bd.tampilkan();
        System.out.println();

        // Cetak Segitiga
        bd = new Segitiga(10.0, 5.0);
        bd.tampilkan();
    }
}