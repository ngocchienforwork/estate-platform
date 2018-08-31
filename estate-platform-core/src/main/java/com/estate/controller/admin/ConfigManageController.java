package com.estate.controller.admin;

import com.estate.constant.SystemConstant;
import com.estate.dto.ConfigurationDTO;
import com.estate.service.IConfigurationService;
import com.estate.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ConfigManageController {

  @Autowired
  private IConfigurationService configurationService;

  @RequestMapping(value = "/admin/configuration/list", method = RequestMethod.GET)
  public ModelAndView getConfigurations(@ModelAttribute(SystemConstant.MODEL) ConfigurationDTO model, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("admin/config/list");
    DisplayTagUtils.initSearchBean(request, model);
    List<ConfigurationDTO> configurations = configurationService.getConfigurations(model.getSearchValue(),
        new PageRequest(model.getPage() - 1, model.getMaxPageItems()));
    model.setListResult(configurations);
    model.setTotalItems(configurationService.getTotalItems(model.getSearchValue()));
    mav.addObject(SystemConstant.MODEL, model);
    return mav;
  }

  @RequestMapping(value = "/admin/configuration/edit", method = RequestMethod.GET)
  public ModelAndView addConfig(@ModelAttribute(SystemConstant.MODEL) ConfigurationDTO model, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("admin/config/edit");
    model.setConfigTypes(configurationService.getConfigTypes());
    mav.addObject(SystemConstant.MODEL,model);
    return mav;
  }

  @RequestMapping(value = "/admin/configuration/edit/{id}", method = RequestMethod.GET)
  public ModelAndView updateConfig(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("admin/config/edit");
    ConfigurationDTO model = configurationService.findConfigById(id);
    model.setConfigTypes(configurationService.getConfigTypes());
    mav.addObject(SystemConstant.MODEL, model);
    return mav;
  }
}
