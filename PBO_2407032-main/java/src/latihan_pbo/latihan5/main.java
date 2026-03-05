package latihan_pbo.latihan5;

import tugas_pbo.tugas1.jajargenjang;
import tugas_pbo.tugas1.lingkaran;
import tugas_pbo.tugas1.persegi;
import tugas_pbo.tugas1.persegipanjang;
import tugas_pbo.tugas1.segitiga;

public class main {
    public static void main(String[] args) {
        segitiga s1 = new segitiga();
        s1.setAlas(10);
        s1.setTinggi(50);
        System.out.println("Luas Segitiga = " + s1.LuasSegitiga());

        persegi p1 = new persegi();
        p1.setSisi(10);
        System.out.println("Luas Persegi = " + p1.LuasPersegi());

        persegipanjang pj1 = new persegipanjang();
        pj1.setPanjang(10);
        pj1.setLebar(50);
        System.out.println("Luas Persegi Panjang  = " + pj1.LuasPersegiPanjang());

        jajargenjang jg1 = new jajargenjang();
        jg1.setAlas(10);
        jg1.setTinggi(100);
        System.out.println("Luas jajar genjang = " + jg1.LuasJajarGenjang());

        lingkaran lk = new lingkaran();
        lk.setJarijari(10);
        System.out.println("Luas lingkaran = " + lk.luaslingkaran());
    }
}
