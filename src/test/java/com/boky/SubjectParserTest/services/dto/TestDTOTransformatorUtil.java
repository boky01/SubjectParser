/**
 * 
 */
package com.boky.SubjectParserTest.services.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.enums.SemesterClosing;
import com.boky.SubjectParser.services.dto.DTOTransformatorUtil;
import com.boky.SubjectParser.services.dto.SpecializationWithNameDTO;
import com.boky.SubjectParser.services.dto.SubjectDTO;
import com.boky.SubjectParser.services.dto.SubjectWithCodeAndNameAndSemesterDTO;

/**
 * @author Andras_Bokor
 *
 */
public class TestDTOTransformatorUtil {

    /**
     * Test method for {@link com.boky.SubjectParser.services.dto.DTOTransformatorUtil#convertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList(java.util.List)}.
     */
    @Test
    public void testConvertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList() {
        Specialization specializationMagasep = new Specialization("Magasépítési");
        Specialization specializationKotelezo = new Specialization("Kötelező");

        Subject subject1 = new Subject("SGYMMET236XXX", "Magasépítési acélszerkezetek", 1, 3, 0, SemesterClosing.FELEVKOZI, 5, 7, new HashSet<Dependency>(),
                new HashSet<Specialization>(), "Test description");
        subject1.getDependencies().add(
                new Dependency(subject1, specializationMagasep, false, new Subject("SGYMMET2316XA", null, null, null, null, null, null, null, null, null, null)));
        subject1.getSpecializations().add(specializationMagasep);

        Subject subject2 = new Subject("SGYMMET236ZZZ", "Alacsonyépítési faszerkezetek", 2, 4, 1, SemesterClosing.FELEVKOZI, 6, 6, new HashSet<Dependency>(),
                new HashSet<Specialization>(), "Test description2");
        subject2.getDependencies().add(
                new Dependency(subject2, specializationKotelezo, false, new Subject("SGYMMET2316YY", null, null, null, null, null, null, null, null, null, null)));
        subject2.getSpecializations().add(specializationKotelezo);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        List<SubjectWithCodeAndNameAndSemesterDTO> expectedCodeAndNameAndSemesterDTOs = new ArrayList<>();
        expectedCodeAndNameAndSemesterDTOs.add(new SubjectWithCodeAndNameAndSemesterDTO("SGYMMET236XXX", "Magasépítési acélszerkezetek", 7));
        expectedCodeAndNameAndSemesterDTOs.add(new SubjectWithCodeAndNameAndSemesterDTO("SGYMMET236ZZZ", "Alacsonyépítési faszerkezetek", 6));

        Assert.assertEquals(expectedCodeAndNameAndSemesterDTOs, DTOTransformatorUtil.convertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList(subjects));
    }

    /**
     * Test method for {@link com.boky.SubjectParser.services.dto.DTOTransformatorUtil#convertSubjectToSubjectWithCodeAndNameAndSemesterDTO(com.boky.SubjectParser.daolayer.entities.SubjectDTO)}.
     */
    @Test
    public void testConvertSubjectToSubjectWithCodeAndNameAndSemesterDTO() {

        Specialization specializationMagasep = new Specialization("Magasépítési");

        Subject subject = new Subject("SGYMMET236XXX", "Magasépítési acélszerkezetek", 1, 3, 0, SemesterClosing.FELEVKOZI, 5, 7, new HashSet<Dependency>(),
                new HashSet<Specialization>(), "Test description");
        subject.getDependencies().add(
                new Dependency(subject, specializationMagasep, false, new Subject("SGYMMET2316XA", null, null, null, null, null, null, null, null, null, null)));
        subject.getSpecializations().add(specializationMagasep);

        SubjectWithCodeAndNameAndSemesterDTO expectedSubjectWithCodeAndNameAndSemesterDTO = new SubjectWithCodeAndNameAndSemesterDTO("SGYMMET236XXX",
                "Magasépítési acélszerkezetek", 7);

        Assert.assertEquals(expectedSubjectWithCodeAndNameAndSemesterDTO, DTOTransformatorUtil.convertSubjectToSubjectWithCodeAndNameAndSemesterDTO(subject));
    }

    /**
     * Test method for {@link com.boky.SubjectParser.services.dto.DTOTransformatorUtil#convertSpecializatonListToSpecializationWithNameDTOList(java.util.List)}.
     */
    @Test
    public void testConvertSpecializatonListToSpecializationWithNameDTOList() {
        List<Specialization> specializations = new ArrayList<>();
        specializations.add(new Specialization("KOTELEZO", "Kötelező"));
        specializations.add(new Specialization("GEOTECHNIKAI", "Geotechnikai"));
        specializations.add(new Specialization("MAGASEPITESI", "Magasépítési"));
        List<SpecializationWithNameDTO> expectedSpecializationWithNameDTOs = new ArrayList<>();
        expectedSpecializationWithNameDTOs.add(new SpecializationWithNameDTO("Kötelező"));
        expectedSpecializationWithNameDTOs.add(new SpecializationWithNameDTO("Geotechnikai"));
        expectedSpecializationWithNameDTOs.add(new SpecializationWithNameDTO("Magasépítési"));

        Assert.assertEquals(expectedSpecializationWithNameDTOs, DTOTransformatorUtil.convertSpecializatonListToSpecializationWithNameDTOList(specializations));
    }

    /**
     * Test method for {@link com.boky.SubjectParser.services.dto.DTOTransformatorUtil#convertSpecializatonToSpecializationWithNameDTO(com.boky.SubjectParser.daolayer.entities.Specialization)}.
     */
    @Test
    public void testConvertSpecializatonToSpecializationWithNameDTO() {
        Specialization specialization = new Specialization("KOTELEZO", "Kötelező");

        SpecializationWithNameDTO expectedSpecializationWithNameDTO = new SpecializationWithNameDTO("Kötelező");

        Assert.assertEquals(expectedSpecializationWithNameDTO, DTOTransformatorUtil.convertSpecializatonToSpecializationWithNameDTO(specialization));
    }

    /**
     * Test method for {@link com.boky.SubjectParser.services.dto.DTOTransformatorUtil#convertSubjectToSubjectDTO(com.boky.SubjectParser.daolayer.entities.SubjectDTO)}.
     */
    @Test
    public void testConvertSubjectToSubjectDao() {
        Specialization specialization = new Specialization("Magasépítési");
        Subject subject = new Subject("SGYMMET236XXX", "Magasépítési acélszerkezetek", 1, 3, 0, SemesterClosing.FELEVKOZI, 5, 7, new HashSet<Dependency>(),
                new HashSet<Specialization>(), "Test description");
        subject.getDependencies().add(new Dependency(subject, specialization, false, new Subject("SGYMMET2316XA", null, null, null, null, null, null, null, null, null, null)));
        subject.getSpecializations().add(specialization);

        Specialization specializationForDTO = new Specialization("Magasépítési");
        SubjectDTO expectedSubjectDTO = new SubjectDTO("SGYMMET236XXX", "Magasépítési acélszerkezetek", 1, 3, 0, SemesterClosing.FELEVKOZI, 5, 7, new ArrayList<Dependency>(),
                new ArrayList<Specialization>(), "Test description", new ArrayList<Dependency>());
        expectedSubjectDTO.getDependencies().add(
                new Dependency(subject, specializationForDTO, false, new Subject("SGYMMET2316XA", null, null, null, null, null, null, null, null, null, null)));
        expectedSubjectDTO.getSpecializations().add(specializationForDTO);

        Assert.assertEquals(expectedSubjectDTO, DTOTransformatorUtil.convertSubjectToSubjectDTO(subject));
    }
}
