package project.electro.server.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.electro.server.dtos.UserDto;
import project.electro.server.entities.User;
import project.electro.server.enums.ActivityTypeEnum;
import project.electro.server.enums.PasswordStatusEnum;
import project.electro.server.exceptions.UserExistsByUsernameException;
import project.electro.server.repository.UserRepository;
import project.electro.server.utils.Utils;

@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	public List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize){

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<User> pageUsers = userRepository.findAll(pageable);
		List<User> users = pageUsers.getContent();
		List<UserDto> usersDto = new ArrayList<UserDto>();
		if(users.size() != 0) {
			users.forEach(user -> {
				usersDto.add(convertToUserDto(user));
			});
			
		}
		
		return usersDto;
	}
	
	public UserDto findByUsername(String username) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isPresent())
			return convertToUserDto(user.get());
		return null;
	}
	
	public UserDto getLoggedUser() throws Exception {
		
		Utils utils = new Utils();
		
		String username = utils.getLoggedUser().getUsername();
		UserDto loggedUser = findByUsername(username);
		
		return loggedUser;
		
	}
	
	public UserDto register(UserDto userDto) throws Exception {
		
		userDto.setCreatedDate(LocalDateTime.now());
		
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserExistsByUsernameException(userDto.getUsername());
		}
		
		String password = userDto.getPassword();
		
		userDto.setPassword(passwordEncoder.encode(password));
		
		User user =  convertToUser(userDto);
		user = userRepository.save(user);
		
		Utils.createActivity(ActivityTypeEnum.CREATE, "user " + user.getUsername()+  " created");

		return convertToUserDto(user);
		
	}
	
	public UserDto updateUserName(UserDto userDto) throws Exception{
		
		String username = userDto.getUsername();
		
		if (userRepository.existsByUsername(username))
			throw new UserExistsByUsernameException(username);
		
		User user =  convertToUser(userDto);
		user = userRepository.save(user);
		
		Utils.createActivity(ActivityTypeEnum.UPDATE, "user " + user.getUsername()+  " updated");

		return convertToUserDto(user);
		
	}
	
	public UserDto updateUserPassword(UserDto userDto) throws Exception {
		
		String newPassword = userDto.getPassword();
		
		userDto.setPassword(passwordEncoder.encode(newPassword));
		userDto.setPasswordStatus(PasswordStatusEnum.NEW);
		
		User user =  convertToUser(userDto);
		user = userRepository.save(user);
		Utils.createActivity(ActivityTypeEnum.UPDATE, "user " + user.getUsername()+  " updated");
		return convertToUserDto(user);
		
	}
	public void deleteUser(Long id) throws Exception {
		
		userRepository.deleteById(id);
		Optional<User> user = userRepository.findById(id);
		if (userRepository.existsByUsername(user.get().getUsername()) == false) {
			
			LOGGER.info("Delete was successful");
			Utils.createActivity(ActivityTypeEnum.DELETE, "user " + user.get().getUsername()+  " deleted");
		}
		else
			LOGGER.warning("Delete has failed for user: " + user.get().getUsername());
	}
	

	private User convertToUser(UserDto userDto) {
		
		User user = new User();
		user.setCreatedDate(userDto.getCreatedDate());
		user.setPassword(userDto.getPassword());
		user.setPasswordStatus(userDto.getPasswordStatus());
		user.setUsername(userDto.getUsername());
		user.setRole(userDto.getRole());
		user.setId(userDto.getId());
		
		return user;
	}
	
	private UserDto convertToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setCreatedDate(user.getCreatedDate());
		userDto.setPasswordStatus(user.getPasswordStatus());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole());
		userDto.setId(user.getId());
		
		return userDto;
	}
	
}
