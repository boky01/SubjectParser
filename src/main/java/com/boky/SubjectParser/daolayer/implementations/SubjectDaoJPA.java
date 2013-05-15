package com.boky.SubjectParser.daolayer.implementations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;

@Repository
public class SubjectDaoJPA extends BasicDaoJPA<Subject> implements SubjectDao {

	private static final String GET_FORWARD_DEPENDENCIES = "SELECT dep FROM Dependency dep WHERE dep.dependencySubject.id = ?1 ";

	/**
	 * Get a subject by the name of subject.
	 * 
	 * @param subjectName
	 *            name of the subject.
	 * @return Subject object, if there is no it will return with null.
	 */
	@Override
	public Subject getSubjectByName(String name) {
		List<Subject> result = runOwnQuery("FROM Subject WHERE name = ?1", name);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<Subject> getSubjectsBySpecializationName(String name) {
		return runOwnQuery("SELECT sub FROM Subject sub join sub.specializations spec WHERE spec.name = ?1", name);
	}

	@Override
	public List<Subject> getSubjectsBySpecializationId(String id) {
		return runOwnQuery("SELECT sub FROM Subject sub join sub.specializations spec WHERE spec.id = ?1", id);
	}

	@Override
	public void deleteById(Serializable id) {
		//remove forward dependencies
		List<Dependency> resultList = getForwardDependencies((String) id);
		for (Dependency dependency : resultList) {
			dependency.getSubject().getDependencies().remove(dependency);
			super.saveOrUpdate(dependency.getSubject());
		}
		getEntityManager().flush();

		super.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	private List<Dependency> getForwardDependencies(String id) {
		return super.createQuery(GET_FORWARD_DEPENDENCIES, new Object[] { id }).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(Subject entity) {
		removeUnecessaryDependenies(entity, entity.getDependencies());
		removeUnecessaryForwardDependencies(entity, getForwardDependencies(entity.getId())); //The query equals with getForwardDependencies from DependencyDao
		super.saveOrUpdate(entity);

	}

	/*
	 * If the specialization of the subject changes than the forward dependencies which belongs to the removed specialization have to be removed as well.
	 */
	private void removeUnecessaryForwardDependencies(Subject entity, List<Dependency> forwardDependencies) {

		for (Dependency dependency : forwardDependencies) {
			if (!entity.getSpecializations().contains(dependency.getSpecialization())) {
				dependency.getSubject().getDependencies().remove(dependency);
			}
			super.saveOrUpdate(dependency.getSubject());
		}

	}

	/*
	 * If the specialization of the subject changes than the dependencies which belongs to the removed specialization have to be removed as well.
	 */
	private void removeUnecessaryDependenies(Subject entity, Set<Dependency> dependencies) {
		List<Dependency> dependenciesToDelete = createDependenciesToDelete(entity, dependencies);
		entity.getDependencies().removeAll(dependenciesToDelete);
	}

	private List<Dependency> createDependenciesToDelete(Subject entity, Set<Dependency> dependencies) {
		List<Dependency> dependenciesToDelete = new ArrayList<>();
		for (Dependency dependency : dependencies) {
			if (!entity.getSpecializations().contains(dependency.getSpecialization())) {
				dependenciesToDelete.add(dependency);

			}
		}
		return dependenciesToDelete;
	}

}
