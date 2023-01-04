package project.electro.server.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ProductDto extends BaseEntityDto{

	@NotNull
	private String productName;
	
	private int fabricationYear;

	@NotNull
	private Long stock;
	
	@Column(name = "price", scale = 2, nullable = false)
	@NotNull
	private Double price;
	
	private String unitMeasurement;
	
	private int warrantyYears;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getFabricationYear() {
		return fabricationYear;
	}

	public void setFabricationYear(int fabricationYear) {
		this.fabricationYear = fabricationYear;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}

	public int getWarrantyYears() {
		return warrantyYears;
	}

	public void setWarrantyYears(int warrantyYears) {
		this.warrantyYears = warrantyYears;
	}
	
}
