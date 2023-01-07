package project.electro.server.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public UserDto create(UserDto userDto) throws UserExistsByUsernameException {
		
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
	
	public UserDto updateUserName(UserDto userDto) throws UserExistsByUsernameException{
		
		String username = userDto.getUsername();
		
		if (userRepository.existsByUsername(username))
			throw new UserExistsByUsernameException(username);
		
		User user =  convertToUser(userDto);
		user = userRepository.save(user);

		return convertToUserDto(user);
		
	}
	
	public UserDto updateUserPassword(UserDto userDto) {
		
		String newPassword = userDto.getPassword();
		
		userDto.setPassword(passwordEncoder.encode(newPassword));
		userDto.setPasswordStatus(PasswordStatusEnum.NEW);
		
		User user =  convertToUser(userDto);
		user = userRepository.save(user);
		
		return convertToUserDto(user);
		
	}
	public void deleteUser(Long id) {
		
		userRepository.deleteById(id);
		Optional<User> user = userRepository.findById(id);
		if (userRepository.existsByUsername(user.get().getUsername()) == false) {
			
			LOGGER.info("Delete was successful");
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
		
		return user;
	}
	
	private UserDto convertToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setCreatedDate(user.getCreatedDate());
		userDto.setPassword(user.getPassword());
		userDto.setPasswordStatus(user.getPasswordStatus());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole());
		
		return userDto;
	}
	
}
