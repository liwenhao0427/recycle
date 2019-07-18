package recycle.servlet;


import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recycle.bean.Area;
import recycle.util.Page;

public class AreaServlet extends BaseBackServlet {
	
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
        
		String name= params.get("name");
		Area c = new Area();
		c.setName(name);
		areaDAO.add(c);
		
		return "@admin_area_list";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		areaDAO.delete(id);
		return "@admin_area_list";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Area c = areaDAO.get(id);
		request.setAttribute("c", c);
		return "admin/editArea.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
        
		System.out.println(params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));

		Area c = new Area();
		c.setId(id);
		c.setName(name);
		areaDAO.update(c);
		
		return "@admin_area_list";

	}

	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Area> cs = areaDAO.list(page.getStart(),page.getCount());
		int total = areaDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);
		
		return "admin/listArea.jsp";
	}
}
