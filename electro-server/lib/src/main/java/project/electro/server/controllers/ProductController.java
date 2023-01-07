package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.electro.server.dtos.ProductDto;

@RestController
@RequestMapping("/products")
public class ProductController implements BaseEntityController<ProductDto>{

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAll(){
		return null;
	}
	
	@Override
	public ProductDto create(@Valid ProductDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto update(@Valid ProductDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(@NotNull Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
