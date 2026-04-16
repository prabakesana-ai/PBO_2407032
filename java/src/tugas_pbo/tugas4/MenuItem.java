package tugas_pbo.tugas4;

public class MenuItem extends Menu {
    private int jumlah;

    public MenuItem(String nama, double harga, int jumlah) {
        super(nama, harga);
        this.jumlah = jumlah;
    }

    @Override
    public double hitungTotal() {
        return getHarga() * jumlah;
    }

    @Override
    public void tampilkanMenu() {
        System.out.println(getNama() + " x" + jumlah + " = Rp" + hitungTotal());
    }
}