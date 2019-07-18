package recycle.dao;
 
import java.awt.geom.Area;
import java.security.MessageDigest;
import java.sql.Connection;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import recycle.bean.User;
import recycle.util.DBUtil;
import recycle.util.ShaUtil;
  


public class UserDAO {

	 
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from User";
  
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
  
    public void add(User bean) {
  
        String sql = "insert into user values(null ,? ,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            String passwordSha = ShaUtil.getSha1(bean.getPassword());
            ps.setString(2, passwordSha);
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getRealName());
            ps.setString(5, bean.getAlipay());
            ps.setFloat(6, bean.getBalance());
            ps.setInt(7, bean.getArea().getId());
            
            System.out.println(ps);
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
  
    public void update(User bean) {
  
        String sql = "update user set name= ? , password = ?,mobile = ?,realname=?,alipay=?,aid=?,balance=?  where id = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            String passwordSha = ShaUtil.getSha1(bean.getPassword());
            ps.setString(2, passwordSha);
            
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getRealName());
            ps.setString(5, bean.getAlipay());
            ps.setInt(6, bean.getArea().getId());
            ps.setFloat(7, bean.getBalance());
            
            
            ps.setInt(8, bean.getId());
            
            System.out.println("User update"+ps);
            
            ps.execute();
            DBUtil.release(c, ps, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from User where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public User get(int id) {
        User bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from User where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new User();
                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                int aid = rs.getInt("aid");
                recycle.bean.Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                float balance = rs.getFloat("balance");
                bean.setBalance(balance);
                
                bean.setId(id);
                
            }
            DBUtil.release(c, s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();
  
        String sql = "select * from User order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                User bean = new User();
                int id = rs.getInt(1);
 
                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                int aid = rs.getInt("aid");
                recycle.bean.Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                float balance = rs.getFloat("balance");
                bean.setBalance(balance);
                 
                bean.setId(id);
                beans.add(bean);
                
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
 
    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;
 
    }
 
    public User get(String name) {
        User bean = null;
          
        String sql = "select * from User where name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                
                bean.setId(id);
                
                
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                int aid = rs.getInt("aid");
                float balance = rs.getFloat("balance");
                bean.setBalance(balance);
                recycle.bean.Area area=new AreaDAO().get(aid);
                
                bean.setArea(area);
                
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
 
    public User get(String name, String password) {
        User bean = null;
          
        String sql = "select * from User where name = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            String passwordSha = ShaUtil.getSha1(password);
            ps.setString(2, passwordSha);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
                
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                float balance = rs.getFloat("balance");
                bean.setBalance(balance);
                int aid = rs.getInt("aid");
                recycle.bean.Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
}