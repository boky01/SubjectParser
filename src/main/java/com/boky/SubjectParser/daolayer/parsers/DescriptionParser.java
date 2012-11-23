package com.boky.SubjectParser.daolayer.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.boky.SubjectParser.daolayer.entities.Subject;

@Component("DescriptionParser")
public class DescriptionParser implements IParser {

    Map<String, String> codeDescription; // kod es leiras egymashozrendelese

    /**
     * @param subjects List what contains subjects. The description will be added to every subject.
     */
    @Override
    public void parse(List<Subject> subjects, String path) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        String actualId = null; //Actual Suject ID
        StringBuilder sb = null; // Store the description
        codeDescription = new HashMap<String, String>();
        try (Scanner sc = new Scanner(new File(path), "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); //actual line
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) { // pl. SGYMTUB2447XA
                    if (actualId != null && sb != null) { // Az elso targynal meg nem lehet az elozot tarolni.
                        codeDescription.put(actualId, sb.toString().trim());
                    }
                    actualId = line;
                    sb = new StringBuilder();
                } else {
                    if (sb == null) {
                        throw new IllegalArgumentException("The file does not start with a code of subject!");
                    }
                    sb.append(line + "\n");

                }
                if (!sc.hasNextLine()) { // Enelkul az utolso targyat nem irna bele
                    codeDescription.put(actualId, sb.toString().trim());
                }
            }
        }
        mergeWithMainList(subjects);

        //        for (String tmp : codeDescription.keySet()) {
        //            System.out.println(tmp + ": " + codeDescription.get(tmp));
        //        }
    }

    private void mergeWithMainList(List<Subject> subjects) {
        for (Subject tmp : subjects) {
            String description = codeDescription.get(tmp.getId());
            if (description != null) {
                tmp.setDescription(description);
            } else {
                tmp.setDescription("Nem található leírás.");
            }
        }
    }

}
