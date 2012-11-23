/**
 * 
 */
package com.boky.SubjectParserTest.daolayer.parsers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.parsers.IParser;
import com.boky.SubjectParser.daolayer.parsers.Parser;

/**
 * @author Andras_Bokor
 *
 */
public class ParserTest {

    static List<Subject> listForParser;
    static Parser parser;
    static IParser subjectParser;
    static IParser dependencyParser;
    static IParser descriptionParser;

    @BeforeClass
    public static void setUpBeforeClass() {
        parser = new Parser();
        subjectParser = mock(IParser.class);
        dependencyParser = mock(IParser.class);
        descriptionParser = mock(IParser.class);

        parser.setSubjectParser(subjectParser);
        parser.setDependenyParser(dependencyParser);
        parser.setDescriptionParser(descriptionParser);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.parsers.Parser#parseFiles()}.
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testParseFiles() throws FileNotFoundException {
        parser.parseFiles();
        verify(subjectParser, times(1)).parse(anyList(), anyString());
        verify(dependencyParser, times(1)).parse(anyList(), anyString());
        verify(descriptionParser, times(1)).parse(anyList(), anyString());
    }
}
