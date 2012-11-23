/**
 * 
 */
package com.boky.SubjectParser.daolayer.interfaces;

import com.boky.SubjectParser.daolayer.entities.Specialization;

/**
 * @author Andras_Bokor
 *
 */
public interface SpecializationDao extends BasicDao<Specialization> {
    /**
     * Get a subject by the name of specialization.
     * @param name of the specialization
     * @return Specialization object, if there is no any match, it will give a null.
     */
    public Specialization getSpecializationByName(String name);
}
