package civilize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
	private Connection con;

	// สร้างเมธอดชื่อเดียวกับคลาส (constructor)
	public ProductDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); // โหลด JDBC Driver
		con = DriverManager.getConnection("jdbc:mysql://localhost/blueshop?characterEncoding=utf-8", "root", ""); // เชื่อมต่อกับฐานข้อมูล
	}
	
	// สร้างเมธอดสำหรับปิดการเชื่อมต่อฐานข้อมูล
	public void closeConnection() throws SQLException {
		con.close();
	}
	
	// สร้างเมธอดสำหรับดึงข้อมูลจาก ResultSet มาเก็บใน JavaBeans
	private Product mappingProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product(
				resultSet.getInt("pid"), 
				resultSet.getString("pname"),
				resultSet.getString("pdetail"), 
				resultSet.getInt("price"));
		return product; // ส่งกลับเป็น javabean
	}

	// สร้างเมธอดที่ทำงานกับฐานข้อมูล
	public Product getProduct(int id) throws SQLException {		
		Product product = null;
		
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM product WHERE pid = ?"); // เตรียมคำสั่ง SQL
		pStatement.setInt(1, id);		
		ResultSet resultSet = pStatement.executeQuery(); // ส่งคำสั่ง SQL ไปยังฐานข้อมูล		
		if (resultSet.next()) // ถ้าพบข้อมูล
			product = mappingProduct(resultSet); // นำผลลัพธ์ที่ฐานข้อมูลส่งกลับแปลงเป็น object
		
		return product; 	// ส่งกลับเป็น javabean
	}
	
	public ArrayList<Product> getAllProduct() throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();  // ประกาศอาร์เรย์เปล่าสำหรับเก็บ javabeans
		
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM product"); // เตรียมคำสั่ง SQL		
		ResultSet resultSet = pStatement.executeQuery();  // ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		// วนลูปอ่านผลลัพธ์ทีละแถว
		while (resultSet.next()) {
			Product p = mappingProduct(resultSet); // แปลงข้อมูลเป็น javabean
			productList.add(p); // เก็บ javabean ในอาร์เรย์
		}
		
		return productList; // ส่งกลับเป็นอาร์เรย์ของ javabean
	}
	 
	
	public void createProduct (Product product) throws SQLException {
		PreparedStatement pStatement = con.prepareStatement("INSERT INTO product (pname, pdetail, price) VALUES (?, ?, ?)");
		pStatement.setString(1, product.getProductName());
		pStatement.setString(2, product.getProductDetail());
		pStatement.setInt(3, product.getPrice());
		pStatement.executeUpdate();
	}
	

	public void updateProduct (Product product) throws SQLException {	 
        PreparedStatement pStatement = con.prepareStatement("UPDATE product SET pname = ?, pdetail = ?, price = ? WHERE pid = ?");	        
        pStatement.setString(1, product.getProductName());
        pStatement.setString(2, product.getProductDetail());
        pStatement.setInt(3, product.getPrice());
        pStatement.setInt(4, product.getPid());
        pStatement.executeUpdate();
	}

	public void deleteProduct (int id) throws SQLException {	 
        PreparedStatement pStatement = con.prepareStatement("DELETE FROM product WHERE pid = ?");
        pStatement.setInt(1, id);
        pStatement.executeUpdate();
	}
	
	// ควรสร้างเมธอด main สำหรับรันทดสอบ ก่อนนำไปใช้จริงใน servlet
	public static void main(String[] args) {
		try {
			// ก่อนใช้เมธอดต่างๆใน DAO ต้องสร้าง object ก่อน 
			// ในที่นี้จะเรียกเมธอด ProductDAO() ซึ่งมีการโหลด JDBC Driver และเชื่อมต่อฐานข้อมูล
			ProductDAO productDAO = new ProductDAO(); 
			
			// ดึงข้อมูลตามรหัสสินค้า
			Product oneProduct = productDAO.getProduct(1);
			System.out.println(oneProduct.getProductName() + " " + oneProduct.getProductDetail());
			
			// ดึงข้อมูลสินค้าทั้งหมด
			ArrayList<Product> list = productDAO.getAllProduct();
			for (Product product : list) {
				System.out.println(product.getProductName() + " " + product.getProductDetail());
			}
			
			// เพิ่มข้อมูล
			//Product newProduct = new Product(0, "นาฬิกาใหม่", "ดูเวลา + ดูอัตราเผาผลาญ", 350);			
			//productDAO.createProduct(newProduct);
			
			// แก้ไขข้อมูล
			//Product oldProduct = new Product(1, "Update Centrum", "สูตรใหม่", 400);
			//productDAO.updateProduct(oldProduct);
			
			// ลบข้อมูล
			//productDAO.deleteProduct(20);
			
			// ปิดการเชื่อมต่อ
			productDAO.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}