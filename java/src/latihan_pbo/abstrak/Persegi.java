package latihan_pbo.abstrak;

public class Persegi extends BangunDatar{
    private double sisi;

    public Persegi (double sisi){
        super("Persegi");
        this.sisi = sisi;
    }

    @Override
    double hitungLuas(){
        return sisi * sisi;
    }
}
