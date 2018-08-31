package com.estate.controller.admin.api;

import com.estate.dto.ConfigurationDTO;
import com.estate.dto.UserDTO;
import com.estate.service.IConfigurationService;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajax/config")
public class ConfigurationAPI {

	@Autowired
	private IConfigurationService configurationService;
	
	@PostMapping
	public ResponseEntity<ConfigurationDTO> createConfig(@RequestBody ConfigurationDTO newConfig) {
		return ResponseEntity.ok(configurationService.insert(newConfig));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ConfigurationDTO> updateConfig(@PathVariable("id") long id, @RequestBody ConfigurationDTO configurationDTO) {
		return ResponseEntity.ok(configurationService.update(id, configurationDTO));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteConfig(@RequestBody long[] configIds) {
		if (configIds.length > 0) {
			configurationService.delete(configIds);
		}
		return ResponseEntity.noContent().build();
	}
}
