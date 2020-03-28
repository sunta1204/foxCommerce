package civilize.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DashboardUserController { 
    private DashboardUserDAO dashboardUserDAO;
    private ImageContentDAO imageContentDAO;
	
	public DashboardUserController() throws ClassNotFoundException, SQLException {
        dashboardUserDAO = new DashboardUserDAO(); 
        imageContentDAO = new ImageContentDAO();
    }
    
    @GetMapping("/dashboardUser")
    public String salePageList(Model model) throws SQLException {
    	ArrayList<SalePage> list = dashboardUserDAO.getAllSalePage();
    	model.addAttribute("salePageList", list);
    	return "/User/dashboard";
    }

    @RequestMapping("/salePage/{page_id}")
    public String salePageDetail(@PathVariable("page_id") int page_id, Model model) throws SQLException {
        ImageContent p = imageContentDAO.getSalePage(page_id);
    	model.addAttribute("salePageDetail", p);
        return "/User/salePageDetail";
    }

}



