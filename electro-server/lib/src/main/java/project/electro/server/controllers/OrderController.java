package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import project.electro.server.dtos.OrderDTO;
import project.electro.server.services.OrderService;

public class OrderController extends BaseEntityController<OrderDTO>{

	@Autowired
	private OrderService orderService;
	
	@Override
	OrderDTO create(@Valid OrderDTO entity) throws Exception {
		
		return orderService.updateOrderDto(entity);
	}

	@Override
	OrderDTO update(@Valid OrderDTO entity) throws Exception {
		
		return orderService.updateOrderDto(entity);
	}

	@Override
	void delete(Long id) throws Exception {
		
		orderService.delete(id);
	}

	@Override
	public List<OrderDTO> getAll(Integer pageNumber, Integer pageSize) {
		
		return orderService.getAllOrders(pageNumber, pageSize);
	}

	
	
}
