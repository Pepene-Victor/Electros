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
public class AdministratorService implements BaseUserEntityService<AdministratorDto>{

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public AdministratorDto findByUser(String username) {
		
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			Administrator admin = administratorRepository.findByUserId(user.get().getId());
			return convertToAdminDto(admin);
		}
		return null;
	}
	@Override
	public AdministratorDto create(AdministratorDto administratorDto, String username) throws Exception {
		Optional<User> user = userRepository.findByUsername(username);
		try {
			administratorDto.setCreatedDate(LocalDateTime.now());
			administratorDto.setEmail(username);
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
	@Override
	public AdministratorDto update(AdministratorDto administratorDto) throws Exception {
		try {
			Optional<Administrator> adminToUpdate = administratorRepository.findById(administratorDto.getId());
			administratorDto.setId(adminToUpdate.get().getId());
			Administrator admin  = convertToAdmin(administratorDto);
			admin = administratorRepository.save(admin);
			return convertToAdminDto(admin);
		}catch(Exception e){
			
			throw new Exception("Update failed");
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
		admin.setId(administratorDto.getId());
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
		adminDto.setId(administrator.getId());
		return adminDto;
	}
	
}
