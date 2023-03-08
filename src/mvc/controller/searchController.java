package mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dto.Product;

@WebServlet("/searchController")
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public searchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("searchWord");
		ProductDAO dao = new ProductDAO();
		// 맵말고 리스트로 하면 값 안가져옴
		Map<String,String> map = new HashMap<String, String>();
		map.put("search2", search);
		List<Product> list = dao.getSearch(map);
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("res", list);
		if(list==null) {
			response.sendRedirect("/products.jsp");
		}
		request.setAttribute("search", list);
		request.getRequestDispatcher("/products.jsp").forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
