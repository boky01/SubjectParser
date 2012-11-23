package com.boky.SubjectParser.daolayer.parsers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boky.SubjectParser.daolayer.entities.Subject;

@Service
public class Parser {

    @Resource(name = "SubjectParser")
    private IParser subjectParser;

    @Resource(name = "DependencyParser")
    private IParser dependenyParser;

    @Resource(name = "DescriptionParser")
    private IParser descriptionParser;

    public Parser() {
        super();
    }

    public void setSubjectParser(IParser subjectParser) {
        this.subjectParser = subjectParser;
    }

    public void setDependenyParser(IParser dependenyParser) {
        this.dependenyParser = dependenyParser;
    }

    public void setDescriptionParser(IParser descriptionParser) {
        this.descriptionParser = descriptionParser;
    }

    public List<Subject> parseFiles() throws FileNotFoundException {
        List<Subject> subjects = new ArrayList<Subject>(); // Store subjects
        subjectParser.parse(subjects, "src/main/resources/YBLTargyak.txt");
        dependenyParser.parse(subjects, "src/main/resources/YBLTargyakDependencies.txt");
        descriptionParser.parse(subjects, "src/main/resources/YBLTargyakDescription.txt");
        return subjects;
    }

}
