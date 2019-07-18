package recycle.bean;
 
public class OrderImage {
     
    private Order order;
    private int id;
 
    public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
 
   
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}