package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

public interface IRolePersistence {
	public List<String> loadRolesByUserID(Long userID);
}
