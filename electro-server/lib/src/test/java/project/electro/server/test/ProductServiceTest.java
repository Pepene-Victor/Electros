package project.electro.server.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.electro.server.dtos.ProductDto;
import project.electro.server.services.ProductService;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

	public static final String PRODUCT_NAME = "product1";
	public static final Long STOCK = 100l;
	public static final Double PRICE = 99.99;
	
	public static final String PRODUCT_NAME_2 = "product2";
	public static final Long STOCK_2 = 200l;
	public static final Double PRICE_2 = 199.99;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ObjectMapper mapper;
	
	private ProductDto persistedProduct;
	private Long id;
	
	@Test
	public void test001Create() throws  Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName(PRODUCT_NAME);
		productDto.setStock(STOCK);
		productDto.setPrice(PRICE);
		
		MultipartFile file = null;
		String value = mapper.writeValueAsString(productDto);
		
		persistedProduct = productService.createProductDto(value, file);
		updateProduct();
		assertNotNull(persistedProduct);
		
	}
	public void updateProduct() {
		
		id = persistedProduct.getId();
	}
	
	@Test
	public void test002Update() throws Exception {

		persistedProduct.setProductName(PRODUCT_NAME_2);
		persistedProduct.setStock(STOCK_2);
		persistedProduct.setPrice(PRICE_2);
		persistedProduct = productService.updateProductDto(persistedProduct);
		Assertions.assertEquals(PRODUCT_NAME_2, persistedProduct.getProductName());
		Assertions.assertEquals(STOCK_2, persistedProduct.getStock());
		Assertions.assertEquals(PRICE_2, persistedProduct.getPrice());
		updateProduct();
	}
	
	
	@Test
	public void test004Delete() throws Exception {
		productService.delete(id);
	
		Assertions.assertFalse(productService.existsById(id));
		
	}
	
}
