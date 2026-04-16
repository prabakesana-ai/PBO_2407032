package tugas_pbo.tugas3;

public class Segitiga extends BangunDatar {

    public Segitiga(double alas, double tinggi) {
        super(alas, tinggi);
    }

    @Override
    public double hitungLuas() {
        return 0.5 * getVarA() * getVarB();
    }

    @Override
    public void tampilkan() {
        System.out.println("Segitiga");
        System.out.println("======================");
        System.out.println("Alas   : " + getVarA());
        System.out.println("Tinggi : " + getVarB());
        System.out.println("Luas   : " + hitungLuas());
        System.out.println("======================");
    }
}
