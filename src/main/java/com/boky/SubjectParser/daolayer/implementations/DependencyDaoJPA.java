package com.boky.SubjectParser.daolayer.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.interfaces.DependencyDao;

@Repository
public class DependencyDaoJPA extends BasicDaoJPA<Dependency> implements DependencyDao {

	@Override
	public List<Dependency> getDependenciesBySubjectId(String id) {
		return runOwnQuery("FROM Dependency dep WHERE dep.subject.id = ?1", id);
	}

	@Override
	public List<Dependency> getDependenciesBySubjectIdAndSpecializationId(String subjectId, String specializationId) {
		return runOwnQuery("FROM Dependency dep WHERE dep.subject.id = ?1 AND (dep.specialization.id = ?2 OR  dep.specialization.id = ?3)", subjectId, specializationId, "KOTELEZO");
	}

	@Override
	public List<Dependency> getDependenciesByDependencySubjectIdAndSpecializationId(String id, String specializationId) {
		return runOwnQuery("FROM Dependency dep where dep.dependencySubject.id=?1 AND (dep.specialization.id=?2 OR  dep.specialization.id = ?3)", id, specializationId, "KOTELEZO");
	}

	@Override
	public List<Dependency> getForwardDependenciesOfASubject(String id) {
		return runOwnQuery("SELECT dep FROM Dependency dep WHERE dep.dependencySubject.id = ?1 ", id);
	}

}
