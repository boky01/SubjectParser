package com.boky.SubjectParser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Subject;

@Component
public class MyController {
    @Autowired
    UseDatabaseSerivce databaseSerivce;

    public void tryController() {
        String specialization = "Magasépítési";
        Subject subject = databaseSerivce.getSubjectInformation("SGYMMET236XXX");
        System.out.println(subject.toString());
        writeDependecies(subject, specialization, 0);
        writeForwardDependecies(subject, specialization, 0);

    }

    private void writeDependecies(Subject subject, String specialization, int level) {
        System.out.println(crateTabs(level) + subject.getName());
        for (Dependency dependency : subject.getDependencies()) {
            if (dependency.getSpecialization().getName().equals(specialization) | dependency.getSpecialization().getName().equals("Kötelező")) {
                writeDependecies(dependency.getDependencySubject(), specialization, level + 1);

            }
        }

    }

    private void writeForwardDependecies(Subject subject, String specialization, int level) {
        System.out.println(crateTabs(level) + subject.getName());
        for (Dependency dependency : subject.getForwardDependencies()) {
            if (dependency.getSpecialization().getName().equals(specialization) | dependency.getSpecialization().getName().equals("Kötelező")) {
                writeForwardDependecies(dependency.getSubject(), specialization, level + 1);

            }
        }

    }

    private String crateTabs(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
