/**
 * 
 */
package com.boky.SubjectParser.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boky.SubjectParser.services.facade.IFacade;

/**
 * @author Andras_Bokor
 * 
 */
@Controller
@RequestMapping("/")
public class WebControllerForRoot {

	@Autowired
	IFacade facade;

	@RequestMapping(value = "/")
	public String rootPath() {
		return "redirect:/fuggosegek/kotelezo";
	}

	@RequestMapping(value = "/errorpages/{errorPage}")
	public String errorPages(@PathVariable String errorPage, Model model) {
		model.addAttribute("specializations",
				facade.getAllSpecializationWithNameAndId());
		return errorPage;
	}

	@RequestMapping(value = "surprise")
	public String prettyGirl(Model model) {
		model.addAttribute("specializations",
				facade.getAllSpecializationWithNameAndId());
		model.addAttribute("imageName", facade.getDalyImageName());
		return "prettyGirl";
	}

	@RequestMapping(value = "contacts")
	public String contacts(Model model) {
		model.addAttribute("specializations",
				facade.getAllSpecializationWithNameAndId());
		return "contacts";
	}

}
