package project.electro.server.dtos;

import java.util.Set;

import project.electro.server.entities.Product;
import project.electro.server.enums.OrderStatusEnum;

public class OrderDTO extends BaseUserEntityDto{

	Set<Product> product;
	
	private Integer productsCount;

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
