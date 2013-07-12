package com.boky.SubjectParser.services.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "specializations")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecializationsForEditDTO {

	@XmlElement(name = "specialization", type = SpecializationWithNameAndCodeDTO.class)
	private ArrayList<SpecializationWithNameAndCodeDTO> specialization;

	public SpecializationsForEditDTO() {
		specialization = new ArrayList<>();
	}

	public ArrayList<SpecializationWithNameAndCodeDTO> getSpecialization() {
		return specialization;
	}

	public void setSpecialization(ArrayList<SpecializationWithNameAndCodeDTO> specialization) {
		this.specialization = specialization;
	}
}
