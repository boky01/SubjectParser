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
import com.boky.SubjectParser.daolayer.parsers.DependencyParser;

/**
 * @author Andras_Bokor
 *
 */
public class DependencyParserTest {

    static DependencyParser dependencyParser;

    @BeforeClass
    public static void setUpBeforeClass() {
        dependencyParser = new DependencyParser();
        dependencyParser.setSpecializations(ParserTestsUtil.getSpecializations());
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.DependencyParser#parse(java.util.List, java.lang.String)}.
     * @throws FileNotFoundException 
     */
    @Test
    public void testParse() throws FileNotFoundException {
        List<Subject> subjects = createBasicSubjectList();
        List<Subject> expected = createExpectedSubjectList();
        dependencyParser.parse(subjects, "src/test/resources/YBLTargyakDependencies_test.txt");
        System.out.println(subjects.toString());
        System.out.println(expected.toString());
        Assert.assertEquals(expected, subjects);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test(expected = FileNotFoundException.class)
    public void testParseWhenTheFileNameIsWrongShouldThrowFileNotFoundException() throws FileNotFoundException {
        List<Subject> subjects = new ArrayList<Subject>();
        dependencyParser.parse(subjects, "wrongFilename");
    }

    private List<Subject> createBasicSubjectList() {
        List<Subject> result = new ArrayList<Subject>();
        Set<Specialization> expectedSpecializations = new HashSet<Specialization>();
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("KOTELEZO"));
        result.add(new Subject("SGYMASZ2021XA", "Ábrázoló geometria", 1, 2, 0, SemesterClosing.FELEVKOZI, 4, 1, new HashSet<Dependency>(), expectedSpecializations, ""));
        expectedSpecializations = new HashSet<Specialization>();
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("GEOTECHNIKA"));
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("TELEPULESI"));
        result.add(new Subject("SGYMTUB2317XA", "A katasztrófavédelem alapjai", 2, 1, 0, SemesterClosing.VIZSGA, 3, 7, new HashSet<Dependency>(), expectedSpecializations, ""));
        return result;
    }

    private List<Subject> createExpectedSubjectList() {
        List<Subject> result = new ArrayList<Subject>();
        Set<Specialization> expectedSpecializations = new HashSet<Specialization>();

        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("KOTELEZO"));
        Subject subject = new Subject("SGYMASZ2021XA", "Ábrázoló geometria", 1, 2, 0, SemesterClosing.FELEVKOZI, 4, 1, new HashSet<Dependency>(), expectedSpecializations, "");

        expectedSpecializations = new HashSet<Specialization>();
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("GEOTECHNIKA"));
        expectedSpecializations.add(ParserTestsUtil.getSpecializations().get("TELEPULESI"));
        Subject subject2 = new Subject("SGYMTUB2317XA", "A katasztrófavédelem alapjai", 2, 1, 0, SemesterClosing.VIZSGA, 3, 7, new HashSet<Dependency>(), expectedSpecializations,
                "");

        List<Dependency> expectedDependencies = new ArrayList<Dependency>();
        expectedDependencies.add(new Dependency(subject, ParserTestsUtil.getSpecializations().get("KOTELEZO"), false, subject2));
        expectedDependencies.add(new Dependency(subject2, ParserTestsUtil.getSpecializations().get("TELEPULESI"), true, subject));

        subject.getDependencies().add(expectedDependencies.get(0));
        subject2.getDependencies().add(expectedDependencies.get(1));
        result.add(subject);
        result.add(subject2);

        return result;
    }
}
