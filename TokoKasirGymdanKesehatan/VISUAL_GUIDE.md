# 🎨 VISUAL GUIDE - TEMPORARY BUTTON IMPLEMENTATION

## 📐 UI Layout

### Login Screen - SEBELUM (Original)
```
┌─────────────────────────────────────────────────┐
│              SISTEM KASIR TOKO                  │  ← Title
│                                                 │
│  Username:  [________________]                  │
│  Password:  [________________]                  │
│                                                 │
│  Error message area (jika ada)                 │
│                                                 │
│            [   LOGIN BUTTON  ]                  │
│                                                 │
│  Demo: username=admin, password=admin123       │
│                                                 │
│                                                 │
└─────────────────────────────────────────────────┘
```

### Login Screen - SESUDAH (With Temporary Buttons)
```
┌─────────────────────────────────────────────────┐
│              SISTEM KASIR TOKO                  │  ← Title
│                                                 │
│  Username:  [________________]                  │
│  Password:  [________________]                  │
│                                                 │
│  Error message area (jika ada)                 │
│                                                 │
│            [   LOGIN BUTTON  ]                  │
│                                                 │
│  Demo: username=admin, password=admin123       │
│                                                 │
│  [Ad] [Ka] [Cu]  ← TEMPORARY BUTTONS          │
└─────────────────────────────────────────────────┘
     ↑
Pojok kiri bawah
X: 10-210px
Y: 620px
```

---

## 🔘 Button Details

### Button 1: Admin
```
┌──────┐
│ Admin│ ← Text: "Admin"
└──────┘
  ↑
Color: RGB(200, 50, 50) - Red
Position: X=10, Y=620
Size: 60 x 30 pixels
```

### Button 2: Kasir
```
┌──────┐
│ Kasir│ ← Text: "Kasir"
└──────┘
  ↑
Color: RGB(50, 150, 50) - Green
Position: X=75, Y=620
Size: 60 x 30 pixels
```

### Button 3: Customer
```
┌────────┐
│Customer│ ← Text: "Customer"
└────────┘
  ↑
Color: RGB(50, 100, 200) - Blue
Position: X=140, Y=620
Size: 70 x 30 pixels
```

---

## 📊 Coordinate System

```
Window = 1000 x 700

     0                    500                  1000
     ┌────────────────────┬────────────────────┐
   0 │                    │                    │
     │                    │                    │
 100 │         TITLE      │                    │
     │    SISTEM KASIR    │                    │
 200 │                    │                    │
     ├─ Username Field ───┤                    │
 250 │                    │                    │
     ├─ Password Field ───┤                    │
 300 │                    │                    │
     │                    │                    │
 400 │    LOGIN BUTTON    │                    │
     │                    │                    │
 480 │    Demo Info       │                    │
     │                    │                    │
 600 │                    │                    │
     │ [B][B][B]          │                    │ ← BUTTONS HERE
 620 │                    │                    │
     │                    │                    │
 700 └────────────────────┴────────────────────┘
```

---

## 🎯 Button Click Flow Diagram

```
┌─────────────────────────────────────────┐
│         User Sees Login Screen          │
│    with 3 Buttons (Red, Green, Blue)    │
└──────────────┬──────────────────────────┘
               │
       ┌───────┼───────┐
       ↓       ↓       ↓
    [ADMIN] [KASIR] [CUSTOMER]
       │       │       │
       ↓       ↓       ↓
  onClick  onClick  onClick
    │       │       │
    ├─→ quickLogin("admin", "ADMIN", 1)
    ├─→ quickLogin("kasir", "KASIR", 2)
    └─→ quickLogin("customer", "CUSTOMER", 3)
       │
       ↓
  User user = new User(id, username, role)
       │
       ↓
  MainPanel mainPanel = new MainPanel(frame, user)
       │
       ↓
  frame.setContentPane(mainPanel)
       │
       ↓
  frame.revalidate()
  frame.repaint()
       │
       ↓
┌─────────────────────────────────────────┐
│   Dashboard Muncul Sesuai Role           │
│   (Admin/Kasir/Customer Interface)       │
└─────────────────────────────────────────┘
```

---

## 🎭 Role-Based Interface

### ADMIN Dashboard
```
┌─────────────────────────────────────────────────┐
│ SISTEM KASIR TOKO - ADMIN                       │ ← Header
├──────────────┬──────────────────────────────────┤
│ SIDEBAR      │ CONTENT PANEL                    │
│ (200px wide) │ (800px wide)                     │
│              │                                  │
│ [Button 1]   │ Admin Content                    │
│ Kelola       │ (Product Mgmt, Reports, etc)    │
│ Produk       │                                  │
│              │                                  │
│ [Button 2]   │                                  │
│ Kategori     │                                  │
│ Produk       │                                  │
│              │                                  │
│ [Button 3]   │                                  │
│ Laporan      │                                  │
│              │                                  │
│ [Button 4]   │                                  │
│ Transaksi    │                                  │
│              │                                  │
│ [Button 5]   │                                  │
│ Kelola User  │                                  │
│              │                                  │
│ [Button 6]   │                                  │
│ Backup       │                                  │
│              │                                  │
│              │ [LOGOUT BTN]                     │
└──────────────┴──────────────────────────────────┘
```

### KASIR Dashboard
```
┌─────────────────────────────────────────────────┐
│ SISTEM KASIR TOKO - KASIR                       │ ← Header
├──────────────┬──────────────────────────────────┤
│ SIDEBAR      │ CONTENT PANEL                    │
│ (200px wide) │ (800px wide)                     │
│              │                                  │
│ [Button 1]   │ Kasir Content                    │
│ Transaksi    │ (POS/Transaction Screen)        │
│ Penjualan    │                                  │
│              │                                  │
│ [Button 2]   │                                  │
│ Lihat Stok   │                                  │
│              │                                  │
│ [Button 3]   │                                  │
│ Transaksi    │                                  │
│ Pelanggan    │                                  │
│              │                                  │
│              │                                  │
│              │                                  │
│              │ [LOGOUT BTN]                     │
└──────────────┴──────────────────────────────────┘
```

### CUSTOMER Dashboard
```
┌─────────────────────────────────────────────────┐
│ SISTEM KASIR TOKO - CUSTOMER                    │ ← Header
├──────────────┬──────────────────────────────────┤
│ SIDEBAR      │ CONTENT PANEL                    │
│ (200px wide) │ (800px wide)                     │
│              │                                  │
│ [Button 1]   │ Customer Content                 │
│ Katalog      │ (Product Catalog)               │
│ Produk       │                                  │
│              │                                  │
│ [Button 2]   │                                  │
│ Transaksi    │                                  │
│ Saya         │                                  │
│              │                                  │
│              │                                  │
│              │                                  │
│              │ [LOGOUT BTN]                     │
└──────────────┴──────────────────────────────────┘
```

---

## 🎨 Color Reference

### Admin Button (Red)
```
RGB(200, 50, 50)
┌─────────────────┐
│ Hex: #C83232    │
│ Visual:         │
│   █████████     │ ← Red/Dark Red
└─────────────────┘
```

### Kasir Button (Green)
```
RGB(50, 150, 50)
┌─────────────────┐
│ Hex: #329632    │
│ Visual:         │
│   █████████     │ ← Green/Dark Green
└─────────────────┘
```

### Customer Button (Blue)
```
RGB(50, 100, 200)
┌─────────────────┐
│ Hex: #3264C8    │
│ Visual:         │
│   █████████     │ ← Blue/Dark Blue
└─────────────────┘
```

---

## 📍 Pixel Coordinates

### Frame Window Dimensions
- **Total Width**: 1000 pixels
- **Total Height**: 700 pixels

### Button Positioning (from top-left origin)

```
Frame Window (1000 x 700)
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│                                                             │
│                                                             │
│                    ... content ...                          │
│                                                             │
│                                                             │
│  (10,620)   (75,620)   (140,620)                           │
│  ┌──────┐ ┌──────┐ ┌────────┐                              │
│  │Admin │ │Kasir │ │Customer│                              │
│  └──────┘ └──────┘ └────────┘                              │
│  ↑       ↑       ↑                                          │
│  Y=620   Y=620   Y=620                                      │
│  X=10    X=75    X=140                                      │
│                                                             │
└─────────────────────────────────────────────────────────────┘
   X: 0                                                      1000
   Y: 700
```

---

## 🔄 Program Flow Diagram

```
START
  ↓
Main.java
  ↓
JFrame Created (1000x700)
  ↓
LoginPanel(frame)
  ↓
Constructor runs:
  - Add title, username field, password field
  - Add login button
  - Add demo label
  - addTemporaryButtons() ← CALL THIS
  ↓
addTemporaryButtons():
  - Create Admin button (Red)
    ├─ Position: 10, 620
    ├─ Size: 60x30
    ├─ Color: Red
    └─ OnClick: quickLogin("admin", "ADMIN", 1)
  
  - Create Kasir button (Green)
    ├─ Position: 75, 620
    ├─ Size: 60x30
    ├─ Color: Green
    └─ OnClick: quickLogin("kasir", "KASIR", 2)
  
  - Create Customer button (Blue)
    ├─ Position: 140, 620
    ├─ Size: 70x30
    ├─ Color: Blue
    └─ OnClick: quickLogin("customer", "CUSTOMER", 3)
  ↓
frame.setVisible(true)
  ↓
LoginPanel displayed with 3 buttons
  ↓
USER CLICKS BUTTON
  ↓
quickLogin() called
  ↓
User object created
MainPanel created & displayed
  ↓
Dashboard muncul sesuai role
```

---

## 🎓 Code Structure Visualization

```
LoginPanel.java
├── Constructor
│   ├── setLayout(null)  ← Absolute positioning
│   ├── Add title label
│   ├── Add username field
│   ├── Add password field
│   ├── Add error label
│   ├── Add login button
│   ├── Add demo label
│   └── addTemporaryButtons()  ← NEW
│
├── addTemporaryButtons()  ← NEW METHOD
│   ├── Create Admin button
│   │   └── setBounds(10, 620, 60, 30)
│   ├── Create Kasir button
│   │   └── setBounds(75, 620, 60, 30)
│   ├── Create Customer button
│   │   └── setBounds(140, 620, 70, 30)
│   └── Add all buttons to panel
│
├── quickLogin()  ← NEW METHOD
│   ├── Create User object
│   ├── Print debug message
│   ├── Create MainPanel
│   ├── Set as content pane
│   └── Revalidate & repaint
│
└── handleLogin()  ← EXISTING (UNCHANGED)
    ├── Get input from fields
    ├── Validate auth (database)
    └── Show MainPanel or error
```

---

## 📋 Testing Checklist with Visual Positions

```
GUI POSITIONS TO CHECK:

Login Screen (1000x700):
  ☑ Title "SISTEM KASIR TOKO" centered at top
  ☑ Username field in middle-left
  ☑ Password field below username
  ☑ Large LOGIN button below password
  ☑ Demo text below login button
  ☑ Admin button at (10, 620) - RED
  ☑ Kasir button at (75, 620) - GREEN
  ☑ Customer button at (140, 620) - BLUE

Dashboard (after clicking button):
  ☑ Header shows role correctly
  ☑ Sidebar shows correct menu count
  ☑ Content panel displays
  ☑ Logout button present
```

---

## 🎯 Success Indicators

```
✅ PASS If:
  • Buttons visible at bottom-left of login screen
  • Each button has correct color (Red/Green/Blue)
  • Clicking button switches to dashboard
  • Dashboard header matches role
  • Correct menu items appear per role
  • Logout button works
  • Returns to login screen
  • Can click different button

❌ FAIL If:
  • Buttons not visible
  • Wrong colors
  • Click doesn't do anything
  • Dashboard doesn't appear
  • Wrong menu items
  • No logout button
  • Crash on button click
```

---

**Visual guide complete!** 🎨

Sekarang Anda punya gambaran jelas tentang layout, positioning, dan flow aplikasi.

Silakan jalankan program dan verifikasi visual sesuai guide ini!
