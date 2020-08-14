package CSCI5308.GroupFormationTool.Security;

public class SecurityConfig {
	private static SecurityConfig uniqueInstance = null;

	private IPasswordEncryption passwordEncryption;
	private IPasswordPolicyPersistence passwordPolicyDB;
	private IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationship;

	private SecurityConfig() {
		passwordEncryption = new BCryptPasswordEncryption();
		passwordPolicyDB = new PasswordPolicyDB();
		userAuthHistoryRelationship = new UserAuthHistoryRelationshipDB();
	}

	public static SecurityConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new SecurityConfig();
		}

		return uniqueInstance;
	}

	public IPasswordEncryption getPasswordEncryption() {
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}

	public PasswordPolicy getPasswordPolicy() {
		return PasswordPolicy.getInstance(passwordPolicyDB);
	}

	public IPasswordPolicyPersistence getPasswordPolicyDB() {
		return passwordPolicyDB;
	}

	public void setPasswordPolicyDB(IPasswordPolicyPersistence passwordPolicyDB) {
		this.passwordPolicyDB = passwordPolicyDB;
	}

	public IUserAuthHistoryRelationshipPersistence getUserAuthHistoryRelationship() {
		return userAuthHistoryRelationship;
	}

	public void setUserAuthHistoryRelationship(IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationship) {
		this.userAuthHistoryRelationship = userAuthHistoryRelationship;
	}

}
