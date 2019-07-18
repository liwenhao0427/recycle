package recycle.servlet;
 
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import recycle.bean.Order;
import recycle.dao.OrderDAO;
import recycle.util.Page;
 
public class OrderServlet extends BaseBackServlet {
 
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }
 
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;   
    }
 
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }
 
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        List<Order> os = orderDAO.list(page.getStart(),page.getCount());
        int total = orderDAO.getTotal();
        page.setTotal(total);
        
        request.setAttribute("os", os);
        request.setAttribute("page", page);
         
        return "admin/listOrder.jsp";
    }
}