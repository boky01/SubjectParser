package com.boky.SubjectParser.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boky.SubjectParser.services.dto.DependencyDepthAndDescriptionDTO;
import com.boky.SubjectParser.services.dto.InitializationDataForWebDTO;
import com.boky.SubjectParser.services.facade.IFacade;

@Controller
@RequestMapping("/fuggosegek")
public class WebControllerForFuggosegek {

	@Autowired
	IFacade facade;

	private String[] semesterWithRomanNums = { "I", "II", "III", "VI", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI" };

	@RequestMapping(value = { "/", "" })
	public String rootPath(HttpServletRequest request) {
		return "redirect:/fuggosegek/kotelezo";
	}

	@RequestMapping(value = "/{specialization}")
	public String getInitializationDatas(@PathVariable String specialization, Model model) {
		InitializationDataForWebDTO initializedData = facade.getInitializedData(specialization.toUpperCase());
		model.addAttribute("specializations", initializedData.getSpecializations());
		model.addAttribute("subjects", initializedData.getSubjects());
		model.addAttribute("actualSpecialization", initializedData.getActualSpecialization());
		model.addAttribute("numberOfSemesters", initializedData.getNumberOfSemesters());
		model.addAttribute("semesterWithRomanNums", semesterWithRomanNums);
		return "initialize";
	}

	@RequestMapping(value = "valaszthato")
	public String getInitializationDatasForValaszthatoSpecialization(Model model) {
		InitializationDataForWebDTO initializedData = facade.getInitializedData("VALASZTHATO");
		model.addAttribute("specializations", initializedData.getSpecializations());
		model.addAttribute("subjects", initializedData.getSubjects());
		model.addAttribute("actualSpecialization", initializedData.getActualSpecialization());
		return "initializeForValaszthatoSpecialization";
	}

	@RequestMapping(value = "showDependencies", method = RequestMethod.GET)
	@ResponseBody
	public DependencyDepthAndDescriptionDTO showDependencies(@RequestParam String subjectId, @RequestParam String specializationId) {
		DependencyDepthAndDescriptionDTO dto = facade.getDependencyDepthAndDescription(subjectId, specializationId);
		return dto;
	}
}
