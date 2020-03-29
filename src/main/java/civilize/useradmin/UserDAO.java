package civilize.useradmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO{
    private Connection con;

    // สร้างเมธอดชื่อเดียวกับคลาส (constructor)
	public UserDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); // โหลด JDBC Driver
		con = DriverManager.getConnection("jdbc:mysql://localhost/sale_page_db_v2?characterEncoding=utf-8", "root", ""); // เชื่อมต่อกับฐานข้อมูล
    }

    // สร้างเมธอดสำหรับปิดการเชื่อมต่อฐานข้อมูล
	public void closeConnection() throws SQLException {
		con.close();
    }
    
    // สร้างเมธอดสำหรับดึงข้อมูลจาก ResultSet มาเก็บใน JavaBeans
	private User mappingUser(ResultSet resultSet) throws SQLException {
		User user = new User(
				resultSet.getInt("u_id"), 
				resultSet.getString("username"),
				resultSet.getString("password"), 
                resultSet.getString("email"),
                resultSet.getString("tel"),
				resultSet.getString("expiry_date"), 
                resultSet.getString("type"),
                resultSet.getString("Inspector")
                );
		return user; // ส่งกลับเป็น javabean
    }
    
    // สร้างเมธอดที่ทำงานกับฐานข้อมูล
		public User getUser(String username) throws SQLException {		
			User user = null;
			
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM user WHERE username = ?"); // เตรียมคำสั่ง SQL
			pStatement.setString(1, username);		
			ResultSet resultSet = pStatement.executeQuery(); // ส่งคำสั่ง SQL ไปยังฐานข้อมูล		
			if (resultSet.next()) // ถ้าพบข้อมูล
				user = mappingUser(resultSet); // นำผลลัพธ์ที่ฐานข้อมูลส่งกลับแปลงเป็น object
			
			return user; 	// ส่งกลับเป็น javabean
		}
		
		//Login
		public User login(String username , String password) throws SQLException{
			User user = null;
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			ResultSet resultSet = pStatement.executeQuery();
			if(resultSet.next())
				user = mappingUser(resultSet);
			return user;
		}

	public ArrayList<User> getAllSalePage() throws SQLException {
		ArrayList<User> salePageList = new ArrayList<User>();  // ประกาศอาร์เรย์เปล่าสำหรับเก็บ javabeans
		
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM sale_page WHERE"); // เตรียมคำสั่ง SQL		
		ResultSet resultSet = pStatement.executeQuery();  // ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		// วนลูปอ่านผลลัพธ์ทีละแถว
		while (resultSet.next()) {
			User p = mappingUser(resultSet); // แปลงข้อมูลเป็น javabean
			salePageList.add(p); // เก็บ javabean ในอาร์เรย์
		}
		
		return salePageList; // ส่งกลับเป็นอาร์เรย์ของ javabean
    }
    
    

	
}