package project.electro.server.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.electro.server.dtos.AdministratorDto;
import project.electro.server.entities.Administrator;
import project.electro.server.entities.User;
import project.electro.server.repository.AdministratorRepository;
import project.electro.server.repository.UserRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public AdministratorDto create(AdministratorDto administratorDto, String username) throws Exception {
		
		Optional<User> user = userRepository.findByUsername(username);
		try {
			administratorDto.setCreatedDate(LocalDateTime.now());
			Administrator admin = convertToAdmin(administratorDto);
			
			if(user.isPresent()) {
				admin.setUser(user.get());
			}
			admin = administratorRepository.save(admin);
			return convertToAdminDto(admin);
		}catch(Exception e){
			
			throw new Exception("Can't create details for user: " + user.get().getUsername());
		}

	}
	

	private Administrator convertToAdmin(AdministratorDto administratorDto) {
		
		Administrator admin = new Administrator();
		admin.setCompany(administratorDto.getCompany());
		admin.setEmail(administratorDto.getEmail());
		admin.setCreatedDate(administratorDto.getCreatedDate());
		admin.setFirstName(administratorDto.getFirstName());
		admin.setLastName(administratorDto.getLastName());
		admin.setPersonalPhoneNumber(administratorDto.getPersonalPhoneNumber());
		admin.setStatus(administratorDto.getStatus());
		admin.setUser(administratorDto.getUser());
		return admin;
	}
	private AdministratorDto convertToAdminDto(Administrator administrator) {
		
		AdministratorDto adminDto = new AdministratorDto();
		adminDto.setCompany(administrator.getCompany());
		adminDto.setEmail(administrator.getEmail());
		adminDto.setCreatedDate(administrator.getCreatedDate());
		adminDto.setFirstName(administrator.getFirstName());
		adminDto.setLastName(administrator.getLastName());
		adminDto.setPersonalPhoneNumber(administrator.getPersonalPhoneNumber());
		adminDto.setStatus(administrator.getStatus());
		adminDto.setUser(administrator.getUser());
		return adminDto;
	}
	
}
