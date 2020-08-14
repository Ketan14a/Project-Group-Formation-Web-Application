package CSCI5308.GroupFormationTool.SecurityTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Security.IUserAuthHistoryRelationshipPersistence;

public class UserAuthHistoryRelationshipDBMock implements IUserAuthHistoryRelationshipPersistence {

	@Override
	public boolean addPasswordHistory(User user) {
		
		return true;
	}

	@Override
	public List<String> getPreviousPasswords(User user, int limit) {
		
		List<String> passwords = new ArrayList<>();
		passwords.add("$2a$10$qoliVscElqyDqZVH34S4WOntaKfySyhke4YRO60WH2ctL8HVaBAue");
		return passwords;
	}

}
