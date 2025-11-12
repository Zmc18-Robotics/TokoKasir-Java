# Quick Reference Guide - Sistem Kasir Toko

Panduan cepat untuk penggunaan sehari-hari aplikasi Sistem Kasir Toko.

---

## 🚀 Startup Cepat

### Windows
```bash
cd TokoKasir
build.bat
```

### Linux/Mac
```bash
cd TokoKasir
chmod +x build.sh
./build.sh
```

### Manual (Semua OS)
```bash
javac -d bin src/*.java
java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main  (Windows)
java -cp bin:lib/sqlite-jdbc-3.44.0.0.jar Main  (Linux/Mac)
```

---

## 🔐 Login Default

```
Username: admin
Password: admin123
Role: ADMIN
```

---

## 👨‍💼 Kasir - Transaksi Penjualan

### Langkah-Langkah:
1. Login dengan username kasir
2. Klik "Transaksi Penjualan"
3. Pilih produk → Masukkan jumlah → Klik "Tambah ke Keranjang"
4. Ulangi untuk produk lain
5. Klik "Cetak Struk"
6. Transaksi selesai ✓

### Shortcut:
- **Lihat Stok**: Menu "Lihat Stok" (read-only)
- **Batal Transaksi**: Klik "Batal"
- **Hapus Item**: Klik "Hapus" di keranjang

---

## 👔 Owner - Manajemen Produk

### Tambah Produk:
1. Klik "Kelola Produk"
2. Klik "Tambah Produk"
3. Isi form (Nama, Kategori, Supplier, Harga, Stok, Diskon, Deskripsi)
4. Klik "Simpan"

### Edit Produk:
1. Pilih produk di tabel
2. Klik "Edit Produk"
3. Ubah data
4. Klik "Simpan"

### Hapus Produk:
1. Pilih produk di tabel
2. Klik "Hapus Produk"
3. Konfirmasi "Ya"

### Refresh Data:
- Klik "Refresh" untuk update tabel

---

## 📊 Owner - Laporan Penjualan

### Lihat Laporan:
1. Klik "Laporan Penjualan"
2. Lihat statistik (Total Pendapatan, Total Transaksi)
3. Lihat tabel transaksi

### Lihat Detail Transaksi:
1. Pilih transaksi di tabel
2. Klik "Lihat Detail"
3. Lihat informasi dan item yang dibeli

---

## 👥 Owner - Kelola User

### Tambah User:
1. Klik "Kelola User"
2. Klik "Tambah User"
3. Isi username, password, pilih role
4. Klik "Simpan"

### Hapus User:
1. Pilih user di tabel
2. Klik "Hapus User"
3. Konfirmasi "Ya"

**Catatan**: User admin default tidak bisa dihapus

---

## 💾 Owner/Admin - Backup Database

### Backup:
1. Klik "Backup Database"
2. Notifikasi sukses muncul
3. File backup: `tokokasir_backup_YYYYMMDD_HHmmss.db`

---

## 🔧 Troubleshooting Cepat

| Masalah | Solusi |
|---------|--------|
| "Class not found: org.sqlite.JDBC" | Pastikan sqlite-jdbc-3.44.0.0.jar di folder lib/ |
| "Cannot find symbol" | Compile ulang: `javac -d bin src/*.java` |
| Aplikasi tidak merespons | Tunggu 5 detik, database sedang inisialisasi |
| Database tidak bisa ditulis | Pastikan folder punya write permission |
| Login gagal | Cek username dan password (case-sensitive) |
| Stok tidak berkurang | Refresh halaman atau restart aplikasi |

---

## 📋 Keyboard Shortcuts

| Aksi | Shortcut |
|------|----------|
| Logout | Klik tombol "Logout" di top-right |
| Refresh Data | Klik tombol "Refresh" |
| Tambah Item | Klik "Tambah ke Keranjang" |
| Hapus Item | Klik "Hapus" di keranjang |
| Cetak Struk | Klik "Cetak Struk" |

---

## 💡 Tips & Tricks

### Kasir:
- Produk dengan stok 0 tidak muncul di dropdown
- Diskon otomatis dihitung dari harga produk
- Stok berkurang otomatis setelah transaksi
- Bisa batal transaksi tanpa menyimpan

### Owner:
- Kategori dan supplier harus dibuat sebelum produk
- Harga dan stok bisa diubah kapan saja
- Diskon dalam persentase (0-100%)
- Backup database secara berkala

### Admin:
- Akses semua fitur seperti Owner
- Bisa mengelola user dengan role apapun
- Bisa backup database

---

## 📊 Perhitungan Harga

```
Harga Final = Harga × (1 - Diskon%)
Subtotal = Harga Final × Jumlah
Total = Sum(Subtotal semua item)
```

**Contoh:**
- Produk: Rp 100.000
- Diskon: 10%
- Harga Final: Rp 100.000 × (1 - 10%) = Rp 90.000
- Jumlah: 2
- Subtotal: Rp 90.000 × 2 = Rp 180.000

---

## 🗂️ File Penting

| File | Fungsi |
|------|--------|
| tokokasir.db | Database utama |
| tokokasir_backup_*.db | File backup |
| src/*.java | Source code |
| lib/sqlite-jdbc-*.jar | SQLite driver |
| build.bat | Script build Windows |
| build.sh | Script build Linux/Mac |

---

## 🔐 Keamanan

- **Password**: Jangan bagikan password admin
- **Backup**: Backup database secara berkala
- **User**: Buat user terpisah untuk setiap kasir
- **Role**: Gunakan role yang sesuai untuk setiap user

---

## 📞 Bantuan Cepat

### Lupa Password?
- Hubungi admin untuk reset manual

### Database Corrupt?
- Restore dari file backup: `tokokasir_backup_*.db`
- Rename menjadi `tokokasir.db`

### Aplikasi Crash?
- Jalankan ulang aplikasi
- Jika masih error, cek file database

### Stok Tidak Akurat?
- Refresh halaman
- Cek transaksi di laporan
- Hubungi admin jika ada discrepancy

---

## 📈 Statistik & Laporan

### Total Pendapatan
```
= Sum(total_amount dari semua transaksi)
```

### Total Transaksi
```
= Count(transaksi)
```

### Rata-rata Transaksi
```
= Total Pendapatan / Total Transaksi
```

---

## 🎯 Checklist Harian

### Pagi (Kasir):
- [ ] Login dengan username kasir
- [ ] Cek stok produk
- [ ] Siap melayani transaksi

### Sore (Owner):
- [ ] Lihat laporan penjualan hari ini
- [ ] Cek stok produk yang menipis
- [ ] Update stok jika diperlukan

### Malam (Admin):
- [ ] Backup database
- [ ] Cek laporan penjualan
- [ ] Verifikasi data

---

## 🚨 Emergency Procedures

### Jika Database Hilang:
1. Restore dari file backup
2. Rename `tokokasir_backup_YYYYMMDD_HHmmss.db` → `tokokasir.db`
3. Jalankan ulang aplikasi

### Jika Lupa Password Admin:
1. Buka database dengan SQLite browser
2. Update password di tabel users
3. Jalankan ulang aplikasi

### Jika Aplikasi Tidak Bisa Dijalankan:
1. Cek Java terinstall: `java -version`
2. Cek SQLite driver ada di lib/
3. Compile ulang: `javac -d bin src/*.java`
4. Jalankan ulang

---

## 📚 Dokumentasi Lengkap

Untuk informasi lebih detail, baca:
- **INDEX.md** - Panduan navigasi
- **README.md** - Dokumentasi umum
- **SETUP.md** - Panduan instalasi
- **FEATURES.md** - Fitur lengkap
- **SAMPLE_DATA.md** - Data contoh

---

## 🎓 Training Checklist

### Untuk Kasir Baru:
- [ ] Pahami cara login
- [ ] Pahami cara input transaksi
- [ ] Pahami cara cetak struk
- [ ] Pahami cara lihat stok
- [ ] Praktik dengan data contoh

### Untuk Owner Baru:
- [ ] Pahami cara kelola produk
- [ ] Pahami cara kelola kategori
- [ ] Pahami cara kelola supplier
- [ ] Pahami cara lihat laporan
- [ ] Pahami cara kelola user
- [ ] Pahami cara backup database

### Untuk Admin Baru:
- [ ] Pahami semua fitur Owner
- [ ] Pahami pengelolaan sistem
- [ ] Pahami backup & recovery
- [ ] Pahami troubleshooting

---

## 📞 Support Contact

Untuk bantuan teknis, hubungi:
- **Email**: [developer email]
- **Phone**: [developer phone]
- **Hours**: [support hours]

---

**Selamat menggunakan Sistem Kasir Toko!** 🎉

Untuk pertanyaan lebih lanjut, baca dokumentasi lengkap atau hubungi support.
