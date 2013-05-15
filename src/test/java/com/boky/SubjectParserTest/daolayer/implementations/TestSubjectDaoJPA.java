package com.boky.SubjectParserTest.daolayer.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.enums.SemesterClosing;
import com.boky.SubjectParser.daolayer.implementations.SpecializationDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SubjectDaoJPA;
import com.boky.SubjectParserTest.springconfig.DaoConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfigTest.class })
public class TestSubjectDaoJPA {

	SubjectDaoJPA subjectDaoJPA = new SubjectDaoJPA();
	SpecializationDaoJPA specializationDaoJPA = new SpecializationDaoJPA();

	@Autowired
	private DaoTestingUtil dbUnitInitializer;

	EntityManager em;

	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitInitializer.getDatabaseConnection(), dbUnitInitializer.getDataSet());

		em = dbUnitInitializer.getFactory().createEntityManager();
		em.getTransaction().begin();
		subjectDaoJPA.setEntityManager(em);
		specializationDaoJPA.setEntityManager(em);
	}

	@After
	public void tearDown() {
		em.close();
	}

	@Test
	public void testGetSubjectById() {
		Set<Specialization> specializations = new HashSet<Specialization>();
		specializations.add(new Specialization("KOTELEZO", "Kötelező"));

		Subject expectedSubject = new Subject("SGYMMET201XXX", "Mechanika I. (Statika)", 1, 1, 1, SemesterClosing.VIZSGA, 1, 1, new HashSet<Dependency>(), specializations, "First");
		Subject actualSubject = subjectDaoJPA.getById("SGYMMET201XXX");

		assertEquals(expectedSubject, actualSubject);
		assertEquals(expectedSubject.getSpecializations(), actualSubject.getSpecializations());
		assertEquals(expectedSubject.getDependencies(), actualSubject.getDependencies());
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getSubjectByName(java.lang.String)} .
	 */
	@Test
	public void testGetSubjectByName() {
		Set<Specialization> specializations = new HashSet<Specialization>();
		specializations.add(new Specialization("KOTELEZO", "Kötelező"));

		Subject expectedSubject = new Subject("SGYMMET201XXX", "Mechanika I. (Statika)", 1, 1, 1, SemesterClosing.VIZSGA, 1, 1, new HashSet<Dependency>(), specializations, "First");
		Subject actualSubject = subjectDaoJPA.getSubjectByName("Mechanika I. (Statika)");

		assertEquals(expectedSubject, actualSubject);
		assertEquals(expectedSubject.getSpecializations(), actualSubject.getSpecializations());
		assertEquals(expectedSubject.getDependencies(), actualSubject.getDependencies());
	}

	@Test
	public void testGetSubjectsBySpecializationId() {
		List<Subject> expectedSubjectList = createExceptedSubjectListForGetSubjectsBySpecializationSomethingTest();
		List<Subject> actualSubjectList = subjectDaoJPA.getSubjectsBySpecializationId("GEOTECHNIKAI");

		assertEquals(expectedSubjectList.size(), actualSubjectList.size());
		for (int i = 0; i < actualSubjectList.size(); i++) {
			assertEquals(expectedSubjectList.get(i), actualSubjectList.get(i));
			assertEquals(expectedSubjectList.get(i).getSpecializations(), actualSubjectList.get(i).getSpecializations());
			assertEquals(expectedSubjectList.get(i).getDependencies(), actualSubjectList.get(i).getDependencies());
		}
	}

	@Test
	public void testGetSubjectsBySpecializationName() {
		List<Subject> expectedSubjectList = createExceptedSubjectListForGetSubjectsBySpecializationSomethingTest();
		List<Subject> actualSubjectList = subjectDaoJPA.getSubjectsBySpecializationName("Geotechnikai");

		assertEquals(expectedSubjectList.size(), actualSubjectList.size());
		for (int i = 0; i < actualSubjectList.size(); i++) {
			assertEquals(expectedSubjectList.get(i), actualSubjectList.get(i));
			assertEquals(expectedSubjectList.get(i).getSpecializations(), actualSubjectList.get(i).getSpecializations());
			assertEquals(expectedSubjectList.get(i).getDependencies(), actualSubjectList.get(i).getDependencies());
		}
	}

	private List<Subject> createExceptedSubjectListForGetSubjectsBySpecializationSomethingTest() {
		Subject subject1 = new Subject("SGYMMET203XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject2 = new Subject("SGYMTUB2317XA", null, null, null, null, null, null, null, null, null, null);
		Subject subject3 = new Subject("SGYMMET206XXX", null, null, null, null, null, null, null, null, null, null);

		Set<Specialization> specializations = new HashSet<Specialization>();
		specializations.add(new Specialization("KOTELEZO", "Kötelező"));
		specializations.add(new Specialization("GEOTECHNIKAI", "Geotechnikai"));

		Set<Dependency> dependencies = new HashSet<>();
		dependencies.add(new Dependency(subject3, new Specialization("KOTELEZO", "Kötelező"), false, subject1).setId(4L));
		dependencies.add(new Dependency(subject3, new Specialization("KOTELEZO", "Kötelező"), true, subject2).setId(5L));
		dependencies.add(new Dependency(subject3, new Specialization("MAGASEPITESI", "Magasépítési"), true, subject2).setId(6L));

		List<Subject> expectedSubjectList = new ArrayList<Subject>();
		expectedSubjectList.add(new Subject("SGYMMET206XXX", "Fa- és acélszerkezetek I.", 5, 5, 5, SemesterClosing.ALAIRAS, 5, 5, dependencies, specializations, "Fifth"));

		specializations = new HashSet<Specialization>();
		specializations.add(new Specialization("GEOTECHNIKAI", "Geotechnikai"));
		specializations.add(new Specialization("TELEPULESI", "Települési"));
		specializations.add(new Specialization("MAGASEPITESI", "Magasépítési"));

		expectedSubjectList.add(new Subject("SGYMTUB2317XA", "A katasztrófavédelem alapjai", 6, 6, 6, SemesterClosing.VIZSGA, 6, 6, new HashSet<Dependency>(), specializations,
				"Sixth"));
		return expectedSubjectList;
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateSubject(com.boky.SubjectParser.daolayer.entities.Subject)} .
	 * 
	 * @throws DatabaseUnitException
	 */
	@Test
	public void testSaveOrUpdateWhenSave() throws DataSetException {

		Set<Specialization> specializations = new HashSet<Specialization>();
		specializations.add(new Specialization("TEST", "Test specialization"));
		Subject insertSubject = new Subject("SGYMMET203YYY", "Mechanika VII.", 7, 7, 7, SemesterClosing.VIZSGA, 7, 7, new HashSet<Dependency>(), specializations, "First");

		subjectDaoJPA.saveOrUpdate(insertSubject);
		subjectDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		assertEquals(7, actualDatabaseDataSet.getTable("subject").getRowCount());
		assertEquals(10, actualDatabaseDataSet.getTable("subject_specialization").getRowCount());
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateSubject(com.boky.SubjectParser.daolayer.entities.Subject)} .
	 * 
	 * @throws DatabaseUnitException
	 */
	@Test
	public void testSaveOrUpdateSubjectWhenUpdateAndChangeADependency() throws DatabaseUnitException {
		Subject subject = subjectDaoJPA.getById("SGYMMET206XXX");
		Dependency tmp = new Dependency(subjectDaoJPA.getById(subject.getId()), ((Specialization) subject.getSpecializations().toArray()[0]), false,
				subjectDaoJPA.getById("SGYMMET201XXX"));
		subject.setName("Updated name");
		subject.setDependencies(new HashSet<Dependency>());

		Specialization specializationToDelete = null;
		for (Specialization specialization : subject.getSpecializations()) {
			if (specialization.getId().equals("GEOTECHNIKAI")) {
				specializationToDelete = specialization;
				break;
			}
		}
		subject.getSpecializations().remove(specializationToDelete);
		subject.getDependencies().add(tmp);

		subjectDaoJPA.saveOrUpdate(subject);
		subjectDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenUpdateASubject.xml");
		Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);

	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateSubject(com.boky.SubjectParser.daolayer.entities.Subject)} .
	 * 
	 * @throws DatabaseUnitException
	 */
	@Test
	public void testSaveOrUpdateSubjectWhenUpdateAndRemoveASpecialization() throws DatabaseUnitException {

		Subject subject = subjectDaoJPA.getById("SGYMMET202XXX");
		subject.setName("Updated name");

		Specialization specializationToDelete = null;
		for (Specialization specialization : subject.getSpecializations()) {
			if (specialization.getId().equals("KOTELEZO")) {
				specializationToDelete = specialization;
				break;
			}
		}
		subject.getSpecializations().remove(specializationToDelete);

		subjectDaoJPA.saveOrUpdate(subject);
		subjectDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenRemoveASpecializationFromSubject.xml");
		Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);

	}

	@Test
	public void testDeleteSubjectById() {
		subjectDaoJPA.deleteById("SGYMMET202XXX");
		subjectDaoJPA.getEntityManager().getTransaction().commit();

		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenDeleteASubject.xml");

		try {
			Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);
		} catch (DatabaseUnitException e) {
			fail("There was an DatabaseUnitException: " + e);
		}
	}

	@Test
	public void testGetAllSubject() throws DataSetException {
		List<Subject> allSubject = subjectDaoJPA.getAllEntity();

		assertNotNull(allSubject);
		assertEquals(allSubject.size(), dbUnitInitializer.getDataSet().getTable("subject").getRowCount());

	}
}
