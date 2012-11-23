package com.boky.SubjectParserTest.daolayer.implementations;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoTestingUtil {
    Logger LOGGER = LoggerFactory.getLogger(DaoTestingUtil.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory factory;
    private IDataSet dataSet;

    private IDatabaseConnection databaseConnection;

    public DaoTestingUtil() {

    }

    @PostConstruct
    public void setUp() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        try {
            dataSet = builder.build(new File("src/test/resources/dbUnitXmls/basicDataSet.xml"));
            databaseConnection = new DatabaseConnection(dataSource.getConnection());
        } catch (MalformedURLException | DatabaseUnitException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        databaseConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
    }

    public IDataSet createActualDataSet() {
        IDataSet dataSet = null;
        try {
            dataSet = getDatabaseConnection().createDataSet();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataSet;
    }

    public IDataSet createExpectedDataSet(String fileName) {
        FlatXmlDataSet dataSet = null;
        try {
            dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dbUnitXmls/" + fileName));
        } catch (MalformedURLException | DataSetException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataSet;
    }

    //Getters and setters

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public IDataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(IDataSet dataSet) {
        this.dataSet = dataSet;
    }

    public IDatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
