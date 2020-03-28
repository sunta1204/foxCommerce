package civilize.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DashboardUserController { 
    private DashboardUserDAO dashboardUserDAO;
	
	public DashboardUserController() throws ClassNotFoundException, SQLException {
        dashboardUserDAO = new DashboardUserDAO(); 
    }
    
    @GetMapping("/dashboardUser")
    public String search(Model model) throws SQLException {
    	ArrayList<SalePage> list = dashboardUserDAO.getAllSalePage();
    	model.addAttribute("salePageList", list);
    	return "/User/dashboard";
    }

}



