package com.boky.SubjectParser.services;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.interfaces.DependencyDao;
import com.boky.SubjectParser.daolayer.interfaces.SpecializationDao;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;
import com.boky.SubjectParser.daolayer.parsers.Parser;

@Service
@Transactional
public class DatabaseBuilderService {

    @Autowired
    private DependencyDao dependencyDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private SpecializationDao specializationDao;

    @Autowired
    private Parser parser;

    public void buildDatabaseFromFiles() {
        try {
            List<Subject> subjects = parser.parseFiles();
            writeDatasToDatabase(subjects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeDatasToDatabase(List<Subject> subjects) {
        for (Subject tmp : subjects) {
            subjectDao.saveOrUpdate(tmp);
        }
    }

    public void setParser(Parser parser) {
        this.parser = parser;
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
