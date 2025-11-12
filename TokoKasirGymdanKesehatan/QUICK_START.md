# 🚀 QUICK START GUIDE - SISTEM KASIR TOKO

## ⚡ INSTANT START

### 1️⃣ Compile (First time or after code changes):
```bash
cd c:\Users\Muhammad Zidane A\Documents\Code\Java\TokoKasir
javac -d bin src/*.java
```

### 2️⃣ Run Application:
```bash
java -cp bin;lib/sqlite-jdbc-3.51.0.0.jar Main
```

### 3️⃣ Run Tests (to verify system):
```bash
java -cp bin;lib/sqlite-jdbc-3.51.0.0.jar AutomatedTests
```

---

## 👤 LOGIN CREDENTIALS

```
┌─────────┬──────────┬─────────┐
│ Username│ Password │ Role    │
├─────────┼──────────┼─────────┤
│ admin   │ admin123 │ ADMIN   │
│ kasir   │ kasir123 │ KASIR   │
│ customer│ customer │ CUSTOMER│
│         │  123     │         │
└─────────┴──────────┴─────────┘
```

---

## 📋 MENU STRUCTURE

### ADMIN
```
├─ Kelola Produk (CRUD Produk)
├─ Kategori Produk (CRUD Kategori)
├─ Laporan Penjualan (View & Stats)
├─ Transaksi Pelanggan (Manage Orders)
├─ Kelola User (CRUD Users)
├─ Backup Database
└─ Logout
```

### KASIR
```
├─ Transaksi Penjualan (Checkout)
├─ Lihat Stok (View Products)
├─ Transaksi Pelanggan (View Orders)
└─ Logout
```

### CUSTOMER
```
├─ Katalog Produk (Browse & Order)
├─ Transaksi Saya (View My Orders)
└─ Logout
```

---

## 🗄️ DATABASE

**Location**: `tokokasir.db` (SQLite)

**Sample Data**:
- Users: 3 (admin, kasir, customer)
- Categories: 4 (Elektronik, Pakaian, Makanan, Buku)
- Suppliers: 3 (Supplier A, B, C)
- Products: 6 (Laptop, Mouse, T-Shirt, Jeans, Kopi, Buku)

---

## 📚 DOCUMENTATION

| File | Purpose |
|------|---------|
| `TESTING_GUIDE.md` | Complete testing instructions |
| `CHANGELOG_FIXES.md` | All changes & fixes made |
| `FINAL_VERIFICATION_REPORT.md` | Test results & status |
| `README.md` | General project info |

---

## 🧪 TEST COVERAGE

- ✅ Database Connection
- ✅ User Creation (3 users)
- ✅ Category Data (4 categories)
- ✅ Supplier Data (3 suppliers)
- ✅ Product Data (6 products)
- ✅ User Authentication (all 3 roles)
- ✅ Database Table Structure (7 tables)

**Result**: All 7/7 Tests PASSED ✓

---

## 🐛 COMMON ISSUES & FIXES

### Issue: "Database connection failed"
**Fix**: Make sure `sqlite-jdbc-3.51.0.0.jar` exists in `lib/` folder

### Issue: "Cannot find symbol"
**Fix**: Recompile with `javac -d bin src/*.java`

### Issue: "Login failed"
**Fix**: 
- Check username & password (case-sensitive)
- Delete `tokokasir.db` and rerun to reset
- Check debug output in console

### Issue: "No products in dropdown"
**Fix**: 
- Login as Admin
- Go to "Kelola Produk"
- Add products with stock > 0

---

## 🔄 DATABASE RESET

To reset database to initial state:

1. Delete `tokokasir.db` file
2. Recompile: `javac -d bin src/*.java`
3. Run application: `java -cp bin;lib/sqlite-jdbc-3.51.0.0.jar Main`

Database will be recreated with sample data automatically.

---

## 📊 SAMPLE DATA

### Products Available:
1. **Laptop Dell** - Rp 5,000,000 (Stock: 10, Discount: 5%)
2. **Mouse Logitech** - Rp 150,000 (Stock: 50, Discount: 0%)
3. **T-Shirt Polos** - Rp 50,000 (Stock: 100, Discount: 10%)
4. **Celana Jeans** - Rp 150,000 (Stock: 50, Discount: 15%)
5. **Kopi Nescafe** - Rp 25,000 (Stock: 200, Discount: 0%)
6. **Buku Pemrograman Java** - Rp 85,000 (Stock: 30, Discount: 5%)

---

## 💡 TIPS

1. **For Admin Testing**: Start by adding/editing products
2. **For Kasir Testing**: Use admin-added products in transactions
3. **For Customer Testing**: Place orders and check status updates
4. **Run AutomatedTests**: Before starting manual testing

---

## ✅ VERIFICATION CHECKLIST

Before reporting issues, check:
- [ ] Database file exists (`tokokasir.db`)
- [ ] JAR file present (`lib/sqlite-jdbc-3.51.0.0.jar`)
- [ ] All files compiled (`bin/` folder has .class files)
- [ ] Using correct credentials
- [ ] Running from correct directory

---

## 📞 DEBUG INFO

Check console output for:
- `DEBUG: Tabel database berhasil dibuat/diverifikasi`
- `DEBUG: Admin user berhasil dibuat`
- `DEBUG: Kasir user berhasil dibuat`
- `DEBUG: Customer user berhasil dibuat`
- `DEBUG: Sample categories berhasil dibuat`
- `DEBUG: Sample suppliers berhasil dibuat`
- `DEBUG: Sample products berhasil dibuat`

If all above messages appear, system is ready.

---

## 🎯 NEXT STEPS

1. ✅ Compile & Run
2. ✅ Test login with all 3 user roles
3. ✅ Run AutomatedTests
4. ✅ Do manual testing per TESTING_GUIDE.md
5. ✅ Report any issues

---

**Version**: 1.0  
**Status**: Production Ready  
**Last Updated**: November 12, 2025  
**Ready for UAT**: Yes ✓
