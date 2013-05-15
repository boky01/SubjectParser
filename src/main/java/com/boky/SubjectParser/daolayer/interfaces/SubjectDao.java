package com.boky.SubjectParser.daolayer.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import com.boky.SubjectParser.daolayer.entities.Subject;

public interface SubjectDao extends BasicDao<Subject> {

	/**
	 * Get a subject by the name of subject.
	 * 
	 * @param subjectName name of the subject.
	 * @return Subject object
	 */
	public Subject getSubjectByName(String name);

	/**
	 * Get a subject by the id of a specialization.
	 * 
	 * @param name Id of the specialization.
	 * @return Subject list.
	 */
	public List<Subject> getSubjectsBySpecializationName(String name);

	/**
	 * Get a subject by the id of a specialization.
	 * 
	 * @param name name of the specialization.
	 * @return Subject list.
	 */
	public List<Subject> getSubjectsBySpecializationId(String id);

	public EntityManager getEntityManager();
}
