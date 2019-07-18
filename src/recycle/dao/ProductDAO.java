package recycle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recycle.bean.Category;
import recycle.bean.Product;
import recycle.bean.ProductImage;
import recycle.util.DBUtil;
import recycle.util.DateUtil;

public class ProductDAO {
 
    public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from Product where cid = " + cid;
 
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
 
    public void add(Product bean) {

        String sql = "insert into Product values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, bean.getName());
            
            ps.setFloat(2, bean.getPrice());
            ps.setString(3, bean.getUnit());
            ps.setFloat(4, bean.getStartNumber());
            ps.setInt(5, bean.getCategory().getId());
            ps.setTimestamp(6, DateUtil.d2t(bean.getCreateDate()));
            ps.setString(7, bean.getqRemarks());
            ps.setString(8, bean.gettRemarks());
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
 
    public void update(Product bean) {

        String sql = "update Product set name= ?, price=?, unit=?,startnumber=?,cid=?, createDate = ?, qRemarks=?,tRemarks=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
ps.setString(1, bean.getName());
            
            ps.setFloat(2, bean.getPrice());
            ps.setString(3, bean.getUnit());
            ps.setFloat(4, bean.getStartNumber());
            ps.setInt(5, bean.getCategory().getId());
            ps.setTimestamp(6, DateUtil.d2t(bean.getCreateDate()));
            ps.setString(7, bean.getqRemarks());
            ps.setString(8, bean.gettRemarks());
            ps.setInt(9, bean.getId());
            ps.execute();
            DBUtil.release(c, ps, null);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from Product where id = " + id;
            
            s.execute(sql);
            DBUtil.release(c,s, null);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public Product get(int id) {
        Product bean = new Product();
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from Product where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {

                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float startNumber = rs.getFloat("startNumber");
                String unit = rs.getString("unit");
                
                int cid = rs.getInt("cid");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String qRemarks = rs.getString("qRemarks");
                String tRemarks = rs.getString("tRemarks");
                
                
                bean.setName(name);
                bean.setPrice(price);
                bean.setStartNumber(startNumber);
                bean.setUnit(unit);
                bean.setqRemrks(qRemarks);
                bean.settRemarks(tRemarks);
                
                
                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);
            }
            DBUtil.release(c, s, rs);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
    }
 
    //列出分类下的图书
    public List<Product> list(int cid) {
        return list(cid,0, Short.MAX_VALUE);
    }
 
    public List<Product> list(int cid, int start, int count) {
        List<Product> beans = new ArrayList<Product>();
        Category category = new CategoryDAO().get(cid);
        String sql = "select * from Product where cid = ? order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, cid);
        	ps.setInt(2, start);
            ps.setInt(3, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float startNumber = rs.getFloat("startNumber");
                String unit = rs.getString("unit");
                
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String qRemarks = rs.getString("qRemarks");
                String tRemarks = rs.getString("tRemarks");
                
                
                bean.setName(name);
                bean.setPrice(price);
                bean.setStartNumber(startNumber);
                bean.setUnit(unit);
                bean.setqRemrks(qRemarks);
                bean.settRemarks(tRemarks);
                
                
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);
                beans.add(bean);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return beans;
    }
    public List<Product> list() {
    	return list(0,Short.MAX_VALUE);
    }
    public List<Product> list(int start, int count) {
        List<Product> beans = new ArrayList<Product>();

        String sql = "select * from Product limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

        	ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float startNumber = rs.getFloat("startNumber");
                String unit = rs.getString("unit");
                
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String qRemarks = rs.getString("qRemarks");
                String tRemarks = rs.getString("tRemarks");
                
                
                bean.setName(name);
                bean.setPrice(price);
                bean.setStartNumber(startNumber);
                bean.setUnit(unit);
                bean.setqRemrks(qRemarks);
                bean.settRemarks(tRemarks);
                
                
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);

                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);
                beans.add(bean);
            }
            DBUtil.release(c, ps, rs);
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return beans;
    }    

    //填充分类信息
	public void fill(List<Category> cs) {
		for (Category c : cs) 
			fill(c);
	}
	public void fill(Category c) {
			List<Product> ps = this.list(c.getId());
			c.setProducts(ps);
	}

	public void fillByRow(List<Category> cs) {
		int productNumberEachRow = 8;
		for (Category c : cs) {
			List<Product> products =  c.getProducts();
			List<List<Product>> productsByRow =  new ArrayList<>();
			for (int i = 0; i < products.size(); i+=productNumberEachRow) {
				int size = i+productNumberEachRow;
				size= size>products.size()?products.size():size;
				List<Product> productsOfEachRow =products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			c.setProductsByRow(productsByRow);
		}
	}
	
	public void setFirstProductImage(Product p) {
		List<ProductImage> pis= new ProductImageDAO().list(p);
		if(!pis.isEmpty())
			p.setFirstProductImage(pis.get(0));		
	}
	
	

	public List<Product> search(String keyword, int start, int count) {
		 List<Product> beans = new ArrayList<Product>();
		 
		 if(null==keyword||0==keyword.trim().length())
			 return beans;
	        String sql = "select * from Product where name like ? limit ?,? ";
	 
	        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	        	ps.setString(1, "%"+keyword.trim()+"%");
	        	ps.setInt(2, start);
	            ps.setInt(3, count);
	 
	            ResultSet rs = ps.executeQuery();
	 
	            while (rs.next()) {
	                Product bean = new Product();
	                int id = rs.getInt(1);
	                int cid = rs.getInt("cid");
	                String name = rs.getString("name");
	                float price = rs.getFloat("price");
	                float startNumber = rs.getFloat("startNumber");
	                String unit = rs.getString("unit");
	                
	                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
	                String qRemarks = rs.getString("qRemarks");
	                String tRemarks = rs.getString("tRemarks");
	                
	                
	                bean.setName(name);
	                bean.setPrice(price);
	                bean.setStartNumber(startNumber);
	                bean.setUnit(unit);
	                bean.setqRemrks(qRemarks);
	                bean.settRemarks(tRemarks);
	                
	                
	                bean.setCreateDate(createDate);
	                bean.setId(id);
	                setFirstProductImage(bean);

	                Category category = new CategoryDAO().get(cid);
	                bean.setCategory(category);
	                setFirstProductImage(bean);                
	                beans.add(bean);
	            }
	            DBUtil.release(c, ps, rs);
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	        return beans;
	}
}
