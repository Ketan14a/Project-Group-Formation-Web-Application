package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDB implements IUserPersistence {
	private Logger logger = LoggerFactory.getLogger(UserDB.class);

	public void loadUserByID(long id, User user) {

		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			logger.info("The user is loaded from the database.");
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
					logger.info("User's FirstName is:" + firstName);
				}
			}

		}

		catch (SQLException e) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", e.getMessage(), e.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user) {
		CallStoredProcedure proc = null;
		long userID = -1;
		try {
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			logger.info("Loading the user by its banner ID : " + bannerID);
			if (null != results) {
				while (results.next()) {
					userID = results.getLong(1);
					logger.info("User ID is fetched :" + userID);
				}
			}
		}

		catch (SQLException e) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", e.getMessage(), e.getErrorCode());
		}

		finally {
			if (null != proc) {

				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}

		if (userID > -1) {
			loadUserByID(userID, user);
			logger.info("Load the user if there is any user ID is there in the list");
		}
	}

	public boolean createUser(User user) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
			logger.info("Create the user by added the credentials in the database");
			logger.info("User with Banner ID : " + user.getBanner() + " creation successful");
		}

		catch (SQLException e) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", e.getMessage(), e.getErrorCode());
		}

		finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		logger.info("User creation successfull");
		return true;

	}

	public boolean updateUser(User user) {
		return false;
	}
}
