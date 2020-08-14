package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IUserAuthHistoryRelationship {
	public boolean addPasswordHistory(User user, IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB);

	public List<String> getPreviousPasswords(User user, int limit,
			IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB);
}
