/**
 * 
 */
package com.boky.SubjectParser.services.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boky.SubjectParser.services.dto.DependencyDepthAndDescriptionDTO;
import com.boky.SubjectParser.services.dto.InitializationDataForWebDTO;
import com.boky.SubjectParser.services.dto.SpecializationWithNameAndCodeDTO;
import com.boky.SubjectParser.services.services.IServiceForWeb;

/**
 * @author Andras_Bokor
 *
 */
@Component
public class Facade implements IFacade {

    @Autowired
    IServiceForWeb serviceForWeb;

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.services.facade.IFacade#getInitializedData(java.lang.String)
     */
    @Override
    public InitializationDataForWebDTO getInitializedData(String specialization) {
        return serviceForWeb.getInitializedData(specialization);

    }

    @Override
    public List<SpecializationWithNameAndCodeDTO> getAllSpecializationWithNameAndId() {
        return serviceForWeb.getAllSpecializationWithNameAndId();
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.services.facade.IFacade#getDependencyDepthAndDescription(java.lang.String, java.lang.String)
     */
    @Override
    public DependencyDepthAndDescriptionDTO getDependencyDepthAndDescription(String subjectId, String specialization) {
        return serviceForWeb.getDependencyDepthAndDescription(subjectId, specialization);
    }

    @Override
    public String getImageName() {
        return serviceForWeb.getImageName("resources/image");
    }

    public IServiceForWeb getServiceForWeb() {
        return serviceForWeb;
    }

    public void setServiceForWeb(IServiceForWeb serviceForWeb) {
        this.serviceForWeb = serviceForWeb;
    }

}
