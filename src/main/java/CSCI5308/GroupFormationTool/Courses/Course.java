package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Course {
	private Logger logger = LoggerFactory.getLogger(Course.class);
	private long id;
	private String title;
	private ICourseUserRelationship userRoleDecider;

	public Course() {
		setDefaults();
	}

	public Course(long id, ICoursePersistence courseDB) {
		setDefaults();
		courseDB.loadCourseByID(id, this);
		logger.info("Loading the course using course ID and setting the value to default");
	}

	public void setDefaults() {
		id = -1;
		title = "";
		userRoleDecider = new CourseUserRelationship();
		logger.info("Setting default values for the model Course");
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public boolean delete(ICoursePersistence courseDB) {
		return courseDB.deleteCourse(id);
	}

	public boolean createCourse(ICoursePersistence courseDB) {
		return courseDB.createCourse(this);
	}

	public boolean enrollUserInCourse(Role role, User user) {
		logger.info("Enrolling the user in a specific course");
		return userRoleDecider.enrollUserInCourse(user, this, role);
	}

	public boolean isCurrentUserEnrolledAsRoleInCourse(Role role) {
		logger.info("Checking if the user is Enrolled in the course or not");
		return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
	}

	public boolean isCurrentUserEnrolledAsRoleInCourse(String role) {
		Role r = Role.valueOf(role.toUpperCase());
		logger.info("Is current user having a role and determine which role");
		return isCurrentUserEnrolledAsRoleInCourse(r);
	}

	public List<Role> getAllRolesForCurrentUserInCourse() {
		logger.info("List of roles for a particular user according to a course");

		return userRoleDecider.loadAllRoluesForUserInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), this);
	}

	public void loadCourseByID(ICoursePersistence courseDB, long courseId){
		courseDB.loadCourseByID(courseId, this);
	}
}
