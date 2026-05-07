package Tugas6;

public class Minuman extends Menu {
    private String ukuran; // Atribut tambahan

    public Minuman(String nama, double harga, String ukuran) {
        super(nama, harga);
        this.ukuran = ukuran;
    }

    // Override method dari super class
    @Override
    public void detailMenu() {
        System.out.println("[Minuman] " + getNama() + " [" + ukuran + "] - Rp" + getHarga());
    }
}