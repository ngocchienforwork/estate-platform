package com.estate.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.estate.constant.SystemConstant;
import com.estate.dto.BuildingDTO;

@Controller(value = "buildingsControllerOfAdmin")
public class BuildingController {
	@RequestMapping(value = "/admin/building/list", method = RequestMethod.GET)
	public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/list");
		// DisplayTagUtils.initSearchBean(request, model);
		//
		// List<UserDTO> news = userService.getUsers(model.getSearchValue(),
		// new PageRequest(model.getPage() - 1, model.getMaxPageItems()));
		// model.setListResult(news);
		// model.setTotalItems(userService.getTotalItems(model.getSearchValue()));
		List<String> districts = Arrays.asList("quận 1", "quận 2", "quận 3");
		model.setDistricts(districts);
		List<String> buildingTypes = Arrays.asList("tầng trệt", "nguyên căn", "nội thất");
		model.setBuildingTypes(buildingTypes);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}
}
