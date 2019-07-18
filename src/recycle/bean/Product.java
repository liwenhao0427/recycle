package recycle.bean;
 
import java.util.Date;
 
import java.util.List;
 
public class Product {
	
    private String name;
    private float price;
    private Date createDate;
    private Category category;
    private int id;
    private String unit;
    private float startNumber;
    private String qRemarks;
    private String tRemarks;
    
    private ProductImage firstProductImage;
    private List<ProductImage> productImages;
    
    private int saleCount;
 
    public float getStartNumber() {
        return startNumber;
    }
    public void setStartNumber(float startNumber) {
        this.startNumber = startNumber;
    }
    
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getqRemarks() {
        return qRemarks;
    }
    public void setqRemrks(String qRemarks) {
        this.qRemarks = qRemarks;
    }
    public String gettRemarks() {
        return tRemarks;
    }
    public void settRemarks(String tRemarks) {
        this.tRemarks = tRemarks;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
     
    public String toString(){
        return name;
    }
    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }
    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }
    public List<ProductImage> getProductImages() {
        return productImages;
    }
    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public int getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
     
}