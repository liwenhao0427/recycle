package recycle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recycle.bean.Withdraw;
import recycle.bean.User;
import recycle.util.DBUtil;
import recycle.util.DateUtil;

public class WithdrawDAO {

	public static final String waitPay = "waitPay";
	public static final String finish = "finish";
	public static final String delete = "delete";
	
	
	
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from WithDraw " ;
 
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
 
    public void add(Withdraw bean) {

        String sql = "insert into WithDraw values(null,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	
            ps.setInt(1, bean.getUser().getId());
            ps.setTimestamp(2, DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(3, DateUtil.d2t(bean.getPayDate()));
            ps.setFloat(4, bean.getVal());
            ps.setString(5, bean.getStatus());
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
 
    public void update(Withdraw bean) {

        String sql = "update WithDraw set uid=?,createdate=?,paydate=?,val=?,status=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {


            ps.setInt(1, bean.getUser().getId());
            ps.setTimestamp(2, DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(3, DateUtil.d2t(bean.getPayDate()));
            ps.setFloat(4, bean.getVal());
            ps.setString(5, bean.getStatus());
            
            ps.setInt(6, bean.getId());
            ps.execute();
            DBUtil.release(c, ps, null);
            
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from WithDraw where id = " + id;
            
            s.execute(sql);
            DBUtil.release(c, s, null);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public Withdraw get(int id) {
        Withdraw bean = new Withdraw();
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from WithDraw where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {

            
                int uid = rs.getInt("uid");
                User user = new UserDAO().get(uid);
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                float val = rs.getFloat("val");
                String status = rs.getString("status");
                
                bean.setUser(user);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setVal(val);
                bean.setStatus(status);
                bean.setId(id);
            }
            DBUtil.release(c, s, rs);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<Withdraw> list() {
        return list(0, Short.MAX_VALUE);
    }
 
    public List<Withdraw> list( int start, int count) {
        List<Withdraw> beans = new ArrayList<Withdraw>();
        String sql = "select * from WithDraw order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Withdraw bean = new Withdraw();
                int id = rs.getInt(1);
                
                int uid = rs.getInt("uid");
                User user = new UserDAO().get(uid);
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                float val = rs.getFloat("val");
                String status=rs.getString("status");
                
                bean.setUser(user);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setVal(val);
                bean.setStatus(status);
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
