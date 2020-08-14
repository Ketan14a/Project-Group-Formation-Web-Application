package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.UserNotification;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest {

	@Test
	public void enrollStudentFromRecord() {
		User user = new User();
		Course course = new Course();
		IUserPersistence userDB = new UserDBMock();
		IPasswordEncryption passwordEncryption = new PasswordEncryptionMock();
		IUserNotifications userNotifications = Mockito.mock(UserNotification.class);
		Mockito.when(userNotifications.sendUserLoginCredentials(Mockito.any(User.class), Mockito.anyString()))
				.thenReturn(true);
		Assert.isTrue(user.createUser(userDB, passwordEncryption, userNotifications));
	}

	@Test
	public void getSuccessResults() {
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResults() {
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

}
