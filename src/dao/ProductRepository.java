package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Product;
import mvc.database.JDBConnect;

public class ProductRepository extends JDBConnect {
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();
	private static ProductRepository instance = new ProductRepository();

	public static ProductRepository getInstance(){
		return instance;
	} 

	public ProductRepository() {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "select*from product";
		System.out.println(sql);
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Product dto = new Product();
				dto.setProductId(rs.getString("p_id"));
				dto.setPname(rs.getString("p_name"));
				dto.setCategory(rs.getString("p_category"));
				dto.setManufacturer(rs.getString("p_manufacturer"));
				dto.setFilename(rs.getString("p_filename"));
				dto.setUnitPrice(rs.getString("p_unitPrice"));
				listOfProducts.add(dto);
				System.out.println(listOfProducts);
				
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 연결이 실패되었습니다.<br>");
			System.out.println("Exception: " + e.getMessage());
		}
		
		
//		Product phone = new Product("P1234", "iPhone 6s", 800000);
//		phone.setDescription("4.7-inch, 1334X750 Renina HD display, 8-megapixel iSight Camera");
//		phone.setCategory("Smart Phone");
//		phone.setManufacturer("Apple");
//		phone.setUnitsInStock(1000);
//		phone.setCondition("New");
//		phone.setFilename("P1234.png");
//
//		Product notebook = new Product("P1235", "LG PC �׷�", 1500000);
//		notebook.setDescription("13.3-inch, IPS LED display, 5rd Generation Intel Core processors");
//		notebook.setCategory("Notebook");
//		notebook.setManufacturer("LG");
//		notebook.setUnitsInStock(1000);
//		notebook.setCondition("Refurbished");
//		notebook.setFilename("P1235.png");
//
//		Product tablet = new Product("P1236", "Galaxy Tab S", 900000);
//		tablet.setDescription("212.8*125.6*6.6mm,  Super AMOLED display, Octa-Core processor");
//		tablet.setCategory("Tablet");
//		tablet.setManufacturer("Samsung");
//		tablet.setUnitsInStock(1000);
//		tablet.setCondition("Old");
//		tablet.setFilename("P1236.png");
//
//		listOfProducts.add(phone);
//		listOfProducts.add(notebook);
//		listOfProducts.add(tablet);
	}

	public ArrayList<Product> getAllProducts() {
		return listOfProducts;
	}
	
	public Product getProductById(String productId) {
		Product productById = null;
		for (int i = 0; i < listOfProducts.size(); i++) {
			Product product = listOfProducts.get(i);
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}
		//System.out.println(productById);
		//System.out.println(listOfProducts);
		return productById;
	}
	
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
}
