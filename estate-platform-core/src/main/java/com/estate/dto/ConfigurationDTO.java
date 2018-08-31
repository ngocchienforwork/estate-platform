package com.estate.dto;

import java.util.Map;

public class ConfigurationDTO extends AbstractDTO<ConfigurationDTO> {

	private static final long serialVersionUID = 2234998334162241379L;

	private String type;
	private String name;
	private String key;
	private String value;
	private Map<String,String> configTypes;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getConfigTypes() {
		return configTypes;
	}

	public void setConfigTypes(Map<String, String> configTypes) {
		this.configTypes = configTypes;
	}
}
