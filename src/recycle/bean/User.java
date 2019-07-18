package recycle.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
    private String name;
    private int id;
    private String mobile;
    private String realname;
    private String alipay;
    private Area area;
    private float balance;
    
    public float getBalance() {
    	return balance;
    }
    public void setBalance(float balance) {
    	this.balance=balance;
    }
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealName() {
        return realname;
    }
    public void setRealName(String realname) {
        this.realname = realname;
    }
    
    public String getAlipay() {
        return alipay;
    }
    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }
    
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }
    
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getAnonymousName(){
        if(null==name)
            return null;
         
        if(name.length()<=1)
            return "*";
         
        if(name.length()==2)
            return name.substring(0,1) +"*";
         
        char[] cs =name.toCharArray();
        for (int i = 1; i < cs.length-1; i++) {
            cs[i]='*';
        }
        return new String(cs);
         
    }
     
}