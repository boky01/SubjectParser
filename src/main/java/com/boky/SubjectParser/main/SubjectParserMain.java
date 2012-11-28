package com.boky.SubjectParser.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.boky.SubjectParser.services.DatabaseBuilderService;
import com.boky.configclasses.ContextConfig;

/*
 * ALTER DATABASE dependencies DEFAULT CHARACTER SET utf8
 * COLLATE=utf8_general_ci; Ez ahhoz kell, hogy minden utf8as legyen, plusz a
 * my.ini-ben is atirtam mindent latin1-rol utf8-ra. 
 * 
 * Linuxon: etc/mysql/my.cnf:
 * [mysqld]
 * default-character-set=utf8 
 * default-collation=utf8_general_ci
 * character-set-server=utf8 
 * collation-server=utf8_general_ci init-connect='SET * NAMES utf8'
 * 
 * [client]
 * default-character-set=utf8
 * 
 * default-character-set is deprecated in 5.5. You should use instead:
 * character-set-server = utf8
 * 
 * osszes tabla kiuritese: 
 * truncatesubject_specialization; truncate specialization; truncate subject;truncate dependency;
 * 
 * Ha a tomcat nem talalna a WebApplicationInitializer implementaciot jobb gomb
 * a projektre -> properties, deployment assembly -> add -> es ott a java build
 * path entries -> Maven vmi Ez el fog tunni, minden Maven -> update
 * project-nel, ennek a megelozesehez szukseg van egy masik pluginra. Maven
 * Integration for WTP. Install new software: m2eclipse-wtp updates -
 * http://download.jboss.org/jbosstools/updates/m2eclipse-wtp/
 * 


 /**
 * With this class you are able to fill up the database with data. 
 * @author bokor
 *
 */
public class SubjectParserMain {
	final static Logger logger = LoggerFactory
			.getLogger(SubjectParserMain.class);
	private static DatabaseBuilderService databaseBuilder;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("context.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(
				ContextConfig.class);

		databaseBuilder = context.getBean(DatabaseBuilderService.class);
		databaseBuilder.buildDatabaseFromFiles();

		// UseDatabaseSerivce uds = context.getBean(UseDatabaseSerivce.class);
		// logger.info("++++++++++++++++++++++++++++++++++++");

		// uds.testAMethod();
		// uds.testAMethod2();

		// MyController myController = context.getBean(MyController.class);
		// myController.tryController();
		//
		// logger.info("++++++++++++++++++++++++++++++++++++");
		// logger.info("Finish");
		System.out.println("finish");
	}

	public static DatabaseBuilderService getDatabaseBuilder() {
		return databaseBuilder;
	}

	public static void setDatabaseBuilder(DatabaseBuilderService databaseBuilder) {
		SubjectParserMain.databaseBuilder = databaseBuilder;
	}

}
