package recycle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recycle.bean.Area;
import recycle.bean.Order;
import recycle.bean.Product;
import recycle.bean.Recycler;
import recycle.bean.User;
import recycle.util.DBUtil;
import recycle.util.DateUtil;
 
public class OrderDAO {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String waitReceive = "waitReceive";
	
	public static final String finish = "finish";
	public static final String delete = "delete";
	public static final String acceptance = "acceptance";
	
	
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from Order_";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            DBUtil.release(c, s, rs);
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(Order bean) {

        String sql = "insert into order_ values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getAddress());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setString(5, bean.getUserMessage());
            
            ps.setTimestamp(6,  DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(7,  DateUtil.d2t(bean.getPayDate()));
            ps.setTimestamp(8,  DateUtil.d2t(bean.getDeliveryDate()));
            ps.setTimestamp(9,  DateUtil.d2t(bean.getConfirmDate()));
            ps.setInt(10, bean.getUser().getId());
            ps.setInt(11, bean.getProduct().getId());
            ps.setInt(12, bean.getUser().getArea().getId());
            Recycler recycler=bean.getRecycler();
            if(null==recycler)ps.setString(13, null);
            else ps.setInt(13, bean.getRecycler().getId());
            
            ps.setFloat(14, bean.getNumber());
            ps.setFloat(15, bean.getSumPrice());
           
            ps.setString(16, bean.getStatus());
            ps.setString(17, bean.getReservation());
            
            
            ps.execute();
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public void update(Order bean) {

        String sql = "update order_ set orderCode =?,address= ?, receiver=?,mobile=?,userMessage=? ,createDate = ? , payDate =? , deliveryDate =?, confirmDate = ? ,  uid=?,pid=?,number=?,sumprice=?, status=?,Reservation=?,aid=?,rid=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getAddress());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setString(5, bean.getUserMessage());
            
            ps.setTimestamp(6,  DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(7,  DateUtil.d2t(bean.getPayDate()));
            ps.setTimestamp(8,  DateUtil.d2t(bean.getDeliveryDate()));
            ps.setTimestamp(9,  DateUtil.d2t(bean.getConfirmDate()));
            ps.setInt(10, bean.getUser().getId());
            ps.setInt(11, bean.getProduct().getId());
            ps.setFloat(12, bean.getNumber());
            ps.setFloat(13, bean.getSumPrice());
           
            ps.setString(14, bean.getStatus());
            ps.setString(15, bean.getReservation());
            
            ps.setInt(16, bean.getUser().getArea().getId());
            Recycler recycler=bean.getRecycler();
            if(null==recycler)ps.setString(17, null);
            else ps.setInt(17, bean.getRecycler().getId());
            
            ps.setInt(18, bean.getId());
            
            System.out.println("update:"+ps);
            ps.execute();
            DBUtil.release(c, ps, null);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from Order_ where id = " + id;
 
            s.execute(sql);
            DBUtil.release(c, s, null);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public Order get(int id) {
        Order bean = new Order();
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from Order_ where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
            	String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");
                int pid =rs.getInt("pid");
                float number=rs.getFloat("number");
                float sumPrice=rs.getFloat("sumprice");
                String reservation = rs.getString("reservation");
                int aid=rs.getInt("aid");
                Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                int rid=rs.getInt("rid");
                Recycler recycler=new RecyclerDAO().get(rid);
                bean.setRecycler(recycler);
                
                
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setNumber(number);
                bean.setSumPrice(sumPrice);
                bean.setReservation(reservation);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                
                bean.setId(id);
            }
            DBUtil.release(c, s, rs);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }
 
    public List<Order> list(int start, int count) {
        List<Order> beans = new ArrayList<Order>();
 
        String sql = "select * from Order_ order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Order bean = new Order();
                int aid=rs.getInt("aid");
                Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                int rid=rs.getInt("rid");
                Recycler recycler=new RecyclerDAO().get(rid);
                bean.setRecycler(recycler);
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");
                int pid =rs.getInt("pid");
                float number=rs.getFloat("number");
                float sumPrice=rs.getFloat("sumprice");
                String reservation = rs.getString("reservation");
                
                
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setNumber(number);
                bean.setSumPrice(sumPrice);
                bean.setReservation(reservation);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                
                            
                
                int id = rs.getInt("id");
                bean.setId(id);
                
                beans.add(bean);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return beans;
    }
    
    public List<Order> list(int uid,String excludedStatus) {
        return list(uid,excludedStatus,0, Short.MAX_VALUE);
    }
     
    public List<Order> list(int uid, String excludedStatus, int start, int count) {
    	List<Order> beans = new ArrayList<Order>();
    	
    	String sql = "select * from Order_ where uid = ? and status != ? order by id desc limit ?,? ";
    	
    	try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
    		
    		ps.setInt(1, uid);
    		ps.setString(2, excludedStatus);
    		ps.setInt(3, start);
    		ps.setInt(4, count);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
    			Order bean = new Order();
    			String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int pid =rs.getInt("pid");
                float number=rs.getFloat("number");
                float sumPrice=rs.getFloat("sumprice");
                String reservation = rs.getString("reservation");
                
                int aid=rs.getInt("aid");
                Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                int rid=rs.getInt("rid");
                Recycler recycler=new RecyclerDAO().get(rid);
                bean.setRecycler(recycler);
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setNumber(number);
                bean.setSumPrice(sumPrice);
                bean.setReservation(reservation);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                
                            
                
                int id = rs.getInt("id");
                bean.setId(id);
    			beans.add(bean);
    		}
            DBUtil.release(c, ps, rs);
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	return beans;
    }
    
    
    public List<Order> recyclerList(int rid,String excludedStatus) {
        return recyclerList(rid,excludedStatus,0, Short.MAX_VALUE);
    }
     
    public List<Order> recyclerList(int rid, String excludedStatus, int start, int count) {
    	List<Order> beans = new ArrayList<Order>();
    	
    	String sql = "select * from Order_ where rid = ? and status = ? order by id desc limit ?,? ";
    	
    	try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
    		
    		ps.setInt(1, rid);
    		ps.setString(2, excludedStatus);
    		ps.setInt(3, start);
    		ps.setInt(4, count);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
    			Order bean = new Order();
    			int uid=rs.getInt("uid");
                
    			String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int pid =rs.getInt("pid");
                float number=rs.getFloat("number");
                float sumPrice=rs.getFloat("sumprice");
                String reservation = rs.getString("reservation");
                
                int aid=rs.getInt("aid");
                Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                Recycler recycler=new RecyclerDAO().get(rid);
                bean.setRecycler(recycler);
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setNumber(number);
                bean.setSumPrice(sumPrice);
                bean.setReservation(reservation);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                
                            
                
                int id = rs.getInt("id");
                bean.setId(id);
    			beans.add(bean);
    		}
            DBUtil.release(c, ps, rs);
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	return beans;
    }
    
    public List<Order> list(String excludedStatus,int aid) {
        return list(aid,excludedStatus,0, Short.MAX_VALUE);
    }
     
    public List<Order> list(String excludedStatus, int start, int count,int aid) {
    	List<Order> beans = new ArrayList<Order>();
    	
    	String sql = "select * from Order_ where status = ? and aid=?  order by id desc limit ?,? ";
    	
    	try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
    		
    		ps.setString(1, excludedStatus);
    		ps.setInt(2,aid);
    		ps.setInt(3, start);
    		ps.setInt(4, count);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
    			Order bean = new Order();
    			String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int pid =rs.getInt("pid");
                float number=rs.getFloat("number");
                float sumPrice=rs.getFloat("sumprice");
                String reservation = rs.getString("reservation");
                
                Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                int rid=rs.getInt("rid");
                Recycler recycler=new RecyclerDAO().get(rid);
                bean.setRecycler(recycler);
                
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setNumber(number);
                bean.setSumPrice(sumPrice);
                bean.setReservation(reservation);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                int uid=rs.getInt("uid");
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                
                            
                int id = rs.getInt("id");
                bean.setId(id);
    			beans.add(bean);
    		}
            DBUtil.release(c, ps, rs);
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	return beans;
    }
}
