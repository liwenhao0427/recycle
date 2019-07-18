package recycle.servlet;


import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recycle.bean.Area;
import recycle.util.Page;

public class OnlineServlet extends BaseBackServlet {
	
	
	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines");
		if(onLines==null){
			onLines = Collections.synchronizedMap(new HashMap<String, HttpSession>() );//ʹ��ͬ��������Map
			getServletContext().setAttribute("onLines", onLines);
		}
		
		int total = onLines.size();
        page.setTotal(total);
        
		
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Iterator<Entry<String, HttpSession>> it = onLines.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, HttpSession> en = it.next();
			
			Map<String, Object> m = new HashMap<String, Object>();
			
			m.put("name", en.getValue().getId() );
			m.put("user", en.getValue().getAttribute("user"));
			
			String creationTime = sdf.format(new Date(en.getValue().getCreationTime()));
			m.put("creationTime", creationTime);
			String lastAccessTime = sdf.format( new Date(en.getValue().getLastAccessedTime()) );
			m.put("lastAccessTime", lastAccessTime);
			
			m.put("ip", en.getValue().getAttribute("ip"));
			
			lists.add(m);
		}
		
		request.setAttribute("onLines", lists);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		
        
		return "admin/listOnline.jsp";
	}


	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines");
		if(onLines.containsKey(name)){
			HttpSession session = onLines.get(name);
			session.invalidate();
			onLines.remove(name,session);
		}
		return "@admin_online_list";
	}


	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}
}
