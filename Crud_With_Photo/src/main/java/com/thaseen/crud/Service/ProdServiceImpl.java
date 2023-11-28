package com.thaseen.crud.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.hibernate.grammars.hql.HqlParser.SortDirectionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thaseen.crud.Model.Product;
import com.thaseen.crud.Repository.ProductRepo;

@Service

public class ProdServiceImpl implements ProdService{

	@Autowired
	ProductRepo prorepo;
	
	
	@Override
	public List<Product> getAllProduct(String keyword) {
		if(keyword != null) {
			return prorepo.search(keyword);
		}
		else {
			return (List<Product>) prorepo.findAll();
		}
	}

	@Override
	public Product saveProduct(Product product) {

		return this.prorepo.save(product);
	}

	@Override
	public Product getProductById(long id) {

		Optional<Product> opt=prorepo.findById(id);
		Product product=null;
		if(opt.isPresent()) {
			product=opt.get();
		}
		else {
			throw new RuntimeException("Product Not Found for id :"+id);
		}
		
		return product;
	}

	@Override
	public void deleteProductByid(long id) {
		this.prorepo.deleteById(id);
		
	}

//	@Override
//	public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sotrDir) {
//
//		Sort sort = SortDirection.equalI
//		
//		Pageable page= PageRequest.of(pageNo -1, pageSize, sort);
//		return this.prorepo.findAll((Example<S>) page);
//		
//		
//		return null;
//	}

	
	
	
	
	
}
