package CSCI5308.GroupFormationTool.Security;

public class PasswordPolicy {

	private static PasswordPolicy uniqueInstance = null;

	private int minLength;
	private int maxLength;
	private int minUpperLength;
	private int minLowerLength;
	private int minSpecial;
	private String notAllowed;
	private int minHistory;
	private boolean minLengthFlag;
	private boolean maxLengthFlag;
	private boolean minUpperLengthFlag;
	private boolean minLowerLengthFlag;
	private boolean minSpecialFlag;
	private boolean notAllowedFlag;
	private boolean minHistoryFlag;

	private static String policies;

	public static String getPolicies() {
		return policies;
	}

	public void setPolicies(String policies) {
		PasswordPolicy.policies = policies;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinUpperLength() {
		return minUpperLength;
	}

	public void setMinUpperLength(int minUpperLength) {
		this.minUpperLength = minUpperLength;
	}

	public int getMinLowerLength() {
		return minLowerLength;
	}

	public void setMinLowerLength(int minLowerLength) {
		this.minLowerLength = minLowerLength;
	}

	public int getMinSpecial() {
		return minSpecial;
	}

	public void setMinSpecial(int minSpecial) {
		this.minSpecial = minSpecial;
	}

	public String getNotAllowed() {
		return notAllowed;
	}

	public void setNotAllowed(String notAllowed) {
		this.notAllowed = notAllowed;
	}

	public int getMinHistory() {
		return minHistory;
	}

	public void setMinHistory(int minHistory) {
		this.minHistory = minHistory;
	}

	public boolean isMinLengthFlag() {
		return minLengthFlag;
	}

	public void setMinLengthFlag(boolean minLengthFlag) {
		this.minLengthFlag = minLengthFlag;
	}

	public boolean isMaxLengthFlag() {
		return maxLengthFlag;
	}

	public void setMaxLengthFlag(boolean maxLengthFlag) {
		this.maxLengthFlag = maxLengthFlag;
	}

	public boolean isMinUpperLengthFlag() {
		return minUpperLengthFlag;
	}

	public void setMinUpperLengthFlag(boolean minUpperLengthFlag) {
		this.minUpperLengthFlag = minUpperLengthFlag;
	}

	public boolean isMinLowerLengthFlag() {
		return minLowerLengthFlag;
	}

	public void setMinLowerLengthFlag(boolean minLowerLengthFlag) {
		this.minLowerLengthFlag = minLowerLengthFlag;
	}

	public boolean isMinSpecialFlag() {
		return minSpecialFlag;
	}

	public void setMinSpecialFlag(boolean minSpecialFlag) {
		this.minSpecialFlag = minSpecialFlag;
	}

	public boolean isNotAllowedFlag() {
		return notAllowedFlag;
	}

	public void setNotAllowedFlag(boolean notAllowedFlag) {
		this.notAllowedFlag = notAllowedFlag;
	}

	public boolean isMinHistoryFlag() {
		return minHistoryFlag;
	}

	public void setMinHistoryFlag(boolean minHistoryFlag) {
		this.minHistoryFlag = minHistoryFlag;
	}

	private PasswordPolicy(IPasswordPolicyPersistence persistence) {
		persistence.loadPasswordPolicy(this);
		System.out.println("policy" + this.maxLength);
		policies();

	}

	public static PasswordPolicy getInstance(IPasswordPolicyPersistence persistence) {
		if (null == uniqueInstance) {
			uniqueInstance = new PasswordPolicy(persistence);
		}
		return uniqueInstance;
	}

	public void policies() {
		policies = "";
		if (this.minLengthFlag) {
			policies = policies + "Minimum length:" + String.valueOf(this.minLength) + "\n";
		}
		if (this.maxLengthFlag) {
			policies = policies + "Maximum length:" + String.valueOf(this.maxLength) + "\n";
		}
		if (this.minUpperLengthFlag) {
			policies = policies + "Minimum Uppercase characters:" + String.valueOf(this.minUpperLength) + "\n";
		}
		if (this.minLowerLengthFlag) {
			policies = policies + "Minimum Lowercase characters:" + String.valueOf(this.minLowerLength) + "\n";
		}
		if (this.minSpecialFlag) {
			policies = policies + "Minimum special characters:" + String.valueOf(this.minSpecial) + "\n";
		}
		if (this.notAllowedFlag) {
			policies = policies + "Notallowed characters:" + String.valueOf(this.minLength) + "\n";
		}

	}

}
