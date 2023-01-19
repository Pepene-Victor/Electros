package project.electro.server.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import project.electro.server.enums.OrderStatusEnum;


@Entity
@Table(name = "orders")
public class Order extends BaseUserEntity{
	
	@Column(name = "product")
	@ManyToMany(mappedBy = "orders")
	Set<Product> product;
	
	@Column(name = "products_count")
	private Integer productsCount;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private OrderStatusEnum orderStatus;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Integer getProductsCount() {
		return productsCount;
	}

	public void setProductsCount(Integer productsCount) {
		this.productsCount = productsCount;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
