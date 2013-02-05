package com.boky.SubjectParser.daolayer.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.boky.SubjectParser.daolayer.enums.SemesterClosing;

@Entity
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4467249429120749248L;
	@Id
	@Size(min = 13, max = 13, message = "An id of a subject has to be exactly 13 charachters!")
	@Pattern(regexp = "[A-Z0-9]+")
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Integer theoretical;
	@Column(nullable = false)
	private Integer practical;
	@Column(nullable = false)
	private Integer labor;
	@Column(nullable = false)
	@Enumerated
	private SemesterClosing semesterClosing;
	@Column(nullable = false)
	private Integer credit;
	@Column(nullable = false)
	private Integer offeredSemester;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "subject", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Dependency> dependencies;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "subject_specialization", joinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "specialization_id", referencedColumnName = "id") })
	private Set<Specialization> specializations;
	@Column(nullable = false, length = 256)
	private String description;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "dependencySubject", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Dependency> forwardDependencies;

	public Subject() {
	}

	public Subject(String subjectId, String name, Integer theoretical,
			Integer practical, Integer labor, SemesterClosing semesterClosing,
			Integer credit, Integer offeredSemester,
			Set<Dependency> dependencies, Set<Specialization> specializations,
			String description) {
		super();
		this.id = subjectId;
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
		this.forwardDependencies = new HashSet<Dependency>();
	}

	public String getId() {
		return id;
	}

	public void setId(String subjectId) {
		this.id = subjectId;
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

	public Set<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<Dependency> dependencies) {
		if (dependencies != null) {
			if (this.dependencies == null) {
				this.dependencies = dependencies;
			} else {
				this.dependencies.clear();
				this.dependencies.addAll(dependencies);
			}
		} else {
			this.dependencies = null;
		}
	}

	public Set<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Set<Specialization> specializations) {
		this.specializations = specializations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SemesterClosing getSemesterClosing() {
		return semesterClosing;
	}

	public void setSemesterClosing(SemesterClosing semesterClosing) {
		this.semesterClosing = semesterClosing;
	}

	public Set<Dependency> getForwardDependencies() {
		return forwardDependencies;
	}

	public void setForwardDependencies(Set<Dependency> forwardDependencies) {
		this.forwardDependencies = forwardDependencies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Subject other = (Subject) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", theoretical="
				+ theoretical + ", practical=" + practical + ", labor=" + labor
				+ ", semeseterClosing=" + semesterClosing + ", credit="
				+ credit + ", offeredSemester=" + offeredSemester
				+ ", dependencies=" + dependencies + ", specializations="
				+ specializations + ", description=" + description + "]";
	}

}
