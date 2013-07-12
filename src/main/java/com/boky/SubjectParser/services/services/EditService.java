/**
 * 
 */
package com.boky.SubjectParser.services.services;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boky.SubjectParser.services.dto.SpecializationsForEditDTO;
import com.boky.SubjectParser.services.dto.SubjectForEditDTO;
import com.boky.SubjectParser.services.services.error.EditServiceException;

/**
 * @author bokor
 * 
 */
@Service
@javax.jws.WebService(serviceName = "EditService", endpointInterface = "com.boky.SubjectParser.services.services.EditServiceEndpoint")
public class EditService implements EditServiceEndpoint {
	private EditServiceHelper editServiceHelper;

	@Resource
	private WebServiceContext context;

	/**
	 * 
	 */

	public EditService() {
	}

	@Override
	public String sayHello() {
		return "Hello Word!";
	}

	@Override
	public SubjectForEditDTO getSubjectById(String id) throws EditServiceException {

		SubjectForEditDTO result = getEditServiceHelper().getSubjectById(id);

		if (result == null) {
			throw new EditServiceException("There is no subject with this id: " + id);
		}
		return result;
	}

	@Override
	public void updateSubject(SubjectForEditDTO paramSubject) throws EditServiceException {
		getEditServiceHelper().updateSubject(paramSubject);
	}

	@Override
	public void saveSubject(SubjectForEditDTO subject) throws EditServiceException {
		getEditServiceHelper().saveSubject(subject);
	}

	@Override
	public void deleteSubject(String id) throws EditServiceException {
		getEditServiceHelper().deleteSubject(id);
	}

	@Override
	public SpecializationsForEditDTO getSpecializationCodesAndNames() throws EditServiceException {
		return getEditServiceHelper().getSpecializationsWithCodeAndName();
	}

	/*
	 * This complicated getter needed so the EditServiceHelper object will be in the Spring context
	 */
	public EditServiceHelper getEditServiceHelper() {
		if (editServiceHelper == null) {
			ServletContext servletContext = (ServletContext) context.getMessageContext().get("javax.xml.ws.servlet.context");
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			editServiceHelper = webApplicationContext.getAutowireCapableBeanFactory().getBean(EditServiceHelper.class);
		}
		return editServiceHelper;
	}
}
