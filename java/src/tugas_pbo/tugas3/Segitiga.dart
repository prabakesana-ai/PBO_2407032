import 'BangunDatar.dart';

class Segitiga extends BangunDatar {
  Segitiga(double alas, double tinggi) : super(alas, tinggi);

  @override
  double hitungLuas() {
    return 0.5 * varA * varB;
  }

  @override
  void tampilkan() {
    print("Segitiga");
    print("==========================");
    print("Alas   : $varA");
    print("Tinggi : $varB");
    print("Luas   : ${hitungLuas()}");
    print("==========================");
  }
}