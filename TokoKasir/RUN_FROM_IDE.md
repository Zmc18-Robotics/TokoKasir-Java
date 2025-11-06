# 🚀 Cara Running Aplikasi dari IDE

Panduan lengkap untuk menjalankan aplikasi langsung dari IDE (IntelliJ, Eclipse, NetBeans, VS Code, dll).

---

## ✅ Persiapan Awal

### 1. Download SQLite JAR
1. Buka: https://github.com/xerial/sqlite-jdbc/releases
2. Download: `sqlite-jdbc-3.44.0.0.jar`
3. **Letakkan di folder**: `lib/sqlite-jdbc-3.44.0.0.jar`

### 2. Struktur Folder Harus Seperti Ini:
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
│   └── sqlite-jdbc-3.44.0.0.jar  ← PENTING!
└── bin/
```

---

## 🎯 Running dari IDE

### **IntelliJ IDEA**

#### Langkah 1: Buka Project
1. File → Open
2. Pilih folder `TokoKasir`
3. Klik Open

#### Langkah 2: Setup Library
1. File → Project Structure
2. Pilih "Libraries"
3. Klik "+" → "Java"
4. Pilih file: `lib/sqlite-jdbc-3.44.0.0.jar`
5. Klik OK

#### Langkah 3: Run Program
1. Buka file: `src/Main.java`
2. Klik kanan pada `Main.java`
3. Pilih "Run 'Main.main()'"
4. Atau tekan: **Shift + F10**

---

### **Eclipse**

#### Langkah 1: Buka Project
1. File → Open Projects from File System
2. Pilih folder `TokoKasir`
3. Klik Finish

#### Langkah 2: Setup Library
1. Klik kanan pada project → Build Path → Configure Build Path
2. Pilih tab "Libraries"
3. Klik "Add External JARs"
4. Pilih: `lib/sqlite-jdbc-3.44.0.0.jar`
5. Klik Apply and Close

#### Langkah 3: Run Program
1. Buka file: `src/Main.java`
2. Klik kanan → Run As → Java Application
3. Atau tekan: **Alt + Shift + X, J**

---

### **NetBeans**

#### Langkah 1: Buka Project
1. File → Open Project
2. Pilih folder `TokoKasir`
3. Klik Open Project

#### Langkah 2: Setup Library
1. Klik kanan pada project → Properties
2. Pilih "Libraries"
3. Klik "Add JAR/Folder"
4. Pilih: `lib/sqlite-jdbc-3.44.0.0.jar`
5. Klik OK

#### Langkah 3: Run Program
1. Buka file: `src/Main.java`
2. Klik kanan → Run File
3. Atau tekan: **Shift + F6**

---

### **Visual Studio Code**

#### Langkah 1: Install Extension
1. Buka VS Code
2. Pergi ke Extensions (Ctrl + Shift + X)
3. Cari: "Extension Pack for Java"
4. Install

#### Langkah 2: Buka Folder
1. File → Open Folder
2. Pilih folder `TokoKasir`
3. Klik Select Folder

#### Langkah 3: Setup Library
1. Buka Command Palette (Ctrl + Shift + P)
2. Ketik: "Java: Configure Classpath"
3. Pilih "Referenced Libraries"
4. Klik "Add"
5. Pilih: `lib/sqlite-jdbc-3.44.0.0.jar`

#### Langkah 4: Run Program
1. Buka file: `src/Main.java`
2. Klik "Run" di atas method `main`
3. Atau tekan: **Ctrl + F5**

---

### **Command Line (Tanpa IDE)**

Jika Anda tidak menggunakan IDE, gunakan command line:

```bash
# 1. Masuk ke folder proyek
cd C:\Users\Muhammad Zidane A\.qodo\TokoKasir

# 2. Compile semua file
javac -d bin -cp lib/sqlite-jdbc-3.44.0.0.jar src/*.java

# 3. Jalankan aplikasi
java -cp bin;lib/sqlite-jdbc-3.44.0.0.jar Main
```

---

## ✅ Jika Berhasil

Jika semua berjalan lancar, akan muncul:

1. **Login Screen** dengan form:
   - Username field
   - Password field
   - Login button

2. **Login dengan:**
   - Username: `admin`
   - Password: `admin123`

3. **Main Dashboard** akan muncul dengan menu sesuai role

---

## ❌ Troubleshooting

### Error: "Class not found: org.sqlite.JDBC"
**Solusi:**
- Pastikan file `sqlite-jdbc-3.44.0.0.jar` ada di folder `lib/`
- Pastikan library sudah ditambahkan ke project classpath
- Restart IDE

### Error: "Cannot find symbol"
**Solusi:**
- Compile ulang semua file
- Pastikan semua file .java ada di folder `src/`
- Restart IDE

### Error: "Main class not found"
**Solusi:**
- Pastikan file `Main.java` ada di folder `src/`
- Pastikan class `Main` memiliki method `main(String[] args)`
- Compile ulang

### Aplikasi tidak merespons saat startup
**Solusi:**
- Tunggu 5-10 detik, database sedang diinisialisasi
- Cek console untuk error messages
- Pastikan folder `lib/` memiliki file JAR

---

## 📝 Catatan Penting

1. **Folder lib/ HARUS ada** dengan file `sqlite-jdbc-3.44.0.0.jar`
2. **Folder src/ HARUS ada** dengan semua file .java
3. **Folder bin/ akan dibuat otomatis** saat compile
4. **Database tokokasir.db akan dibuat otomatis** saat pertama kali running

---

## 🎯 Quick Summary

### Langkah Singkat:
1. ✅ Download `sqlite-jdbc-3.44.0.0.jar`
2. ✅ Letakkan di folder `lib/`
3. ✅ Buka `Main.java` di IDE
4. ✅ Tekan tombol RUN/PLAY
5. ✅ Login dengan `admin/admin123`

**Selesai!** Aplikasi siap digunakan.

---

## 💡 Tips

- Jika menggunakan IDE, lebih mudah tekan tombol RUN/PLAY
- Jika menggunakan command line, ikuti langkah di atas
- Pastikan Java JDK sudah terinstall (bukan hanya JRE)
- Cek versi Java: `java -version` (harus 8 atau lebih tinggi)

---

**Selamat mencoba!** 🚀
