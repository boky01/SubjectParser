/**
 * 
 */
package com.boky.SubjectParser.view.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.boky.configclasses.ContextConfig;

/**
 * @author Andras_Bokor
 * 
 */
public class WebXml implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ServletContextConfig.class);
        //        applicationContext.refresh();

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ContextConfig.class);
        //        rootContext.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext));

        System.out.println("pina");

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatherServlet", new DispatcherServlet(applicationContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

    }
}
