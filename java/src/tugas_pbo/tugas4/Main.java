package tugas_pbo.tugas4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Menu> masterMenu = Menu.getDaftarMenu(); // Ambil menu dari file Menu
        List<MenuItem> pesanan = new ArrayList<>();
        
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\n=== DAFTAR MENU RESTORAN ===");
            for (int i = 0; i < masterMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
                masterMenu.get(i).tampilkanMenu();
            }
            System.out.println("0. Selesai & Hitung Total");
            
            System.out.print("Pilih nomor menu: ");
            int pilihan = input.nextInt();

            if (pilihan > 0 && pilihan <= masterMenu.size()) {
                Menu menuTerpilih = masterMenu.get(pilihan - 1);
                
                System.out.print("Masukkan jumlah untuk " + menuTerpilih.getNama() + ": ");
                int jml = input.nextInt();
                
                // Simpan ke dalam list pesanan menggunakan class MenuItem
                pesanan.add(new MenuItem(menuTerpilih.getNama(), menuTerpilih.getHarga(), jml));
                System.out.println("Berhasil menambahkan ke pesanan.");
            } else if (pilihan == 0) {
                lanjut = false;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        // Tampilkan Ringkasan dan Total
        System.out.println("\n========== STRUK PEMBAYARAN ==========");
        double totalAkhir = 0;
        for (MenuItem m : pesanan) {
            m.tampilkanMenu();
            totalAkhir += m.hitungTotal();
        }
        System.out.println("--------------------------------------");
        System.out.println("TOTAL KESELURUHAN: Rp" + totalAkhir);
        System.out.println("======================================");
        
        input.close();
    }
}