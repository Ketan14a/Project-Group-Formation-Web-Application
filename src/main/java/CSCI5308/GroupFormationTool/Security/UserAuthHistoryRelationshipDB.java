package CSCI5308.GroupFormationTool.Security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserAuthHistoryRelationshipDB implements IUserAuthHistoryRelationshipPersistence {
	private Logger logger = LoggerFactory.getLogger(UserAuthHistoryRelationshipDB.class);
	
	@Override
	public boolean addPasswordHistory(User user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreatePasswordHistory(?)");
			proc.setParameter(1, user.getBannerID());
			proc.execute();
		} catch (SQLException sqlException) {
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
		return true;
	}

	@Override
	public List<String> getPreviousPasswords(User user, int limit) {
		List<String> passwords = new ArrayList<String>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadPreviousPasswords(?, ?)");
			proc.setParameter(1, user.getID());
			proc.setParameter(2, limit);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String password = results.getString(1);
					passwords.add(password);
				}
			}
		} catch (SQLException sqlException) {
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
		return passwords;

	}

}
