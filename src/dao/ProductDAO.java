package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.Product;
import mvc.database.JDBConnect;

public class ProductDAO extends JDBConnect{

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public Product getProductOne(String id) {
		Product dto = new Product();
		String sql = "select*from product where p_id=?";
		System.out.println(id);
		try {

			//			conn = DriverManager.getConnection(url, user, password);
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if(rs.next()) {
				dto.setProductId(rs.getString(1));
				dto.setPname(rs.getString(2));
				dto.setUnitPrice(rs.getString(3));
				dto.setCategory(rs.getString(4));
				dto.setManufacturer(rs.getString(5));
				dto.setFilename(rs.getString(6));
				//				String ca =dto.getCategory();
				//				System.out.println(ca);

			}
		}catch (Exception e) {
			System.out.println("데이터베이스 연결이 실패되었습니다.<br>");
			System.out.println("Exception: " + e.getMessage());
		}
		return dto;
	}
	
	
	public List<Product> getProductAll() {
		List<Product> list = new ArrayList<>();
		String sql = "select*from product";
		try {

			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {
				Product dto = new Product();
				dto.setProductId(rs.getString(1));
				dto.setPname(rs.getString(2));
				dto.setUnitPrice(rs.getString(3));
				dto.setCategory(rs.getString(4));
				dto.setManufacturer(rs.getString(5));
				dto.setFilename(rs.getString(6));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("데이터베이스 연결이 실패되었습니다.<br>");
			System.out.println("Exception: " + e.getMessage());
		}
		return list;
	}
	
	
	public List<Product> getSearch(Map<String, String> map) {
		List<Product> list = new ArrayList<>();
		System.out.println(map);
		String sql = "select * from product where p_name like '%"+map.get("search2")+ "%'";
		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Product dto = new Product();
				dto.setProductId(rs.getString(1));
				dto.setPname(rs.getString(2));
				dto.setUnitPrice(rs.getString(3));
				dto.setCategory(rs.getString(4));
				dto.setManufacturer(rs.getString(5));
				dto.setFilename(rs.getString(6));
				list.add(dto);
				System.out.println(list);
			}
		}catch (Exception e) {
			System.out.println("데이터베이스 연결이 실패되었습니다.<br>");
			System.out.println("Exception: " + e.getMessage());
		}
		return list;
	}
	
}
