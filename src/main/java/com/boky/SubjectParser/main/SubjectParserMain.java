package com.boky.SubjectParser.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.boky.SubjectParser.services.DatabaseBuilderService;
import com.boky.configclasses.ContextConfig;

/**
 * ALTER DATABASE dependencies DEFAULT CHARACTER SET utf8 COLLATE=utf8_general_ci;
 * Ez ahhoz kell, hogy minden utf8as legyen, plusz a my.ini-ben is atirtam mindent latin1-rol utf8-ra. 
 * select sub.name as elso, sub2.name as masodik, s.name as szakirany from subject sub inner join dependency d on d.subjectId=sub.subjectId inner join subject sub2 on d.dependencySubject=sub2.subjectId inner join specialization s on s.id=d.specialization_id;
 * Ez a targyak fuggosegeit keri le.
 * truncate subject_specialization; truncate specialization; truncate subject; truncate dependency;
 * osszes tabla kiuritese
 * Ha a tomcat nem talalna a WebApplicationInitializer implementaciot
 * jobb gomb a projektre -> properties, deployment assembly -> add -> es ott a java build path entries -> Maven vmi
 * Ez el fog tunni, minden Maven -> update project-nel, ennek a megelozesehez szukseg van egy masik pluginra.
 * Maven Integration for WTP. Install new software: m2eclipse-wtp updates - http://download.jboss.org/jbosstools/updates/m2eclipse-wtp/
 * 
 * @author Andras_Bokor
 *
 */
public class SubjectParserMain {
    final static Logger logger = LoggerFactory.getLogger(SubjectParserMain.class);
    private static DatabaseBuilderService databaseBuilder;

    /**
     * @param args
     */
    public static void main(String[] args) {
        //        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);

        databaseBuilder = context.getBean(DatabaseBuilderService.class);
        databaseBuilder.buildDatabaseFromFiles();
        //        UseDatabaseSerivce uds = context.getBean(UseDatabaseSerivce.class);
        //        logger.info("++++++++++++++++++++++++++++++++++++");

        //        uds.testAMethod();
        //        uds.testAMethod2();

        //        MyController myController = context.getBean(MyController.class);
        //        myController.tryController();
        //
        //        logger.info("++++++++++++++++++++++++++++++++++++");
        //        logger.info("Finish");
    }

    public static DatabaseBuilderService getDatabaseBuilder() {
        return databaseBuilder;
    }

    public static void setDatabaseBuilder(DatabaseBuilderService databaseBuilder) {
        SubjectParserMain.databaseBuilder = databaseBuilder;
    }

}
