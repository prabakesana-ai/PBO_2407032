package tugas_pbo.tugas2;

public class BangunRuang extends BangunDatar {
    private double tinggi;

    public BangunRuang(double panjang, double lebar, double tinggi) {
        super(panjang, lebar);
        this.tinggi = tinggi;
    }

    @Override
    public double hitungLuas(){
        return panjang * lebar;
    }

    public double hitungVolume() {
        return super.hitungLuas() * tinggi;
    }
    
    public double hitungVolume2() {
        return hitungLuas() * tinggi;
    }
}