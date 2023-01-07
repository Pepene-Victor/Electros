package project.electro.server.services;

import project.electro.server.dtos.BaseUserEntityDto;

public interface BaseUserEntityService<T extends BaseUserEntityDto> {
	
	 T create(T entityDto, String username) throws Exception;
	 
	 T update(T entityDto) throws Exception;
	 
	 T findByUser(String username);
	
}
