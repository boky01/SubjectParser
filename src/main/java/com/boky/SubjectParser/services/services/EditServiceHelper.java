package com.boky.SubjectParser.services.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SpecializationDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SubjectDaoJPA;
import com.boky.SubjectParser.daolayer.interfaces.DependencyDao;
import com.boky.SubjectParser.daolayer.interfaces.SpecializationDao;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;
import com.boky.SubjectParser.services.dto.DTOTransformatorUtil;
import com.boky.SubjectParser.services.dto.DependencyForEditDTO;
import com.boky.SubjectParser.services.dto.SubjectForEditDTO;
import com.boky.SubjectParser.services.services.error.EditServiceException;

/**
 * This class needed because in the web service the autowired does not work. The spring context is available via web service context but the update does not work. The solution is
 * the manual transaction handling so I organize this logic to this class.
 * 
 * @author bokor
 * 
 */
@Service
public class EditServiceHelper extends SpringBeanAutowiringSupport {

	SubjectDao subjectDao;

	SpecializationDao specializationDao;

	DependencyDao dependencyDao;

	@Autowired
	private EntityManagerFactory factory;

	public EditServiceHelper() {
		super();
		subjectDao = new SubjectDaoJPA();
		specializationDao = new SpecializationDaoJPA();
		dependencyDao = new DependencyDaoJPA();
	}

	public SubjectForEditDTO getSubjectById(String id) {
		SubjectForEditDTO result = null;
		EntityManager em = factory.createEntityManager();
		startTransaction(em);

		Subject subjectFromDb = subjectDao.getById(id);

		closeTransaction(em);

		if (subjectFromDb != null) {
			result = DTOTransformatorUtil.convertSubjectToSubjectForEditDTO(subjectFromDb);
		}

		return result;
	}

	public void updateSubject(SubjectForEditDTO paramSubject) throws EditServiceException {
		EntityManager em = factory.createEntityManager();
		startTransaction(em);

		Subject subjectForUpdate = subjectDao.getById(paramSubject.getId());
		if (subjectForUpdate == null) {
			throw new EditServiceException("There is no subject with this id");
		}
		updateSubjectData(paramSubject, subjectForUpdate);

		subjectDao.saveOrUpdate(subjectForUpdate);

		closeTransaction(em);
	}

	private void updateSubjectData(SubjectForEditDTO paramSubject, Subject subjectForUpdate) {
		if (!paramSubject.getName().equals(subjectForUpdate.getName())) {
			subjectForUpdate.setName(paramSubject.getName());
		}
		if (!paramSubject.getTheoretical().equals(subjectForUpdate.getTheoretical())) {
			subjectForUpdate.setTheoretical(paramSubject.getTheoretical());
		}
		if (!paramSubject.getPractical().equals(subjectForUpdate.getPractical())) {
			subjectForUpdate.setPractical(paramSubject.getPractical());
		}
		if (!paramSubject.getLabor().equals(subjectForUpdate.getLabor())) {
			subjectForUpdate.setLabor(paramSubject.getLabor());
		}
		if (!paramSubject.getSemesterClosing().equals(subjectForUpdate.getSemesterClosing())) {
			subjectForUpdate.setSemesterClosing(paramSubject.getSemesterClosing());
		}
		if (!paramSubject.getCredit().equals(subjectForUpdate.getCredit())) {
			subjectForUpdate.setCredit(paramSubject.getCredit());
		}
		if (!paramSubject.getOfferedSemester().equals(subjectForUpdate.getOfferedSemester())) {
			subjectForUpdate.setOfferedSemester(paramSubject.getOfferedSemester());
		}

		// Add new dependencies
		List<Dependency> dependencyList = new ArrayList<>(); // Because the contains() method of Set class uses hashCode() which uses id of Dependency but the dependency which comes from parameter has no id.
		dependencyList.addAll(subjectForUpdate.getDependencies());
		for (DependencyForEditDTO dependency : paramSubject.getDependencies()) {
			Dependency dep = new Dependency(subjectDao.getById(subjectForUpdate.getId()), specializationDao.getById(dependency.getSpecialization()),
					dependency.isOnlyRegistration(), subjectDao.getById(dependency.getDependencySubject()));
			if (!dependencyList.contains(dep)) {
				subjectForUpdate.getDependencies().add(dep);
			}
		}

		// Remove unnecessary dependencies
		List<Dependency> dependenciesToDelete = new ArrayList<>();
		for (Dependency depencency : subjectForUpdate.getDependencies()) {
			if (!paramSubject.getDependencies().contains(DTOTransformatorUtil.convertDependencyToDependencyForEditDTO(depencency))) {
				dependenciesToDelete.add(depencency);
			}
		}
		subjectForUpdate.getDependencies().removeAll(dependenciesToDelete);

		// Handle specializations
		List<String> specializationIdsFromSubjectForUpdate = new ArrayList<>();
		for (Specialization specialization : subjectForUpdate.getSpecializations()) {
			specializationIdsFromSubjectForUpdate.add(specialization.getId());
		}

		for (String specializationId : paramSubject.getSpecializations()) {
			if (!specializationIdsFromSubjectForUpdate.contains(specializationId)) {
				subjectForUpdate.getSpecializations().add(specializationDao.getById(specializationId));
			}
		}

		for (Specialization specialization : subjectForUpdate.getSpecializations()) {
			if (!paramSubject.getSpecializations().contains(specialization.getId())) {
				subjectForUpdate.getSpecializations().remove(specialization);
			}
		}

		// End of handle specializations

		//		if (!paramSubject.getDescription().equals(subjectForUpdate.getDescription())) {
		//			subjectForUpdate.setDescription(paramSubject.getDescription());
		//		}
	}

	public void saveSubject(SubjectForEditDTO subject) throws EditServiceException {
		EntityManager em = factory.createEntityManager();
		startTransaction(em);

		if (subjectDao.getById(subject.getId()) != null) {
			throw new EditServiceException("A subject already exists with id: " + subject.getId());
		}

		Set<Specialization> specializations = fillupSpeciailzationsForSave(subject);

		Subject subjectToSave = new Subject(subject.getId(), subject.getName(), subject.getTheoretical(), subject.getPractical(), subject.getLabor(), subject.getSemesterClosing(),
				subject.getCredit(), subject.getOfferedSemester(), new HashSet<Dependency>(), specializations, subject.getDescription());
		Set<Dependency> dependencies = fillupDependenciesForSave(subject, subjectToSave);
		subjectToSave.getDependencies().addAll(dependencies);
		subjectDao.saveOrUpdate(subjectToSave);

		closeTransaction(em);
	}

	private Set<Specialization> fillupSpeciailzationsForSave(SubjectForEditDTO subject) {
		Set<Specialization> result = new HashSet<>();
		for (String specId : subject.getSpecializations()) {
			result.add(specializationDao.getById(specId));
		}
		return result;
	}

	private Set<Dependency> fillupDependenciesForSave(SubjectForEditDTO subject, Subject subjectToSave) {
		Set<Dependency> result = new HashSet<>();
		for (DependencyForEditDTO dto : subject.getDependencies()) {
			Dependency dependency = new Dependency(subjectToSave, specializationDao.getById(dto.getSpecialization()), dto.isOnlyRegistration(), subjectDao.getById(dto
					.getDependencySubject()));
			result.add(dependency);
		}
		return result;
	}

	public void deleteSubject(String id) {
		EntityManager em = factory.createEntityManager();
		startTransaction(em);

		subjectDao.deleteById(id);

		closeTransaction(em);
	}

	private void closeTransaction(EntityManager em) {
		em.getTransaction().commit();
		em.close();
	}

	private void startTransaction(EntityManager em) {
		em.getTransaction().begin();
		subjectDao.setEntityManager(em);
		specializationDao.setEntityManager(em);
		dependencyDao.setEntityManager(em);
	}
}
