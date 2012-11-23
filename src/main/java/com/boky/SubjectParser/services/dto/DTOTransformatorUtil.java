package com.boky.SubjectParser.services.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;

public class DTOTransformatorUtil {

    public static List<SubjectWithCodeAndNameAndSemesterDTO> convertSubjectListToSubjectWithCodeAndNameAndSemesterDTOList(List<Subject> subjects) {
        List<SubjectWithCodeAndNameAndSemesterDTO> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            dtos.add(convertSubjectToSubjectWithCodeAndNameAndSemesterDTO(subject));
        }
        return dtos;
    }

    public static SubjectWithCodeAndNameAndSemesterDTO convertSubjectToSubjectWithCodeAndNameAndSemesterDTO(Subject subject) {
        return new SubjectWithCodeAndNameAndSemesterDTO(subject.getId(), subject.getName(), subject.getOfferedSemester());
    }

    public static List<SpecializationWithNameDTO> convertSpecializatonListToSpecializationWithNameDTOList(List<Specialization> specializations) {
        List<SpecializationWithNameDTO> dtos = new ArrayList<>();
        for (Specialization specialization : specializations) {
            dtos.add(convertSpecializatonToSpecializationWithNameDTO(specialization));
        }

        return dtos;

    }

    public static SpecializationWithNameDTO convertSpecializatonToSpecializationWithNameDTO(Specialization specialization) {
        return new SpecializationWithNameDTO(specialization.getName());
    }

    public static List<SpecializationWithNameAndCodeDTO> convertSpecializatonListToSpecializationWithNameAndCodeDTOList(Collection<Specialization> specializations) {
        List<SpecializationWithNameAndCodeDTO> dtos = new ArrayList<>();
        for (Specialization specialization : specializations) {
            dtos.add(convertSpecializatonToSpecializationWithNameAndCodeDTO(specialization));
        }

        return dtos;

    }

    public static SpecializationWithNameAndCodeDTO convertSpecializatonToSpecializationWithNameAndCodeDTO(Specialization specialization) {
        return new SpecializationWithNameAndCodeDTO(specialization.getId(), specialization.getName());
    }

    public static SubjectDTO convertSubjectToSubjectDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setCredit(subject.getCredit());
        subjectDTO.setDescription(subject.getDescription());
        subjectDTO.setLabor(subject.getLabor());
        subjectDTO.setOfferedSemester(subject.getOfferedSemester());
        subjectDTO.setPractical(subject.getPractical());
        subjectDTO.setSemesterClosing(subject.getSemesterClosing());
        subjectDTO.setTheoretical(subject.getTheoretical());
        subjectDTO.getDependencies().addAll(subject.getDependencies());
        subjectDTO.getForwardDependencies().addAll(subject.getForwardDependencies());
        subjectDTO.getSpecializations().addAll(subject.getSpecializations());
        return subjectDTO;
    }
}
