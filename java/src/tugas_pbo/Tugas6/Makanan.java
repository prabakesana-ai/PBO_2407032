package Tugas6;

public class Makanan extends Menu {
    private String jenis; // Atribut tambahan

    public Makanan(String nama, double harga, String jenis) {
        super(nama, harga);
        this.jenis = jenis;
    }

    // Override method dari super class
    @Override
    public void detailMenu() {
        System.out.println("[Makanan] " + getNama() + " (" + jenis + ") - Rp" + getHarga());
    }
}