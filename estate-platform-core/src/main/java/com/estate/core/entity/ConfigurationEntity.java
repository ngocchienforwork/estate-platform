package com.estate.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity extends BaseEntity {

	private static final long serialVersionUID = -3641402942916578699L;

	@Column
	private String type;

	@Column
	private String name;

	@Column(unique = true)
	private String key;

	@Column(columnDefinition = "text")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
