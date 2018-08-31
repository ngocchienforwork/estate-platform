package com.estate.dto;

import java.util.List;

public class BuildingDTO extends AbstractDTO<BuildingDTO> {

	private static final long serialVersionUID = 4428522696190004907L;
	private List<String> districts;
	private String districtCode;
	private String districtName;
	private List<UserDTO> userDTOs;
	private String userName;
	private List<String> buildingTypes;
	private String buildingTypeName;
	
	public List<String> getDistricts() {
		return districts;
	}
	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<UserDTO> getUserDTOs() {
		return userDTOs;
	}
	public void setUserDTOs(List<UserDTO> userDTOs) {
		this.userDTOs = userDTOs;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getBuildingTypes() {
		return buildingTypes;
	}
	public void setBuildingTypes(List<String> buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
	public String getBuildingTypeName() {
		return buildingTypeName;
	}
	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}
	
}
