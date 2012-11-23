package com.boky.SubjectParser.daolayer.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.enums.SemesterClosing;

/**
 * 
 */

/**
 * @author Andras_Bokor
 * 
 */
@Component("SubjectParser")
public class SubjectParser implements IParser {

    @Resource(name = "basicSpecializations")
    private Map<String, Specialization> specializations;

    /**
     * @param subjects
     *            List what will be filled with subjects.
     */
    @Override
    public void parse(List<Subject> subjects, String path) throws FileNotFoundException {
        Specialization actualSpecialization = null;
        try (Scanner sc = new Scanner(new File(path), "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); // actual line
                if (reachedNewSpecialization(line)) { // new specialization is reached
                    actualSpecialization = specializations.get(line);
                } else {
                    int index;
                    if ((index = alreadyHaveThisSubject(line, subjects)) != -1) {
                        subjects.get(index).getSpecializations().add(actualSpecialization); // add specialization to the subject
                    } else { // There is no subject with this code in the list yet so need to create one!
                        Subject subject = createSubject(line, actualSpecialization);
                        subjects.add(subject);
                    }
                }
            }
        }
    }

    /**
     * Decide if the subject is already in the list by subject ID.
     * 
     * @param line
     * @return If subject is int the list it will return its index. If not it will return with -1.
     */
    private int alreadyHaveThisSubject(String line, List<Subject> subjects) {
        String subjectCode = line.split(";")[0];
        int i = 0;
        for (Subject tmp : subjects) {
            if (tmp.getId().equals(subjectCode)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Create new subject.
     * 
     * @param line
     *            Value of line where the parsing is standing.
     * @param position
     *            Which is the actual specialization.
     * @return Created subject.
     */
    private Subject createSubject(String line, Specialization specialization) {

        String[] tokens = line.split(";");
        Subject result =
                new Subject(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                        getSemesterClosing(tokens[5].charAt(0)), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]),
                        new HashSet<Dependency>(), new HashSet<Specialization>(), "");
        result.getSpecializations().add(specialization);

        return result;
    }

    private SemesterClosing getSemesterClosing(char closing) {
        SemesterClosing semesterClosing = null;
        switch (closing) {
        case 65: // A
            semesterClosing = SemesterClosing.ALAIRAS;
            break;
        case 70: // F
            semesterClosing = SemesterClosing.FELEVKOZI;
            break;
        case 86: // V
            semesterClosing = SemesterClosing.VIZSGA;
            break;
        default:
            semesterClosing = SemesterClosing.UNKNOW;
            break;
        }
        return semesterClosing;
    }

    private boolean reachedNewSpecialization(String line) {
        for (String specialization : specializations.keySet()) {
            if (specialization.equals(line)) {
                return true;
            }
        }
        return false;
    }

    public void setSpecializations(Map<String, Specialization> specializations) {
        this.specializations = specializations;
    }

}
