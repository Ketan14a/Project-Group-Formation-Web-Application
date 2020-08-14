package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence {
	private Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);

	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID) {
		List<User> users = new ArrayList<User>();
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			procedure.setParameter(1, role.toString());
			procedure.setParameter(2, courseID);
			ResultSet results = procedure.executeWithResults();
			logger.info("Finding all the Users having a course Role");
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					User user = new User();
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					users.add(user);
					logger.info("User with FirstName : {} found", firstName);
				}
			}

		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return users;
	}

	public List<User> findAllUsersWithCourseRole(Role role, long courseID) {
		List<User> users = new ArrayList<User>();
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			procedure.setParameter(1, role.toString());
			procedure.setParameter(2, courseID);
			ResultSet results = procedure.executeWithResults();
			logger.info("Finding the users having a course role");
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					User user = new User();
					user.setID(userID);
					users.add(user);
					logger.info("User found");
				}
			}
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return users;
	}

	public boolean enrollUser(Course course, User user, Role role) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spEnrollUser(?, ?, ?)");
			procedure.setParameter(1, course.getId());
			procedure.setParameter(2, user.getID());
			procedure.setParameter(3, role.toString());
			procedure.execute();
			logger.info("Enrolling a user for particular role");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return true;
	}

	public List<Role> loadUserRolesForCourse(Course course, User user) {
		List<Role> roles = new ArrayList<Role>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			logger.info("Loading All the roles for a user in a course");
			if (null != results) {
				while (results.next()) {
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
					logger.info("Role added : {}", role);
				}
			}
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return roles;
	}
}
