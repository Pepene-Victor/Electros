package project.electro.server.config.authentication;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.electro.server.enums.RoleEnum;
import project.electro.server.repository.UserRepository;




@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		project.electro.server.entities.User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		return new User(username, user.getPassword(), getAuthority(user.getRole()));
	}
	
	private List<SimpleGrantedAuthority> getAuthority(RoleEnum role) {
		
		return Arrays.asList(new SimpleGrantedAuthority(role.getValue()));
	}
	
}
