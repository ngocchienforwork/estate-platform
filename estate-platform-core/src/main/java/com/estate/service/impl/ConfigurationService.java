package com.estate.service.impl;

import com.estate.constant.ConfigurationConstant;
import com.estate.converter.ConfigurationConverter;
import com.estate.core.entity.ConfigurationEntity;
import com.estate.core.repository.ConfigurationRepository;
import com.estate.dto.ConfigurationDTO;
import com.estate.service.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationService implements IConfigurationService {

  @Autowired
  private ConfigurationRepository configurationRepository;

  @Autowired
  private ConfigurationConverter configurationConverter;

  @Override
  public List<ConfigurationDTO> getConfigurations(String searchValue, PageRequest pageable) {
    Page<ConfigurationEntity> configPage = null;
    if (searchValue != null) {
      configPage = configurationRepository.findByTypeContainingIgnoreCaseOrNameContainingIgnoreCaseOrKeyContainingIgnoreCase(searchValue, searchValue, searchValue, pageable);
    } else {
      configPage = configurationRepository.findAll(pageable);
    }
    List<ConfigurationEntity> configurationEntities = configPage.getContent();
    List<ConfigurationDTO> result = new ArrayList<>();
    for (ConfigurationEntity item : configurationEntities) {
      ConfigurationDTO configurationDTO = configurationConverter.convertToDto(item);
      result.add(configurationDTO);
    }
    return result;
  }

  @Override
  public int getTotalItems(String searchValue) {
    int totalItem = 0;
    if (searchValue != null) {
      totalItem = (int) configurationRepository.countByTypeContainingIgnoreCaseOrNameContainingIgnoreCaseOrKeyContainingIgnoreCase(searchValue, searchValue, searchValue);
    } else {
      totalItem = (int) configurationRepository.count();
    }
    return totalItem;
  }

  @Override
  @Transactional
  public ConfigurationDTO insert(ConfigurationDTO newConfig) {
    ConfigurationEntity configuration = configurationConverter.convertToEntity(newConfig);
    return configurationConverter.convertToDto(configurationRepository.save(configuration));
  }

  @Override
  @Transactional
  public ConfigurationDTO update(long id, ConfigurationDTO updateConfig) {
    ConfigurationEntity oldConfig = configurationRepository.findOne(id);
    oldConfig.setKey(updateConfig.getKey());
    oldConfig.setName(updateConfig.getName());
    oldConfig.setType(updateConfig.getType());
    oldConfig.setValue(updateConfig.getValue());
    return configurationConverter.convertToDto(configurationRepository.save(oldConfig));
  }

  @Override
  @Transactional
  public void delete(long[] configIds) {
    for (Long item: configIds) {
      configurationRepository.delete(item);
    }
  }

  @Override
  public ConfigurationDTO findConfigById(Long id) {
    ConfigurationEntity entity = configurationRepository.findOne(id);
    return configurationConverter.convertToDto(entity);
  }

	@Override
	public Map<String, String> getConfigTypes() {
    Map<String,String> configTypes = new HashMap<>();
    configTypes.put(ConfigurationConstant.EMAIL_CONFIGURATION, ConfigurationConstant.EMAIL_CONFIGURATION);
    configTypes.put(ConfigurationConstant.EMAIL_TEMPLATE, ConfigurationConstant.EMAIL_TEMPLATE);
    return configTypes;
	}
}
