package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.List;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;

public class GroupFormationPolicyDBMock implements IGroupFormationPolicyPersistence{

	@Override
	public boolean createSurveyPolicies(List<IGroupFormationPolicies> listOfGroupFormationPolicies) {

		return true;
	}

}
