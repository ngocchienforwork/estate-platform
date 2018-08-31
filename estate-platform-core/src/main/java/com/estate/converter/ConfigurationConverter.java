package com.estate.converter;

import com.estate.core.entity.ConfigurationEntity;
import com.estate.core.entity.UserEntity;
import com.estate.dto.ConfigurationDTO;
import com.estate.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ConfigurationDTO convertToDto(ConfigurationEntity entity) {
				ConfigurationDTO result = modelMapper.map(entity, ConfigurationDTO.class);
        return result;
    }

    public ConfigurationEntity convertToEntity(ConfigurationDTO dto) {
				ConfigurationEntity result = modelMapper.map(dto, ConfigurationEntity.class);
        return result;
    }
}
