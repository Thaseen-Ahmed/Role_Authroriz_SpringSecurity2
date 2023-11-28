package com.thaseen.crud.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.thaseen.crud.FileUploadUtil;
import com.thaseen.crud.Model.Product;
import com.thaseen.crud.Service.ProdService;

import io.micrometer.common.util.StringUtils;

@Controller
public class ProdController {

	@Autowired
	private ProdService proserv;
	

	@RequestMapping("index")
	public String Project1(Model model, @Param("keyword") String keyword) {
		
		List<Product> listProduct=proserv.getAllProduct(keyword);
//		model.addAttribute("listProduct", listProduct);
		model.addAttribute("keyword", keyword);
		 if (listProduct != null){
		       model.addAttribute("listProduct", listProduct);
		       System.out.println("YAY");
		   }

		 return "index";
	}
	
	@GetMapping("/showNewProductForm")
	public String Project2(Model model) {
		Product pro = new Product();
		model.addAttribute("product", pro);
		return "Add_Product";
	}
	
	
	@PostMapping("/saveProduct")
	public RedirectView saveProduct (@ModelAttribute("product") Product product,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
			
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

	product.setPhotos(fileName);

	Product saveProduct = proserv.saveProduct(product);
	String uploadDir = "product-photos/" + saveProduct.getId();
	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

	return new RedirectView("/", true);
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String UpdateImage(@PathVariable(value = "id") long id, Model model) {
	// get employee from the service
	Product product = proserv.getProductById(id);
	model.addAttribute("product", product);
	return "Edit-product";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") long id) {
		this.proserv.deleteProductByid(id);
		return "redirect:/";
		
	}
	
	
	
}
