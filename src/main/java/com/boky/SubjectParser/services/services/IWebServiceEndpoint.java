/**
 * 
 */
package com.boky.SubjectParser.services.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.boky.SubjectParser.services.dto.SubjectForEditDTO;
import com.boky.SubjectParser.services.services.error.WebServiceException;

/**
 * @author bokor
 * 
 */
@WebService(serviceName = "EditService")
@SOAPBinding(style = Style.RPC)
public interface IWebServiceEndpoint {

	@WebMethod
	@WebResult(name = "subject")
	public SubjectForEditDTO getSubjectById(@WebParam(name = "id") String id) throws WebServiceException;

	@WebMethod
	public void updateSubject(@WebParam(name = "subject") SubjectForEditDTO subject) throws WebServiceException;

	@WebMethod
	public void saveSubject(@WebParam(name = "subject") SubjectForEditDTO subject) throws WebServiceException;

	@WebMethod
	public void deleteSubject(@WebParam(name = "id") String id) throws WebServiceException;

}
