# Setup Aplikasi Kasir Toko

## Langkah-Langkah Setup

### 1. Persiapan Awal

Pastikan Anda memiliki:
- Java JDK 8 atau lebih tinggi (download dari https://www.oracle.com/java/technologies/downloads/)
- Command Prompt atau Terminal

### 2. Download SQLite JDBC Driver

Aplikasi ini membutuhkan SQLite JDBC Driver untuk berkomunikasi dengan database.

**Opsi A: Download Manual**
1. Buka https://github.com/xerial/sqlite-jdbc/releases
2. Download file `sqlite-jdbc-3.44.0.0.jar` (atau versi terbaru)
3. Letakkan file tersebut di folder `lib/` dalam direktori proyek

**Opsi B: Menggunakan Maven** (jika sudah terinstall)
```bash
mvn dependency:copy-dependencies -DoutputDirectory=lib
```

### 3. Struktur Folder

Pastikan struktur folder seperti ini:
```
TokoKasir/
├── src/
│   ├── Main.java
│   ├── DatabaseManager.java
│   ├── User.java
│   ├── Product.java
│   ├── LoginPanel.java
│   ├── MainPanel.java
│   ├── KasirPanel.java
│   ├── StockPanel.java
│   ├── ProductPanel.java
│   ├── CategoryPanel.java
│   ├── SupplierPanel.java
│   ├── ReportPanel.java
│   └── UserPanel.java
├── lib/
│   └── sqlite-jdbc-3.44.0.0.jar
├── bin/                    (akan dibuat otomatis)
├── build.bat
├── README.md
└── SETUP.md
```

### 4. Compile Program

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

### 5. Jalankan Program

**Windows (menggunakan batch file):**
```bash
build.bat
```

**Windows (manual):**
```bash
java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main
```

**Linux/Mac:**
```bash
java -cp bin:lib/sqlite-jdbc-3.44.0.0.jar Main
```

### 6. Login Pertama Kali

Setelah aplikasi berjalan, gunakan kredensial default:
- **Username**: admin
- **Password**: admin123

## Troubleshooting

### Error: "Class not found: org.sqlite.JDBC"
**Solusi**: Pastikan file `sqlite-jdbc-3.44.0.0.jar` sudah ada di folder `lib/`

### Error: "Cannot find symbol"
**Solusi**: Pastikan semua file `.java` sudah di-compile dengan benar. Coba compile ulang:
```bash
javac -d bin src/*.java
```

### Error: "Database file not found"
**Solusi**: Database akan dibuat otomatis saat pertama kali menjalankan aplikasi. Pastikan folder proyek memiliki write permission.

### Aplikasi tidak merespons
**Solusi**: Tunggu beberapa detik, database sedang diinisialisasi. Jika masih tidak merespons, tutup dan jalankan ulang.

## Verifikasi Instalasi

Untuk memverifikasi bahwa semuanya terinstall dengan benar:

1. Buka Command Prompt/Terminal
2. Navigasi ke folder proyek
3. Jalankan:
   ```bash
   java -version
   ```
   Pastikan versi Java 8 atau lebih tinggi

4. Cek apakah file SQLite JDBC ada:
   ```bash
   dir lib
   ```
   atau
   ```bash
   ls lib
   ```

## Membuat Shortcut (Windows)

Untuk memudahkan menjalankan aplikasi, buat file `run.bat`:

```batch
@echo off
cd /d "%~dp0"
java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main
pause
```

Kemudian double-click file `run.bat` untuk menjalankan aplikasi.

## Catatan Penting

1. **Database**: File `tokokasir.db` akan dibuat di folder yang sama dengan aplikasi
2. **Backup**: File backup akan disimpan dengan nama `tokokasir_backup_YYYYMMDD_HHmmss.db`
3. **Folder bin**: Folder ini akan dibuat otomatis saat compile
4. **Permissions**: Pastikan folder proyek memiliki write permission untuk membuat database

## Setelah Setup Berhasil

1. Login dengan username: `admin` dan password: `admin123`
2. Buat user baru untuk Kasir dan Owner
3. Tambahkan kategori dan supplier
4. Tambahkan produk
5. Mulai melayani transaksi

Selamat menggunakan Sistem Kasir Toko!
