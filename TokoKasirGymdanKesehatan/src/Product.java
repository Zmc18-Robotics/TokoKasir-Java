public class Product {
    private int id;
    private String name;
    private int categoryId;
    private int supplierId;
    private double price;
    private int stock;
    private double discount;
    private String description;
    private String imagePath;
    
    public Product(int id, String name, int categoryId, int supplierId, 
                   double price, int stock, double discount, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.description = description;
        this.imagePath = imagePath;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCategoryId() { return categoryId; }
    public int getSupplierId() { return supplierId; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public double getDiscount() { return discount; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
    
    public double getFinalPrice() {
        return price * (1 - discount / 100);
    }
}
