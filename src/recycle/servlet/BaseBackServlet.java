package recycle.servlet;


import java.io.InputStream;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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


public abstract class BaseBackServlet extends HttpServlet {

	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) ;
	public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) ;
	
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
			int count = 5;
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
	
	public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
			InputStream is =null;
			try {
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            factory.setSizeThreshold(1024 * 10240);
	             
	            List items = upload.parseRequest(request);
	            Iterator iter = items.iterator();
	            while (iter.hasNext()) {
	                FileItem item = (FileItem) iter.next();
	                if (!item.isFormField()) {
	                    is = item.getInputStream();
	                } else {
	                	String paramName = item.getFieldName();
	                	String paramValue = item.getString();
	                	paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
	                	params.put(paramName, paramValue);
	                }
	            }
	             
	
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return is;
		}
	
}
