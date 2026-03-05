package latihan_pbo.latihan4;

public class Coba {
    protected double panjang;
    protected double lebar;
    protected double sisi;

    public double getPanjang(){
        return panjang;
    }

    public double getLebar(){
        return lebar;
    }

    public void setPanjang(double panjang){
        this.panjang = panjang;
    }

    public void setLebar(double lebar){
        this.lebar=lebar;
    }

    public void cetakLuas(){
        System.out.println("Luas = " + panjang * lebar);
    }
}



