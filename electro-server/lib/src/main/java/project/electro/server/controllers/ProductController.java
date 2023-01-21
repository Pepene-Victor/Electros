package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.electro.server.dtos.ProductDto;
import project.electro.server.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseEntityController<ProductDto>{

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/create-product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ProductDto create(@Valid @RequestParam("entity") String entity, @RequestParam(name = "file", required = false) MultipartFile file) throws Exception {
		
		return productService.createProductDto(entity, file);
	}

	@Override
	ProductDto update(@Valid @RequestBody ProductDto entity) throws Exception {
		
		return productService.updateProductDto(entity);
	}

	@Override
	void delete(@NotNull @PathVariable Long id) throws Exception {
	
		productService.delete(id);
	}

	@Override
	public List<ProductDto> getAll(Integer pageNumber, Integer pageSize) {
		
		return productService.getAll(pageNumber, pageSize);
	}



	
}
