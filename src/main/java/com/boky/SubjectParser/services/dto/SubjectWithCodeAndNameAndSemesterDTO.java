package com.boky.SubjectParser.services.dto;

/**
 * This DTO contains the name and id and semester of a subject.
 * @author Andras_Bokor
 *
 */
public class SubjectWithCodeAndNameAndSemesterDTO {
    private String id;
    private String name;
    private Integer offeredSemester;

    public SubjectWithCodeAndNameAndSemesterDTO(String id, String name, Integer offeredSemester) {
        super();
        this.id = id;
        this.name = name;
        this.offeredSemester = offeredSemester;
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

    public Integer getOfferedSemester() {
        return offeredSemester;
    }

    public void setOfferedSemester(Integer offeredSemester) {
        this.offeredSemester = offeredSemester;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((offeredSemester == null) ? 0 : offeredSemester.hashCode());
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
        SubjectWithCodeAndNameAndSemesterDTO other = (SubjectWithCodeAndNameAndSemesterDTO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        return true;
    }

    @Override
    public String toString() {
        return "SubjectWithCodeAndNameAndSemesterDTO [id=" + id + ", name=" + name + ", offeredSemester=" + offeredSemester + "]";
    }

}
