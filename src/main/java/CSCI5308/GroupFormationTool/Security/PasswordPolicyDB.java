package CSCI5308.GroupFormationTool.Security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class PasswordPolicyDB implements IPasswordPolicyPersistence {
	private Logger logger = LoggerFactory.getLogger(PasswordPolicyDB.class);

	@Override
	public void loadPasswordPolicy(PasswordPolicy policy) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadPasswordPolicy()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				if (results.next()) {
					policy.setMinLength(results.getInt("minLength"));
					policy.setMinLengthFlag(results.getBoolean("minLengthFlag"));

					policy.setMaxLength(results.getInt("maxLength"));
					policy.setMaxLengthFlag(results.getBoolean("maxLengthFlag"));

					policy.setMinLowerLength(results.getInt("minLower"));
					policy.setMinLowerLengthFlag(results.getBoolean("minLowerFlag"));

					policy.setMinUpperLength(results.getInt("minUpper"));
					policy.setMinUpperLengthFlag(results.getBoolean("minUpperFlag"));

					policy.setMinSpecial(results.getInt("minSpecial"));
					policy.setMinSpecialFlag(results.getBoolean("minSpecialFlag"));

					policy.setNotAllowed(results.getString("notAllowed"));
					policy.setNotAllowedFlag(results.getBoolean("notAllowedFlag"));

					policy.setMinHistory(results.getInt("minHistory"));
					policy.setMinHistoryFlag(results.getBoolean("minHistoryFlag"));

				}
			}
		}

		catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} catch (Exception exception) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", exception.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

	}
}
