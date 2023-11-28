package com.thaseen.crud.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.thaseen.crud.Model.Product;

public interface ProdService {

	List<Product> getAllProduct(String keyword);
	
	Product saveProduct(Product product);
	Product getProductById(long id);
	
	void deleteProductByid(long id);
	
//	Page<Product> findPaginated(int pageNo,int pageSize, String sortField, String sotrDir);
	
}
