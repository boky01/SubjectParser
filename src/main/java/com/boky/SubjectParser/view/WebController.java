/**
 * 
 */
package com.boky.SubjectParser.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boky.SubjectParser.services.dto.DependencyDepthAndDescriptionDTO;
import com.boky.SubjectParser.services.dto.InitializationDataForWebDTO;
import com.boky.SubjectParser.services.facade.IFacade;

/**
 * @author Andras_Bokor
 *
 */
@Controller
public class WebController {

    @Autowired
    IFacade facade;

    private String[] semesterWithRomanNums = {"I", "II", "III", "VI", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI"};

    @RequestMapping(value = "/")
    public String rootPath() {
        return "redirect:/kotelezo";
    }

    @RequestMapping(value = {"kotelezo", "geotechnikai", "magasepitesi", "telepulesi", "tuzvedelmi"}, method = RequestMethod.GET)
    public String getInitializationDatas(HttpServletRequest request, Model model) {
        String actualSpecialization = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1).toUpperCase(); // /SubjectParser/kotelezo -> KOTELEZO

        InitializationDataForWebDTO initializedData = facade.getInitializedData(actualSpecialization);
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

    @RequestMapping(value = "/error404")
    public String error404(Model model) {
        model.addAttribute("specializations", facade.getAllSpecializationWithNameAndId());
        return "error404";
    }

    @RequestMapping(value = "showDependencies", method = RequestMethod.GET)
    @ResponseBody
    public DependencyDepthAndDescriptionDTO showDependencies(@RequestParam String subjectId, @RequestParam String specializationId) {
        DependencyDepthAndDescriptionDTO dto = facade.getDependencyDepthAndDescription(subjectId, specializationId);
        return dto;
    }

    @RequestMapping(value = "surprise")
    public String prettyGirl(Model model) {
        model.addAttribute("specializations", facade.getAllSpecializationWithNameAndId());
        model.addAttribute("imageName", facade.getImageName());
        return "prettyGirl";
    }

    @RequestMapping(value = "contacts")
    public String contacts(Model model) {
        model.addAttribute("specializations", facade.getAllSpecializationWithNameAndId());
        return "contacts";
    }

}
