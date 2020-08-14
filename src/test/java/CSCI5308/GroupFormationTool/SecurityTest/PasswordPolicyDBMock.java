package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;

public class PasswordPolicyDBMock implements IPasswordPolicyPersistence {

	@Override
	public void loadPasswordPolicy(PasswordPolicy policy) {
		policy.setMinLength(1);
		policy.setMinLengthFlag(true);

		policy.setMaxLength(10);
		policy.setMaxLengthFlag(true);

		policy.setMinUpperLength(1);
		policy.setMinUpperLengthFlag(true);

		policy.setMinLowerLength(1);
		policy.setMinLowerLengthFlag(true);

		policy.setMinSpecial(1);
		policy.setMinSpecialFlag(true);

		policy.setNotAllowed("$");
		policy.setNotAllowedFlag(true);

		policy.setMinHistory(3);
		policy.setMinHistoryFlag(true);

	}

}
