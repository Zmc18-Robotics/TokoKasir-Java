# File Manifest - Sistem Kasir Toko

Daftar lengkap semua file dalam proyek Sistem Kasir Toko.

---

## 📁 Struktur Direktori

```
TokoKasir/
├── src/                              (Source Code - 13 files)
├── lib/                              (External Libraries)
├── bin/                              (Compiled Files - auto-generated)
├── Documentation Files               (8 files)
├── Build Scripts                     (2 files)
└── Configuration Files               (1 file)
```

---

## 📄 Source Code Files (src/)

### Core Application
| File | Ukuran | Deskripsi |
|------|--------|-----------|
| **Main.java** | ~300 lines | Entry point aplikasi, inisialisasi GUI |
| **DatabaseManager.java** | ~150 lines | Manajemen database, CRUD operations |
| **User.java** | ~50 lines | Model user dengan role |
| **Product.java** | ~60 lines | Model produk dengan harga dan stok |

### UI Panels
| File | Ukuran | Deskripsi |
|------|--------|-----------|
| **LoginPanel.java** | ~100 lines | UI login screen |
| **MainPanel.java** | ~150 lines | Main dashboard dengan sidebar menu |
| **KasirPanel.java** | ~350 lines | UI transaksi penjualan kasir |
| **StockPanel.java** | ~80 lines | UI lihat stok produk |
| **ProductPanel.java** | ~250 lines | UI manajemen produk (CRUD) |
| **CategoryPanel.java** | ~200 lines | UI manajemen kategori (CRUD) |
| **SupplierPanel.java** | ~200 lines | UI manajemen supplier (CRUD) |
| **ReportPanel.java** | ~200 lines | UI laporan penjualan |
| **UserPanel.java** | ~150 lines | UI manajemen user |

**Total Source Code**: ~2,000 lines

---

## 📚 Documentation Files

### Getting Started
| File | Tujuan |
|------|--------|
| **INDEX.md** | Panduan navigasi dokumentasi, quick start guide, FAQ |
| **SETUP.md** | Panduan instalasi lengkap, troubleshooting |
| **QUICK_REFERENCE.md** | Panduan cepat penggunaan sehari-hari |

### Detailed Documentation
| File | Tujuan |
|------|--------|
| **README.md** | Dokumentasi umum, fitur, cara menggunakan |
| **FEATURES.md** | Penjelasan detail setiap fitur per role |
| **SAMPLE_DATA.md** | Data contoh untuk testing, cara menambahkan data |

### Project Information
| File | Tujuan |
|------|--------|
| **PROJECT_SUMMARY.txt** | Ringkasan proyek, overview, struktur |
| **NOTES.md** | Catatan teknis, arsitektur, development notes |

### Quality Assurance
| File | Tujuan |
|------|--------|
| **VERIFICATION_CHECKLIST.md** | Checklist verifikasi semua fitur |
| **FILE_MANIFEST.md** | Daftar file (file ini) |

---

## 🔧 Build & Configuration Files

| File | OS | Tujuan |
|------|----|----|
| **build.bat** | Windows | Script compile dan run aplikasi |
| **build.sh** | Linux/Mac | Script compile dan run aplikasi |

---

## 📊 Database Files (Auto-Generated)

| File | Tujuan |
|------|--------|
| **tokokasir.db** | Database utama SQLite |
| **tokokasir_backup_*.db** | File backup database |

---

## 📦 Library Files (lib/)

| File | Versi | Tujuan |
|------|-------|--------|
| **sqlite-jdbc-3.44.0.0.jar** | 3.44.0.0 | SQLite JDBC Driver |

---

## 📋 File Details

### Main.java
```
Fungsi: Entry point aplikasi
Kelas: Main
Method: main(String[] args)
Tanggung Jawab:
- Inisialisasi GUI
- Setup database
- Show login screen
```

### DatabaseManager.java
```
Fungsi: Manajemen database
Kelas: DatabaseManager
Method Utama:
- initializeDatabase()
- getConnection()
- authenticateUser()
- backupDatabase()
Tanggung Jawab:
- Create/Read/Update/Delete operations
- Database initialization
- User authentication
- Database backup
```

### LoginPanel.java
```
Fungsi: UI login
Kelas: LoginPanel extends JPanel
Komponen:
- JTextField untuk username
- JPasswordField untuk password
- JButton untuk login
- JLabel untuk error message
Tanggung Jawab:
- Tampilkan login form
- Validasi input
- Authenticate user
- Navigate ke main panel
```

### MainPanel.java
```
Fungsi: Main dashboard
Kelas: MainPanel extends JPanel
Komponen:
- Top panel (user info, logout)
- Sidebar (menu navigasi)
- Content panel (dynamic)
Tanggung Jawab:
- Tampilkan menu sesuai role
- Navigate antar panel
- Manage session
```

### KasirPanel.java
```
Fungsi: Transaksi penjualan
Kelas: KasirPanel extends JPanel
Komponen:
- Product dropdown
- Quantity spinner
- Cart table
- Total label
- Print button
Tanggung Jawab:
- Input transaksi
- Manage cart
- Calculate total
- Save transaction
- Print receipt
```

### ProductPanel.java
```
Fungsi: Manajemen produk
Kelas: ProductPanel extends JPanel
Komponen:
- Product table
- Add/Edit/Delete buttons
- Form dialog
Tanggung Jawab:
- Display products
- Add product
- Edit product
- Delete product
- Load categories & suppliers
```

### ReportPanel.java
```
Fungsi: Laporan penjualan
Kelas: ReportPanel extends JPanel
Komponen:
- Statistics panel
- Transaction table
- Detail button
Tanggung Jawab:
- Display sales statistics
- Show transaction list
- Show transaction details
```

---

## 🔐 File Permissions

| File | Read | Write | Execute |
|------|------|-------|---------|
| src/*.java | ✓ | ✓ | - |
| lib/*.jar | ✓ | - | - |
| bin/*.class | ✓ | - | ✓ |
| *.md | ✓ | ✓ | - |
| *.bat | ✓ | ✓ | ✓ |
| *.sh | ✓ | ✓ | ✓ |
| *.db | ✓ | ✓ | - |

---

## 📊 File Statistics

### Source Code
- Total Files: 13
- Total Lines: ~2,000
- Languages: Java
- Largest File: KasirPanel.java (~350 lines)

### Documentation
- Total Files: 10
- Total Lines: ~3,000
- Format: Markdown, Text
- Largest File: FEATURES.md (~500 lines)

### Build Scripts
- Total Files: 2
- Formats: Batch, Shell
- Platforms: Windows, Linux/Mac

### Total Project
- Total Files: 25+
- Total Lines: ~5,000+
- Disk Space: ~500 KB (tanpa database)

---

## 🔄 File Dependencies

### Source Code Dependencies
```
Main.java
├── DatabaseManager.java
├── LoginPanel.java
└── MainPanel.java

MainPanel.java
├── User.java
├── KasirPanel.java
├── StockPanel.java
├── ProductPanel.java
├── CategoryPanel.java
├── SupplierPanel.java
├── ReportPanel.java
└── UserPanel.java

KasirPanel.java
├── User.java
├── Product.java
└── DatabaseManager.java

ProductPanel.java
├── DatabaseManager.java
└── Product.java

ReportPanel.java
└── DatabaseManager.java

UserPanel.java
└── DatabaseManager.java

CategoryPanel.java
└── DatabaseManager.java

SupplierPanel.java
└── DatabaseManager.java

StockPanel.java
└── DatabaseManager.java

LoginPanel.java
├── DatabaseManager.java
└── User.java
```

### External Dependencies
```
All Java Files
└── lib/sqlite-jdbc-3.44.0.0.jar
```

---

## 📝 File Naming Convention

### Java Files
- **Model Classes**: `Product.java`, `User.java`
- **Manager Classes**: `DatabaseManager.java`
- **UI Classes**: `*Panel.java` (e.g., `LoginPanel.java`)
- **Main Class**: `Main.java`

### Documentation Files
- **Guides**: `SETUP.md`, `QUICK_REFERENCE.md`
- **References**: `README.md`, `INDEX.md`
- **Details**: `FEATURES.md`, `NOTES.md`
- **Data**: `SAMPLE_DATA.md`
- **Quality**: `VERIFICATION_CHECKLIST.md`
- **Info**: `PROJECT_SUMMARY.txt`, `FILE_MANIFEST.md`

### Build Files
- **Windows**: `build.bat`
- **Unix**: `build.sh`

### Database Files
- **Main**: `tokokasir.db`
- **Backup**: `tokokasir_backup_YYYYMMDD_HHmmss.db`

---

## 🔍 File Search Guide

### Untuk Menemukan...

**Fitur Kasir**
- Lihat: `src/KasirPanel.java`
- Dokumentasi: `FEATURES.md` (Section 2)

**Fitur Owner**
- Lihat: `src/ProductPanel.java`, `src/ReportPanel.java`, `src/UserPanel.java`
- Dokumentasi: `FEATURES.md` (Section 3)

**Database Operations**
- Lihat: `src/DatabaseManager.java`
- Dokumentasi: `NOTES.md` (Database Operations)

**UI Components**
- Lihat: `src/*Panel.java`
- Dokumentasi: `NOTES.md` (UI Components)

**Setup Instructions**
- Lihat: `SETUP.md`
- Quick: `QUICK_REFERENCE.md`

**Troubleshooting**
- Lihat: `SETUP.md` (Troubleshooting section)
- FAQ: `INDEX.md` (FAQ section)

**Sample Data**
- Lihat: `SAMPLE_DATA.md`

**Testing**
- Lihat: `VERIFICATION_CHECKLIST.md`

---

## 📦 Packaging & Distribution

### Untuk Distribusi
1. Compile: `javac -d bin src/*.java`
2. Include:
   - `src/` (source code)
   - `lib/` (libraries)
   - `bin/` (compiled files)
   - `*.md` (documentation)
   - `*.bat`, `*.sh` (build scripts)
3. Exclude:
   - `tokokasir.db` (akan dibuat otomatis)
   - `tokokasir_backup_*.db` (backup files)

### Ukuran File
- Source Code: ~100 KB
- Compiled: ~200 KB
- Libraries: ~5 MB
- Documentation: ~500 KB
- **Total**: ~6 MB

---

## 🔐 Backup Strategy

### Files to Backup
- `tokokasir.db` (database)
- `src/` (source code)
- `*.md` (documentation)

### Files NOT to Backup
- `bin/` (dapat di-regenerate)
- `tokokasir_backup_*.db` (sudah backup)

### Backup Frequency
- Database: Daily
- Source Code: Weekly
- Documentation: Monthly

---

## 📋 File Checklist

### Sebelum Deployment
- [ ] Semua file .java ada di src/
- [ ] sqlite-jdbc-3.44.0.0.jar ada di lib/
- [ ] build.bat dan build.sh ada
- [ ] Semua dokumentasi .md ada
- [ ] PROJECT_SUMMARY.txt ada
- [ ] VERIFICATION_CHECKLIST.md ada

### Sebelum Distribution
- [ ] Compile berhasil
- [ ] bin/ folder ada dengan .class files
- [ ] Database tokokasir.db terbuat
- [ ] Aplikasi dapat dijalankan
- [ ] Semua fitur berfungsi

---

## 🔄 Version Control

### Git Ignore
```
bin/
*.class
tokokasir.db
tokokasir_backup_*.db
.DS_Store
```

### Commit Strategy
- Commit per fitur
- Include documentation updates
- Tag untuk release

---

## 📞 File Maintenance

### Regular Tasks
- [ ] Update dokumentasi saat ada perubahan
- [ ] Backup database secara berkala
- [ ] Review dan update NOTES.md
- [ ] Update CHANGELOG

### Quarterly Tasks
- [ ] Review semua file
- [ ] Update version numbers
- [ ] Archive old backups
- [ ] Update dependencies

---

**Last Updated**: [Date]
**Total Files**: 25+
**Total Size**: ~6 MB
**Status**: Complete

---

Untuk informasi lebih detail tentang file tertentu, lihat dokumentasi yang sesuai.
