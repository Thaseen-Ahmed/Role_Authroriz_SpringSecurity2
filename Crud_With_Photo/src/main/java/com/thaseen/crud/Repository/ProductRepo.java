package com.thaseen.crud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thaseen.crud.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	@Query("SELECT product FROM Product product WHERE CONCAT(product.id, product.productName, ' ',product.price) LIKE%?1%")
			public List<Product> search(String keyword);
			public Product findByproductName(String productName);
			

}
