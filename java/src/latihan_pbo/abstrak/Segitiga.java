package latihan_pbo.abstrak;

public class Segitiga extends BangunDatar{
    private double alas, tinggi;

    public Segitiga(double alas, double tinggi){
        super("Segitiga");
        this.alas = alas;
        this.tinggi = tinggi;
    }

    @Override
    double hitungLuas(){
        return 0.5 * alas * tinggi;
    }
}
