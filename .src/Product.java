public class Product {
    int productNum;
    String description;
    String department;
    Double price;
    Double storeDis;
    Double loyaltyDis;
    Double digitalCoup;

    public Product(int productNum, String description, String department, Double price, Double storeDis, Double loyaltyDis, Double digitalCoup){
        this.productNum = productNum;
        this.description = description;
        this.department = department;
        this.price = price;
        this.storeDis = storeDis;
        this.loyaltyDis = loyaltyDis;
        this.digitalCoup = digitalCoup;
    }

    public int getProductNum(){
        return productNum;
    }

    public String getDescription(){
        return description;
    }

    public String getDepartment(){
        return department;
    }

    public Double getPrice(){
        return price;
    }

    public Double getStoreDiscount(){
        return storeDis;
    }

    public Double getLoyaltyDiscount(){
        return loyaltyDis;
    }

    public Double getDigitalCoupon(){
        return digitalCoup;
    }
}
