package com.estate.service;

import com.estate.dto.ConfigurationDTO;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface IConfigurationService {
  List<ConfigurationDTO> getConfigurations(String searchValue, PageRequest pageRequest);

  int getTotalItems(String searchValue);

  ConfigurationDTO insert(ConfigurationDTO newConfig);

  ConfigurationDTO update(long id, ConfigurationDTO configurationDTO);

  void delete(long[] configIds);

  ConfigurationDTO findConfigById(Long id);

  Map<String,String> getConfigTypes();
}
