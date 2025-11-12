# Panduan Testing Sistem Kasir Toko

## User Credentials untuk Testing

### 1. Admin User
- **Username**: `admin`
- **Password**: `admin123`
- **Role**: ADMIN

**Fitur yang dapat diakses:**
- ✅ Kelola Produk (Tambah, Edit, Hapus)
- ✅ Kategori Produk (Tambah, Edit, Hapus)
- ✅ Laporan Penjualan (Lihat statistik & detail transaksi)
- ✅ Transaksi Pelanggan (Lihat, Update status)
- ✅ Kelola User (Tambah, Hapus)
- ✅ Backup Database
- ✅ Logout

---

### 2. Kasir User
- **Username**: `kasir`
- **Password**: `kasir123`
- **Role**: KASIR

**Fitur yang dapat diakses:**
- ✅ Transaksi Penjualan (Tambah produk ke keranjang, Cetak struk)
- ✅ Lihat Stok (Melihat stok semua produk)
- ✅ Transaksi Pelanggan (Lihat pesanan pelanggan)
- ✅ Logout

---

### 3. Customer User
- **Username**: `customer`
- **Password**: `customer123`
- **Role**: CUSTOMER

**Fitur yang dapat diakses:**
- ✅ Katalog Produk (Lihat produk dengan diskon, filter stok > 0)
- ✅ Transaksi Saya (Lihat pesanan pribadi)
- ✅ Logout

---

## Test Cases

### ✅ Test Case 1: Login Admin
1. Buka aplikasi
2. Masukkan username: `admin`
3. Masukkan password: `admin123`
4. Klik tombol "Login"
5. **Expected**: Login berhasil, menampilkan menu Admin dengan opsi:
   - Kelola Produk
   - Kategori Produk
   - Laporan Penjualan
   - Transaksi Pelanggan
   - Kelola User
   - Backup Database

### ✅ Test Case 2: Login Kasir
1. Buka aplikasi atau klik Logout jika sedang login
2. Masukkan username: `kasir`
3. Masukkan password: `kasir123`
4. Klik tombol "Login"
5. **Expected**: Login berhasil, menampilkan menu Kasir dengan opsi:
   - Transaksi Penjualan
   - Lihat Stok
   - Transaksi Pelanggan

### ✅ Test Case 3: Login Customer
1. Buka aplikasi atau klik Logout jika sedang login
2. Masukkan username: `customer`
3. Masukkan password: `customer123`
4. Klik tombol "Login"
5. **Expected**: Login berhasil, menampilkan menu Customer dengan opsi:
   - Katalog Produk
   - Transaksi Saya

### ✅ Test Case 4: Admin - Kelola Produk
1. Login sebagai Admin
2. Klik "Kelola Produk"
3. **Expected**: Menampilkan tabel produk dengan sample data
4. Coba "Tambah Produk" - jika ada error tentang kategori/supplier, pastikan sudah ada di database
5. Coba "Edit Produk" - pilih salah satu dan ubah data
6. Coba "Hapus Produk" - pilih salah satu

### ✅ Test Case 5: Admin - Kategori Produk
1. Login sebagai Admin
2. Klik "Kategori Produk"
3. **Expected**: Menampilkan daftar kategori produk (tidak "Kelola Supplier")
4. Coba "Tambah Kategori", "Edit Kategori", "Hapus Kategori"

### ✅ Test Case 6: Admin - Laporan Penjualan
1. Login sebagai Admin
2. Klik "Laporan Penjualan"
3. **Expected**: Menampilkan tabel transaksi (jika ada) dengan statistik total pendapatan dan jumlah transaksi

### ✅ Test Case 7: Admin - Transaksi Pelanggan
1. Login sebagai Admin
2. Klik "Transaksi Pelanggan"
3. **Expected**: Menampilkan daftar pesanan pelanggan (jika ada)
4. Opsi: Update status (Proses, Selesai, Batalkan)

### ✅ Test Case 8: Kasir - Transaksi Penjualan
1. Login sebagai Kasir
2. Klik "Transaksi Penjualan"
3. **Expected**: Menampilkan form penjualan dengan:
   - Dropdown pilih produk
   - Input jumlah
   - Tombol "Tambah ke Keranjang"
   - Tabel keranjang
   - Tombol "Cetak Struk" dan "Batal"
4. Coba tambahkan beberapa produk ke keranjang
5. Klik "Cetak Struk" - akan menampilkan preview struk

### ✅ Test Case 9: Kasir - Lihat Stok
1. Login sebagai Kasir
2. Klik "Lihat Stok"
3. **Expected**: Menampilkan tabel semua produk beserta stoknya

### ✅ Test Case 10: Customer - Katalog Produk
1. Login sebagai Customer
2. Klik "Katalog Produk"
3. **Expected**: Menampilkan daftar produk dengan:
   - Nama, Kategori, Harga Original, Diskon %, Harga Setelah Diskon, Stok
4. Tombol "Pesan Produk" untuk membuat pesanan
5. Pilih produk dan klik "Pesan Produk"
6. Isi form pesanan (jumlah, catatan)
7. Klik "Pesan Sekarang"

### ✅ Test Case 11: Customer - Transaksi Saya
1. Login sebagai Customer
2. Klik "Transaksi Saya"
3. **Expected**: Menampilkan pesanan yang dibuat oleh customer ini
4. Status akan berubah sesuai dengan yang diupdate oleh Admin/Kasir

### ✅ Test Case 12: Logout
1. Dari halaman manapun, klik tombol "Logout"
2. **Expected**: Kembali ke halaman login

---

## Perubahan yang Dilakukan

### 1. Fix Login Issues
- ✅ Menambahkan static initializer untuk load SQLite JDBC driver
- ✅ Memperbaiki method `authenticateUser()` untuk properly close connections
- ✅ Menambahkan `ensureAdminExists()` yang membuat 3 user (admin, kasir, customer)
- ✅ Membuat `InitializeData` class untuk insert sample data

### 2. Menu Changes
- ✅ **Dihapus**: "Kelola Supplier" (dari menu semua role)
- ✅ **Diubah**: "Kelola Kategori" → "Kategori Produk"
- ✅ **Diubah**: "Pesanan Pelanggan" → "Transaksi Pelanggan"
- ✅ **Diubah**: "Pesanan Saya" → "Transaksi Saya"

### 3. Menu Structure per Role

#### ADMIN Menu:
```
- Kelola Produk
- Kategori Produk
- Laporan Penjualan
- Transaksi Pelanggan
- Kelola User
- Backup Database
```

#### KASIR Menu:
```
- Transaksi Penjualan
- Lihat Stok
- Transaksi Pelanggan
```

#### CUSTOMER Menu:
```
- Katalog Produk
- Transaksi Saya
```

---

## Sample Data yang Tersedia

### Kategori:
1. Elektronik
2. Pakaian
3. Makanan
4. Buku

### Supplier:
1. Supplier A
2. Supplier B
3. Supplier C

### Produk:
1. Laptop Dell - 5,000,000 (5% diskon)
2. Mouse Logitech - 150,000
3. T-Shirt Polos - 50,000 (10% diskon)
4. Celana Jeans - 150,000 (15% diskon)
5. Kopi Nescafe - 25,000
6. Buku Pemrograman Java - 85,000 (5% diskon)

---

## Troubleshooting

### Problem: Login tidak bisa
**Solution**: 
- Pastikan username dan password tepat (lihat credentials di atas)
- Cek terminal untuk pesan debug
- Pastikan database sudah diinisialisasi (lihat pesan "DEBUG: Admin user berhasil dibuat" di terminal)

### Problem: Produk tidak muncul di Kasir Panel
**Solution**:
- Pastikan ada produk dengan stok > 0 di database
- Admin bisa menambah produk melalui menu "Kelola Produk"

### Problem: Tidak bisa membuat pesanan
**Solution**:
- Sebagai Customer, pastikan ada produk dengan stok > 0
- Jika ada error "FOREIGN KEY", pastikan kategori dan supplier sudah ada di database

---

## Notes
- Backup database akan dibuat dengan timestamp otomatis
- Setiap transaksi akan mengurangi stok produk secara otomatis
- Status pesanan: PENDING → DIPROSES → SELESAI atau DIBATALKAN
