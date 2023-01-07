package project.electro.server.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.electro.server.dtos.CustomerDto;
import project.electro.server.entities.Customer;
import project.electro.server.entities.User;
import project.electro.server.repository.CustomerRepository;
import project.electro.server.repository.UserRepository;
@Service
@Transactional
public class CustomerService implements BaseUserEntityService<CustomerDto>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerDto create(CustomerDto entityDto, String username) throws Exception {
		
		Optional<User> user = userRepository.findByUsername(username);
		try {
			entityDto.setCreatedDate(LocalDateTime.now());
			entityDto.setEmail(username);
			Customer customer = convertToCustomer(entityDto);
			
			if(user.isPresent()) {
				customer.setUser(user.get());
			}
			customer = customerRepository.save(customer);
			return convertToCustomerDto(customer);
		}catch(Exception e){
			
			throw new Exception("Can't create details for user: " + user.get().getUsername());
		}
	}

	@Override
	public CustomerDto update(CustomerDto entityDto) throws Exception {
		try {
			Optional<Customer> customerToUpdate = customerRepository.findById(entityDto.getId());
			entityDto.setId(customerToUpdate.get().getId());
			Customer customer  = convertToCustomer(entityDto);
			customer = customerRepository.save(customer);
			return convertToCustomerDto(customer);
		}catch(Exception e){
			
			throw new Exception("Update failed");
		}

	}

	@Override
	public CustomerDto findByUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			Customer customer = customerRepository.findByUserId(user.get().getId());
			return convertToCustomerDto(customer);
		}
		return null;

	}
	
	private CustomerDto convertToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setAge(customer.getAge());
		customerDto.setCreatedDate(customer.getCreatedDate());
		customerDto.setEmail(customer.getEmail());
		customerDto.setGender(customer.getGender());
		customerDto.setName(customer.getName());
		customerDto.setStatus(customer.getStatus());
		customerDto.setUser(customer.getUser());
		customerDto.setId(customer.getId());
		
		return customerDto;
	}

	private Customer convertToCustomer(CustomerDto entityDto) {

		Customer customer = new Customer();
		customer.setAge(entityDto.getAge());
		customer.setCreatedDate(entityDto.getCreatedDate());
		customer.setEmail(entityDto.getEmail());
		customer.setGender(entityDto.getGender());
		customer.setName(entityDto.getName());
		customer.setStatus(entityDto.getStatus());
		customer.setUser(entityDto.getUser());
		customer.setId(entityDto.getId());
		return customer;
	}

}
