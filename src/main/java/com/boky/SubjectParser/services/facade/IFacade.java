package com.boky.SubjectParser.services.facade;

import java.util.List;

import com.boky.SubjectParser.services.dto.DependencyDepthAndDescriptionDTO;
import com.boky.SubjectParser.services.dto.InitializationDataForWebDTO;
import com.boky.SubjectParser.services.dto.SpecializationWithNameAndCodeDTO;

/**
 * Interface for Facades, it contains the services of server.
 * 
 * @author Andras_Bokor
 * 
 */
public interface IFacade {

	/**
	 * It gets Subjects names related to the Kotelezo and given specialization.
	 * It also get all names of specializations.
	 * 
	 * @param specialization
	 *            The specialization which subjects we want to see for.
	 * @return InitializationDataForWebDTO which contains the Subjects names
	 *         related to the Kotelezo and given specilization and the names of
	 *         specializations.
	 */
	public InitializationDataForWebDTO getInitializedData(String specialization);

	/**
	 * Get the dependencies to a subject on the given specialization.
	 * 
	 * @param subjectId
	 *            This subject information will be visible.
	 * @param specialization
	 *            The set specialization.
	 * @return Dependencies to a subject on the given specialization
	 */
	public DependencyDepthAndDescriptionDTO getDependencyDepthAndDescription(
			String subjectId, String specialization);

	/**
	 * Get all the specializations with name and id.
	 * 
	 * @return All specializatioin name and id.
	 */
	public List<SpecializationWithNameAndCodeDTO> getAllSpecializationWithNameAndId();

	/**
	 * Get the name of the displayed image randomly.
	 * 
	 * @return An image name which is string.
	 */
	public String getImageName();

	/**
	 * Get the name of the displayed image randomly. The picture depends on
	 * which day of the year is the actual day.
	 * 
	 * @return An image name which is string.
	 */
	public String getDalyImageName();
}
