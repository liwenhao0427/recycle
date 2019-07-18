package recycle.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

import recycle.bean.OrderImage;
import recycle.bean.Order;
import recycle.util.DBUtil;
  
public class OrderImageDAO {
  
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from OrderImage";
  
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
  
    public void add(OrderImage bean) {
 
        String sql = "insert into OrderImage values(null,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getOrder().getId());
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
  
    public void update(OrderImage bean) {
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from OrderImage where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public OrderImage get(int id) {
    	OrderImage bean = new OrderImage();
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from OrderImage where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                int oid = rs.getInt("oid");
                Order order = new OrderDAO().get(oid);
                
                bean.setId(id);
                bean.setOrder(order);
            }
            DBUtil.release(c, s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<OrderImage> list(Order p) {
        return list(p,0, Short.MAX_VALUE);
    }
  
    public List<OrderImage> list(Order p, int start, int count) {
        List<OrderImage> beans = new ArrayList<OrderImage>();
  
        String sql = "select * from OrderImage where cid =?  order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, p.getId());
           
            ps.setInt(2, start);
            ps.setInt(3, count);
             
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
 
            	OrderImage bean = new OrderImage();
                int id = rs.getInt(1);
                int oid = rs.getInt("oid");
                Order order = new OrderDAO().get(oid);
                
                bean.setId(id);
                bean.setOrder(order);
               
                   
                beans.add(bean);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
  
}