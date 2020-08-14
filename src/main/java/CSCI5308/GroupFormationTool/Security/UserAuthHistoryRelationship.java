package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class UserAuthHistoryRelationship implements IUserAuthHistoryRelationship {

	@Override
	public boolean addPasswordHistory(User user,
			IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB) {

		return userAuthHistoryRelationshipDB.addPasswordHistory(user);
	}

	@Override
	public List<String> getPreviousPasswords(User user, int limit,
			IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB) {

		return userAuthHistoryRelationshipDB.getPreviousPasswords(user, limit);
	}

}
