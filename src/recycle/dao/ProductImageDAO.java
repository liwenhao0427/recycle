package recycle.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import recycle.bean.Product;
import recycle.bean.ProductImage;
import recycle.util.DBUtil;
  
public class ProductImageDAO {
  
   
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from ProductImage";
  
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
  
    public void add(ProductImage bean) {
 
        String sql = "insert into ProductImage values(null,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getProduct().getId());
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
  
    public void update(ProductImage bean) {
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from ProductImage where id = " + id;
  
            s.execute(sql);
            DBUtil.release(c, s, null);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public ProductImage get(int id) {
        ProductImage bean = new ProductImage();
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from ProductImage where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                int pid = rs.getInt("pid");
                String type = rs.getString("type");
                Product product = new ProductDAO().get(pid);
                bean.setProduct(product);
                bean.setId(id);
            }
            DBUtil.release(c, s, rs);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<ProductImage> list(Product p) {
        return list(p,0, Short.MAX_VALUE);
    }
  
    public List<ProductImage> list(Product p, int start, int count) {
        List<ProductImage> beans = new ArrayList<ProductImage>();
  
        String sql = "select * from ProductImage where pid =?  order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, p.getId());
            
            ps.setInt(2, start);
            ps.setInt(3, count);
             
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
 
                ProductImage bean = new ProductImage();
                int id = rs.getInt(1);
 
                bean.setProduct(p);
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