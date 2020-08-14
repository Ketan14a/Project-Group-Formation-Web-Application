package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IUserAuthHistoryRelationshipPersistence {
	public boolean addPasswordHistory(User user);

	public List<String> getPreviousPasswords(User user, int limit);

}
