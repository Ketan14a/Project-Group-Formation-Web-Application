package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence {
	private Logger logger = LoggerFactory.getLogger(CourseDB.class);

	public List<Course> loadAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = procedure.executeWithResults();
			logger.info("Load all the courses added by the Admin department");
			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					Course course = new Course();
					course.setId(id);
					course.setTitle(title);
					courses.add(course);
					logger.info("Course available with Title : {}", title);
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
		return courses;
	}

	public void loadCourseByID(long id, Course course) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spFindCourseByID(?)");
			procedure.setParameter(1, id);
			ResultSet results = procedure.executeWithResults();
			logger.info("Loading the course with ID : {}", id);
			if (null != results) {
				while (results.next()) {
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
					logger.info("Load course with the Title : {}", title);
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
	}

	public boolean createCourse(Course course) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spCreateCourse(?, ?)");
			procedure.setParameter(1, course.getTitle());
			procedure.registerOutputParameterLong(2);
			procedure.execute();
			logger.info("Course created successful");
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

	public boolean deleteCourse(long id) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spDeleteCourse(?)");
			procedure.setParameter(1, id);
			procedure.execute();
			logger.info("Deletion of course successful");
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
}
