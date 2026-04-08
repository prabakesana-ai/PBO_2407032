package tugas_pbo.tugas1;

public class bangundatar {
    private double panjang;
    private double lebar;
    private double alas;
    private double tinggi;
    private double sisi;
    private double jarijari;
    private double phi;

    public bangundatar(){
        this.phi = 3.14;
    }

    public void setPanjang(double panjang){
        this.panjang = panjang;
    }
    public void setLebar(double lebar){
        this.lebar = lebar;
    }
    public void setAlas(double alas){
        this.alas = alas;
    }
    public void setTinggi(double tinggi){
        this.tinggi = tinggi;
    }
    public void setSisi(double sisi){
        this.sisi = sisi;
    }

    public void setJarijari(double jarijari) {
        this.jarijari = jarijari;
    }
    public double getLebar() {
        return lebar;
    }
    public double getAlas() {
        return alas;
    }
    public double getTinggi() {
        return tinggi;
    }
    public double getSisi() {
        return sisi;
    }
    public double getPanjang(){
        return panjang;
    }
    public double getPhi() {
        return phi;
    }
    public double getJarijari() {
        return jarijari;
    }
    
}
