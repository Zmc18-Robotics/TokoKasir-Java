================================================================================
                    SISTEM KASIR TOKO - README FIRST
================================================================================

Selamat datang di Sistem Kasir Toko!

Aplikasi ini adalah sistem kasir toko berbasis Java dengan fitur lengkap untuk
mengelola transaksi penjualan, produk, kategori, supplier, dan laporan.

================================================================================
                          MULAI DARI SINI
================================================================================

1. BACA FILE INI TERLEBIH DAHULU (Anda sedang membacanya!)

2. BACA: START_HERE.md
   - Panduan cepat 5 menit
   - Setup awal
   - Login default
   - Troubleshooting cepat

3. BACA: SETUP.md
   - Instalasi lengkap
   - Download SQLite driver
   - Compile program
   - Troubleshooting detail

4. JALANKAN APLIKASI
   Windows: build.bat
   Linux/Mac: ./build.sh

5. LOGIN
   Username: admin
   Password: admin123

6. BACA DOKUMENTASI SESUAI KEBUTUHAN
   - Kasir: QUICK_REFERENCE.md (Kasir section)
   - Owner: QUICK_REFERENCE.md (Owner section)
   - Developer: NOTES.md

================================================================================
                          FILE PENTING
================================================================================

UNTUK MEMULAI:
- START_HERE.md              ← Baca ini dulu!
- SETUP.md                   ← Instalasi
- QUICK_REFERENCE.md         ← Panduan cepat

UNTUK DOKUMENTASI:
- README.md                  ← Dokumentasi umum
- FEATURES.md                ← Fitur lengkap
- INDEX.md                   ← Navigasi & FAQ

UNTUK DEVELOPER:
- NOTES.md                   ← Catatan teknis
- FILE_MANIFEST.md           ← Daftar file
- VERIFICATION_CHECKLIST.md  ← QA checklist

UNTUK DATA:
- SAMPLE_DATA.md             ← Data contoh

UNTUK INFO:
- PROJECT_SUMMARY.txt        ← Ringkasan proyek
- COMPLETION_REPORT.md       ← Laporan penyelesaian

================================================================================
                          QUICK START (5 MENIT)
================================================================================

1. Download SQLite driver:
   https://github.com/xerial/sqlite-jdbc/releases
   Letakkan di: lib/sqlite-jdbc-3.44.0.0.jar

2. Compile:
   javac -d bin src/*.java

3. Jalankan:
   Windows: build.bat
   Linux/Mac: ./build.sh

4. Login:
   Username: admin
   Password: admin123

5. Selesai! Aplikasi siap digunakan.

Untuk detail lebih lanjut, baca START_HERE.md

================================================================================
                          ROLE & PERMISSION
================================================================================

KASIR:
✅ Input transaksi penjualan
✅ Cetak struk
✅ Lihat stok (read-only)
❌ Tidak bisa ubah harga/hapus produk

OWNER:
✅ Kelola produk (CRUD)
✅ Kelola kategori (CRUD)
✅ Kelola supplier (CRUD)
✅ Lihat laporan penjualan
✅ Kelola user
✅ Backup database

ADMIN:
✅ Semua fitur Owner
✅ Pengelolaan sistem
✅ Backup database

================================================================================
                          STRUKTUR FOLDER
================================================================================

TokoKasir/
├── src/                      (Source code Java)
├── lib/                      (External libraries)
├── bin/                      (Compiled files - auto-generated)
├── START_HERE.md             ← Baca ini dulu!
├── SETUP.md                  (Instalasi)
├── README.md                 (Dokumentasi umum)
├── QUICK_REFERENCE.md        (Panduan cepat)
├── FEATURES.md               (Fitur lengkap)
├── INDEX.md                  (Navigasi & FAQ)
├── NOTES.md                  (Catatan teknis)
├── FILE_MANIFEST.md          (Daftar file)
├── SAMPLE_DATA.md            (Data contoh)
├── VERIFICATION_CHECKLIST.md (QA checklist)
├── PROJECT_SUMMARY.txt       (Ringkasan proyek)
├── COMPLETION_REPORT.md      (Laporan penyelesaian)
├── build.bat                 (Build script Windows)
├── build.sh                  (Build script Linux/Mac)
└── README_FIRST.txt          (File ini)

================================================================================
                          PERSYARATAN SISTEM
================================================================================

- Java JDK 8 atau lebih tinggi
- SQLite JDBC Driver (sqlite-jdbc-3.44.0.0.jar)
- Minimal 100 MB disk space
- RAM minimal 512 MB
- OS: Windows, Linux, atau macOS

================================================================================
                          FITUR UTAMA
================================================================================

1. SISTEM LOGIN & OTORISASI
   - Login dengan username & password
   - Role-based access control (KASIR, OWNER, ADMIN)
   - Session management

2. MODUL KASIR
   - Input transaksi penjualan
   - Pilih produk dan jumlah
   - Cetak struk
   - Lihat stok

3. MODUL OWNER
   - CRUD Produk, Kategori, Supplier
   - Laporan penjualan
   - Kelola user
   - Backup database

4. DATABASE
   - SQLite dengan 6 tabel
   - Foreign key constraints
   - Audit trail dengan timestamp

5. KEAMANAN
   - Input validation
   - SQL injection prevention
   - Role-based access control
   - Password protection

================================================================================
                          TROUBLESHOOTING CEPAT
================================================================================

Error: "Class not found: org.sqlite.JDBC"
→ Pastikan sqlite-jdbc-3.44.0.0.jar ada di folder lib/

Error: "Cannot find symbol"
→ Compile ulang: javac -d bin src/*.java

Aplikasi tidak merespons
→ Tunggu 5 detik, database sedang inisialisasi

Login gagal
→ Cek username dan password (case-sensitive)

Stok tidak berkurang
→ Refresh halaman atau restart aplikasi

Untuk troubleshooting lebih detail, baca SETUP.md

================================================================================
                          DOKUMENTASI
================================================================================

Dokumentasi lengkap tersedia dalam file:

UNTUK MEMULAI:
- START_HERE.md              (Panduan cepat 5 menit)
- SETUP.md                   (Instalasi & troubleshooting)

UNTUK PENGGUNA:
- QUICK_REFERENCE.md         (Panduan cepat penggunaan)
- README.md                  (Dokumentasi umum)
- FEATURES.md                (Fitur lengkap)
- INDEX.md                   (Navigasi & FAQ)

UNTUK DEVELOPER:
- NOTES.md                   (Catatan teknis)
- FILE_MANIFEST.md           (Daftar file)
- VERIFICATION_CHECKLIST.md  (QA checklist)

UNTUK DATA:
- SAMPLE_DATA.md             (Data contoh untuk testing)

UNTUK INFO:
- PROJECT_SUMMARY.txt        (Ringkasan proyek)
- COMPLETION_REPORT.md       (Laporan penyelesaian)

================================================================================
                          LANGKAH SELANJUTNYA
================================================================================

1. Baca START_HERE.md untuk panduan cepat
2. Ikuti langkah instalasi di SETUP.md
3. Jalankan aplikasi dengan build.bat atau build.sh
4. Login dengan admin/admin123
5. Baca dokumentasi sesuai kebutuhan Anda

================================================================================
                          INFORMASI PENTING
================================================================================

✓ Database akan dibuat otomatis saat pertama kali menjalankan aplikasi
✓ File database: tokokasir.db
✓ Backup database: tokokasir_backup_YYYYMMDD_HHmmss.db
✓ Jangan bagikan password admin
✓ Backup database secara berkala
✓ Buat user terpisah untuk setiap kasir

================================================================================
                          KONTAK & SUPPORT
================================================================================

Untuk pertanyaan atau masalah:
- Baca dokumentasi yang sesuai
- Lihat FAQ di INDEX.md
- Lihat troubleshooting di SETUP.md
- Hubungi developer

Email: [developer email]
Phone: [developer phone]
Hours: [support hours]

================================================================================
                          LISENSI
================================================================================

Aplikasi ini dibuat untuk keperluan pembelajaran dan pengembangan sistem
kasir toko.

================================================================================

SELAMAT MENGGUNAKAN SISTEM KASIR TOKO!

Untuk memulai, baca START_HERE.md terlebih dahulu.

================================================================================
