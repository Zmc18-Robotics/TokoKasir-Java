public class User {
    private int id;
    private String username;
    private String role; // KASIR, OWNER, ADMIN, CUSTOMER
    
    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public boolean isKasir() {
        return "KASIR".equals(role);
    }
    
    public boolean isOwner() {
        return "OWNER".equals(role);
    }
    
    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }
    
    public boolean isCustomer() {
        return "CUSTOMER".equals(role);
    }
}
