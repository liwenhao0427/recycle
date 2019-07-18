package recycle.servlet;
 
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.util.HtmlUtils;
 
import recycle.bean.Recycler;
import recycle.util.Page;
 
public class RecyclerServlet extends BaseBackServlet {

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
        List<Recycler> us = recyclerDAO.list(page.getStart(),page.getCount());
        int total = recyclerDAO.getTotal();
        page.setTotal(total);
         
        request.setAttribute("us", us);
        request.setAttribute("page", page);
         
        return "admin/listRecycler.jsp";
    }
}