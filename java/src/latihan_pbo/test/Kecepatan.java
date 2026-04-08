package latihan_pbo.test;

import java.util.Scanner;

public class Kecepatan {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jarak dalam meter: ");
        double jarak = input.nextDouble();

        System.out.print("Jam masukan: ");
        int jam = input.nextInt();

        System.out.print("Menit masukan: ");
        int menit = input.nextInt();

        System.out.print("Detik masukan: ");
        int detik = input.nextInt();

        int totalDetik = jam * 3600 + menit * 60 + detik;

        double meterPerDetik = jarak / totalDetik;
        double kmPerJam = meterPerDetik * 3.6;
        double milPerJam = meterPerDetik * 3600 / 1609;

        System.out.printf("Kecepatan anda dalam meter/detik adalah %.8f\n", meterPerDetik);
        System.out.printf("Kecepatan anda dalam km/jam adalah %.8f\n", kmPerJam);
        System.out.printf("Kecepatan anda dalam mil/jam adalah %.8f\n", milPerJam);

        input.close();
    }
}