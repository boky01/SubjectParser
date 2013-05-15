package com.boky.SubjectParser.services.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.boky.SubjectParser.daolayer.entities.Dependency;
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
		subjectDTO.getSpecializations().addAll(subject.getSpecializations());
		return subjectDTO;
	}

	public static DependencyForEditDTO convertDependencyToDependencyForEditDTO(Dependency dependency) {
		DependencyForEditDTO result = new DependencyForEditDTO();
		result.setDependencySubject(dependency.getDependencySubject().getId());
		result.setOnlyRegistration(dependency.isOnlyRegistration());
		result.setSpecialization(dependency.getSpecialization().getId());

		return result;
	}

	public static SubjectForEditDTO convertSubjectToSubjectForEditDTO(Subject subject) {
		SubjectForEditDTO dto = new SubjectForEditDTO();
		dto.setId(subject.getId());
		dto.setName(subject.getName());
		dto.setTheoretical(subject.getTheoretical());
		dto.setPractical(subject.getPractical());
		dto.setLabor(subject.getLabor());
		dto.setSemesterClosing(subject.getSemesterClosing());
		dto.setCredit(subject.getCredit());
		dto.setOfferedSemester(subject.getOfferedSemester());
		dto.setDependencies(createDependeciesFromSubject(subject.getDependencies()));
		dto.setSpecializations(getIdsOfSpecializations(subject.getSpecializations()));
		dto.setDescription(subject.getDescription());

		return dto;
	}

	private static List<DependencyForEditDTO> createDependeciesFromSubject(Set<Dependency> dependenciesParam) {
		List<DependencyForEditDTO> dtos = new ArrayList<>();
		for (Dependency dependency : dependenciesParam) {
			DependencyForEditDTO dto = new DependencyForEditDTO();
			dto.setDependencySubject(dependency.getDependencySubject().getId());
			dto.setOnlyRegistration(dependency.isOnlyRegistration());
			dto.setSpecialization(dependency.getSpecialization().getId());
			dtos.add(dto);
		}
		return dtos;
	}

	private static List<String> getIdsOfSpecializations(Set<Specialization> specializations2) {
		List<String> result = new ArrayList<>();
		for (Specialization specialization : specializations2) {
			result.add(specialization.getId());
		}
		return result;
	}

}
