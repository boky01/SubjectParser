/**
 * 
 */
package com.boky.SubjectParser.services.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.interfaces.DependencyDao;
import com.boky.SubjectParser.daolayer.interfaces.SpecializationDao;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;
import com.boky.SubjectParser.services.dto.DTOTransformatorUtil;
import com.boky.SubjectParser.services.dto.DependencyDepthAndDescriptionDTO;
import com.boky.SubjectParser.services.dto.InitializationDataForWebDTO;
import com.boky.SubjectParser.services.dto.SpecializationWithNameAndCodeDTO;
import com.boky.SubjectParser.services.dto.SubjectWithCodeAndNameAndSemesterDTO;

/**
 * @author Andras_Bokor
 *
 */
@Service
@Transactional
public class ServiceForWeb implements IServiceForWeb, ApplicationContextAware {
    final Logger logger = LoggerFactory.getLogger(ServiceForWeb.class);
    private ApplicationContext applicationContext;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private DependencyDao dependencyDao;

    @Autowired
    private SpecializationDao specializationDao;

    @Autowired
    private Random randomGenerator;

    
    private ServletContext servletContext;

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.services.services.IServiceForWeb#getInitializedData(java.lang.String)
     */
    @Override
    public InitializationDataForWebDTO getInitializedData(String specializationId) {
        List<SubjectWithCodeAndNameAndSemesterDTO> subjectWithCodeAndNameAndSemesterDTOs = DTOTransformatorUtil
                .convertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList(subjectDao.getSubjectsBySpecializationId(specializationId));
        if (!specializationId.equals("KOTELEZO") && !specializationId.equals("VALASZTHATO")) {
            subjectWithCodeAndNameAndSemesterDTOs.addAll(DTOTransformatorUtil.convertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList(subjectDao
                    .getSubjectsBySpecializationId("KOTELEZO")));
        }

        List<SpecializationWithNameAndCodeDTO> specializationWithNameAndCodeDTOs = DTOTransformatorUtil
                .convertSpecializatonListToSpecializationWithNameAndCodeDTOList(specializationDao.getAllEntity());
        Integer numberOfSemester = applicationContext.getBean("numberOfSemester", Integer.class);
        SpecializationWithNameAndCodeDTO actualSpecialization = DTOTransformatorUtil.convertSpecializatonToSpecializationWithNameAndCodeDTO(specializationDao
                .getById(specializationId));
        InitializationDataForWebDTO initializationData = new InitializationDataForWebDTO(specializationWithNameAndCodeDTOs, subjectWithCodeAndNameAndSemesterDTOs,
                actualSpecialization, numberOfSemester);

        return initializationData;
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.services.services.IServiceForWeb#getDependencyDepthAndDescription(java.lang.String, java.lang.String)
     */
    @Override
    public DependencyDepthAndDescriptionDTO getDependencyDepthAndDescription(String subjectId, String specialization) {
        Subject subject = subjectDao.getById(subjectId);
        Map<String, Integer> subjectDepths = new HashMap<>();
        buildDependencyDepthHashMap(subject, specialization, 0, false, false, subjectDepths); //backward
        buildDependencyDepthHashMap(subject, specialization, 0, false, true, subjectDepths); //forward

        DependencyDepthAndDescriptionDTO resultDTO = new DependencyDepthAndDescriptionDTO();
        resultDTO.setDependencyDepths(subjectDepths);
        resultDTO.setDescription(createDescriptionFromSubject(subject));
        return resultDTO;
    }

    @Override
    public List<SpecializationWithNameAndCodeDTO> getAllSpecializationWithNameAndId() {
        List<SpecializationWithNameAndCodeDTO> specializationWithNameAndCodeDTOs = DTOTransformatorUtil
                .convertSpecializatonListToSpecializationWithNameAndCodeDTOList(specializationDao.getAllEntity());
        return specializationWithNameAndCodeDTOs;
    }

    private String createDescriptionFromSubject(Subject subject) {
        List<SpecializationWithNameAndCodeDTO> specializations = DTOTransformatorUtil.convertSpecializatonListToSpecializationWithNameAndCodeDTOList(subject.getSpecializations());
        return "Azonosító: " + subject.getId() + "\nNév: " + subject.getName() + "\nE/GY/L/FZ: " + subject.getTheoretical() + "/" + subject.getPractical() + "/"
                + subject.getLabor() + "/" + subject.getSemesterClosing().getName() + "\nKredit: " + subject.getCredit() + "\nAjánlott félév: " + subject.getOfferedSemester()
                + "\nSzakirányok: " + specializations + "\nLeírás: " + subject.getDescription() + "\n\n";
    }

    private void buildDependencyDepthHashMap(Subject subject, String specialization, int level, boolean isOnlyRegistration, boolean isForward, Map<String, Integer> subjectDepths) {
        int actualLevel = level;
        if (!subjectDepthsContainsADeeperDependency(subjectDepths, subject.getId(), actualLevel, isForward)) {
            if (isOnlyRegistration) {
                if (levelIsOneOrMinusOne(actualLevel)) {
                    subjectDepths.put(subject.getId(), isForward ? 99 : -99); //99 means it is an onlyRegistration dependency
                } else {
                    subjectDepths.put(subject.getId(), actualLevel);
                }
                actualLevel = isForward ? (actualLevel - 1) : (actualLevel + 1); //The student learn this subject at the same time so there is no needed to increase the level
            } else {
                subjectDepths.put(subject.getId(), actualLevel);
            }
        }
        for (Dependency dependency : isForward ? subject.getForwardDependencies() : subject.getDependencies()) {
            if (dependency.getSpecialization().getId().equals(specialization) || dependency.getSpecialization().getId().equals("KOTELEZO")) {
                buildDependencyDepthHashMap(isForward ? dependency.getSubject() : dependency.getDependencySubject(), specialization, isForward ? (actualLevel + 1)
                        : (actualLevel - 1), dependency.isOnlyRegistration(), isForward, subjectDepths);

            }
        }

    }

    private boolean levelIsOneOrMinusOne(int level) {
        return level == 1 || level == -1;
    }

    /**
     * It is needed because one subject is reachable in more path. It will be kept only the deepest level.
     * @param subjectDepths The subjectDepths map.
     * @param id Id of subject.
     * @param level 
     * @param isForward It will decide if we are seeking the highes or the lowest level number.
     * @return true or false
     */
    private boolean subjectDepthsContainsADeeperDependency(Map<String, Integer> subjectDepths, String id, int level, boolean isForward) {
        if (subjectDepths.containsKey(id)) {
            if (isForward) {
                if (subjectDepths.get(id) >= level) {
                    return true;
                }
            } else {
                if (subjectDepths.get(id) <= level) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getImageName(String imageFolder) {
        File imageDirectory = new File(servletContext.getRealPath(imageFolder));
        File[] images = imageDirectory.listFiles();
        String imageName = images[randomGenerator.nextInt(images.length)].getName();

        return imageName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

}
