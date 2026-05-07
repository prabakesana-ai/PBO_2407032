package latihan_pbo.abstrak;

public abstract class BangunDatar {
    private String nama;

    public BangunDatar (String nama){
        this.nama = nama;
    }

    abstract double hitungLuas();

    public void Display(){
        System.out.println("ini adalah" + nama);
    }
}
