package com.boky.SubjectParser.services.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.boky.SubjectParser.daolayer.enums.SemesterClosing;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubjectForEditDTO {

	@XmlElement(required = true, type = String.class)
	private String id;
	@XmlElement(required = true, type = String.class)
	private String name;
	@XmlElement(required = true, type = Integer.class)
	private Integer theoretical;
	@XmlElement(required = true, type = Integer.class)
	private Integer practical;
	@XmlElement(required = true, type = Integer.class)
	private Integer labor;
	@XmlElement(required = true, type = SemesterClosing.class)
	private SemesterClosing semesterClosing;
	@XmlElement(required = true, type = Integer.class)
	private Integer credit;
	@XmlElement(required = true, type = Integer.class)
	private Integer offeredSemester;
	@XmlElementWrapper(name = "dependencies", required = true)
	@XmlElement(name = "dependency", required = true, type = DependencyForEditDTO.class)
	private List<DependencyForEditDTO> dependencies;
	@XmlElementWrapper(name = "specializations", required = true)
	@XmlElement(name = "specialization", type = String.class)
	private List<String> specializations;
	@XmlElement(required = true, type = String.class)
	private String description;

	public SubjectForEditDTO() {
		super();
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

	public List<DependencyForEditDTO> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<DependencyForEditDTO> dependencies) {
		this.dependencies = dependencies;
	}

	public List<String> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<String> specializations) {
		this.specializations = specializations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SubjectForEditDTO [id=" + id + ", name=" + name + ", theoretical=" + theoretical + ", practical=" + practical + ", labor=" + labor + ", semesterClosing="
				+ semesterClosing + ", credit=" + credit + ", offeredSemester=" + offeredSemester + ", dependencies=" + dependencies + ", specializations=" + specializations
				+ ", description=" + description.subSequence(0, 10) + "..." + "]";
	}
}
