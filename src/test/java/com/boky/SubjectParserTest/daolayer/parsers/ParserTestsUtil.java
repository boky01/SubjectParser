package com.boky.SubjectParserTest.daolayer.parsers;

import java.util.HashMap;
import java.util.Map;

import com.boky.SubjectParser.daolayer.entities.Specialization;

public class ParserTestsUtil {

    private static Map<String, Specialization> specializations;

    static {
        specializations = new HashMap<String, Specialization>();
        specializations.put("KOTELEZO", new Specialization("Kötelező"));
        specializations.put("GEOTECHNIKAI", new Specialization("Geotechnikai"));
        specializations.put("TELEPULESI", new Specialization("Települési"));
        specializations.put("MAGASEPITESI", new Specialization("Magasépítési"));
        specializations.put("TUZVEDELMI", new Specialization("Tűz- és katasztrófavédelmi"));
        specializations.put("VALASZTHATO", new Specialization("Szabadon választható"));
    }

    public static Map<String, Specialization> getSpecializations() {
        return specializations;
    }
}
