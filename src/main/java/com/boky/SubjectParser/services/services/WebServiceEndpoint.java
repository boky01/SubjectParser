/**
 * 
 */
package com.boky.SubjectParser.services.services;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.springframework.stereotype.Service;

import com.boky.SubjectParser.services.dto.SubjectForEditDTO;
import com.boky.SubjectParser.services.services.error.WebServiceException;

/**
 * @author bokor
 * 
 */

@Service
@javax.jws.WebService(serviceName = "EditService", endpointInterface = "com.boky.SubjectParser.services.services.IWebServiceEndpoint")
public class WebServiceEndpoint implements IWebServiceEndpoint {

	private WebServiceHelper webServiceHelper;

	@Resource
	private WebServiceContext context;

	/**
	 * 
	 */

	public WebServiceEndpoint() {
	}

	@Override
	public SubjectForEditDTO getSubjectById(String id) throws WebServiceException {

		SubjectForEditDTO result = getWebServiceHelper().getSubjectById(id);

		if (result == null) {
			throw new WebServiceException("There is no subject with this id: " + id);
		}
		return result;
	}

	@Override
	public void updateSubject(SubjectForEditDTO paramSubject) throws WebServiceException {
		getWebServiceHelper().updateSubject(paramSubject);
	}

	@Override
	public void saveSubject(SubjectForEditDTO subject) throws WebServiceException {
		getWebServiceHelper().saveSubject(subject);
	}

	@Override
	public void deleteSubject(String id) throws WebServiceException {
		getWebServiceHelper().deleteSubject(id);
	}

	public WebServiceHelper getWebServiceHelper() {
		if (webServiceHelper == null) {
			webServiceHelper = new WebServiceHelper();
			new WebServiceHelper().getSubjectById("");
		}
		return webServiceHelper;
	}

	public void setWebServiceHelper(WebServiceHelper webServiceHelper) {
		this.webServiceHelper = webServiceHelper;
	}
}
