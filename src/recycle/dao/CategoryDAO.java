package recycle.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import recycle.bean.Category;
import recycle.util.DBUtil;
  
public class CategoryDAO {
  
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from Category";
  
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
  
    public void add(Category bean) {
  
        String sql = "insert into category values(null,?)";
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
  
    public void update(Category bean) {
  
        String sql = "update category set name= ? where id = ?";
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

            String sql = "delete from Category where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Category get(int id) {
        Category bean = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from Category where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                bean = new Category();
                String name = rs.getString(2);
                bean.setName(name);
                bean.setId(id);
            }
            DBUtil.release(c, s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<Category> list(int start, int count) {
        List<Category> beans = new ArrayList<Category>();
  
        String sql = "select * from Category order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Category bean = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                bean.setId(id);
                bean.setName(name);
                beans.add(bean);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
  
}