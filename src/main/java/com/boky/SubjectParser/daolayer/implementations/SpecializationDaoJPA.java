package com.boky.SubjectParser.daolayer.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boky.SubjectParser.daolayer.entities.Specialization;
import com.boky.SubjectParser.daolayer.interfaces.SpecializationDao;

@Repository
public class SpecializationDaoJPA extends BasicDaoJPA<Specialization> implements SpecializationDao {

    @Override
    public Specialization getSpecializationByName(String name) {
        List<Specialization> result = runOwnQuery("FROM Specialization WHERE name = ?1", name);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

}
