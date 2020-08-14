package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Security.IRandomPasswordGenerator;
import CSCI5308.GroupFormationTool.Security.RandomPasswordGenerator;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentCSVImport
{
	private Logger logger = LoggerFactory.getLogger(StudentCSVImport.class);
	private List<String> successResults;
	private List<String> failureResults;
	private Course course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;
	private IUserNotifications userNotifications;
	private IRandomPasswordGenerator randomPasswordGenerator;

	public StudentCSVImport(IStudentCSVParser parser, Course course)
	{
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = AccessControlConfig.instance().getUserDB();
		passwordEncryption = SecurityConfig.instance().getPasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}
	
	private void enrollStudentFromRecord()
	{
		List<User> studentList = parser.parseCSVFile(failureResults);
		for(User u : studentList)
		{	
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName +" " + email;
			
			User user = new User();
			userDB.loadUserByBannerID(bannerID, user);
			logger.info("Enrolling the Student from Record");
			if (user.isValidUser()==false)
			{
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				userNotifications = AccessControlConfig.instance().getUserNotification();
				randomPasswordGenerator = new RandomPasswordGenerator();
				user.setPassword(randomPasswordGenerator.generate());
				if (user.createUser(userDB, passwordEncryption, userNotifications))
				{
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				}
				else
				{
					failureResults.add("Unable to save this user to DB: " + userDetails);
					return;
				}
			}
			if (course.enrollUserInCourse(Role.STUDENT, user))
			{
				successResults.add("User enrolled in course: " + userDetails);
			}else {
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}
		}
	}
	
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	public List<String> getFailureResults()
	{
		return failureResults;
	}
}
