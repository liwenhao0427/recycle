package recycle.servlet;
 
import java.lang.reflect.Method;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recycle.dao.AreaDAO;
import recycle.dao.CategoryDAO;
import recycle.dao.OrderDAO;
import recycle.dao.OrderImageDAO;
import recycle.dao.ProductDAO;
import recycle.dao.ProductImageDAO;
import recycle.dao.RecyclerDAO;
import recycle.dao.UserDAO;
import recycle.dao.WithdrawDAO;
import recycle.util.Page;
 
public class BaseForeServlet extends HttpServlet{
 
	protected AreaDAO areaDAO = new AreaDAO();
	protected CategoryDAO categoryDAO = new CategoryDAO();
	protected OrderDAO orderDAO = new OrderDAO();
	protected OrderImageDAO orderImageDAO = new OrderImageDAO();
	protected ProductDAO productDAO = new ProductDAO();
	protected ProductImageDAO productImageDAO = new ProductImageDAO();
	protected UserDAO userDAO = new UserDAO();
	protected RecyclerDAO recyclerDAO = new RecyclerDAO();
	protected WithdrawDAO withdrawDAO = new WithdrawDAO();
	
	
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
             
            int start= 0;
            int count = 10;
            try {
                start = Integer.parseInt(request.getParameter("page.start"));
            } catch (Exception e) {
                 
            }
             
            try {
                count = Integer.parseInt(request.getParameter("page.count"));
            } catch (Exception e) {
            }
             
            Page page = new Page(start,count);
             
            String method = (String) request.getAttribute("method");
 
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class,Page.class);
             
            String redirect = m.invoke(this,request, response,page).toString();
             
            if(redirect.startsWith("@"))
                response.sendRedirect(redirect.substring(1));
            else if(redirect.startsWith("%"))
                response.getWriter().print(redirect.substring(1));
            else
                request.getRequestDispatcher(redirect).forward(request, response);
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}