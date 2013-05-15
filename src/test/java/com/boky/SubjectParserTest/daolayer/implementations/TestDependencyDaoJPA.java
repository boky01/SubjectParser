package com.boky.SubjectParserTest.daolayer.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

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
import com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SpecializationDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SubjectDaoJPA;
import com.boky.SubjectParserTest.springconfig.DaoConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfigTest.class })
public class TestDependencyDaoJPA {

	DependencyDaoJPA dependencyDaoJPA = new DependencyDaoJPA();
	SubjectDaoJPA subjectDaoJPA = new SubjectDaoJPA();
	SpecializationDaoJPA specializationDaoJPA = new SpecializationDaoJPA();
	EntityManager em;

	@Autowired
	private DaoTestingUtil dbUnitInitializer;

	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitInitializer.getDatabaseConnection(), dbUnitInitializer.getDataSet());

		em = dbUnitInitializer.getFactory().createEntityManager();
		em.getTransaction().begin();
		dependencyDaoJPA.setEntityManager(em);
		subjectDaoJPA.setEntityManager(em);
		specializationDaoJPA.setEntityManager(em);
	}

	@After
	public void tearDown() {
		em.close();
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getDependencyById(java.lang.Long)} .
	 */
	@Test
	public void testGetDependencyById() {
		Subject subject1 = new Subject("SGYMMET202XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject2 = new Subject("SGYMMET201XXX", null, null, null, null, null, null, null, null, null, null);

		Dependency expectedDependency = new Dependency(subject1, new Specialization("KOTELEZO", "Kötelező"), false, subject2).setId(1L);
		Dependency actualDependency = dependencyDaoJPA.getById(1L);
		System.out.println(actualDependency.getSubject().toString());

		Assert.assertEquals(expectedDependency, actualDependency);
	}

	@Test
	public void testGetAllDependency() throws DataSetException {
		List<Dependency> allDependency = dependencyDaoJPA.getAllEntity();

		Assert.assertNotNull(allDependency);
		Assert.assertEquals(allDependency.size(), dbUnitInitializer.createActualDataSet().getTable("dependency").getRowCount());
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getDependeniesBySubjectId(java.lang.String)} .
	 */
	@Test
	public void testGetDependeniesBySubjectId() {
		Subject subject1 = new Subject("SGYMMET203XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject2 = new Subject("SGYMTUB2317XA", null, null, null, null, null, null, null, null, null, null);
		Subject subject3 = new Subject("SGYMMET206XXX", null, null, null, null, null, null, null, null, null, null);

		List<Dependency> expectedDependeniesBySubjectId = new ArrayList<Dependency>();
		expectedDependeniesBySubjectId.add(new Dependency(subject3, new Specialization("KOTELEZO", "Kötelező"), false, subject1).setId(4L));
		expectedDependeniesBySubjectId.add(new Dependency(subject3, new Specialization("KOTELEZO", "Kötelező"), true, subject2).setId(5L));
		expectedDependeniesBySubjectId.add(new Dependency(subject3, new Specialization("MAGASEPITESI", "Magasépítési"), true, subject2).setId(6L));

		List<Dependency> dependeniesBySubjectId = dependencyDaoJPA.getDependenciesBySubjectId("SGYMMET206XXX");
		Assert.assertEquals(expectedDependeniesBySubjectId, dependeniesBySubjectId);
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getDependenciesBySubjectIdAndSpecializationId(java.lang.String, java.lang.Long)} .
	 */
	@Test
	public void testGetDependenciesBySubjectIdAndSpecialization() {
		Subject subject1 = new Subject("SGYMMET203XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject2 = new Subject("SGYMMET206XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject3 = new Subject("SGYMTUB2317XA", null, null, null, null, null, null, null, null, null, null);

		List<Dependency> expectedDependeniesBySubjectId = new ArrayList<Dependency>();
		expectedDependeniesBySubjectId.add(new Dependency(subject2, new Specialization("KOTELEZO", "Kötelező"), false, subject1).setId(4L));
		expectedDependeniesBySubjectId.add(new Dependency(subject2, new Specialization("KOTELEZO", "Kötelező"), true, subject3).setId(5L));
		List<Dependency> actualDependeniesBySubjectId = dependencyDaoJPA.getDependenciesBySubjectIdAndSpecializationId("SGYMMET206XXX", "KOTELEZO");

		Assert.assertEquals(expectedDependeniesBySubjectId, actualDependeniesBySubjectId);
	}

	/**
	 * Test method for
	 * {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getDependenciesByDependencySubjectIdAndSpecializationId(java.lang.String, java.lang.Long)} .
	 */
	@Test
	public void testGetDependeciesByDependencySubjectIdAndSpecializationId() {
		Subject subject1 = new Subject("SGYMMET205XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject2 = new Subject("SGYMMET206XXX", null, null, null, null, null, null, null, null, null, null);
		Subject subject3 = new Subject("SGYMMET203XXX", null, null, null, null, null, null, null, null, null, null);

		List<Dependency> expectedDependeniesBySubjectId = new ArrayList<Dependency>();
		expectedDependeniesBySubjectId.add(new Dependency(subject1, new Specialization("KOTELEZO", "Kötelező"), false, subject3).setId(3L));
		expectedDependeniesBySubjectId.add(new Dependency(subject2, new Specialization("KOTELEZO", "Kötelező"), false, subject3).setId(4L));
		List<Dependency> dependeniesBySubjectId = dependencyDaoJPA.getDependenciesByDependencySubjectIdAndSpecializationId("SGYMMET203XXX", "KOTELEZO");

		Assert.assertEquals(expectedDependeniesBySubjectId, dependeniesBySubjectId);

	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#deleteDependencyById(java.lang.Long)} .
	 * 
	 * @throws DatabaseUnitException
	 */
	@Test
	public void testDeleteDependencyById() throws DatabaseUnitException {
		dependencyDaoJPA.deleteById(1L);
		dependencyDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenDeleteADependency.xml");
		Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);

	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateDependency(com.boky.SubjectParser.daolayer.entities.Dependency)} .
	 */
	@Test
	public void testSaveOrUpdateDependencyWhenSave() throws DatabaseUnitException {
		Dependency dependency = new Dependency(subjectDaoJPA.getById("SGYMTUB2317XA"), specializationDaoJPA.getById("GEOTECHNIKAI"), false, subjectDaoJPA.getById("SGYMMET201XXX"));
		dependencyDaoJPA.saveOrUpdate(dependency);
		dependencyDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		actualDatabaseDataSet.getTable("specialization").getValue(0, "id");
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenInsertADependency.xml");
		Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);
	}

	/**
	 * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateDependency(com.boky.SubjectParser.daolayer.entities.Dependency)} .
	 */
	@Test
	public void testSaveOrUpdateDependencyWhenUpdate() throws DatabaseUnitException {
		Dependency dependency = dependencyDaoJPA.getById(2L);
		dependency.setDependencySubject(subjectDaoJPA.getById("SGYMMET201XXX"));
		dependencyDaoJPA.saveOrUpdate(dependency);
		dependencyDaoJPA.getEntityManager().getTransaction().commit();
		IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
		IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenUpdateADependency.xml");
		Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);
	}

	@Test
	public void testGetForwardDependencies() {
		boolean ok = true;
		Subject subject = subjectDaoJPA.getById("SGYMMET203XXX");
		List<Dependency> forwardDependencies = dependencyDaoJPA.getForwardDependenciesOfASubject("SGYMMET203XXX");
		for (Dependency dependency : forwardDependencies) {
			if (!dependency.getId().equals(3L) && !dependency.getId().equals(4L)) {
				ok = false;
			}
		}
		Assert.assertTrue(ok);
	}
}
