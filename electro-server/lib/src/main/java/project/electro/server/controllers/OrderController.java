package project.electro.server.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.electro.server.dtos.OrderDTO;
import project.electro.server.dtos.ProductDto;
import project.electro.server.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseEntityController<OrderDTO>{

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	OrderDTO create(@RequestParam("entity") String entity) throws Exception {
		
		return orderService.createOrderDto(entity);
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
