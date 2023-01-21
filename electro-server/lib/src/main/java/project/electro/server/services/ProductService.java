package project.electro.server.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.electro.server.dtos.ProductDto;
import project.electro.server.entities.Product;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.exceptions.ProductExistsByIdException;
import project.electro.server.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ActivityService activityService;

	private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
	
	public List<ProductDto> getAll(Integer pageNumber, Integer pageSize){
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Product> pageProducts = productRepository.findAll(pageable);
		List<Product> products = pageProducts.getContent();
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		if(products.size() != 0) {
			products.forEach(product -> {
				productsDto.add(convertToProductDto(product));
			});
			
		}
		
		return productsDto;
	}
	
	public ProductDto createProductDto(String productString, MultipartFile file) throws Exception {
	
		ProductDto productDto = new ObjectMapper().readValue(productString, ProductDto.class);
		
		if(file != null){
			try {
				productDto.setImage(Base64.getEncoder().encode(file.getBytes()));
			}catch(IOException e) {
				
				e.printStackTrace();
			}
		}
			
			Product product = convertToProduct(productDto);
			product = productRepository.save(product);
			activityService.createActivity(ActivityTypeEnum.CREATE, "product with id " + product.getId()+  " created");
			return convertToProductDto(product);
		
	}
	
	public ProductDto updateProductDto(ProductDto productDto) throws Exception {
		Optional<Product> productToUpdate = productRepository.findById(productDto.getId());
		if(productToUpdate.isPresent()) {
			productDto.setId(productToUpdate.get().getId());
			Product product = convertToProduct(productDto);
			product = productRepository.save(product);
			activityService.createActivity(ActivityTypeEnum.UPDATE, "product with id " + product.getId()+  " updated");
			return convertToProductDto(product);
		}
		else {
			throw new Exception("Product not found");
		}
		
	}
	
	public void delete(Long id) throws Exception {
		
		productRepository.deleteById(id);
		
		if (productRepository.existsById(id) == false) {
			
			LOGGER.info("Delete was successful");
			activityService.createActivity(ActivityTypeEnum.DELETE, "product with id " +id+  " deleted");
		}
		else
			LOGGER.warning("Delete has failed for user: " + id);
	}


	private ProductDto convertToProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCreatedDate(product.getCreatedDate());
		productDto.setFabricationYear(product.getFabricationYear());
		productDto.setImage(product.getImage());
		productDto.setPrice(product.getPrice());
		productDto.setProductName(product.getProductName());
		productDto.setStock(product.getStock());
		productDto.setUnitMeasurement(product.getUnitMeasurement());
		productDto.setWarrantyYears(product.getWarrantyYears());
		return productDto;
	}



	private Product convertToProduct(ProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setCreatedDate(productDto.getCreatedDate());
		product.setFabricationYear(productDto.getFabricationYear());
		product.setImage(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setProductName(productDto.getProductName());
		product.setStock(productDto.getStock());
		product.setUnitMeasurement(productDto.getUnitMeasurement());
		product.setWarrantyYears(productDto.getWarrantyYears());
		return product;
	}
}
