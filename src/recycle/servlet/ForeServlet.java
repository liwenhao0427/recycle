package recycle.servlet;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.util.HtmlUtils;

import recycle.bean.Area;
import recycle.bean.Category;
import recycle.bean.Order;
import recycle.bean.Product;
import recycle.bean.ProductImage;
import recycle.bean.Recycler;
import recycle.bean.User;
import recycle.bean.Withdraw;
import recycle.dao.CategoryDAO;
import recycle.dao.OrderDAO;
import recycle.dao.ProductDAO;
import recycle.dao.ProductImageDAO;
import recycle.util.Page;
import recycle.util.ShaUtil;

public class ForeServlet extends BaseForeServlet {
	
	private Object lock = new Object();
	public String home(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Category> cs= new CategoryDAO().list();
		new ProductDAO().fill(cs);
		new ProductDAO().fillByRow(cs);
		request.setAttribute("cs", cs);
		
		return "home.jsp";
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response, Page page) {
		String name = request.getParameter("name");
		if(name.isEmpty()) {
			request.setAttribute("msg", "请输入合法用户名!");
			return "register.jsp";	
		}
		String password = request.getParameter("password");
		if(password.isEmpty()) {
			request.setAttribute("msg", "请输入合法密码!");
			return "register.jsp";	
		}
		name = HtmlUtils.htmlEscape(name);
		System.out.println(name);
		boolean exist = userDAO.isExist(name);
		
		if(exist){
			request.setAttribute("msg", "该用户已存在");
			return "register.jsp";	
		}
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		Area area= areaDAO.get(1);
		user.setArea(area);
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		userDAO.add(user);
		
		return "registerSuccess.jsp";	
	}	
	
	public String recycler(HttpServletRequest request, HttpServletResponse response, Page page) {
		return "recyclerRegister.jsp";	
	}	
	
	public String recyclerRegister(HttpServletRequest request, HttpServletResponse response, Page page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		String mobile = request.getParameter("mobile");
		
		name = HtmlUtils.htmlEscape(name);
		
		if(name.isEmpty()) {
			request.setAttribute("msg", "请输入合法用户名!");
			return "recyclerRegister.jsp";	
		}
		if(password.isEmpty()) {
			request.setAttribute("msg", "请输入合法密码!");
			return "recyclerRegister.jsp";	
		}
		boolean exist = recyclerDAO.isExist(name);
		
		if(exist){
			request.setAttribute("msg", "该用户已存在");
			return "recyclerRegister.jsp";	
		}
		
		Recycler recycler = new Recycler();
		
		Area area= areaDAO.get(1);
		recycler.setArea(area);
		recycler.setMobile(mobile);
		recycler.setPassword(password);
		recycler.setName(name);
		recycler.setRealName(realName);
		recyclerDAO.add(recycler);
		return "recyclerSuccess.jsp";	
	}	
	public String login(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null!=user)return "@foremySpace";
	    
		String name = request.getParameter("name");
		name = HtmlUtils.htmlEscape(name);
		String password = request.getParameter("password");		
		
		user = userDAO.get(name,password);
		
		if(null==user){
			request.setAttribute("msg", "用户名或密码错误");
			return "login.jsp";	
		}
		
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines");
		if(onLines==null){
			onLines = Collections.synchronizedMap(new HashMap<String, HttpSession>() );
			getServletContext().setAttribute("onLines", onLines);
		}
		if(onLines.containsKey(name)){
			HttpSession session = onLines.get(name);
			session.invalidate();
			onLines.remove(name, session);
			System.out.println("Find:"+name+" "+session.getId()+" "+onLines );
			
		}
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("ip", request.getRemoteAddr());
		onLines.put(name, request.getSession());
		System.out.println("Add:"+name+request.getSession().getId() );
		return "@forehome";	
	}	
	
	public String recyclerLogin(HttpServletRequest request, HttpServletResponse response, Page page) {
		String name = request.getParameter("name");
		name = HtmlUtils.htmlEscape(name);
		String password = request.getParameter("password");		
		
		Recycler user = recyclerDAO.get(name,password);
		
		if(null==user){
			request.setAttribute("msg", "用户名或密码错误");
			return "recyclerLogin.jsp";	
		}
		
		
		Map<String, HttpSession> rOnLines = (Map<String, HttpSession>) getServletContext().getAttribute("rOnLines");
		if(rOnLines==null){
			rOnLines = Collections.synchronizedMap(new HashMap<String, HttpSession>() );//ʹ��ͬ��������Map
			getServletContext().setAttribute("rOnLines", rOnLines);
		}
		if(rOnLines.containsKey(name)){
			HttpSession session = rOnLines.get(name);
			session.invalidate();
			rOnLines.remove(name, session);
			System.out.println("Find:"+name+" "+session.getId()+" "+rOnLines );
		}
		rOnLines.put(name, request.getSession());
		
		request.getSession().setAttribute("recycler", user);
		return "@forereceive";	
	}	
	public String product(HttpServletRequest request, HttpServletResponse response, Page page) {
	    int pid = Integer.parseInt(request.getParameter("pid"));
	    Product p = productDAO.get(pid);
	     
	    List<ProductImage> productSingleImages = productImageDAO.list(p);
	    p.setProductImages(productSingleImages);
	    
	    request.setAttribute("p", p);
	    return "product.jsp";      
	}  
	
	public String order(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user) {
	    	return "login.jsp";
	    }
		
		int pid = Integer.parseInt(request.getParameter("pid"));
	    Product p = productDAO.get(pid);
	     
	    List<ProductImage> productSingleImages = productImageDAO.list(p);
	    p.setProductImages(productSingleImages);
	    
	    
	    request.setAttribute("p", p);
	    return "order.jsp";      
	}  
	
	
	
	
	public String createOrder(HttpServletRequest request, HttpServletResponse response, Page page) {
	    float number=Float.parseFloat(request.getParameter("number"));
	    String reservation=request.getParameter("reservation");
	    String address=request.getParameter("address");
	    String receiver=request.getParameter("receiver");
	    String mobile=request.getParameter("mobile");
	    int pid = Integer.parseInt(request.getParameter("pid"));
	    String userMessage = request.getParameter("userMessage");
	    Product p = productDAO.get(pid);
	    User user =(User) request.getSession().getAttribute("user");
	    if(null==user) {
	    	return "@forelogin";
	    }
	    
	    Order order = new Order();
	    order.setAddress(address);
	    order.setCreateDate(new Date());
	    order.setMobile(mobile);
	    order.setNumber(number);
	    order.setOrderCode(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +RandomUtils.nextInt(10000));
	    order.setProduct(p);
	    order.setReceiver(receiver);
	    order.setReservation(reservation);
	    order.setStatus(OrderDAO.waitReceive);
	    order.setSumPrice(p.getPrice()*number);
	    order.setUser(user);
	    order.setUserMessage(userMessage);
	    orderDAO.add(order);
	    
		return "@forehome";      
	}  
	
	
	public String listArea(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Area> cs = areaDAO.list(page.getStart(),page.getCount());
		int total = areaDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);
		
		return "listArea.jsp";
	}
	
	
	public String receive(HttpServletRequest request, HttpServletResponse response, Page page) {
        
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    
		if(null==recycler) {
			return "recyclerLogin.jsp";
		}
		
		List<Order> os = orderDAO.list(OrderDAO.waitReceive,page.getStart(),page.getCount(),recycler.getArea().getId());
        int total = orderDAO.getTotal();
        page.setTotal(total);
        
        request.setAttribute("os", os);
        request.setAttribute("page", page);
        
        return "receive.jsp";
    }
	
	
	public String delivery(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler) {
	    	return "recyclerLogin.jsp";
	    }
	    //加锁防止多回收员同时抢单
	    int oid = Integer.parseInt(request.getParameter("oid"));
	    Order order = orderDAO.get(oid);
	    synchronized (lock) {
	    	//只有第一次需要更新.
	    	
//	    	System.out.println("********************************"+order.getStatus()+" "+OrderDAO.waitReceive+" "+OrderDAO.waitReceive.equals(order.getStatus()));
		    if(OrderDAO.waitReceive.equals(order.getStatus())) {
		    	order.setStatus(OrderDAO.waitDelivery);
			    order.setRecycler(recycler);
			    
			    orderDAO.update(order);
		    }
		}
		
		
	    return "@forereceive";      
	}  
	
	
	public String bought(HttpServletRequest request, HttpServletResponse response, Page page) {
	    User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    List<Order> os= orderDAO.list(user.getId(),OrderDAO.delete);
	    
	    request.setAttribute("os", os);
	     
	    return "bought.jsp";       
	}
	
	
	public String deliver(HttpServletRequest request, HttpServletResponse response, Page page) {
	    Recycler recycler = (Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    List<Order> os= orderDAO.recyclerList(recycler.getId(),OrderDAO.waitDelivery);
	    
	    
	    request.setAttribute("os", os);
	     
	    return "deliver.jsp";       
	}
	
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response, Page page){
	    int oid = Integer.parseInt(request.getParameter("oid"));
	    Order o = orderDAO.get(oid);
	    o.setStatus(OrderDAO.delete);
	    orderDAO.update(o);
	    return "%success";     
	}

	public String recyclerConfirm(HttpServletRequest request, HttpServletResponse response, Page page) {
	    int oid = Integer.parseInt(request.getParameter("oid")) ;
	    Order order = orderDAO.get(oid);
	    	
	    order.setStatus(OrderDAO.waitConfirm);
	    order.setDeliveryDate(new Date());
	    
	    orderDAO.update(order);
	    return "%success";        
	}
	
	public String recyclerCancel(HttpServletRequest request, HttpServletResponse response, Page page) {
	    int oid = Integer.parseInt(request.getParameter("oid")) ;
	    Order order = orderDAO.get(oid);
	    	
	    order.setStatus(OrderDAO.waitReceive);
	    order.setRecycler(null);
	    orderDAO.update(order);
	    return "%success";         
	}
	
	public String orderConfirmed(HttpServletRequest request, HttpServletResponse response, Page page) {
	    int oid = Integer.parseInt(request.getParameter("oid"));
	    User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";

	    Order o = orderDAO.get(oid);
	    user.setBalance(user.getBalance()+o.getSumPrice());
	    
	    o.setStatus(OrderDAO.finish);
	    o.setConfirmDate(new Date());
	    orderDAO.update(o);
	    userDAO.update(user);
	    request.getSession().setAttribute("user", user);
		
	    return "%success";
	}
	
	public String mySpace(HttpServletRequest request, HttpServletResponse response, Page page) {
	    User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";

	    request.setAttribute("us", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    return "mySpace.jsp";
	}
	
	public String recyclerSpace(HttpServletRequest request, HttpServletResponse response, Page page) {
	    Recycler user =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==user)return "recyclerLogin.jsp";

	    request.setAttribute("us", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    return "recyclerSpace.jsp";
	}
	
	
	
	public String updatePassword(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			request.setAttribute("msg", "两次密码不一致");
			return "mySpace.jsp";
		}
	    
	    
	    if(null==userDAO.get(user.getName(),request.getParameter("prepassword") )) {
			request.setAttribute("msg", "密码错误");
			return "mySpace.jsp";
		}
	    
	    String password = request.getParameter("password");
	    
	    System.out.println(password);
	    user.setPassword(password);
	    userDAO.update(user);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	
	public String updateMobile(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    String mobile = request.getParameter("mobile");
	    user.setMobile(mobile);
	    userDAO.update(user);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	public String updateArea(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    int aid =Integer.parseInt(request.getParameter("aid")) ;
	    Area area = areaDAO.get(aid);
	    if(null==area) {
	    	request.setAttribute("msg", "抱歉,暂不支持该区域");
			return "mySpace.jsp";	
	    }
	    user.setArea(area);
	    userDAO.update(user);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	public String updateAlipay(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    String alipay = request.getParameter("alipay");
	    user.setAlipay(alipay);
	    userDAO.update(user);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	
	public String updateRealname(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    String realname = request.getParameter("realname");
	    user.setRealName(realname);
	    userDAO.update(user);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	
	
	

	
	public String recyclerUpdatePassword(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    if(!request.getParameter("password").equals(request.getParameter("password2"))) {
			request.setAttribute("msg", "两次输入密码不相等");
			return "recyclerSpace.jsp";
		}
	    if(null==recyclerDAO.get(recycler.getName(),request.getParameter("prepassword") )) {
			request.setAttribute("msg", "密码错误");
			return "recyclerSpace.jsp";
		}
	    
	    String password = request.getParameter("password");
	    if(null==password)return "recyclerLogin.jsp";
	    System.out.println(password);
	    recycler.setPassword(password);
	    recyclerDAO.update(recycler);
	    
	    request.setAttribute("us", recycler);
	    request.getSession().setAttribute("recycler", recycler);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "recyclerSpace.jsp";
	}
	
	public String recyclerUpdateMobile(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    String mobile = request.getParameter("mobile");
	    recycler.setMobile(mobile);
	    recyclerDAO.update(recycler);
	    
	    request.setAttribute("us", recycler);
	    request.getSession().setAttribute("recycler", recycler);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "recyclerSpace.jsp";
	}
	public String recyclerUpdateArea(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
		
		
		
		
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    int aid =Integer.parseInt(request.getParameter("aid")) ;
	    Area area = areaDAO.get(aid);
	    if(null==area) {
	    	request.setAttribute("msg", "抱歉,暂不支持该区域");
			return "recyclerSpace.jsp";	
	    }
	    recycler.setArea(area);
	    recyclerDAO.update(recycler);
	    
	    request.setAttribute("us", recycler);
	    request.getSession().setAttribute("recycler", recycler);
	    
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
	    
		return "recyclerSpace.jsp";
	}
	public String recyclerUpdateAlipay(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    String alipay = request.getParameter("alipay");
	    recycler.setAlipay(alipay);
	    recyclerDAO.update(recycler);
	    
	    request.setAttribute("us", recycler);
	    request.getSession().setAttribute("recycler", recycler);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "recyclerSpace.jsp";
	}
	public String recyclerUpdateRealname(HttpServletRequest request, HttpServletResponse response, Page page) {
		Recycler recycler =(Recycler) request.getSession().getAttribute("recycler");
	    if(null==recycler)return "recyclerLogin.jsp";
	    
	    String realname = request.getParameter("realname");
	    recycler.setRealName(realname);
	    recyclerDAO.update(recycler);
	    
	    request.setAttribute("us", recycler);
	    request.getSession().setAttribute("recycler", recycler);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "recyclerSpace.jsp";
	}
	public String withdraw(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    if(null==user)return "login.jsp";
	    
	    Float sum = Float.parseFloat(request.getParameter("withdraw"));
	    float left = user.getBalance()-sum;
	    if(left<0)return "mySpace.jsp";
	    user.setBalance(left);
	    
	    Withdraw withdraw = new Withdraw();
	    withdraw.setCreateDate(new Date());
	    withdraw.setStatus(withdrawDAO.waitPay);
	    withdraw.setUser(user);
	    withdraw.setVal(sum);
	    userDAO.update(user);
	    withdrawDAO.add(withdraw);
	    
	    request.setAttribute("us", user);
	    request.getSession().setAttribute("user", user);
	    List<Area> as = areaDAO.list(page.getStart(),page.getCount());
		request.setAttribute("as", as);
	    
		return "mySpace.jsp";
	}
	//
	public String logout(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user =(User) request.getSession().getAttribute("user");
	    String name=user.getName();
        
	    Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines");
		if(onLines==null){
			onLines = Collections.synchronizedMap(new HashMap<String, HttpSession>() );
			getServletContext().setAttribute("onLines", onLines);
		}
		if(onLines.containsKey(name)){
			HttpSession session = onLines.get(name);
			session.invalidate();
			onLines.remove(name, session);
			System.out.println("Find:"+name+" "+session.getId()+" "+onLines );
			
		}
        request.getSession().invalidate();
		
        return "@forehome";
    }  
	
}

