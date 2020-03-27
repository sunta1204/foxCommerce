package civilize;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController { 
	private ProductDAO productDAO;
	
	public ProductController() throws ClassNotFoundException, SQLException {
		productDAO = new ProductDAO(); 
	}
	
    @GetMapping("/products")
    public String search(Model model) throws SQLException {
    	ArrayList<Product> list = productDAO.getAllProduct();
    	model.addAttribute("productList", list);
    	return "/list-product";
    }
    
    @GetMapping("/product/{pid}")
    public String detail(@PathVariable("pid")  int pid, Model model) throws SQLException {
    	Product p = productDAO.getProduct(pid);
    	model.addAttribute("product", p);
        return "/detail";
	}
	
	@GetMapping("/")
    public String welcome(Model model) {
    	model.addAttribute("message", "Civilized Land");
        return "/User/dashboard";
    }

}
