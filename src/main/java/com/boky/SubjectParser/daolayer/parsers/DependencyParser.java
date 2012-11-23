package com.boky.SubjectParser.daolayer.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;

@Component("DependencyParser")
public class DependencyParser implements IParser {

    private List<Dependency> dependencies = new ArrayList<Dependency>();

    @Resource(name = "basicSpecializations")
    private Map<String, Specialization> specializations;

    /**
     * @param subjects List what contains subjects. The dependencies will be added to every subject.
     */
    @Override
    public void parse(List<Subject> subjects, String path) throws FileNotFoundException {
        Map<String, String> idNameHash = createIdNameHashMap(subjects);
        Specialization actualSpecialization = null;
        try (Scanner sc = new Scanner(new File(path), "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); //actual line
                if (reachedNewSpecialization(line)) { // new specialization is reached
                    actualSpecialization = specializations.get(line);
                } else {
                    String[] tokens = line.split(";");
                    Subject subject = searchSubjectById(subjects, tokens[0]); // pl SGYMKOM215XXX
                    for (String subjectName : tokens[1].split(",")) { // pl Magasépítési vasbeton szerkezetek,Műtárgyépítés III. (Vasbeton hidak tervezése és építése),Magasépítési acélszerkezetek felvetele
                        boolean isOnlyRegistration = false;
                        if (!subjectName.equals("nincs")) { // ha van fuggoseg
                            if (subjectName.contains("felvétele")) { //Then remove felvetele word.
                                isOnlyRegistration = true;
                                subjectName = removeFelveteleWord(subjectName);
                            }
                            Dependency dependeny = new Dependency(subject, actualSpecialization, isOnlyRegistration, searchSubjectById(subjects, idNameHash.get(subjectName)));
                            dependencies.add(dependeny);
                        }
                    }
                }
            }
        }
        mergeWithMainList(subjects);
        //        for (Dependency tmp : dependencies) {
        //            System.out.println(tmp);
        //        }
    }

    private String removeFelveteleWord(String subjectName) {
        String result = subjectName.substring(0, subjectName.indexOf("felvétele"))
                + subjectName.substring(subjectName.indexOf("felvétele") + "felvétele".length(), subjectName.length()); // Ezzel eltunik a "felvetele szo"
        return result.trim();
    }

    private boolean reachedNewSpecialization(String line) {
        for (String specialization : specializations.keySet()) {
            if (specialization.equals(line)) {
                return true;
            }
        }
        return false;
    }

    private Map<String, String> createIdNameHashMap(List<Subject> subjects) {
        HashMap<String, String> result = new HashMap<String, String>();
        for (Subject tmp : subjects) {
            result.put(tmp.getName(), tmp.getId());
        }
        return result;
    }

    private void mergeWithMainList(List<Subject> subjects) {
        for (Dependency tmp : dependencies) {
            Subject subject = searchSubjectById(subjects, tmp.getSubject().getId());
            subject.getDependencies().add(tmp);
        }

    }

    private Subject searchSubjectById(List<Subject> subjects, String subjectId) {
        for (Subject tmp : subjects) {
            if (tmp.getId().equals(subjectId)) {
                return tmp;
            }
        }
        return null;
    }

    public void setSpecializations(Map<String, Specialization> specializations) {
        this.specializations = specializations;
    }

}
