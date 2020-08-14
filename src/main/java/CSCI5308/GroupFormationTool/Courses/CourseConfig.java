package CSCI5308.GroupFormationTool.Courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseConfig {
	private static CourseConfig uniqueInstance = null;
	private static Logger logger = LoggerFactory.getLogger(CourseConfig.class);
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;

	private CourseConfig() {
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		logger.info("Setting default Course Configuration Setting");
	}

	public static CourseConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CourseConfig();
		}

		logger.info("Setting a unique instance of Course Configuration");
		return uniqueInstance;
	}

	public ICoursePersistence getCourseDB() {
		return courseDB;
	}

	public void setCourseDB(ICoursePersistence courseDB) {
		this.courseDB = courseDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
		return courseUserRelationshipDB;
	}

	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}

}
