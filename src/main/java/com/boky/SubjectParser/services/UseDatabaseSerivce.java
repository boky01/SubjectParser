package com.boky.SubjectParser.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.interfaces.DependencyDao;
import com.boky.SubjectParser.daolayer.interfaces.SpecializationDao;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;

@Service
@Transactional
public class UseDatabaseSerivce {

    final Logger logger = LoggerFactory.getLogger(UseDatabaseSerivce.class);

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private DependencyDao dependencyDao;

    @Autowired
    private SpecializationDao specializationDao;

    private Map<String, Integer> dependecyHash;

    public Subject getSubjectInformation(String id) {
        Subject result = subjectDao.getById(id);
        return result;
    }

    public void testAMethod() {

    }

    public void testAMethod2() {
        //        System.out.println(subjectDao.getAllEntity().size());
        //        System.out.println(subjectDao.getAllEntity().get(0).getSpecializations().isEmpty());
        //        
    }

    public Subject testAMethod3() {
        return subjectDao.getById("SGYMMET203XXX");
    }

    private void getForwardDependencies(Subject subject) {

    }

    private void getDependencies(Subject subject) {

    }

    public void setDependencyDao(DependencyDao dependencyDao) {
        this.dependencyDao = dependencyDao;
    }

    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public void setSpecializationDao(SpecializationDao specializationDao) {
        this.specializationDao = specializationDao;
    }
}
