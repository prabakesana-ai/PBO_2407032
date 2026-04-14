import 'BangunDatar.dart';

class PersegiPanjang extends BangunDatar {
  PersegiPanjang(double panjang, double lebar) : super(panjang, lebar);

  @override
  double hitungLuas() {
    return varA * varB;
  }

  @override
  void tampilkan() {
    print("Persegi Panjang");
    print("==========================");
    print("Panjang: $varA");
    print("Lebar  : $varB");
    print("Luas   : ${hitungLuas()}");
    print("==========================");
  }
}