package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseUserRelationship implements ICourseUserRelationship {
	private Logger logger = LoggerFactory.getLogger(CourseUserRelationship.class);

	public boolean userHasRoleInCourse(User user, Role role, Course course) {
		if (null == user || user.isValidUser() == false) {
			logger.info("Invalid User");
			return false;
		}
		if (null == role) {
			logger.info("No role assigned");
			return false;
		}
		if (null == course) {
			logger.info("No course associated with the user");

			return false;
		}
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfig.instance()
				.getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		logger.info("Getting the Roles for user in a particluar course");
		if (null != roles && roles.contains(role)) {
			logger.info("A role is found for a user");

			return true;
		}
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(User user, Course course) {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfig.instance()
				.getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		logger.info("Loading user role for a course : {}", roles);
		return roles;
	}

	public boolean enrollUserInCourse(User user, Course course, Role role) {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfig.instance()
				.getCourseUserRelationshipDB();
		logger.info("Enroling user in a course : {}", course);
		return userCourseRelationshipDB.enrollUser(course, user, role);
	}
}