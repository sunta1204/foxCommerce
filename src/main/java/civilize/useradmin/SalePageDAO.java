package civilize.useradmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalePageDAO{
    private Connection con;

    // สร้างเมธอดชื่อเดียวกับคลาส (constructor)
	public SalePageDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); // โหลด JDBC Driver
		con = DriverManager.getConnection("jdbc:mysql://localhost/sale_page_db_v2?characterEncoding=utf-8", "root", ""); // เชื่อมต่อกับฐานข้อมูล
    }

    // สร้างเมธอดสำหรับปิดการเชื่อมต่อฐานข้อมูล
	public void closeConnection() throws SQLException {
		con.close();
    }
    
    // สร้างเมธอดสำหรับดึงข้อมูลจาก ResultSet มาเก็บใน JavaBeans
	private SalePage mappingSalePage(ResultSet resultSet) throws SQLException {
		SalePage salePage = new SalePage(
				resultSet.getInt("page_id"), 
				resultSet.getString("page_name"),
				resultSet.getInt("u_id"), 
				resultSet.getString("status"));
		return salePage; // ส่งกลับเป็น javabean
	}

	public ArrayList<SalePage> getUserSalePage(int u_id) throws SQLException {
		ArrayList<SalePage> salePageList = new ArrayList<SalePage>();  // ประกาศอาร์เรย์เปล่าสำหรับเก็บ javabeans
		
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM sale_page WHERE u_id = ?"); // เตรียมคำสั่ง SQL	
		pStatement.setInt(1, u_id);	
		ResultSet resultSet = pStatement.executeQuery();  // ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		// วนลูปอ่านผลลัพธ์ทีละแถว
		while (resultSet.next()) {
			SalePage p = mappingSalePage(resultSet); // แปลงข้อมูลเป็น javabean
			salePageList.add(p); // เก็บ javabean ในอาร์เรย์
		}
		
		return salePageList; // ส่งกลับเป็นอาร์เรย์ของ javabean
	}

	
}