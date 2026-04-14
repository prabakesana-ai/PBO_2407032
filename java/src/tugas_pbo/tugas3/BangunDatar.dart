class BangunDatar {
  double _varA;
  double _varB;

  // Constructor dengan 2 parameter
  BangunDatar(this._varA, this._varB);

  // Constructor dengan 1 parameter (Named Constructor)
  BangunDatar.satuParameter(double variabel) 
      : _varA = variabel, _varB = 0;

  // Getter untuk akses variabel (Encapsulation)
  double get varA => _varA;
  double get varB => _varB;

  double hitungLuas() {
    return 0;
  }

  void tampilkan() {
    print("Bangun Datar");
    print("==========================");
    print("varA: $_varA");
    print("varB: $_varB");
    print("==========================");
  }
}