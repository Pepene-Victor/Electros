package project.electro.server.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "fabrication_yaer")
	private int fabricationYear;
	
	@Column(name = "stock")
	private Long stock;
	
	@Column(name = "price", scale = 2, nullable = false)
	private Double price;
	
	@Column(name = "u_m")
	private String unitMeasurement;
	
	@Column(name = "warranty_years")
	private int warrantyYears;
	
	@Column(name = "image")
	private byte[] image;
	
	@ManyToMany
	@JoinTable(
	  name = "product_order", 
	  joinColumns = @JoinColumn(name = "product_id"), 
	  inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<Order> orders;

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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
