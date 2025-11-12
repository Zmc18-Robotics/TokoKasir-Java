# Sistem Kasir Toko

Aplikasi kasir toko berbasis Java dengan sistem role-based access control (RBAC) untuk mengelola transaksi penjualan, produk, kategori, supplier, dan laporan.

## Fitur Utama

### 1. Customer (Pelanggan)
- **Lihat Katalog Produk**: Melihat semua produk yang tersedia dengan harga dan diskon
- **Pesan Produk**: Membuat pesanan produk dengan jumlah yang diinginkan
- **Lihat Pesanan**: Melihat riwayat pesanan sendiri dan statusnya
- **Catatan Pesanan**: Menambahkan catatan khusus untuk pesanan

### 2. Kasir (Cashier)
- **Input Transaksi**: Melayani transaksi penjualan dengan memilih produk dan jumlah
- **Cetak Struk**: Mencetak struk pembelian setelah transaksi selesai
- **Lihat Stok**: Melihat daftar stok produk (read-only, tidak bisa mengubah)
- **Kelola Pesanan**: Melihat dan memproses pesanan pelanggan
- **Batasan**: Tidak bisa menghapus produk atau mengubah harga

### 3. Owner (Pemilik)
- **CRUD Produk**: Tambah, ubah, hapus produk
- **CRUD Kategori**: Kelola kategori produk
- **CRUD Supplier**: Kelola data supplier
- **Laporan Penjualan**: Melihat laporan penjualan dengan statistik
- **Kelola Pesanan**: Melihat dan memproses pesanan pelanggan dengan statistik
- **Kelola User**: Tambah user baru (Kasir, Owner, Admin, Customer)
- **Backup Database**: Backup database secara manual
- **Akses Penuh**: Semua fitur tersedia

### 4. Admin (Sistem)
- **Semua Akses**: Sama seperti Owner
- **Backup Database**: Backup database secara manual
- **Pengelolaan Teknis**: Mengelola sistem secara keseluruhan

## Struktur Database

### Tabel Utama:
1. **users** - Data pengguna dengan role (CUSTOMER, KASIR, OWNER, ADMIN)
2. **categories** - Kategori produk
3. **suppliers** - Data supplier
4. **products** - Data produk dengan harga, stok, diskon
5. **transactions** - Riwayat transaksi penjualan (kasir)
6. **transaction_items** - Detail item dalam transaksi
7. **orders** - Pesanan pelanggan dengan status tracking

## Cara Menggunakan

### Persyaratan:
- Java JDK 8 atau lebih tinggi
- SQLite JDBC Driver (sudah disertakan)

### Instalasi dan Menjalankan:

1. **Download SQLite JDBC Driver**:
   - Download dari: https://github.com/xerial/sqlite-jdbc/releases
   - Letakkan file `sqlite-jdbc-3.44.0.0.jar` di folder `lib/`

2. **Compile Program**:
   ```bash
   javac -d bin src/*.java
   ```

3. **Jalankan Program**:
   ```bash
   java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main
   ```

   Atau gunakan script batch (Windows):
   ```bash
   build.bat
   ```

### Login Default:

**Admin (untuk mengelola sistem):**
- **Username**: admin
- **Password**: admin123
- **Role**: ADMIN

**Customer (untuk pelanggan):**
- **Username**: customer
- **Password**: customer123
- **Role**: CUSTOMER

## Fitur Aplikasi

### Login Screen
- Interface login yang user-friendly
- Validasi username dan password
- Pesan error untuk login gagal

### Dashboard Customer (Pelanggan)
- **Katalog Produk**: Lihat semua produk dengan harga, diskon, dan stok
- **Pesan Produk**: Pilih produk dan jumlah, tambahkan catatan pesanan
- **Pesanan Saya**: Lihat riwayat pesanan dengan status (PENDING, DIPROSES, SELESAI, DIBATALKAN)
- **Real-time Update**: Lihat total harga otomatis saat mengubah jumlah

### Dashboard Kasir
- Pilih produk dari dropdown
- Masukkan jumlah pembelian
- Tambah ke keranjang
- Lihat total harga dengan diskon otomatis
- Cetak struk setelah transaksi
- Batal transaksi
- **Pesanan Pelanggan**: Lihat dan proses pesanan dari pelanggan

### Manajemen Produk (Owner/Admin)
- Tambah produk baru dengan kategori dan supplier
- Edit harga, stok, diskon, deskripsi
- Hapus produk
- Lihat semua produk dalam tabel

### Manajemen Kategori (Owner/Admin)
- Tambah kategori baru
- Edit kategori
- Hapus kategori

### Manajemen Supplier (Owner/Admin)
- Tambah supplier dengan telepon dan alamat
- Edit data supplier
- Hapus supplier

### Laporan Penjualan (Owner/Admin)
- Lihat semua transaksi
- Statistik total pendapatan
- Statistik total transaksi
- Lihat detail transaksi (item yang dibeli)

### Kelola Pesanan (Semua Role)
- **Customer**: Lihat pesanan sendiri dengan status
- **Kasir/Owner/Admin**: Lihat semua pesanan pelanggan
- **Kasir/Owner/Admin**: Update status pesanan (PENDING → DIPROSES → SELESAI)
- **Kasir/Owner/Admin**: Batalkan pesanan jika diperlukan
- **Owner/Admin**: Lihat statistik pesanan dan pendapatan

### Manajemen User (Owner/Admin)
- Tambah user baru dengan role (Customer, Kasir, Owner, Admin)
- Hapus user (kecuali admin default)
- Lihat semua user

### Backup Database (Owner/Admin)
- Backup database otomatis dengan timestamp
- File backup disimpan dengan nama: `tokokasir_backup_YYYYMMDD_HHmmss.db`

## Struktur File

```
TokoKasir/
├── src/
│   ├── Main.java              # Entry point aplikasi
│   ├── DatabaseManager.java   # Manajemen database
│   ├── User.java              # Model user
│   ├── Product.java           # Model produk
│   ├── LoginPanel.java        # UI login
│   ├── MainPanel.java         # UI utama dengan menu
│   ├── CustomerPanel.java     # UI katalog produk untuk pelanggan
│   ├── OrderPanel.java        # UI kelola pesanan (semua role)
│   ├── KasirPanel.java        # UI transaksi kasir
│   ├── StockPanel.java        # UI lihat stok
│   ├── ProductPanel.java      # UI manajemen produk
│   ├── CategoryPanel.java     # UI manajemen kategori
│   ├── SupplierPanel.java     # UI manajemen supplier
│   ├── ReportPanel.java       # UI laporan penjualan
│   └── UserPanel.java         # UI manajemen user
├── lib/
│   └── sqlite-jdbc-3.44.0.0.jar  # SQLite JDBC Driver
├── bin/                       # Compiled files (auto-generated)
├── build.bat                  # Script build untuk Windows
└── README.md                  # Dokumentasi ini
```

## Keamanan

- Password disimpan dalam database (untuk production, gunakan hashing)
- Role-based access control untuk membatasi akses fitur
- Validasi input pada setiap form
- Database backup untuk mencegah kehilangan data

## Catatan Penting

1. **Database**: Menggunakan SQLite, file database akan dibuat otomatis saat pertama kali menjalankan aplikasi
2. **Default Users**: 
   - User admin (admin123) dibuat otomatis untuk mengelola sistem
   - User customer (customer123) dibuat otomatis untuk pelanggan
3. **Diskon**: Diskon produk dihitung otomatis saat transaksi dan pesanan
4. **Stok**: Stok produk berkurang otomatis setelah transaksi berhasil (belum otomatis untuk pesanan)
5. **Status Pesanan**: Pesanan pelanggan memiliki 4 status (PENDING, DIPROSES, SELESAI, DIBATALKAN)
6. **Backup**: Backup database dapat dilakukan kapan saja dari menu Owner/Admin

## Pengembangan Lebih Lanjut

Fitur yang bisa ditambahkan:
- Integrasi dengan printer untuk cetak struk otomatis
- Export laporan ke Excel/PDF
- Sistem pembayaran online untuk pesanan pelanggan
- Barcode scanner untuk input produk
- Multi-store support
- Password hashing dengan bcrypt
- Audit log untuk semua transaksi
- Dashboard analytics dengan grafik
- Notifikasi real-time untuk pesanan baru
- Rating dan review produk dari pelanggan
- Pengurangan stok otomatis saat pesanan selesai
- Integrasi dengan kurir untuk pengiriman

## Lisensi

Aplikasi ini dibuat untuk keperluan pembelajaran dan pengembangan sistem kasir toko.

## Support

Untuk pertanyaan atau masalah, silakan hubungi developer.
