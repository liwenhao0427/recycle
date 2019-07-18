package recycle.servlet;
 
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recycle.bean.Category;
import recycle.bean.Product;
import recycle.util.Page;
 
public class ProductServlet extends BaseBackServlet {
 
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
         
        String name= request.getParameter("name");
        String unit= request.getParameter("unit");
        float price = Float.parseFloat(request.getParameter("price"));
        float startNumber = Float.parseFloat(request.getParameter("startNumber"));
        String qRemarks= request.getParameter("qRemarks");
        String tRemarks= request.getParameter("tRemarks");
        
        Product p = new Product();
 
        p.setCategory(c);
        p.setName(name);
        p.setCreateDate(new Date());
        p.setPrice(price);
        p.setqRemrks(qRemarks);
        p.settRemarks(tRemarks);
        p.setUnit(unit);
        p.setStartNumber(startNumber);
        
        productDAO.add(p);
        return "@admin_product_list?cid="+cid;
    }
 
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(id);
        productDAO.delete(id);
        return "@admin_product_list?cid="+p.getCategory().getId();
    }
 
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(id);
        request.setAttribute("p", p);
        return "admin/editProduct.jsp";    
    }
     
    
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
        
        String name= request.getParameter("name");
        String unit= request.getParameter("unit");
        float price = Float.parseFloat(request.getParameter("price"));
        float startNumber = Float.parseFloat(request.getParameter("startNumber"));
        String qRemarks= request.getParameter("qRemarks");
        String tRemarks= request.getParameter("tRemarks");
        int id = Integer.parseInt(request.getParameter("id"));
        
        Product p = new Product();
 
        p.setCategory(c);
        p.setName(name);
        p.setCreateDate(new Date());
        p.setPrice(price);
        p.setqRemrks(qRemarks);
        p.settRemarks(tRemarks);
        p.setUnit(unit);
        p.setStartNumber(startNumber);
        p.setId(id);
        
 
        productDAO.update(p);
        return "@admin_product_list?cid="+p.getCategory().getId();
    }
 
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
         
        List<Product> ps = productDAO.list(cid, page.getStart(),page.getCount());
         
        int total = productDAO.getTotal(cid);
        page.setTotal(total);
        page.setParam("&cid="+c.getId());
         
        request.setAttribute("ps", ps);
        request.setAttribute("c", c);
        request.setAttribute("page", page);
        
         
        return "admin/listProduct.jsp";
    }
}