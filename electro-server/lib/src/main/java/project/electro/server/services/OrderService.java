package project.electro.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.electro.server.dtos.OrderDTO;
import project.electro.server.dtos.ProductDto;
import project.electro.server.entities.Order;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.exceptions.ProductExistsByIdException;
import project.electro.server.repository.OrderRepository;


@Transactional
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ActivityService activityService;
	
	private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
	
	public List<OrderDTO> getAllOrders(Integer pageNumber, Integer pageSize){

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Order> pageOrders = orderRepository.findAll(pageable);
		List<Order> orders = pageOrders.getContent();
		List<OrderDTO> ordersDto = new ArrayList<OrderDTO>();
		if(orders.size() != 0) {
			orders.forEach(order -> {
				ordersDto.add(convertToOrderDto(order));
			});
			
		}
		
		return ordersDto;
	}
	
	public OrderDTO createOrderDto(String entity) throws Exception {
		
		OrderDTO orderDto = new ObjectMapper().readValue(entity, OrderDTO.class);
		
		if(orderRepository.findById(orderDto.getId()).isPresent())
			throw new ProductExistsByIdException(orderDto.getId());
		else {
			Order order = convertToOrder(orderDto);
			order = orderRepository.save(order);
			activityService.createActivity(ActivityTypeEnum.CREATE, "product with id " + order.getId()+  " created");
			return convertToOrderDto(order);
		}
		
	}
	
	public OrderDTO updateOrderDto(OrderDTO orderDto) throws Exception {
		Optional<Order> orderToUpdate = orderRepository.findById(orderDto.getId());
		if(orderToUpdate.isPresent()) {
			orderDto.setId(orderToUpdate.get().getId());
			Order order = convertToOrder(orderDto);
			order = orderRepository.save(order);
			activityService.createActivity(ActivityTypeEnum.UPDATE, "product with id " + order.getId()+  " updated");
			return convertToOrderDto(order);
		}
		else {
			throw new Exception("Product not found");
		}
		
	}
	
	public void delete(Long id) throws Exception {
		
		orderRepository.deleteById(id);
		Optional<Order> order = orderRepository.findById(id);
		if (orderRepository.existsById(order.get().getId()) == false) {
			
			LOGGER.info("Delete was successful");
			activityService.createActivity(ActivityTypeEnum.DELETE, "product with id " + order.get().getId()+  " deleted");
		}
		else
			LOGGER.warning("Delete has failed for user: " + order.get().getId());
	}

	private OrderDTO convertToOrderDto(Order order) {
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setCreatedDate(order.getCreatedDate());
		orderDTO.setEmail(order.getEmail());
		orderDTO.setOrderStatus(order.getOrderStatus());
		orderDTO.setProduct(order.getProduct());
		orderDTO.setProductsCount(order.getProductsCount());
		orderDTO.setUser(order.getUser());
		
		return orderDTO;
	}
	
	private Order convertToOrder(OrderDTO orderDto) {
		
		Order order = new Order();
		order.setId(orderDto.getId());
		order.setCreatedDate(orderDto.getCreatedDate());
		order.setEmail(orderDto.getEmail());
		order.setOrderStatus(orderDto.getOrderStatus());
		order.setProduct(orderDto.getProduct());
		order.setProductsCount(orderDto.getProductsCount());
		order.setUser(orderDto.getUser());
		
		return order;
	}

}
