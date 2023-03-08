package mvc.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/ShippingInfo.do")
public class ShippingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ShippingInfo.do/doGet()");
		String cartId = request.getParameter("cartId");
		
//		request.setAttribute("cartId",cartId);
//		request.getRequestDispatcher("/WebMarket/shippingInfo.jsp").forward(request, response);
		
//		forward로 보내면 에러생긴다. 이유는 모르겠다. 나중에 알아보자.
		response.sendRedirect("/WebMarket/shippingInfo.jsp?cartId="+cartId);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ShippingInfo.do/doPost()");
    	
//    	/WebMarket/processShippingInfo.jsp 내용
    	request.setCharacterEncoding("UTF-8");

    	Cookie cartId = new Cookie("Shipping_cartId", URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
    	Cookie name = new Cookie("Shipping_name", URLEncoder.encode(request.getParameter("name"), "utf-8"));
    	Cookie shippingDate = new Cookie("Shipping_shippingDate", URLEncoder.encode(request.getParameter("shippingDate"), "utf-8"));
    	Cookie country = new Cookie("Shipping_country",	URLEncoder.encode(request.getParameter("country"), "utf-8"));
    	Cookie zipCode = new Cookie("Shipping_zipCode", URLEncoder.encode(request.getParameter("zipCode"), "utf-8"));
    	Cookie addressName = new Cookie("Shipping_addressName", URLEncoder.encode(request.getParameter("addressName"), "utf-8"));

    	cartId.setMaxAge(365 * 24 * 60 * 60);
    	name.setMaxAge(365 * 24 * 60 * 60);
    	zipCode.setMaxAge(365 * 24 * 60 * 60);
    	country.setMaxAge(365 * 24 * 60 * 60);
    	addressName.setMaxAge(365 * 24 * 60 * 60);

    	response.addCookie(cartId);
    	response.addCookie(name);
    	response.addCookie(shippingDate);
    	response.addCookie(country);
    	response.addCookie(zipCode);
    	response.addCookie(addressName);

    	response.sendRedirect("/WebMarket/orderConfirmation.jsp");
    	
	}

}
