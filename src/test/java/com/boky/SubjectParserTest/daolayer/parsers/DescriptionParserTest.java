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
import com.boky.SubjectParser.daolayer.parsers.DescriptionParser;

/**
 * @author Andras_Bokor
 *
 */
public class DescriptionParserTest {
    static DescriptionParser descriptionParser;
    public final String FILEPATH = "src/test/resources/YBLTargyakDescription_test.txt";

    @BeforeClass
    public static void setUpBeforeClass() {
        descriptionParser = new DescriptionParser();
    }

    @Test
    public void test() throws FileNotFoundException {
        List<Subject> subjects = createBasicSubjectList();
        List<Subject> expected = createExpectedSubjectList();
        descriptionParser.parse(subjects, FILEPATH);
        //        System.out.println(subjects.toString());
        //        System.out.println(expected.toString());
        Assert.assertEquals(expected, subjects);

    }

    private List<Subject> createExpectedSubjectList() {
        List<Subject> result = createBasicSubjectList();
        result.get(0).setDescription("First subject");
        result.get(1).setDescription("Second subject");
        return result;
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test(expected = FileNotFoundException.class)
    public void testParseWhenTheFileNameIsWrongShouldThrowFileNotFoundException() throws FileNotFoundException {
        List<Subject> subjects = new ArrayList<Subject>();
        descriptionParser.parse(subjects, "wrongFilename");
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test
    public void testParseWhenThereIsSubjectWithoutDescriptionShouldSayNemTalalhatoLeiras() throws FileNotFoundException {
        List<Subject> subjects = createBasicSubjectList();
        Subject testSubject = new Subject("XXXXXXXXXXXXX", "Test subject", 0, 0, 0, SemesterClosing.ALAIRAS, 0, 1, new HashSet<Dependency>(), new HashSet<Specialization>(),
                "Nem található leírás.");
        subjects.add(testSubject);
        List<Subject> expected = createExpectedSubjectList();
        expected.add(testSubject);
        descriptionParser.parse(subjects, FILEPATH);
        //        System.out.println(subjects.toString());
        //        System.out.println(expected.toString());
        Assert.assertEquals(expected, subjects);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.SubjectParser#parse(java.util.List)}.
     * @throws FileNotFoundException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParseWhenTheFileDoesNotStartWithACodeOfSubjectShouldThrowIllegalArgumentException() throws FileNotFoundException {
        descriptionParser.parse(null, "src/test/resources/YBLTargyakDescription_test_whenDoesNotStartWithACode.txt");
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

}
