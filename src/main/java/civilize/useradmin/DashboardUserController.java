package civilize.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DashboardUserController { 
    private SalePageDAO salePageDAO;
    private ImageContentDAO imageContentDAO;
    private UserDAO userDAO;
	
	public DashboardUserController() throws ClassNotFoundException, SQLException {
        salePageDAO = new SalePageDAO(); 
        imageContentDAO = new ImageContentDAO();
        userDAO = new UserDAO();
    }

    @GetMapping("/")
    public String index(Model model) throws SQLException{
        return "/index";
    }
    
	@RequestMapping("/pleaseLogin")
    public String plLogin(Model model) throws SQLException {
		return "/index";
    }

    //login
	@RequestMapping(value = "/userLogin" , method = RequestMethod.POST )
	public String Login(Model model, HttpSession session , @RequestParam("username") String username , @RequestParam("password") String password) throws SQLException{
		try {
			User user =  userDAO.login(username, password);
			if(user == null) {
				//Login Failed User pass invalid
				model.addAttribute("Error" , "Login Failed !!");
				return "/index";
			} else {
					// Admin
					if(user.getType().equals("S")) {
						session.setAttribute("u_id", user.getU_id());
						session.setAttribute("username", user.getUsername());
						
						String user_name = (String) session.getAttribute("username");
						User userProfile = userDAO.getUser(user_name);
						model.addAttribute("memberProfile" , userProfile);
						return "redirect:/User/dashboard";
												
					//User	
					}else if (user.getType().equals("G")) {
						session.setAttribute("u_id", user.getU_id());
						session.setAttribute("username", user.getUsername());
						
						String user_name = (String) session.getAttribute("username");
						User userProfile = userDAO.getUser(user_name);
						model.addAttribute("memberProfile" , userProfile);
						return "redirect:/User/dashboard";
					
                    }else if (user.getType().equals("P")) {
						session.setAttribute("u_id", user.getU_id());
						session.setAttribute("username", user.getUsername());
						
						String user_name = (String) session.getAttribute("username");
						User userProfile = userDAO.getUser(user_name);
						model.addAttribute("memberProfile" , userProfile);
						return "redirect:/User/dashboard";
					
                    }else if (user.getType().equals("D")) {
						session.setAttribute("u_id", user.getU_id());
						session.setAttribute("username", user.getUsername());
						
						String user_name = (String) session.getAttribute("username");
						User userProfile = userDAO.getUser(user_name);
						model.addAttribute("memberProfile" , userProfile);
						return "redirect:/User/dashboard";
					
                    }
                    //Error
				}
			} catch (Exception e) {
				model.addAttribute(e);
				return "/index";
            }
            return "/index";
            
        }
        
    //dashboard User
	@GetMapping("/User/dashboard")
	public String dashboardUser(Model model ,HttpSession session) throws SQLException {
        int u_id = (int)session.getAttribute("u_id");
		String user = (String)session.getAttribute("username");
		String userType = (String)session.getAttribute("type");
		
		if(user != null && userType.equals("D")) { //ถ้า user ไม่มีค่า และ userStatus มีค่าเท่ากับ A 
			ArrayList<SalePage> userSalePageList = salePageDAO.getUserSalePage(u_id);
			model.addAttribute("userSalePageList", userSalePageList);
			
			User userProfile = userDAO.getUser(user);
			model.addAttribute("userProfile" , userProfile);
			return "/User/dashboard";
		}else {
			return "redirect:/pleaseLogin";
		}	
	}   

	// sale page detail
    @RequestMapping("/salePage/{page_id}")
    public String salePageDetail(@PathVariable("page_id") int page_id, Model model) throws SQLException {
        ImageContent p = imageContentDAO.getSalePage(page_id);
    	model.addAttribute("salePageDetail", p);
        return "/User/salePageDetail";
    }

}



