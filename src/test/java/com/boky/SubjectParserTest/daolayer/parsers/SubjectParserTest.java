/**
 * 
 */
package com.boky.SubjectParserTest.daolayer.parsers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.enums.SemesterClosing;
import com.boky.SubjectParser.daolayer.parsers.SubjectParser;

/**
 * @author Andras_Bokor
 *
 */
public class SubjectParserTest {

    static SubjectParser subjectParser;

    @BeforeClass
    public static void setUpBeforeClass() {
        subjectParser = new SubjectParser();
        subjectParser.setSpecializations(ParserTestsUtil.getSpecializations());
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test
    public void testParse() throws FileNotFoundException {
        List<Subject> subjects = new ArrayList<Subject>();
        List<Subject> expected = createExpectedSubjectList();
        subjectParser.parse(subjects, "src/test/resources/YBLTargyak_test.txt");
        //        System.out.println(subjects.toString());
        //        System.out.println(expected.toString());
        Assert.assertEquals(expected, subjects);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test(expected = FileNotFoundException.class)
    public void testParseWhenTheFileNameIsWrongShouldThrowFileNotFoundException() throws FileNotFoundException {
        List<Subject> subjects = new ArrayList<Subject>();
        subjectParser.parse(subjects, "wrongFilename");
    }

    private List<Subject> createExpectedSubjectList() {
        List<Subject> result = new ArrayList<Subject>();
        Set<Specialization> expectedSpecializations = new HashSet<Specialization>();
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("KOTELEZO"));
        result.add(new Subject("SGYMASZ2021XA", "Ábrázoló geometria", 1, 2, 0, SemesterClosing.FELEVKOZI, 4, 1, new HashSet<Dependency>(), expectedSpecializations, ""));
        expectedSpecializations = new HashSet<Specialization>();
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("GEOTECHNIKAI"));
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("TELEPULESI"));
        result.add(new Subject("SGYMTUB2317XA", "A katasztrófavédelem alapjai", 2, 1, 0, SemesterClosing.VIZSGA, 3, 7, new HashSet<Dependency>(), expectedSpecializations, ""));
        return result;
    }
}
