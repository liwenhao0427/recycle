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
 
import recycle.bean.Recycler;
import recycle.util.DBUtil;
import recycle.util.ShaUtil;
  


public class RecyclerDAO {
  
	 
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from Recycler";
  
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
  
    public void add(Recycler bean) {
  
        String sql = "insert into Recycler values(null ,? ,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            String passwordSha = ShaUtil.getSha1(bean.getPassword());
            ps.setString(2, passwordSha);
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getRealName());
            ps.setString(5, bean.getAlipay());
            ps.setInt(6, bean.getArea().getId());
            
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
  
    public void update(Recycler bean) {
  
        String sql = "update Recycler set name= ? , password = ?,mobile = ?,realname=?,alipay=?,aid=?  where id = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            String passwordSha = ShaUtil.getSha1(bean.getPassword());
            ps.setString(2, passwordSha);
            
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getRealName());
            ps.setString(5, bean.getAlipay());
            ps.setInt(6, bean.getArea().getId());
            
            ps.setInt(7, bean.getId());
            
            
            
            System.out.println(ps);
            DBUtil.release(c, ps, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from Recycler where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Recycler get(int id) {
        Recycler bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from Recycler where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new Recycler();
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
                
                
                bean.setId(id);
               
            } DBUtil.release(c, s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<Recycler> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<Recycler> list(int start, int count) {
        List<Recycler> beans = new ArrayList<Recycler>();
  
        String sql = "select * from Recycler order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Recycler bean = new Recycler();
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
                
                
                 
                bean.setId(id);
                beans.add(bean);
                
            }DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
 
    public boolean isExist(String name) {
        Recycler recycler = get(name);
        return recycler!=null;
 
    }
 
    public Recycler get(String name) {
        Recycler bean = null;
          
        String sql = "select * from Recycler where name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new Recycler();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                int aid = rs.getInt("aid");
                recycle.bean.Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                
                bean.setPassword(password);
                bean.setId(id);
                
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
 
    public Recycler get(String name, String password) {
        Recycler bean = null;
          
        String sql = "select * from Recycler where name = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            String passwordSha = ShaUtil.getSha1(password);
            ps.setString(2, passwordSha);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new Recycler();
                int id = rs.getInt("id");
                String mobile = rs.getString("mobile");
                bean.setMobile(mobile);
                String realname = rs.getString("realname");
                bean.setRealName(realname);
                String alipay = rs.getString("alipay");
                bean.setAlipay(alipay);
                int aid = rs.getInt("aid");
                recycle.bean.Area area=new AreaDAO().get(aid);
                bean.setArea(area);
                
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
               
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
}