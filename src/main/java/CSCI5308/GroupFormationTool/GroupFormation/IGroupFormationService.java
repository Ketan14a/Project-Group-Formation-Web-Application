package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IGroupFormationService {
	public Map<Integer, Map<User, List<String>>> createGroups(List<IGroupFormationPolicies> questionPolicies,
			IGroupFormationStudentResponsesPersistence groupFormResponseDb, IUserPersistence userDb);
}
