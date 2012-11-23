package com.boky.SubjectParserTest.daolayer.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SpecializationDaoJPA;
import com.boky.SubjectParser.daolayer.implementations.SubjectDaoJPA;
import com.boky.SubjectParserTest.springconfig.DaoConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfigTest.class})
public class TestSpecializationDaoJPA {

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
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getAllSpecializationWithNameAndId()}.
     * @throws DataSetException 
     */
    @Test
    public void testGetAllSpecialization() throws DataSetException {
        List<Specialization> allSpecialization = specializationDaoJPA.getAllEntity();

        Assert.assertNotNull(allSpecialization);
        Assert.assertEquals(allSpecialization.size(), dbUnitInitializer.createActualDataSet().getTable("specialization").getRowCount());
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getSpecializationById(java.lang.Long)}.
     */
    @Test
    public void testGetSpecializationById() {
        Specialization expectedSpecialization = new Specialization("MAGASEPITESI", "Magasépítési");
        Specialization actualSpecialization = specializationDaoJPA.getById("MAGASEPITESI");

        Assert.assertEquals(expectedSpecialization.getId(), actualSpecialization.getId());

    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#getSpecializationByName(java.lang.String)}.
     */
    @Test
    public void testGetSpecializationByName() {
        Specialization expectedSpecialization = new Specialization("GEOTECHNIKAI", "Geotechnikai");
        Specialization actualSpecializatoin = specializationDaoJPA.getSpecializationByName("Geotechnikai");

        Assert.assertEquals(expectedSpecialization, actualSpecializatoin);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#deleteSpecializationById(java.lang.Long)}.
     */
    @Test
    public void testDeleteSpecializationById() throws DatabaseUnitException {
        specializationDaoJPA.deleteById("KOTELEZO");
        specializationDaoJPA.getEntityManager().getTransaction().commit();
        IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
        IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenDeleteASpecialization.xml");
        Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateSpecialization(com.boky.SubjectParser.daolayer.entities.Specialization)}.
     */
    @Test
    public void testSaveOrUpdateSpecializationWhenSave() throws DatabaseUnitException {
        Set<Subject> subjects = new HashSet<Subject>();
        subjects.addAll(subjectDaoJPA.getAllEntity());
        Specialization specialization = new Specialization("NEWSPECIALIZATION", "New Specialization");
        specialization.setSubjects(subjects);
        specializationDaoJPA.saveOrUpdate(specialization);
        specializationDaoJPA.getEntityManager().getTransaction().commit();
        IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
        IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenInsertASpecialization.xml");
        Assert.assertEquals(expectedDatabaseDataSet.getTable("specialization").getRowCount(), actualDatabaseDataSet.getTable("specialization").getRowCount());
        Assert.assertEquals(expectedDatabaseDataSet.getTable("subject_specialization").getRowCount(), actualDatabaseDataSet.getTable("subject_specialization").getRowCount());
    }

    /**
     * Test method for {@link com.boky.SubjectParser.daolayer.implementations.DependencyDaoJPA2#saveOrUpdateSpecialization(com.boky.SubjectParser.daolayer.entities.Specialization)}.
     */
    @Test
    public void testSaveOrUpdateSpecializationWhenUpdate() throws DatabaseUnitException {
        Specialization specialization = specializationDaoJPA.getById("VALASZTHATO");
        specialization.setName("Updated name");
        specializationDaoJPA.saveOrUpdate(specialization);
        specializationDaoJPA.getEntityManager().getTransaction().commit();
        IDataSet actualDatabaseDataSet = dbUnitInitializer.createActualDataSet();
        IDataSet expectedDatabaseDataSet = dbUnitInitializer.createExpectedDataSet("expectedDataSetWhenUpdateASpecialization.xml");
        Assertion.assertEquals(expectedDatabaseDataSet, actualDatabaseDataSet);

    }

}
