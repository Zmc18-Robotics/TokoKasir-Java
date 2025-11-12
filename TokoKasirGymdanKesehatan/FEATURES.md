# Fitur Lengkap Sistem Kasir Toko

## 1. Sistem Autentikasi & Otorisasi

### Login
- Interface login yang user-friendly
- Validasi username dan password
- Pesan error yang jelas
- Session management per user
- Default admin account (username: admin, password: admin123)

### Role-Based Access Control (RBAC)
- **KASIR**: Akses terbatas untuk transaksi penjualan
- **OWNER**: Akses penuh untuk manajemen bisnis
- **ADMIN**: Akses penuh untuk manajemen sistem

---

## 2. Modul Kasir (Cashier Module)

### 2.1 Transaksi Penjualan
**Fitur:**
- Pilih produk dari dropdown (hanya produk dengan stok > 0)
- Input jumlah pembelian dengan spinner
- Validasi stok sebelum menambah ke keranjang
- Tampilkan harga, diskon, dan subtotal otomatis
- Keranjang belanja dengan tabel interaktif
- Tombol hapus item dari keranjang
- Perhitungan total otomatis dengan diskon

**Proses:**
1. Pilih produk dari dropdown
2. Masukkan jumlah
3. Klik "Tambah ke Keranjang"
4. Ulangi untuk produk lain
5. Klik "Cetak Struk" untuk menyelesaikan transaksi

### 2.2 Cetak Struk
**Fitur:**
- Struk berisi:
  - Header "STRUK PEMBELIAN TOKO"
  - Nama kasir
  - Tanggal dan waktu transaksi
  - Nomor transaksi
  - Daftar item dengan harga dan jumlah
  - Total pembayaran
  - Pesan terima kasih
- Format struk mirip struk toko nyata
- Dapat disimpan atau dicetak

### 2.3 Lihat Stok
**Fitur:**
- Tampilkan semua produk dalam tabel
- Kolom: ID, Nama, Kategori, Harga, Stok, Diskon, Deskripsi
- Read-only (tidak bisa edit)
- Tombol refresh untuk update data
- Sorting otomatis berdasarkan nama produk

### 2.4 Batasan Kasir
- ❌ Tidak bisa menambah produk
- ❌ Tidak bisa mengubah harga produk
- ❌ Tidak bisa menghapus produk
- ❌ Tidak bisa mengubah stok manual
- ❌ Tidak bisa akses menu owner/admin

---

## 3. Modul Owner (Management Module)

### 3.1 Kelola Produk
**Fitur CRUD:**
- **Create**: Tambah produk baru
  - Input: Nama, Kategori, Supplier, Harga, Stok, Diskon, Deskripsi
  - Validasi input
  - Simpan ke database

- **Read**: Lihat semua produk
  - Tabel dengan kolom lengkap
  - Sorting berdasarkan nama
  - Tampilkan kategori dan supplier

- **Update**: Edit produk
  - Ubah semua field
  - Validasi input
  - Update stok, harga, diskon

- **Delete**: Hapus produk
  - Konfirmasi sebelum hapus
  - Hapus dari database

### 3.2 Kelola Kategori
**Fitur CRUD:**
- Tambah kategori baru
- Edit nama dan deskripsi kategori
- Hapus kategori
- Validasi nama unik
- Tabel kategori dengan deskripsi

### 3.3 Kelola Supplier
**Fitur CRUD:**
- Tambah supplier dengan:
  - Nama supplier
  - Nomor telepon
  - Alamat lengkap
- Edit data supplier
- Hapus supplier
- Validasi nama unik
- Tabel supplier dengan kontak

### 3.4 Laporan Penjualan
**Fitur:**
- Statistik:
  - Total pendapatan (sum dari semua transaksi)
  - Total transaksi (count transaksi)
  
- Tabel transaksi:
  - No. Transaksi
  - Nama Kasir
  - Total Pembayaran
  - Metode Pembayaran
  - Tanggal & Waktu

- Detail Transaksi:
  - Klik "Lihat Detail" untuk melihat item yang dibeli
  - Tampilkan: Produk, Jumlah, Harga, Diskon, Subtotal

### 3.5 Kelola User
**Fitur:**
- Tambah user baru:
  - Input username (unik)
  - Input password
  - Pilih role (KASIR, OWNER, ADMIN)
  
- Hapus user:
  - Konfirmasi sebelum hapus
  - Proteksi user admin default
  
- Lihat semua user:
  - Tabel dengan username, role, tanggal dibuat

### 3.6 Backup Database
**Fitur:**
- Backup manual database
- File backup dengan timestamp: `tokokasir_backup_YYYYMMDD_HHmmss.db`
- Notifikasi sukses setelah backup
- Backup disimpan di folder yang sama dengan aplikasi

---

## 4. Modul Admin (System Administration)

### 4.1 Akses Penuh
- Semua fitur Owner tersedia
- Tambahan: Pengelolaan teknis sistem

### 4.2 Backup Database
- Sama seperti Owner
- Dapat melakukan backup kapan saja

### 4.3 Manajemen Sistem
- Akses ke semua data
- Dapat mengelola user dengan role apapun
- Dapat mengubah konfigurasi sistem

---

## 5. Database & Penyimpanan Data

### 5.1 Struktur Database
**Tabel: users**
- id (PRIMARY KEY)
- username (UNIQUE)
- password
- role (KASIR, OWNER, ADMIN)
- created_at (TIMESTAMP)

**Tabel: categories**
- id (PRIMARY KEY)
- name (UNIQUE)
- description

**Tabel: suppliers**
- id (PRIMARY KEY)
- name (UNIQUE)
- phone
- address

**Tabel: products**
- id (PRIMARY KEY)
- name
- category_id (FOREIGN KEY)
- supplier_id (FOREIGN KEY)
- price
- stock
- discount
- description
- image_path
- created_at (TIMESTAMP)

**Tabel: transactions**
- id (PRIMARY KEY)
- user_id (FOREIGN KEY)
- total_amount
- payment_method
- created_at (TIMESTAMP)

**Tabel: transaction_items**
- id (PRIMARY KEY)
- transaction_id (FOREIGN KEY)
- product_id (FOREIGN KEY)
- quantity
- price
- discount
- subtotal

### 5.2 Integritas Data
- Foreign key constraints
- Unique constraints pada username, kategori, supplier
- Cascade delete untuk transaksi
- Timestamp otomatis untuk audit trail

---

## 6. Fitur Keamanan

### 6.1 Autentikasi
- Login dengan username dan password
- Validasi kredensial
- Session per user

### 6.2 Otorisasi
- Role-based access control
- Menu dinamis berdasarkan role
- Pembatasan akses fitur

### 6.3 Validasi Input
- Validasi field kosong
- Validasi format data
- Validasi duplikasi (username, kategori, supplier)
- Validasi stok sebelum transaksi

### 6.4 Audit Trail
- Timestamp pada setiap transaksi
- Pencatatan user yang melakukan transaksi
- Riwayat lengkap di laporan

---

## 7. User Interface

### 7.1 Login Screen
- Judul aplikasi
- Input username
- Input password
- Tombol login
- Pesan error
- Info demo

### 7.2 Main Dashboard
- Top bar: Judul, role user, tombol logout
- Sidebar: Menu navigasi berdasarkan role
- Content area: Panel dinamis sesuai menu
- Responsive layout

### 7.3 Tabel & Form
- Tabel dengan scroll horizontal/vertikal
- Form input dengan validasi
- Dialog untuk tambah/edit
- Tombol aksi (Tambah, Edit, Hapus, Refresh)
- Konfirmasi sebelum aksi destruktif

---

## 8. Perhitungan & Logika Bisnis

### 8.1 Harga Produk
- Harga dasar dari database
- Diskon otomatis berdasarkan persentase
- Harga final = Harga × (1 - Diskon%)
- Subtotal = Harga final × Jumlah

### 8.2 Total Transaksi
- Total = Sum(Subtotal semua item)
- Diskon sudah termasuk dalam subtotal

### 8.3 Manajemen Stok
- Stok berkurang otomatis setelah transaksi
- Validasi stok sebelum transaksi
- Hanya produk dengan stok > 0 yang ditampilkan di kasir

### 8.4 Laporan
- Total pendapatan = Sum(total_amount dari semua transaksi)
- Total transaksi = Count(transaksi)
- Detail per transaksi tersedia

---

## 9. Notifikasi & Pesan

### 9.1 Pesan Sukses
- "Produk berhasil ditambahkan!"
- "Transaksi berhasil disimpan!"
- "Database berhasil di-backup!"

### 9.2 Pesan Error
- "Username atau password salah!"
- "Stok tidak cukup!"
- "Gagal menyimpan transaksi!"
- "Tidak ada produk tersedia!"

### 9.3 Pesan Konfirmasi
- "Yakin ingin menghapus produk ini?"
- "Yakin ingin menghapus user ini?"

---

## 10. Performa & Optimasi

### 10.1 Database Query
- Prepared statement untuk mencegah SQL injection
- Index pada kolom yang sering di-query
- Lazy loading untuk data besar

### 10.2 UI Responsiveness
- Non-blocking operations
- Loading indicator untuk operasi lama
- Refresh data tanpa reload aplikasi

---

## 11. Skalabilitas

Fitur yang dapat ditambahkan di masa depan:
- Multi-store support
- Integrasi payment gateway
- Barcode scanner
- Export laporan (Excel, PDF)
- Dashboard analytics dengan grafik
- Mobile app
- Cloud backup
- Multi-user concurrent access
- Password hashing (bcrypt)
- Two-factor authentication
- API REST untuk integrasi

---

## 12. Kompatibilitas

- **OS**: Windows, Linux, macOS
- **Java**: JDK 8 atau lebih tinggi
- **Database**: SQLite 3
- **UI Framework**: Java Swing

---

Dokumentasi fitur lengkap selesai. Untuk pertanyaan lebih lanjut, silakan merujuk ke README.md atau SETUP.md.
