import 'BangunDatar.dart';

class Persegi extends BangunDatar {
  // Menggunakan constructor 1 parameter dari superclass
  Persegi(double sisi) : super.satuParameter(sisi);

  @override
  double hitungLuas() {
    return varA * varA;
  }

  @override
  void tampilkan() {
    print("Persegi");
    print("==========================");
    print("Sisi: $varA");
    print("Luas: ${hitungLuas()}");
    print("==========================");
  }
}