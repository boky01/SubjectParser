package com.boky.SubjectParser.daolayer.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.boky.SubjectParser.daolayer.entities.Dependency;
import com.boky.SubjectParser.daolayer.entities.Subject;
import com.boky.SubjectParser.daolayer.interfaces.SubjectDao;

@Repository
public class SubjectDaoJPA extends BasicDaoJPA<Subject> implements SubjectDao {

    /**
     * Get a subject by the name of subject.
     * @param subjectName name of the subject.
     * @return Subject object, if there is no it will return with null.
     */
    @Override
    public Subject getSubjectByName(String name) {
        List<Subject> result = runOwnQuery("FROM Subject WHERE name = ?1", name);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Subject> getSubjectsBySpecializationName(String name) {
        return runOwnQuery("SELECT sub FROM Subject sub join sub.specializations spec WHERE spec.name = ?1", name);
    }

    @Override
    public List<Subject> getSubjectsBySpecializationId(String id) {
        return runOwnQuery("SELECT sub FROM Subject sub join sub.specializations spec WHERE spec.id = ?1", id);
    }

    @Override
    public void saveOrUpdate(Subject entity) {
        removeuUnecessaryDependenies(entity, entity.getDependencies());
        removeuUnecessaryForwardDependencies(entity, entity.getForwardDependencies());
        super.saveOrUpdate(entity);

    }

    private void removeuUnecessaryForwardDependencies(Subject entity, Set<Dependency> forwardDependencies) {
        List<Dependency> dependenciesToDelete = createDependenciesToDelete(entity, forwardDependencies);
        entity.getDependencies().removeAll(dependenciesToDelete);
        for (Dependency dependency : dependenciesToDelete) {
            dependency.getSubject().getDependencies().remove(dependency);
            super.saveOrUpdate(dependency.getSubject());
        }

    }

    private void removeuUnecessaryDependenies(Subject entity, Set<Dependency> dependencies) {
        List<Dependency> dependenciesToDelete = createDependenciesToDelete(entity, dependencies);
        entity.getDependencies().removeAll(dependenciesToDelete);
    }

    private List<Dependency> createDependenciesToDelete(Subject entity, Set<Dependency> dependencies) {
        List<Dependency> dependenciesToDelete = new ArrayList<>();
        for (Dependency dependency : dependencies) {
            if (!entity.getSpecializations().contains(dependency.getSpecialization())) {
                dependenciesToDelete.add(dependency);

            }
        }
        return dependenciesToDelete;
    }
}
