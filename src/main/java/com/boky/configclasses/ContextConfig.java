package com.boky.configclasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.boky.SubjectParser.daolayer.entities.Specialization;

@Configuration
@ComponentScan(basePackages = {"com.boky.SubjectParser.daolayer", "com.boky.SubjectParser.services", "com.boky.configclasses"})
@EnableTransactionManagement
@Import({DaoConfig.class})
@EnableAspectJAutoProxy
public class ContextConfig {

    @Bean(name = "basicSpecializations")
    public Map<String, Specialization> basicSpeciailzations() {
        Map<String, Specialization> map = new HashMap<String, Specialization>();
        map.put("KOTELEZO", new Specialization("KOTELEZO", "Kötelező"));
        map.put("GEOTECHNIKAI", new Specialization("GEOTECHNIKAI", "Geotechnikai"));
        map.put("TELEPULESI", new Specialization("TELEPULESI", "Települési"));
        map.put("MAGASEPITESI", new Specialization("MAGASEPITESI", "Magasépítési"));
        map.put("TUZVEDELMI", new Specialization("TUZVEDELMI", "Tűz- és katasztrófavédelmi"));
        map.put("VALASZTHATO", new Specialization("VALASZTHATO", "Szabadon választható"));
        return map;
    }

    @Bean(name = "numberOfSemester")
    public Integer numberOfSemester() {
        return Integer.valueOf(8);
    }

    @Bean
    public Random randomGenerator() {
        return new Random();
    }

}
