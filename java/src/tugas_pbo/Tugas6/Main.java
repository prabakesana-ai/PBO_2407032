package Tugas6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Cafe myCafe = new Cafe("Polindra Cafe");

        // Membuat beberapa menu minuman maupun makanan
        myCafe.tambahMenu(new Makanan("Nasi Goreng", 15000, "Gurih"));
        myCafe.tambahMenu(new Makanan("Ayam Geprek", 12000, "Pedas"));
        myCafe.tambahMenu(new Minuman("Es Teh Manis", 5000, "Large"));
        myCafe.tambahMenu(new Minuman("Kopi Hitam", 7000, "Small"));

        boolean selesai = false;
        double totalBelanja = 0;

        // Proses simulasi wajib menggunakan input dari user
        while (!selesai) {
            myCafe.tampilkanDaftarMenu();
            System.out.print("\nPilih nomor menu (0 untuk bayar & selesai): ");
            
            // Validasi input menggunakan percabangan if (Poin 2i)
            if (input.hasNextInt()) {
                int pilihan = input.nextInt();

                if (pilihan == 0) {
                    selesai = true;
                } else {
                    Menu menuPilihan = myCafe.getMenu(pilihan - 1);
                    if (menuPilihan != null) {
                        totalBelanja += menuPilihan.getHarga(); // Menghitung total harga
                        System.out.println("Berhasil menambah: " + menuPilihan.getNama());
                    } else {
                        System.out.println("Menu tidak tersedia!");
                    }
                }
            } else {
                System.out.println("Harap masukkan angka yang valid!");
                input.next(); // Membersihkan input yang salah agar tidak infinite loop
            }
        }

        // Output akhir
        System.out.println("\n=== TOTAL PEMBAYARAN ===");
        System.out.println("Total Harga: Rp" + totalBelanja);
        System.out.println("Terima kasih sudah memesan!");
        
        input.close();
    }
}