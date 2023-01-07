package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.ProductDto;
import project.electro.server.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseEntityController<ProductDto>{

	@Autowired
	private ProductService productService;
	
	@Override
	ProductDto create(@Valid ProductDto entity) throws Exception {
		// TODO Auto-generated method stub
		return productService.createProductDto(entity);
	}

	@Override
	ProductDto update(@Valid ProductDto entity) throws Exception {
		// TODO Auto-generated method stub
		return productService.updateProductDto(entity);
	}

	@Override
	void delete(@NotNull Long id) throws Exception {
		// TODO Auto-generated method stub
		productService.delete(id);
	}

	@Override
	public List<ProductDto> getAll() {
		// TODO Auto-generated method stub
		return productService.getAll();
	}

	
}
