# Sistem Kasir Toko - Panduan Lengkap

Selamat datang di dokumentasi Sistem Kasir Toko. Panduan ini akan membantu Anda memahami, menginstall, dan menggunakan aplikasi.

## 📋 Daftar Isi

### 1. **SETUP.md** - Panduan Instalasi
   - Persyaratan sistem
   - Download dan instalasi SQLite JDBC
   - Compile program
   - Menjalankan aplikasi
   - Troubleshooting

   **Mulai dari sini jika Anda baru pertama kali!**

### 2. **README.md** - Dokumentasi Umum
   - Deskripsi aplikasi
   - Fitur utama per role
   - Struktur database
   - Cara menggunakan
   - Struktur file proyek

### 3. **FEATURES.md** - Fitur Lengkap
   - Penjelasan detail setiap fitur
   - Modul per role (Kasir, Owner, Admin)
   - Logika bisnis
   - Keamanan
   - Skalabilitas

### 4. **SAMPLE_DATA.md** - Data Contoh
   - User contoh
   - Kategori produk
   - Supplier
   - Produk dengan harga dan stok
   - Cara menambahkan data
   - Tips testing

### 5. **INDEX.md** - File ini
   - Panduan navigasi dokumentasi
   - Quick start guide
   - FAQ

---

## 🚀 Quick Start Guide

### Langkah 1: Setup Awal (5 menit)
```bash
1. Pastikan Java JDK 8+ terinstall
2. Download sqlite-jdbc-3.44.0.0.jar ke folder lib/
3. Buka Command Prompt di folder TokoKasir
4. Jalankan: build.bat (Windows) atau ./build.sh (Linux/Mac)
```

### Langkah 2: Login Pertama
```
Username: admin
Password: admin123
```

### Langkah 3: Tambahkan Data Awal
1. Buat user baru (Kasir, Owner)
2. Tambahkan kategori produk
3. Tambahkan supplier
4. Tambahkan produk

Lihat **SAMPLE_DATA.md** untuk contoh data.

### Langkah 4: Testing
1. Login sebagai Kasir
2. Lakukan transaksi penjualan
3. Cetak struk
4. Login sebagai Owner
5. Lihat laporan penjualan

---

## 👥 Panduan Per Role

### 👨‍💼 Kasir (Cashier)
**Akses:**
- ✅ Transaksi Penjualan
- ✅ Lihat Stok
- ❌ Kelola Produk
- ❌ Kelola Kategori
- ❌ Kelola Supplier
- ❌ Laporan Penjualan
- ❌ Kelola User

**Tugas Utama:**
1. Melayani transaksi penjualan
2. Memilih produk dan jumlah
3. Mencetak struk
4. Melihat stok produk

**Panduan Transaksi:**
1. Pilih produk dari dropdown
2. Masukkan jumlah
3. Klik "Tambah ke Keranjang"
4. Ulangi untuk produk lain
5. Klik "Cetak Struk"
6. Transaksi selesai, stok otomatis berkurang

---

### 👔 Owner (Pemilik)
**Akses:**
- ✅ Semua fitur Kasir
- ✅ Kelola Produk (CRUD)
- ✅ Kelola Kategori (CRUD)
- ✅ Kelola Supplier (CRUD)
- ✅ Laporan Penjualan
- ✅ Kelola User
- ✅ Backup Database

**Tugas Utama:**
1. Mengelola produk, kategori, supplier
2. Melihat laporan penjualan
3. Membuat user baru
4. Backup database

**Panduan Manajemen Produk:**
1. Klik "Kelola Produk"
2. Klik "Tambah Produk" untuk produk baru
3. Isi form: nama, kategori, supplier, harga, stok, diskon, deskripsi
4. Klik "Simpan"
5. Untuk edit: pilih produk, klik "Edit Produk", ubah data, klik "Simpan"
6. Untuk hapus: pilih produk, klik "Hapus Produk", konfirmasi

**Panduan Laporan:**
1. Klik "Laporan Penjualan"
2. Lihat statistik: Total Pendapatan, Total Transaksi
3. Lihat tabel transaksi
4. Klik "Lihat Detail" untuk melihat item yang dibeli

---

### 🔧 Admin (Sistem)
**Akses:**
- ✅ Semua fitur Owner
- ✅ Pengelolaan Teknis Sistem

**Tugas Utama:**
1. Mengelola sistem secara keseluruhan
2. Backup database
3. Monitoring performa
4. Troubleshooting teknis

---

## 📊 Struktur Database

```
users (Pengguna)
├── id
├── username (unik)
├── password
├── role (KASIR, OWNER, ADMIN)
└── created_at

categories (Kategori Produk)
├── id
├── name (unik)
└── description

suppliers (Supplier)
├── id
├── name (unik)
├── phone
└── address

products (Produk)
├── id
├── name
├── category_id → categories
├── supplier_id → suppliers
├── price
├── stock
├── discount
├── description
├── image_path
└── created_at

transactions (Transaksi)
├── id
├── user_id → users
├── total_amount
├── payment_method
└── created_at

transaction_items (Detail Transaksi)
├── id
├── transaction_id → transactions
├── product_id → products
├── quantity
├── price
├── discount
└── subtotal
```

---

## 🔐 Keamanan

### Login
- Username dan password wajib diisi
- Validasi kredensial di database
- Pesan error jelas untuk login gagal

### Role-Based Access
- Setiap role memiliki akses berbeda
- Menu dinamis sesuai role
- Fitur terbatas untuk Kasir

### Validasi Input
- Field kosong tidak diterima
- Duplikasi username/kategori/supplier ditolak
- Stok divalidasi sebelum transaksi

### Audit Trail
- Setiap transaksi tercatat dengan timestamp
- Nama kasir tercatat di setiap transaksi
- Riwayat lengkap di laporan

---

## ❓ FAQ (Frequently Asked Questions)

### Q: Bagaimana cara reset password?
A: Saat ini belum ada fitur reset password. Hubungi admin untuk reset manual di database.

### Q: Bagaimana cara backup database?
A: Login sebagai Owner/Admin, klik "Backup Database". File backup akan dibuat dengan nama `tokokasir_backup_YYYYMMDD_HHmmss.db`.

### Q: Bisakah saya menghapus user admin?
A: Tidak, user admin default tidak bisa dihapus untuk keamanan sistem.

### Q: Bagaimana jika stok produk habis?
A: Produk dengan stok 0 tidak akan muncul di dropdown kasir. Owner dapat menambah stok melalui "Kelola Produk".

### Q: Apakah bisa transaksi dibatalkan?
A: Ya, klik tombol "Batal" di kasir untuk membatalkan transaksi. Stok tidak akan berkurang.

### Q: Bagaimana cara melihat detail transaksi?
A: Login sebagai Owner/Admin, klik "Laporan Penjualan", pilih transaksi, klik "Lihat Detail".

### Q: Apakah bisa mengubah harga produk?
A: Ya, Owner/Admin dapat mengubah harga melalui "Kelola Produk" → "Edit Produk".

### Q: Bagaimana cara menambah diskon produk?
A: Saat menambah/edit produk, isi field "Diskon (%)" dengan persentase diskon yang diinginkan.

### Q: Apakah database bisa dipindahkan?
A: Ya, file `tokokasir.db` dapat dipindahkan ke komputer lain. Pastikan Java dan SQLite JDBC sudah terinstall.

### Q: Bagaimana jika aplikasi crash?
A: Database SQLite cukup robust. Jalankan ulang aplikasi. Jika ada masalah, restore dari file backup.

---

## 📁 Struktur File Proyek

```
TokoKasir/
├── src/                          # Source code Java
│   ├── Main.java                 # Entry point
│   ├── DatabaseManager.java      # Database operations
│   ├── User.java                 # User model
│   ├── Product.java              # Product model
│   ├── LoginPanel.java           # Login UI
│   ├── MainPanel.java            # Main dashboard
│   ├── KasirPanel.java           # Cashier UI
│   ├── StockPanel.java           # Stock view
│   ├── ProductPanel.java         # Product management
│   ├── CategoryPanel.java        # Category management
│   ├── SupplierPanel.java        # Supplier management
│   ├── ReportPanel.java          # Sales report
│   └── UserPanel.java            # User management
├── lib/                          # External libraries
│   └── sqlite-jdbc-3.44.0.0.jar  # SQLite JDBC driver
├── bin/                          # Compiled files (auto-generated)
├── build.bat                     # Build script (Windows)
├── build.sh                      # Build script (Linux/Mac)
├── README.md                     # General documentation
├── SETUP.md                      # Installation guide
├── FEATURES.md                   # Detailed features
├── SAMPLE_DATA.md                # Sample data guide
├── INDEX.md                      # This file
└── tokokasir.db                  # Database (auto-generated)
```

---

## 🛠️ Troubleshooting

### Error: "Class not found: org.sqlite.JDBC"
**Solusi:** Pastikan `sqlite-jdbc-3.44.0.0.jar` ada di folder `lib/`

### Error: "Cannot find symbol"
**Solusi:** Compile ulang dengan `javac -d bin src/*.java`

### Aplikasi tidak merespons saat startup
**Solusi:** Tunggu beberapa detik, database sedang diinisialisasi

### Database tidak bisa ditulis
**Solusi:** Pastikan folder proyek memiliki write permission

Lihat **SETUP.md** untuk troubleshooting lebih lengkap.

---

## 📞 Support & Kontribusi

Untuk pertanyaan, bug report, atau saran fitur, silakan hubungi developer.

---

## 📝 Changelog

### Version 1.0 (Initial Release)
- ✅ Sistem login dengan role-based access
- ✅ Modul kasir dengan transaksi penjualan
- ✅ Manajemen produk, kategori, supplier
- ✅ Laporan penjualan
- ✅ Manajemen user
- ✅ Backup database
- ✅ SQLite database

---

## 📄 Lisensi

Aplikasi ini dibuat untuk keperluan pembelajaran dan pengembangan sistem kasir toko.

---

**Selamat menggunakan Sistem Kasir Toko!** 🎉

Untuk memulai, baca **SETUP.md** terlebih dahulu.
