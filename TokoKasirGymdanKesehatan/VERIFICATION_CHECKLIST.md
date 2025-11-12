# Verification Checklist - Sistem Kasir Toko

Gunakan checklist ini untuk memverifikasi bahwa semua komponen aplikasi sudah berfungsi dengan baik.

## ✅ Pre-Installation Checklist

- [ ] Java JDK 8 atau lebih tinggi sudah terinstall
- [ ] Command Prompt/Terminal dapat diakses
- [ ] Folder TokoKasir sudah dibuat
- [ ] Semua file source code (.java) sudah ada di folder src/
- [ ] Folder lib/ sudah dibuat
- [ ] SQLite JDBC driver (sqlite-jdbc-3.44.0.0.jar) sudah didownload

## ✅ Installation Checklist

- [ ] Compile berhasil tanpa error
- [ ] Folder bin/ terbuat dengan file .class
- [ ] Aplikasi dapat dijalankan
- [ ] Database tokokasir.db terbuat otomatis
- [ ] Login screen muncul

## ✅ Login & Authentication

- [ ] Login dengan admin/admin123 berhasil
- [ ] Login dengan username salah ditolak
- [ ] Login dengan password salah ditolak
- [ ] Pesan error muncul untuk login gagal
- [ ] Logout berfungsi dan kembali ke login screen

## ✅ Kasir Module

### Transaksi Penjualan
- [ ] Dropdown produk menampilkan semua produk dengan stok > 0
- [ ] Dapat memilih produk dari dropdown
- [ ] Dapat mengubah jumlah dengan spinner
- [ ] Tombol "Tambah ke Keranjang" berfungsi
- [ ] Item ditambahkan ke tabel keranjang
- [ ] Harga, diskon, dan subtotal dihitung dengan benar
- [ ] Total otomatis terupdate saat item ditambahkan
- [ ] Tombol "Hapus" di keranjang berfungsi
- [ ] Total berkurang saat item dihapus dari keranjang

### Cetak Struk
- [ ] Tombol "Cetak Struk" berfungsi
- [ ] Struk menampilkan informasi lengkap:
  - [ ] Header "STRUK PEMBELIAN TOKO"
  - [ ] Nama kasir
  - [ ] Tanggal dan waktu
  - [ ] Nomor transaksi
  - [ ] Daftar item dengan harga dan jumlah
  - [ ] Total pembayaran
  - [ ] Pesan terima kasih
- [ ] Transaksi disimpan ke database
- [ ] Stok produk berkurang setelah transaksi
- [ ] Keranjang kosong setelah transaksi selesai

### Lihat Stok
- [ ] Menu "Lihat Stok" dapat diakses
- [ ] Tabel menampilkan semua produk
- [ ] Kolom: ID, Nama, Kategori, Harga, Stok, Diskon, Deskripsi
- [ ] Tabel read-only (tidak bisa edit)
- [ ] Tombol "Refresh" berfungsi

### Batasan Kasir
- [ ] Kasir tidak bisa akses menu "Kelola Produk"
- [ ] Kasir tidak bisa akses menu "Kelola Kategori"
- [ ] Kasir tidak bisa akses menu "Kelola Supplier"
- [ ] Kasir tidak bisa akses menu "Laporan Penjualan"
- [ ] Kasir tidak bisa akses menu "Kelola User"

## ✅ Owner Module

### Kelola Produk
- [ ] Menu "Kelola Produk" dapat diakses
- [ ] Tabel menampilkan semua produk
- [ ] Tombol "Tambah Produk" berfungsi
- [ ] Form tambah produk muncul dengan field:
  - [ ] Nama Produk
  - [ ] Kategori
  - [ ] Supplier
  - [ ] Harga
  - [ ] Stok
  - [ ] Diskon (%)
  - [ ] Deskripsi
- [ ] Produk baru dapat disimpan
- [ ] Tombol "Edit Produk" berfungsi
- [ ] Form edit menampilkan data produk yang dipilih
- [ ] Produk dapat diupdate
- [ ] Tombol "Hapus Produk" berfungsi
- [ ] Konfirmasi muncul sebelum hapus
- [ ] Produk dapat dihapus
- [ ] Tombol "Refresh" berfungsi

### Kelola Kategori
- [ ] Menu "Kelola Kategori" dapat diakses
- [ ] Tabel menampilkan semua kategori
- [ ] Tombol "Tambah Kategori" berfungsi
- [ ] Form tambah kategori muncul
- [ ] Kategori baru dapat disimpan
- [ ] Tombol "Edit Kategori" berfungsi
- [ ] Kategori dapat diupdate
- [ ] Tombol "Hapus Kategori" berfungsi
- [ ] Kategori dapat dihapus
- [ ] Validasi: nama kategori tidak boleh kosong
- [ ] Validasi: nama kategori harus unik

### Kelola Supplier
- [ ] Menu "Kelola Supplier" dapat diakses
- [ ] Tabel menampilkan semua supplier
- [ ] Tombol "Tambah Supplier" berfungsi
- [ ] Form tambah supplier muncul dengan field:
  - [ ] Nama Supplier
  - [ ] Telepon
  - [ ] Alamat
- [ ] Supplier baru dapat disimpan
- [ ] Tombol "Edit Supplier" berfungsi
- [ ] Supplier dapat diupdate
- [ ] Tombol "Hapus Supplier" berfungsi
- [ ] Supplier dapat dihapus
- [ ] Validasi: nama supplier tidak boleh kosong
- [ ] Validasi: nama supplier harus unik

### Laporan Penjualan
- [ ] Menu "Laporan Penjualan" dapat diakses
- [ ] Statistik ditampilkan:
  - [ ] Total Pendapatan (sum dari semua transaksi)
  - [ ] Total Transaksi (count transaksi)
- [ ] Tabel transaksi menampilkan:
  - [ ] No. Transaksi
  - [ ] Kasir
  - [ ] Total
  - [ ] Metode Pembayaran
  - [ ] Tanggal
- [ ] Tombol "Lihat Detail" berfungsi
- [ ] Detail transaksi menampilkan:
  - [ ] Informasi transaksi (No, Kasir, Total, Tanggal)
  - [ ] Tabel item yang dibeli (Produk, Jumlah, Harga, Diskon, Subtotal)
- [ ] Tombol "Refresh" berfungsi

### Kelola User
- [ ] Menu "Kelola User" dapat diakses
- [ ] Tabel menampilkan semua user
- [ ] Tombol "Tambah User" berfungsi
- [ ] Form tambah user muncul dengan field:
  - [ ] Username
  - [ ] Password
  - [ ] Role (KASIR, OWNER, ADMIN)
- [ ] User baru dapat disimpan
- [ ] Tombol "Hapus User" berfungsi
- [ ] Konfirmasi muncul sebelum hapus
- [ ] User dapat dihapus
- [ ] Validasi: username tidak boleh kosong
- [ ] Validasi: password tidak boleh kosong
- [ ] Validasi: username harus unik
- [ ] Proteksi: user admin default tidak bisa dihapus

### Backup Database
- [ ] Tombol "Backup Database" berfungsi
- [ ] File backup dibuat dengan nama: tokokasir_backup_YYYYMMDD_HHmmss.db
- [ ] Notifikasi sukses muncul setelah backup
- [ ] File backup dapat ditemukan di folder proyek

## ✅ Admin Module

- [ ] Admin dapat akses semua fitur Owner
- [ ] Admin dapat akses semua fitur Kasir
- [ ] Admin dapat melakukan backup database
- [ ] Admin dapat mengelola user dengan role apapun

## ✅ Database Integrity

- [ ] Database tokokasir.db terbuat dengan benar
- [ ] Semua tabel terbuat:
  - [ ] users
  - [ ] categories
  - [ ] suppliers
  - [ ] products
  - [ ] transactions
  - [ ] transaction_items
- [ ] Foreign key constraints berfungsi
- [ ] Unique constraints berfungsi
- [ ] Timestamp otomatis pada setiap record baru
- [ ] Data dapat diquery dengan benar

## ✅ Data Validation

- [ ] Field kosong ditolak
- [ ] Duplikasi username ditolak
- [ ] Duplikasi kategori ditolak
- [ ] Duplikasi supplier ditolak
- [ ] Stok negatif tidak bisa ditambahkan
- [ ] Harga negatif tidak bisa ditambahkan
- [ ] Diskon > 100% tidak bisa ditambahkan
- [ ] Jumlah transaksi > stok ditolak

## ✅ UI/UX

- [ ] Login screen user-friendly
- [ ] Main dashboard menampilkan menu sesuai role
- [ ] Sidebar menu responsif
- [ ] Tabel dapat di-scroll
- [ ] Form input jelas dan mudah digunakan
- [ ] Tombol aksi jelas dan mudah diklik
- [ ] Pesan error informatif
- [ ] Pesan sukses muncul setelah aksi berhasil
- [ ] Warna dan layout konsisten

## ✅ Performance

- [ ] Aplikasi startup cepat (< 5 detik)
- [ ] Transaksi disimpan cepat (< 2 detik)
- [ ] Laporan load cepat (< 3 detik)
- [ ] Tidak ada lag saat input data
- [ ] Tidak ada memory leak

## ✅ Security

- [ ] Password tidak ditampilkan di layar
- [ ] Login session terjaga
- [ ] Logout menghapus session
- [ ] Role-based access control berfungsi
- [ ] Menu dinamis sesuai role
- [ ] Fitur terbatas untuk Kasir
- [ ] Audit trail tercatat dengan benar

## ✅ Error Handling

- [ ] Error database ditangani dengan baik
- [ ] Error input ditampilkan dengan pesan jelas
- [ ] Aplikasi tidak crash saat error
- [ ] Pesan error membantu user mengatasi masalah

## ✅ Documentation

- [ ] README.md lengkap dan jelas
- [ ] SETUP.md mudah diikuti
- [ ] FEATURES.md detail dan komprehensif
- [ ] SAMPLE_DATA.md membantu testing
- [ ] INDEX.md memudahkan navigasi
- [ ] PROJECT_SUMMARY.txt informatif

## ✅ Testing Scenarios

### Scenario 1: Transaksi Lengkap
- [ ] Login sebagai kasir
- [ ] Lakukan transaksi dengan 3 produk berbeda
- [ ] Cetak struk
- [ ] Verifikasi stok berkurang
- [ ] Login sebagai owner
- [ ] Lihat transaksi di laporan

### Scenario 2: Manajemen Produk
- [ ] Login sebagai owner
- [ ] Tambah kategori baru
- [ ] Tambah supplier baru
- [ ] Tambah produk dengan kategori dan supplier baru
- [ ] Edit produk (ubah harga, stok, diskon)
- [ ] Hapus produk
- [ ] Verifikasi perubahan di kasir

### Scenario 3: User Management
- [ ] Login sebagai admin
- [ ] Tambah user kasir baru
- [ ] Tambah user owner baru
- [ ] Login dengan user baru
- [ ] Verifikasi akses sesuai role
- [ ] Hapus user
- [ ] Verifikasi user tidak bisa login

### Scenario 4: Backup & Recovery
- [ ] Login sebagai owner
- [ ] Lakukan beberapa transaksi
- [ ] Backup database
- [ ] Verifikasi file backup ada
- [ ] Tutup aplikasi
- [ ] Jalankan ulang aplikasi
- [ ] Verifikasi data masih ada

## ✅ Final Verification

- [ ] Semua fitur berfungsi sesuai requirement
- [ ] Tidak ada bug yang ditemukan
- [ ] Dokumentasi lengkap dan jelas
- [ ] Aplikasi siap untuk production
- [ ] User dapat menggunakan aplikasi tanpa bantuan teknis

---

## Notes

Catatan tambahan atau masalah yang ditemukan:

```
[Tulis catatan di sini]
```

---

## Sign-Off

- Tester: ___________________
- Tanggal: ___________________
- Status: ☐ PASS  ☐ FAIL

---

Jika semua checklist sudah dicentang, aplikasi siap untuk digunakan!
