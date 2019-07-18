package recycle.servlet;
 
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.util.HtmlUtils;

import recycle.bean.User;
import recycle.bean.Withdraw;
import recycle.dao.WithdrawDAO;
import recycle.util.Page;
 
public class WithdrawServlet extends BaseBackServlet {

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
    public String pay(HttpServletRequest request, HttpServletResponse response, Page page) {
    	int id = Integer.parseInt(request.getParameter("id"));
        
    	Withdraw withdraw = withdrawDAO.get(id);
    	int uid = withdraw.getId();
    	
    	withdraw.setStatus(WithdrawDAO.finish);
    	withdraw.setPayDate(new Date());
    	withdrawDAO.update(withdraw);
    	
    	return "@admin_withdraw_list";
    }
 
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        List<Withdraw> ws = withdrawDAO.list(page.getStart(),page.getCount());
        int total = withdrawDAO.getTotal();
        page.setTotal(total);
     
        request.setAttribute("ws", ws);
        request.setAttribute("page", page);
         
        return "admin/listWithdraw.jsp";
    }
    
    
}