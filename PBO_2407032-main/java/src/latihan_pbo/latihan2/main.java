package latihan_pbo.latihan2;

class Cafe{
    String nama;
    String alamat;

    Cafe(String nama, String alamat){
        this.nama = nama;
        this.alamat = alamat;
    }

    void buka(){
        System.out.println(nama + "sudah buka di " + alamat);
    }
}
public class main {
    public static void main(String[] args) {
        Cafe cafe1 = new Cafe("Halaman", "Indramayu");
        cafe1.buka();
        Cafe cafe2 = new Cafe("Obaka", "Indramayu");
        cafe2.tutup();
        Cafe cafe3 = new Cafe("Tomoro", "Indramayu");
        cafe3.buka();
    }
}
