package com.boky.SubjectParser.services.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This DTO contains the initialization data for the Web UI.
 * The initialization data contains the subjects related to a specialization (plus related to the Kotelezo specialization if the selected is not that) 
 * and the selected specialization's name.
 * @author Andras_Bokor
 *
 */
public class InitializationDataForWebDTO {
    private List<SpecializationWithNameAndCodeDTO> specializations;
    private List<SubjectWithCodeAndNameAndSemesterDTO> subjects;
    private SpecializationWithNameAndCodeDTO actualSpecialization;
    private int numberOfSemesters;

    public InitializationDataForWebDTO() {
        super();
        subjects = new ArrayList<>();
        specializations = new ArrayList<>();
    }

    public InitializationDataForWebDTO(List<SpecializationWithNameAndCodeDTO> specializations, List<SubjectWithCodeAndNameAndSemesterDTO> subjects,
            SpecializationWithNameAndCodeDTO actualSpecialization, int numberOfSemesters) {
        super();
        this.specializations = specializations;
        this.subjects = subjects;
        this.actualSpecialization = actualSpecialization;
        this.numberOfSemesters = numberOfSemesters;
    }

    public List<SpecializationWithNameAndCodeDTO> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<SpecializationWithNameAndCodeDTO> specializations) {
        this.specializations = specializations;
    }

    public List<SubjectWithCodeAndNameAndSemesterDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectWithCodeAndNameAndSemesterDTO> subjects) {
        this.subjects = subjects;
    }

    public int getNumberOfSemesters() {
        return numberOfSemesters;
    }

    public void setNumberOfSemesters(int numberOfSemesters) {
        this.numberOfSemesters = numberOfSemesters;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actualSpecialization == null) ? 0 : actualSpecialization.hashCode());
        result = prime * result + numberOfSemesters;
        result = prime * result + ((specializations == null) ? 0 : specializations.hashCode());
        result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
        return result;
    }

    public SpecializationWithNameAndCodeDTO getActualSpecialization() {
        return actualSpecialization;
    }

    public void setActualSpecialization(SpecializationWithNameAndCodeDTO actualSpecialization) {
        this.actualSpecialization = actualSpecialization;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InitializationDataForWebDTO other = (InitializationDataForWebDTO) obj;
        if (actualSpecialization == null) {
            if (other.actualSpecialization != null) {
                return false;
            }
        } else if (!actualSpecialization.equals(other.actualSpecialization)) {
            return false;
        }
        if (numberOfSemesters != other.numberOfSemesters) {
            return false;
        }
        if (specializations == null) {
            if (other.specializations != null) {
                return false;
            }
        } else if (!specializations.equals(other.specializations)) {
            return false;
        }
        if (subjects == null) {
            if (other.subjects != null) {
                return false;
            }
        } else if (!subjects.equals(other.subjects)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InitializationDataForWebDTO [specializations=" + specializations + ", subjects=" + subjects + ", actualSpecialization=" + actualSpecialization
                + ", numberOfSemesters=" + numberOfSemesters + "]";
    }
}
