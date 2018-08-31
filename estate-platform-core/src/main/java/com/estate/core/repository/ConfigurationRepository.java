package com.estate.core.repository;

import com.estate.core.entity.ConfigurationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
	ConfigurationEntity findOneByKey(String key);
	List<ConfigurationEntity> findByType(String type);
	Page<ConfigurationEntity> findByTypeContainingIgnoreCaseOrNameContainingIgnoreCaseOrKeyContainingIgnoreCase(String type, String name, String key, Pageable pageable);
	long countByTypeContainingIgnoreCaseOrNameContainingIgnoreCaseOrKeyContainingIgnoreCase(String type, String name, String key);
}
