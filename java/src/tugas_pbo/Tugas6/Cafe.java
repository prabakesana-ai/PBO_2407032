package Tugas6;

import java.util.ArrayList;

public class Cafe {
    private String namaCafe;
    // Memanfaatkan polymorphism untuk menyimpan object
    private ArrayList<Menu> daftarMenu = new ArrayList<>(); 

    public Cafe(String namaCafe) {
        this.namaCafe = namaCafe;
    }

    public void tambahMenu(Menu menu) {
        daftarMenu.add(menu);
    }

    public void tampilkanDaftarMenu() {
        System.out.println("\n=== Selamat Datang di " + namaCafe + " ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).detailMenu(); // Polymorphism memanggil method yang sama (Poin 2f)
        }
    }

    public Menu getMenu(int index) {
        if (index >= 0 && index < daftarMenu.size()) {
            return daftarMenu.get(index);
        }
        return null;
    }
}