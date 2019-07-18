package recycle.dao;
 
import java.security.MessageDigest;
import java.sql.Connection;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import recycle.bean.Area;
import recycle.util.DBUtil;
  


public class AreaDAO {
  
	 
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from Area";
  
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
  
    public void add(Area bean) {
  
        String sql = "insert into area values(null ,? )";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
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
  
    public void update(Area bean) {
  
        String sql = "update area set name= ?  where id = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getId());
  
            ps.execute();
            DBUtil.release(c, ps, null);
            
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from Area where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Area get(int id) {
        Area bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from Area where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new Area();
                String name = rs.getString("name");
                bean.setName(name);
                bean.setId(id);
            }
            DBUtil.release(c,s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<Area> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<Area> list(int start, int count) {
        List<Area> beans = new ArrayList<Area>();
  
        String sql = "select * from Area order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Area bean = new Area();
                int id = rs.getInt(1);
 
                String name = rs.getString("name");
                bean.setName(name);
               
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