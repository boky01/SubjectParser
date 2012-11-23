package com.boky.SubjectParser.daolayer.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

@Entity
public class Dependency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;
    @Column(nullable = false)
    private boolean onlyRegistration;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "dependencySubject", nullable = false)
    private Subject dependencySubject;

    public Dependency() {
    }

    public Dependency(Subject subject, Specialization specialization, boolean onlyRegistration, Subject dependencySubject) {
        super();
        this.subject = subject;
        this.specialization = specialization;
        this.onlyRegistration = onlyRegistration;
        this.dependencySubject = dependencySubject;
    }

    public Dependency(Long id, Subject subject, Specialization specialization, boolean onlyRegistration, Subject dependencySubject) {
        super();
        Id = id;
        this.subject = subject;
        this.specialization = specialization;
        this.onlyRegistration = onlyRegistration;
        this.dependencySubject = dependencySubject;
    }

    @PreRemove
    public void preRemove() {
        subject.getDependencies().remove(this);
        dependencySubject.getForwardDependencies().remove(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isOnlyRegistration() {
        return onlyRegistration;
    }

    public void setOnlyRegistration(boolean onlyRegistration) {
        this.onlyRegistration = onlyRegistration;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Subject getDependencySubject() {
        return dependencySubject;
    }

    public void setDependencySubject(Subject dependencySubject) {
        this.dependencySubject = dependencySubject;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (Id != null) {
            result = prime * result + (Id.hashCode());
        } else {
            result = prime * result + ((dependencySubject == null) ? 0 : dependencySubject.getId().hashCode());
            result = prime * result + (onlyRegistration ? 1231 : 1237);
            result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
            result = prime * result + ((subject == null) ? 0 : subject.getId().hashCode());
        }
        return result;
    }

    //El kene tavolitani a az id-s konstruktort
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
        Dependency other = (Dependency) obj;
        if (Id == null) {
            if (other.Id != null) {
                return false;
            }
        } else if (!Id.equals(other.Id)) {
            return false;
        }
        if (dependencySubject == null) {
            if (other.dependencySubject != null) {
                return false;
            }
        } else if (!dependencySubject.getId().equals(other.dependencySubject.getId())) {
            return false;
        }
        if (onlyRegistration != other.onlyRegistration) {
            return false;
        }
        if (specialization == null) {
            if (other.specialization != null) {
                return false;
            }
        } else if (!specialization.equals(other.specialization)) {
            return false;
        }
        if (subject == null) {
            if (other.subject != null) {
                return false;
            }
        } else if (!subject.getId().equals(other.subject.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dependency [Id=" + Id + ", subject.id=" + subject.getId() + ", specialization=" + specialization + ", onlyRegistration=" + onlyRegistration
                + ", dependencySubject=" + dependencySubject.getId() + "]";
    }
}
