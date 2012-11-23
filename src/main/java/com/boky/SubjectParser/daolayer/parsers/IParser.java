package com.boky.SubjectParser.daolayer.parsers;

import java.io.FileNotFoundException;
import java.util.List;

import com.boky.SubjectParser.daolayer.entities.Subject;

/**
 * 
 */

/**
 * @author Andras_Bokor
 *
 */
public interface IParser {

    public void parse(List<Subject> subjects, String path) throws FileNotFoundException;

}
