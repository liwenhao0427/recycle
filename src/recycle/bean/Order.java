package recycle.bean;

import java.util.Date;


import recycle.dao.OrderDAO;

public class Order {
	private int id;
	private String orderCode;
	private String address;
	private String receiver;
	private String mobile;
	private String userMessage;
	private Date payDate;
	private Date deliveryDate;
	private Date createDate;
	private Date confirmDate;
	private User user;
	private Product product;
	private float number;
	private float sumPrice;
	private String reservation;
	private Area area;
	private Recycler recycler;
	
	public Area getArea() {
		return area;
	}
	
	public void setArea(Area area) {
		this.area=area;
	}
	
	public Recycler getRecycler() {
		return recycler;
	}
	
	public void setRecycler(Recycler recycler) {
		this.recycler=recycler;
	}
	
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product=product;
	}
	
	public void setNumber(float number) {
		this.number=number;
	}
	public float getNumber() {
		return this.number;
	}
	
	public float getSumPrice() {
		return sumPrice;
	}
	
	public void setSumPrice(float sumPrice) {
		this.sumPrice=sumPrice;
	}
	
	public String getReservation() {
		return this.reservation;
	}
	
	public void setReservation(String reservation) {
		this.reservation=reservation;
	}
	
	
	private int totalNumber;
	private String status;
	
	public String getStatusDesc(){
		String desc ="未知";
		switch(status){
			case OrderDAO.waitPay:
				desc="待付款";
				break;
			case OrderDAO.waitDelivery:
				desc="待配送";
				break;
			case OrderDAO.acceptance:
				desc="待验收";
				break;
			case OrderDAO.waitConfirm:
				desc="待确认";
				break;
			case OrderDAO.finish:
				desc="完成";
				break;
			case OrderDAO.delete:
				desc="刪除";
				break;
			case OrderDAO.waitReceive:
				desc="等待接单";
				break;
			default:
				desc="未知";
		}
		return desc;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	
}
