package tugas_pbo.tugas4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Menu> masterMenu = Menu.getDaftarMenu(); 
        List<MenuItem> pesanan = new ArrayList<>();
        
        double runningTotal = 0; // Variabel untuk menyimpan akumulasi total sementara
        boolean lanjut = true;

        System.out.println("=== SISTEM PEMESANAN KANTIN ===");

        while (lanjut) {
            System.out.println("\n--- DAFTAR MENU ---");
            for (int i = 0; i < masterMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
                masterMenu.get(i).tampilkanMenu();
            }
            System.out.println("0. Selesai & Cetak Struk");
            
            System.out.print("\nPilih nomor menu: ");
            int pilihan = input.nextInt();

            if (pilihan > 0 && pilihan <= masterMenu.size()) {
                Menu menuTerpilih = masterMenu.get(pilihan - 1);
                
                System.out.print("Masukkan jumlah pesanan: ");
                int jml = input.nextInt();
                
                // Membuat objek MenuItem untuk menghitung subtotal
                MenuItem itemBaru = new MenuItem(menuTerpilih.getNama(), menuTerpilih.getHarga(), jml);
                pesanan.add(itemBaru);
                
                // Hitung subtotal item ini
                double subTotal = itemBaru.hitungTotal();
                runningTotal += subTotal; // Tambahkan ke total keseluruhan

                // Menampilkan kisaran harga total sementara
                System.out.println("------------------------------------");
                System.out.println("> Subtotal " + menuTerpilih.getNama() + ": Rp" + subTotal);
                System.out.println("> Total belanja saat ini: Rp" + runningTotal);
                System.out.println("------------------------------------");

            } else if (pilihan == 0) {
                lanjut = false;
            } else {
                System.out.println("Pilihan tidak tersedia, silakan coba lagi.");
            }
        }

        // Cetak Struk Akhir
        if (!pesanan.isEmpty()) {
            System.out.println("\n========== STRUK PEMBAYARAN ==========");
            for (MenuItem m : pesanan) {
                m.tampilkanMenu();
            }
            System.out.println("--------------------------------------");
            System.out.println("TOTAL AKHIR: Rp" + runningTotal);
            System.out.println("======================================");
        } else {
            System.out.println("Tidak ada pesanan yang dibuat.");
        }
        
        input.close();
    }
}