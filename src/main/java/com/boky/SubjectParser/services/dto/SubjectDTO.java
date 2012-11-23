package com.boky.SubjectParser.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.enums.SemesterClosing;

/**
 * This DTO equals with the Subject entity but it uses Lists instead of Sets.
 * It has another toString. It usable on the webpage.
 * @author Andras_Bokor
 *
 */

public class SubjectDTO {
    private String id;
    private String name;
    private Integer theoretical;
    private Integer practical;
    private Integer labor;
    private SemesterClosing semesterClosing;
    private Integer credit;
    private Integer offeredSemester;
    private List<Dependency> dependencies;
    private List<Specialization> specializations;
    private String description;
    private List<Dependency> forwardDependencies;

    public SubjectDTO() {
        super();
        dependencies = new ArrayList<>();
        specializations = new ArrayList<>();
        forwardDependencies = new ArrayList<>();
    }

    public SubjectDTO(String id, String name, Integer theoretical, Integer practical, Integer labor, SemesterClosing semesterClosing, Integer credit, Integer offeredSemester,
            List<Dependency> dependencies, List<Specialization> specializations, String description, List<Dependency> forwardDependencies) {
        super();
        this.id = id;
        this.name = name;
        this.theoretical = theoretical;
        this.practical = practical;
        this.labor = labor;
        this.semesterClosing = semesterClosing;
        this.credit = credit;
        this.offeredSemester = offeredSemester;
        this.dependencies = dependencies;
        this.specializations = specializations;
        this.description = description;
        this.forwardDependencies = forwardDependencies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTheoretical() {
        return theoretical;
    }

    public void setTheoretical(Integer theoretical) {
        this.theoretical = theoretical;
    }

    public Integer getPractical() {
        return practical;
    }

    public void setPractical(Integer practical) {
        this.practical = practical;
    }

    public Integer getLabor() {
        return labor;
    }

    public void setLabor(Integer labor) {
        this.labor = labor;
    }

    public SemesterClosing getSemesterClosing() {
        return semesterClosing;
    }

    public void setSemesterClosing(SemesterClosing semesterClosing) {
        this.semesterClosing = semesterClosing;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getOfferedSemester() {
        return offeredSemester;
    }

    public void setOfferedSemester(Integer offeredSemester) {
        this.offeredSemester = offeredSemester;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dependency> getForwardDependencies() {
        return forwardDependencies;
    }

    public void setForwardDependencies(List<Dependency> forwardDependencies) {
        this.forwardDependencies = forwardDependencies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((credit == null) ? 0 : credit.hashCode());
        result = prime * result + ((dependencies == null) ? 0 : dependencies.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((forwardDependencies == null) ? 0 : forwardDependencies.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((labor == null) ? 0 : labor.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((offeredSemester == null) ? 0 : offeredSemester.hashCode());
        result = prime * result + ((practical == null) ? 0 : practical.hashCode());
        result = prime * result + ((semesterClosing == null) ? 0 : semesterClosing.hashCode());
        result = prime * result + ((specializations == null) ? 0 : specializations.hashCode());
        result = prime * result + ((theoretical == null) ? 0 : theoretical.hashCode());
        return result;
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
        SubjectDTO other = (SubjectDTO) obj;
        if (credit == null) {
            if (other.credit != null) {
                return false;
            }
        } else if (!credit.equals(other.credit)) {
            return false;
        }
        if (dependencies == null) {
            if (other.dependencies != null) {
                return false;
            }
        } else if (!dependencies.equals(other.dependencies)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (forwardDependencies == null) {
            if (other.forwardDependencies != null) {
                return false;
            }
        } else if (!forwardDependencies.equals(other.forwardDependencies)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (labor == null) {
            if (other.labor != null) {
                return false;
            }
        } else if (!labor.equals(other.labor)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (offeredSemester == null) {
            if (other.offeredSemester != null) {
                return false;
            }
        } else if (!offeredSemester.equals(other.offeredSemester)) {
            return false;
        }
        if (practical == null) {
            if (other.practical != null) {
                return false;
            }
        } else if (!practical.equals(other.practical)) {
            return false;
        }
        if (semesterClosing != other.semesterClosing) {
            return false;
        }
        if (specializations == null) {
            if (other.specializations != null) {
                return false;
            }
        } else if (!specializations.equals(other.specializations)) {
            return false;
        }
        if (theoretical == null) {
            if (other.theoretical != null) {
                return false;
            }
        } else if (!theoretical.equals(other.theoretical)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Azonosító: " + id + "\nNév:" + name + ", E/GY/L/FZ: " + theoretical + "/" + practical + "/" + labor + "/: " + semesterClosing + "\nKredit: " + credit
                + "\nAjánlott félév: " + offeredSemester + "Szakirányok: " + specializations + "\nLeírás: " + description;
    }
}
