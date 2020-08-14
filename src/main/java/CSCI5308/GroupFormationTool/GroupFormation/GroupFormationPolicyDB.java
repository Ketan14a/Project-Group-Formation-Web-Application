package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.SQLException;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupFormationPolicyDB implements IGroupFormationPolicyPersistence {

	private Logger logger = LoggerFactory.getLogger(GroupFormationPolicyDB.class);

	@Override
	public boolean createSurveyPolicies(List<IGroupFormationPolicies> listOfGroupFormationPolicies) {
		CallStoredProcedure procedure = null;
		try {
			logger.info("Creating Survey Policies for the Group formation");
			for (IGroupFormationPolicies groupPolicy : listOfGroupFormationPolicies) {
				procedure = new CallStoredProcedure("spCreateGroupPolicy(?, ?, ?, ?,?)");
				procedure.setParameter(1, groupPolicy.getSurveyID());
				procedure.setParameter(2, groupPolicy.getQuestionID());
				procedure.setParameter(3, groupPolicy.getPolicyType());
				procedure.setParameter(4, groupPolicy.getValue());
				procedure.setParameter(5, groupPolicy.getGroupCount());
				procedure.execute();
			}
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} catch (Exception exception) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", exception.getMessage());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return true;
	}
}
