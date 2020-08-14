package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDB implements IRolePersistence {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<String> loadRolesByUserID(Long userID) {
		CallStoredProcedure proc = null;
		List<String> roles = new ArrayList<>();
		try {
			proc = new CallStoredProcedure("spLoadRolesByUserID(?)");
			proc.setParameter(1, userID);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {

					logger.info("Fetching the role of a particular user from the database");
					if (null != results) {
						while (results.next()) {
							String role = results.getString(1);
							roles.add(role);
							logger.info("Adding into the list of Role with : " + role);
						}
					}

				}
			}

		}

		catch (SQLException exception) {
			logger.info("Database error has occured with SQL state not proper");
			logger.error("Error Message : {} Error Code : {}", exception.getMessage(), exception.getErrorCode());
		}

		finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}

		return roles;
	}

}
