package com.boky.SubjectParser.daolayer.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
public class Specialization implements Serializable {
    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_specialization", joinColumns = {@JoinColumn(name = "specialization_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")})
    private Set<Subject> subjects;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "specialization", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Dependency> dependencies;

    public Specialization() {
    }

    public Specialization(String name) {
        this.name = name;
    }

    public Specialization(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @PreRemove
    public void preRemove() {
        Iterator<Subject> iterator = subjects.iterator();
        while (iterator.hasNext()) {
            Subject subject = iterator.next();
            subject.getSpecializations().remove(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Specialization other = (Specialization) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Specialization [id=" + id + ", name=" + name + "]";
    }

}
