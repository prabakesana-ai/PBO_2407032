import 'BangunDatar.dart';
import 'Persegi.dart';
import 'PersegiPanjang.dart';
import 'Segitiga.dart';

void main() {
  // Menggunakan satu tipe variabel (BangunDatar) untuk berbagai objek
  List<BangunDatar> daftarBangun = [
    BangunDatar(3.0, 4.0),
    Persegi(5.0),
    PersegiPanjang(6.0, 2.0),
    Segitiga(4.0, 5.0)
  ];

  // Menampilkan semua isi list
  for (var bangun in daftarBangun) {
    bangun.tampilkan();
    print(""); // Memberi jarak antar output
  }
}