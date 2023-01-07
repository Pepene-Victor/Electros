package project.electro.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.electro.server.dtos.ProductDto;
import project.electro.server.entities.Product;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.exceptions.ProductExistsByIdException;
import project.electro.server.repository.ProductRepository;
import project.electro.server.utils.Utils;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
	
	public List<ProductDto> getAll(){
		
		List<Product> products = productRepository.findAll();
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		if(products.size() != 0) {
			products.forEach(activity -> {
				productsDto.add(convertToProductDto(activity));
			});
			
		}
		
		return productsDto;
	}
	
	public ProductDto createProductDto(ProductDto productDto) throws Exception {
		if(productRepository.findById(productDto.getId()).isPresent())
			throw new ProductExistsByIdException(productDto.getId());
		else {
			Product product = convertToProduct(productDto);
			product = productRepository.save(product);
			Utils.createActivity(ActivityTypeEnum.CREATE, "product with id " + product.getId()+  " created");
			return convertToProductDto(product);
		}
		
	}
	
	public ProductDto updateProductDto(ProductDto productDto) throws Exception {
		Optional<Product> productToUpdate = productRepository.findById(productDto.getId());
		if(productToUpdate.isPresent()) {
			productDto.setId(productToUpdate.get().getId());
			Product product = convertToProduct(productDto);
			product = productRepository.save(product);
			Utils.createActivity(ActivityTypeEnum.UPDATE, "product with id " + product.getId()+  " updated");
			return convertToProductDto(product);
		}
		else {
			throw new Exception("Product not found");
		}
		
	}
	
	public void delete(Long id) throws Exception {
		
		productRepository.deleteById(id);
		Optional<Product> product = productRepository.findById(id);
		if (productRepository.existsById(product.get().getId()) == false) {
			
			LOGGER.info("Delete was successful");
			Utils.createActivity(ActivityTypeEnum.DELETE, "product with id " + product.get().getId()+  " deleted");
		}
		else
			LOGGER.warning("Delete has failed for user: " + product.get().getId());
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
