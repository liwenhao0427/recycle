package recycle.bean;
 
public class ProductImage {
     
    private Product product;
    private int id;
 
    public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
 
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}