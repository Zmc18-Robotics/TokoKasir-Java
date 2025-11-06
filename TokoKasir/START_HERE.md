# 🚀 START HERE - Sistem Kasir Toko

Selamat datang! Panduan ini akan membantu Anda memulai dengan cepat.

---

## ⚡ 5 Menit Setup

### Langkah 1: Download SQLite Driver (2 menit)
1. Buka: https://github.com/xerial/sqlite-jdbc/releases
2. Download: `sqlite-jdbc-3.44.0.0.jar`
3. Letakkan di folder: `lib/sqlite-jdbc-3.44.0.0.jar`

### Langkah 2: Compile Program (1 menit)
**Windows:**
```bash
cd TokoKasir
javac -d bin src/*.java
```

**Linux/Mac:**
```bash
cd TokoKasir
javac -d bin src/*.java
```

### Langkah 3: Jalankan Aplikasi (1 menit)
**Windows:**
```bash
build.bat
```

**Linux/Mac:**
```bash
./build.sh
```

### Langkah 4: Login (1 menit)
```
Username: admin
Password: admin123
```

✅ **Selesai! Aplikasi siap digunakan.**

---

## 📚 Dokumentasi Berdasarkan Kebutuhan

### 🆕 Saya Baru Pertama Kali
👉 Baca: **SETUP.md** → **QUICK_REFERENCE.md**

### 👨‍💼 Saya Kasir
👉 Baca: **QUICK_REFERENCE.md** (Kasir section)

### 👔 Saya Owner
👉 Baca: **QUICK_REFERENCE.md** (Owner section) → **FEATURES.md** (Owner Module)

### 🔧 Saya Developer/Admin
👉 Baca: **NOTES.md** → **FEATURES.md** → **FILE_MANIFEST.md**

### ❓ Saya Punya Pertanyaan
👉 Baca: **INDEX.md** (FAQ section)

### 🐛 Ada Error/Masalah
👉 Baca: **SETUP.md** (Troubleshooting section)

---

## 🎯 Panduan Cepat Per Role

### 👨‍💼 Kasir - Melayani Transaksi

**Apa yang bisa dilakukan:**
- ✅ Input transaksi penjualan
- ✅ Cetak struk
- ✅ Lihat stok produk

**Langkah-langkah:**
1. Login dengan username kasir
2. Klik "Transaksi Penjualan"
3. Pilih produk → Masukkan jumlah → Klik "Tambah ke Keranjang"
4. Ulangi untuk produk lain
5. Klik "Cetak Struk"

**Tidak bisa:**
- ❌ Menambah/mengubah/menghapus produk
- ❌ Mengubah harga
- ❌ Akses menu owner/admin

---

### 👔 Owner - Mengelola Bisnis

**Apa yang bisa dilakukan:**
- ✅ Kelola produk (tambah, ubah, hapus)
- ✅ Kelola kategori
- ✅ Kelola supplier
- ✅ Lihat laporan penjualan
- ✅ Kelola user
- ✅ Backup database

**Langkah-langkah Tambah Produk:**
1. Login dengan username owner
2. Klik "Kelola Produk"
3. Klik "Tambah Produk"
4. Isi form (nama, kategori, supplier, harga, stok, diskon, deskripsi)
5. Klik "Simpan"

**Langkah-langkah Lihat Laporan:**
1. Klik "Laporan Penjualan"
2. Lihat statistik dan tabel transaksi
3. Klik "Lihat Detail" untuk melihat item yang dibeli

---

### 🔧 Admin - Mengelola Sistem

**Apa yang bisa dilakukan:**
- ✅ Semua fitur Owner
- ✅ Pengelolaan teknis sistem
- ✅ Backup database

---

## 📊 Struktur Database

```
Produk
├── Kategori (Makanan, Elektronik, Pakaian, dll)
├── Supplier (Pemasok produk)
└── Harga, Stok, Diskon

Transaksi
├── Kasir (User yang melayani)
├── Item (Produk yang dibeli)
├── Total (Harga akhir)
└── Tanggal & Waktu

User
├── Kasir (Melayani transaksi)
├── Owner (Mengelola bisnis)
└── Admin (Mengelola sistem)
```

---

## 🔐 Login Default

```
Username: admin
Password: admin123
Role: ADMIN
```

**Catatan:** Buat user baru untuk kasir dan owner setelah login pertama kali.

---

## 💡 Tips Penting

### Untuk Kasir
- Produk dengan stok 0 tidak akan muncul di dropdown
- Diskon otomatis dihitung dari harga produk
- Stok berkurang otomatis setelah transaksi
- Bisa batal transaksi tanpa menyimpan

### Untuk Owner
- Kategori dan supplier harus dibuat sebelum produk
- Harga dan stok bisa diubah kapan saja
- Diskon dalam persentase (0-100%)
- Backup database secara berkala

### Untuk Semua
- Jangan bagikan password admin
- Backup database secara berkala
- Buat user terpisah untuk setiap kasir
- Gunakan role yang sesuai untuk setiap user

---

## 🆘 Troubleshooting Cepat

| Masalah | Solusi |
|---------|--------|
| "Class not found: org.sqlite.JDBC" | Pastikan sqlite-jdbc-3.44.0.0.jar di folder lib/ |
| "Cannot find symbol" | Compile ulang: `javac -d bin src/*.java` |
| Aplikasi tidak merespons | Tunggu 5 detik, database sedang inisialisasi |
| Login gagal | Cek username dan password (case-sensitive) |
| Stok tidak berkurang | Refresh halaman atau restart aplikasi |

**Masalah lebih kompleks?** Baca **SETUP.md** (Troubleshooting section)

---

## 📋 Checklist Pertama Kali

- [ ] Download SQLite driver
- [ ] Compile program
- [ ] Jalankan aplikasi
- [ ] Login dengan admin/admin123
- [ ] Buat user kasir baru
- [ ] Buat user owner baru
- [ ] Tambah kategori produk
- [ ] Tambah supplier
- [ ] Tambah produk
- [ ] Test transaksi sebagai kasir
- [ ] Lihat laporan sebagai owner
- [ ] Backup database

---

## 📚 Dokumentasi Lengkap

| File | Untuk Siapa | Isi |
|------|-------------|-----|
| **SETUP.md** | Semua | Instalasi & troubleshooting |
| **QUICK_REFERENCE.md** | Pengguna | Panduan cepat penggunaan |
| **README.md** | Semua | Dokumentasi umum |
| **FEATURES.md** | Developer | Fitur lengkap & detail |
| **SAMPLE_DATA.md** | Tester | Data contoh untuk testing |
| **INDEX.md** | Semua | Navigasi & FAQ |
| **NOTES.md** | Developer | Catatan teknis |
| **FILE_MANIFEST.md** | Developer | Daftar file |
| **VERIFICATION_CHECKLIST.md** | QA | Checklist verifikasi |
| **PROJECT_SUMMARY.txt** | Semua | Ringkasan proyek |

---

## 🎓 Belajar Lebih Lanjut

### Untuk Kasir
1. Baca: QUICK_REFERENCE.md (Kasir section)
2. Praktik: Lakukan transaksi dengan data contoh
3. Tanya: Hubungi owner jika ada pertanyaan

### Untuk Owner
1. Baca: QUICK_REFERENCE.md (Owner section)
2. Baca: FEATURES.md (Owner Module section)
3. Praktik: Kelola produk, kategori, supplier
4. Analisis: Lihat laporan penjualan

### Untuk Developer
1. Baca: NOTES.md (Arsitektur & Teknologi)
2. Baca: FEATURES.md (Fitur Lengkap)
3. Baca: FILE_MANIFEST.md (Struktur File)
4. Explore: Source code di folder src/

---

## 🚀 Next Steps

### Setelah Setup Berhasil

1. **Tambah Data Awal**
   - Buat user kasir dan owner
   - Tambah kategori produk
   - Tambah supplier
   - Tambah produk

2. **Test Aplikasi**
   - Login sebagai kasir
   - Lakukan transaksi
   - Cetak struk
   - Lihat laporan

3. **Backup Database**
   - Login sebagai owner
   - Klik "Backup Database"
   - Verifikasi file backup ada

4. **Training User**
   - Ajarkan kasir cara input transaksi
   - Ajarkan owner cara kelola produk
   - Ajarkan admin cara backup database

---

## 📞 Bantuan

### Pertanyaan Umum
👉 Baca: **INDEX.md** (FAQ section)

### Masalah Teknis
👉 Baca: **SETUP.md** (Troubleshooting section)

### Fitur Tertentu
👉 Baca: **FEATURES.md** (sesuai modul)

### Panduan Cepat
👉 Baca: **QUICK_REFERENCE.md**

### Hubungi Developer
📧 Email: [developer email]
📱 Phone: [developer phone]

---

## ✅ Verifikasi Setup Berhasil

Jika Anda bisa:
- ✅ Menjalankan aplikasi
- ✅ Login dengan admin/admin123
- ✅ Melihat main dashboard
- ✅ Akses menu sesuai role

**Maka setup Anda BERHASIL!** 🎉

---

## 🎯 Tujuan Aplikasi

Aplikasi ini dirancang untuk:
- **Kasir**: Melayani transaksi penjualan dengan cepat dan mudah
- **Owner**: Mengelola produk, stok, dan melihat laporan penjualan
- **Admin**: Mengelola sistem dan backup database

---

## 📝 Catatan Penting

1. **Database**: File `tokokasir.db` akan dibuat otomatis saat pertama kali menjalankan aplikasi
2. **Backup**: Backup database secara berkala untuk mencegah kehilangan data
3. **User**: Buat user terpisah untuk setiap kasir
4. **Password**: Jangan bagikan password admin
5. **Stok**: Stok berkurang otomatis setelah transaksi

---

## 🎉 Selamat!

Anda sudah siap menggunakan **Sistem Kasir Toko**!

Untuk informasi lebih detail, baca dokumentasi yang sesuai dengan kebutuhan Anda.

---

**Pertanyaan?** Baca **INDEX.md** atau hubungi developer.

**Siap memulai?** Ikuti langkah-langkah di atas dan mulai gunakan aplikasi!

---

**Happy Selling!** 🛍️
