package civilize.useradmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageContentDAO{
    private Connection con;

    // สร้างเมธอดชื่อเดียวกับคลาส (constructor)
	public ImageContentDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); // โหลด JDBC Driver
		con = DriverManager.getConnection("jdbc:mysql://localhost/sale_page_db_v2?characterEncoding=utf-8", "root", ""); // เชื่อมต่อกับฐานข้อมูล
    }
    
    // สร้างเมธอดสำหรับปิดการเชื่อมต่อฐานข้อมูล
	public void closeConnection() throws SQLException {
		con.close();
    }
    
    private ImageContent mappImageContent(ResultSet resultSet) throws SQLException {
        ImageContent imageContent = new ImageContent(
            resultSet.getInt("img_id"),
            resultSet.getString("img_file_name"),
            resultSet.getInt("page_id")
        );
        return imageContent;
    }

    // สร้างเมธอดที่ทำงานกับฐานข้อมูล
	public ImageContent getSalePage(int page_id) throws SQLException {		
		ImageContent imageContent = null;
		
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM image_content WHERE page_id = ?"); // เตรียมคำสั่ง SQL
		pStatement.setInt(1, page_id);		
		ResultSet resultSet = pStatement.executeQuery(); // ส่งคำสั่ง SQL ไปยังฐานข้อมูล		
		if (resultSet.next()) // ถ้าพบข้อมูล
            imageContent = mappImageContent(resultSet); // นำผลลัพธ์ที่ฐานข้อมูลส่งกลับแปลงเป็น object
		
		return imageContent; 	// ส่งกลับเป็น javabean
	}
}