package recycle.bean;

import java.util.Date;

public class Withdraw {
     
    private int id;
    private User user;
    private Date createDate;
	private Date payDate;
	private float val;
	private String status;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user=user;
	}
	
	public float getVal() {
		return val;
	}
	
	public void setVal(float val) {
		this.val=val;
	}
	
	public String getStatus() {
		return status;
	}
	
    public void setStatus(String status) {
    	this.status=status;
    }
    
    public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
   
    
}