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

import com.boky.SubjectParser.services.dto.SpecializationsForEditDTO;
import com.boky.SubjectParser.services.dto.SubjectForEditDTO;
import com.boky.SubjectParser.services.services.error.EditServiceException;

/**
 * @author bokor
 * 
 */
@WebService(serviceName = "EditService")
@SOAPBinding(style = Style.RPC)
public interface EditServiceEndpoint {

	/**
	 * Send back a message. Use it for test the connection.
	 * 
	 * @return "Hello Word!".
	 */
	@WebMethod
	@WebResult(name = "message")
	public String sayHello();

	/**
	 * Get back a subject by its id.
	 * 
	 * @param id The id of the subject.
	 * @return SubjectForEditDTO object.
	 * @throws EditServiceException TODO
	 */
	@WebMethod
	@WebResult(name = "subject")
	public SubjectForEditDTO getSubjectById(@WebParam(name = "id") String id) throws EditServiceException;

	/**
	 * Update a subject. See the wsdl for the exact input specification.
	 * 
	 * @param subject SubjectForEditDTO object. The method will examine the changes and update them.
	 */
	@WebMethod
	public void updateSubject(@WebParam(name = "subject") SubjectForEditDTO subject) throws EditServiceException;

	/**
	 * Get a SubjectForEditDTO and save it to the database.
	 * 
	 * @param subject Subject to save.
	 * @throws EditServiceException TODO
	 */
	@WebMethod
	public void saveSubject(@WebParam(name = "subject") SubjectForEditDTO subject) throws EditServiceException;

	/**
	 * Delete the owner of the id.
	 * 
	 * @param id Id of the subject for delete.
	 * @throws EditServiceException TODO
	 */
	@WebMethod
	public void deleteSubject(@WebParam(name = "id") String id) throws EditServiceException;

	/**
	 * Get all specializations with the names and the codes.
	 * 
	 * @return List with all specializations with the names and the codes.
	 * @throws EditServiceException TODO
	 */
	@WebMethod
	@WebResult(name = "specializations")
	public SpecializationsForEditDTO getSpecializationCodesAndNames() throws EditServiceException;

}
