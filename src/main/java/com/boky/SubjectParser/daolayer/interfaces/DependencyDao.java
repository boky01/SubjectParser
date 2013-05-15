package com.boky.SubjectParser.daolayer.interfaces;

import java.util.List;

import com.boky.SubjectParser.daolayer.entities.Dependency;

public interface DependencyDao extends BasicDao<Dependency> {

	/**
	 * Return the dependencies of a subject.
	 * 
	 * @param id Id of a subject
	 * @return Return the dependencies of a subject.
	 */
	public List<Dependency> getDependenciesBySubjectId(String id);

	/**
	 * Return the dependencies of a subject in a concrate specialization.
	 * 
	 * @param subjectId
	 * @param specialization
	 * @return
	 */
	public List<Dependency> getDependenciesBySubjectIdAndSpecializationId(String subjectId, String specializationId);

	/**
	 * Elorefuggosegekhez. Kap egy Dependency.dependencySubject id-t es visszaadja a hozza tartozo Dependency.subject-et.
	 * 
	 * @param id Dependency.dependencySubject
	 * @param specialization Melyik szakiranyban keresunk?
	 * @return Annak a subjectnek a kodja, amelyiknek a fuggosege az adott szakiranyon a parameterben kapott id-hez tartozo subject.
	 */
	public List<Dependency> getDependenciesByDependencySubjectIdAndSpecializationId(String id, String specializationId);

	/**
	 * Get the forward dependencies of a subject.
	 * 
	 * @param id Id of the subject
	 * @return forward dependencies of the subject
	 */
	public List<Dependency> getForwardDependenciesOfASubject(String id);
}
