package CSCI5308.GroupFormationTool.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.AccessControl.User;

import CSCI5308.GroupFormationTool.Security.IUserAuthHistoryRelationshipPersistence;
import CSCI5308.GroupFormationTool.Security.UserAuthHistoryRelationship;

public class UserAuthHistoryRelationshipTest {

	@Test
	public void addPasswordHistory() {
		User user = new User();
		user.setBannerID("B-0832143");
		IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB = new UserAuthHistoryRelationshipDBMock();
		assertTrue(new UserAuthHistoryRelationship().addPasswordHistory(user, userAuthHistoryRelationshipDB));

	}

	@Test
	public void getPreviousPasswords() {
		User user = new User();
		user.setID(1);
		int limit = 1;
		IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB = new UserAuthHistoryRelationshipDBMock();
		assertNotNull(
				new UserAuthHistoryRelationship().getPreviousPasswords(user, limit, userAuthHistoryRelationshipDB));

	}

}
