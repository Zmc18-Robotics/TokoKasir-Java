# 🚀 QUICK ACCESS BUTTONS - TESTING BYPASS

## 📍 LOKASI BUTTON

Button tersedia di **pojok kiri bawah** Login Screen:
```
┌──────────────────────────────────────────────┐
│                                              │
│         SISTEM KASIR TOKO                    │
│                                              │
│  Username: [ ________________ ]              │
│  Password: [ ________________ ]              │
│                                              │
│         [ LOGIN ]                            │
│                                              │
│  Demo: username=admin, password=admin123    │
│                                              │
│  [Ad] [Ka] [Cu]  ← BUTTON TEMPORARY INI    │
└──────────────────────────────────────────────┘

[Ad]  = Admin Button (Merah)
[Ka]  = Kasir Button (Hijau)
[Cu]  = Customer Button (Biru)
```

## 🎯 FUNGSI BUTTON

### 1. **Admin Button** (Warna Merah)
- **Posisi**: X=10, Y=620 (pojok kiri bawah)
- **Ukuran**: 60x30 pixel
- **Fungsi**: Bypass login → Langsung masuk sebagai ADMIN
- **User ID**: 1
- **Username**: admin
- **Role**: ADMIN

### 2. **Kasir Button** (Warna Hijau)
- **Posisi**: X=75, Y=620
- **Ukuran**: 60x30 pixel
- **Fungsi**: Bypass login → Langsung masuk sebagai KASIR
- **User ID**: 2
- **Username**: kasir
- **Role**: KASIR

### 3. **Customer Button** (Warna Biru)
- **Posisi**: X=140, Y=620
- **Ukuran**: 70x30 pixel
- **Fungsi**: Bypass login → Langsung masuk sebagai CUSTOMER
- **User ID**: 3
- **Username**: customer
- **Role**: CUSTOMER

## 💻 IMPLEMENTASI TEKNIS

### Method: `addTemporaryButtons()`
```java
private void addTemporaryButtons() {
    // Admin button
    JButton adminButton = new JButton("Admin");
    adminButton.setFont(new Font("Arial", Font.BOLD, 10));
    adminButton.setBounds(10, 620, 60, 30);
    adminButton.setBackground(new Color(200, 50, 50));
    adminButton.setForeground(Color.WHITE);
    adminButton.addActionListener(e -> quickLogin("admin", "ADMIN", 1));
    add(adminButton);
    
    // Kasir button
    JButton kasirButton = new JButton("Kasir");
    kasirButton.setFont(new Font("Arial", Font.BOLD, 10));
    kasirButton.setBounds(75, 620, 60, 30);
    kasirButton.setBackground(new Color(50, 150, 50));
    kasirButton.setForeground(Color.WHITE);
    kasirButton.addActionListener(e -> quickLogin("kasir", "KASIR", 2));
    add(kasirButton);
    
    // Customer button
    JButton customerButton = new JButton("Customer");
    customerButton.setFont(new Font("Arial", Font.BOLD, 10));
    customerButton.setBounds(140, 620, 70, 30);
    customerButton.setBackground(new Color(50, 100, 200));
    customerButton.setForeground(Color.WHITE);
    customerButton.addActionListener(e -> quickLogin("customer", "CUSTOMER", 3));
    add(customerButton);
}
```

### Method: `quickLogin()`
```java
private void quickLogin(String username, String role, int userId) {
    // Create user directly without checking database
    User user = new User(userId, username, role);
    System.out.println("DEBUG: Quick login sebagai " + role + " (" + username + ")");
    frame.setContentPane(new MainPanel(frame, user));
    frame.revalidate();
    frame.repaint();
}
```

## 🎮 CARA MENGGUNAKAN

### Step 1: Jalankan Program
```powershell
cd "c:\Users\Muhammad Zidane A\Documents\Code\Java\TokoKasir"
java -cp "bin;lib/*" Main
```

### Step 2: Lihat Login Screen
Window aplikasi akan muncul dengan Login Screen

### Step 3: Klik Salah Satu Button
- **Klik "Admin"** → Masuk ke dashboard ADMIN
- **Klik "Kasir"** → Masuk ke dashboard KASIR  
- **Klik "Customer"** → Masuk ke dashboard CUSTOMER

### Step 4: Langsung Ke Main Interface
Program akan langsung menampilkan interface sesuai role masing-masing

## 📊 TESTING GUIDE

### Test Admin Access
1. Klik button "Admin"
2. Verifikasi: Header menunjukkan "SISTEM KASIR TOKO - ADMIN"
3. Sidebar menampilkan menu: Kelola Produk, Kategori, Laporan, User, Backup
4. ✅ PASS jika semua menu ada

### Test Kasir Access
1. Klik button "Kasir"
2. Verifikasi: Header menunjukkan "SISTEM KASIR TOKO - KASIR"
3. Sidebar menampilkan menu: Transaksi Penjualan, Lihat Stok, Transaksi Pelanggan
4. ✅ PASS jika hanya 3 menu ada

### Test Customer Access
1. Klik button "Customer"
2. Verifikasi: Header menunjukkan "SISTEM KASIR TOKO - CUSTOMER"
3. Sidebar menampilkan menu: Katalog Produk, Transaksi Saya
4. ✅ PASS jika hanya 2 menu ada

## 🔍 DEBUG OUTPUT

Setiap kali button diklik, console akan menampilkan:
```
DEBUG: Quick login sebagai ADMIN (admin)
```

Atau:
```
DEBUG: Quick login sebagai KASIR (kasir)
```

Atau:
```
DEBUG: Quick login sebagai CUSTOMER (customer)
```

## ⚙️ KONFIGURASI

### Mengubah Posisi Button
Edit `addTemporaryButtons()` method di LoginPanel.java:
```java
// Ubah koordinat X, Y
adminButton.setBounds(X, Y, WIDTH, HEIGHT);

// Contoh: Pindah ke pojok kanan bawah
adminButton.setBounds(930, 620, 60, 30);  // X=930 (kanan)
```

### Mengubah Warna Button
Edit `setBackground()` di `addTemporaryButtons()`:
```java
// Format: new Color(Red, Green, Blue)
// Range: 0-255

// Admin (default merah):
adminButton.setBackground(new Color(200, 50, 50));

// Ubah ke biru:
adminButton.setBackground(new Color(50, 100, 200));
```

### Mengubah Ukuran Button
Edit `.setBounds(x, y, width, height)`:
```java
// Format: (X, Y, WIDTH, HEIGHT)
// Default Admin: (10, 620, 60, 30)

// Ubah ke lebih besar:
adminButton.setBounds(10, 620, 100, 50);  // width=100, height=50
```

## 🚨 PENTING: TEMPORARY BYPASS

⚠️ **INI ADALAH TESTING BYPASS - BUKAN UNTUK PRODUCTION**

Button ini:
- ✅ Bypass login authentication
- ✅ Memungkinkan testing setiap role tanpa tahu password
- ✅ Debug masalah UI per role
- ✅ Mempercepat development

Untuk:
- ❌ Jangan digunakan di production
- ❌ Jangan diberikan ke end user
- ❌ Sebaiknya dihapus setelah testing selesai

## ✅ MENGHAPUS BUTTON (SETELAH TESTING SELESAI)

Untuk menghapus button, edit `LoginPanel.java`:

### Cara 1: Comment Out
```java
// Temporary quick access buttons (bottom left)
// addTemporaryButtons();  // ← Comment out ini
```

### Cara 2: Hapus Seluruh Method
Hapus:
1. Panggilan method: `addTemporaryButtons();`
2. Method `addTemporaryButtons()`
3. Method `quickLogin()`

## 📝 CHANGELOG

### v1.0 (November 12, 2025)
- ✨ NEW: Tambah 3 temporary button
- 📍 Posisi: Pojok kiri bawah
- 🎯 Fungsi: Quick access testing per role
- 🔍 Debug: Console output untuk tracking

## 🎓 LEARNING RESOURCE

Untuk memahami code lebih dalam:

### File: LoginPanel.java
- Lihat method `addTemporaryButtons()` (line ~90)
- Lihat method `quickLogin()` (line ~110)

### File: MainPanel.java
- Lihat class `MainPanel` - interface setelah login
- Lihat sidebar menu generation berdasarkan role
- Lihat content panel switching

### File: User.java
- Lihat class `User` - user role definition
- Method: `isAdmin()`, `isKasir()`, `isCustomer()`

## 💡 TIPS & TRICKS

### Tip 1: Multiple Testing
Bisa klik button berkali-kali tanpa perlu restart aplikasi
- Klik Admin → lihat admin interface
- Logout → kembali ke login screen
- Klik Kasir → lihat kasir interface

### Tip 2: Console Monitoring
Jalankan dari terminal untuk melihat debug output:
```powershell
java -cp "bin;lib/*" Main | Tee-Object -FilePath debug.log
```

### Tip 3: Speed Testing
Setiap button langsung bawa ke interface tanpa validasi
- Cocok untuk UI/UX testing
- Cocok untuk feature testing
- Cocok untuk bug hunting

## 📌 SUMMARY

| Button | Color | Role | User ID | Username |
|--------|-------|------|---------|----------|
| Admin | Red | ADMIN | 1 | admin |
| Kasir | Green | KASIR | 2 | kasir |
| Customer | Blue | CUSTOMER | 3 | customer |

---

**Status**: ✅ ACTIVE & WORKING  
**Purpose**: Testing & Development  
**Temporary**: Yes, dapat dihapus setelah testing  
**Last Updated**: November 12, 2025
