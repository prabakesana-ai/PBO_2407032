package latihan_pbo.latihan3;

class Cafe {
    public String nama;
    private String alamat;
    protected int jmlMenu;

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }
}
public class main {
   public static void main(String[] args){
    Cafe cafe1 = new Cafe();
    cafe1.nama = "Halaman";
    cafe1.setAlamat("Indramayu");
    System.out.println(cafe1.nama);
    System.out.println(cafe1.getAlamat());
   }
}
