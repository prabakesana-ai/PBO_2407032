package tugas_pbo.tugas1;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== PILIH BANGUN DATAR ===");
        System.out.println("1. Segitiga");
        System.out.println("2. Persegi");
        System.out.println("3. Persegi Panjang");
        System.out.println("4. Jajar Genjang");
        System.out.println("5. Lingkaran");
        System.out.print("Masukkan pilihan (1-5): ");
        int pilih = input.nextInt();

        switch (pilih) {
            case 1:
                segitiga s1 = new segitiga();
                System.out.print("Masukkan alas: ");
                s1.setAlas(input.nextDouble());
                System.out.print("Masukkan tinggi: ");
                s1.setTinggi(input.nextDouble());
                System.out.println("Luas Segitiga = " + s1.LuasSegitiga());
                break;

            case 2:
                persegi p1 = new persegi();
                System.out.print("Masukkan sisi: ");
                p1.setSisi(input.nextDouble());
                System.out.println("Luas Persegi = " + p1.LuasPersegi());
                break;

            case 3:
                persegipanjang pj1 = new persegipanjang();
                System.out.print("Masukkan panjang: ");
                pj1.setPanjang(input.nextDouble());
                System.out.print("Masukkan lebar: ");
                pj1.setLebar(input.nextDouble());
                System.out.println("Luas Persegi Panjang = " + pj1.LuasPersegiPanjang());
                break;

            case 4:
                jajargenjang jg1 = new jajargenjang();
                System.out.print("Masukkan alas: ");
                jg1.setAlas(input.nextDouble());
                System.out.print("Masukkan tinggi: ");
                jg1.setTinggi(input.nextDouble());
                System.out.println("Luas Jajar Genjang = " + jg1.LuasJajarGenjang());
                break;

            case 5:
                lingkaran lk = new lingkaran();
                System.out.print("Masukkan jari-jari: ");
                lk.setJarijari(input.nextDouble());
                System.out.println("Luas Lingkaran = " + lk.luaslingkaran());
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }

        input.close();
    }
}