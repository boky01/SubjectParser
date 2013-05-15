package com.boky.SubjectParser.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dependency")
@XmlAccessorType(XmlAccessType.FIELD)
public class DependencyForEditDTO {

	@XmlElement(required = true, type = String.class)
	private String specialization;
	@XmlElement(required = true, type = boolean.class)
	private boolean onlyRegistration;
	@XmlElement(required = true, type = String.class)
	private String dependencySubject;

	public DependencyForEditDTO() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dependencySubject == null) ? 0 : dependencySubject.hashCode());
		result = prime * result + (onlyRegistration ? 1231 : 1237);
		result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
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
		DependencyForEditDTO other = (DependencyForEditDTO) obj;
		if (dependencySubject == null) {
			if (other.dependencySubject != null) {
				return false;
			}
		} else if (!dependencySubject.equals(other.dependencySubject)) {
			return false;
		}
		if (onlyRegistration != other.onlyRegistration) {
			return false;
		}
		if (specialization == null) {
			if (other.specialization != null) {
				return false;
			}
		} else if (!specialization.equals(other.specialization)) {
			return false;
		}
		return true;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public boolean isOnlyRegistration() {
		return onlyRegistration;
	}

	public void setOnlyRegistration(boolean onlyRegistration) {
		this.onlyRegistration = onlyRegistration;
	}

	public String getDependencySubject() {
		return dependencySubject;
	}

	public void setDependencySubject(String dependencySubject) {
		this.dependencySubject = dependencySubject;
	}
}
