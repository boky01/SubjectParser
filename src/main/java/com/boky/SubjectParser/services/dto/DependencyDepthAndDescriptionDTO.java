package com.boky.SubjectParser.services.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * This DTO contains the depth of a subject dependencies and the description of the given subject.
 * @author Andras_Bokor
 *
 */
public class DependencyDepthAndDescriptionDTO {

    private Map<String, Integer> dependencyDepths;
    private String description;

    public DependencyDepthAndDescriptionDTO() {
        super();
        this.dependencyDepths = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getDependencyDepths() {
        return dependencyDepths;
    }

    public void setDependencyDepths(Map<String, Integer> dependencyDepths) {
        this.dependencyDepths = dependencyDepths;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dependencyDepths == null) ? 0 : dependencyDepths.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        DependencyDepthAndDescriptionDTO other = (DependencyDepthAndDescriptionDTO) obj;
        if (dependencyDepths == null) {
            if (other.dependencyDepths != null) {
                return false;
            }
        } else if (!dependencyDepths.equals(other.dependencyDepths)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DependencyShowAndDescriptionDTO [dependencyDepths=" + dependencyDepths + ", description=" + description + "]";
    }

}
