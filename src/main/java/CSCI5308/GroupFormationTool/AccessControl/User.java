package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IUserAuthHistoryRelationship;
import CSCI5308.GroupFormationTool.Security.IUserAuthHistoryRelationshipPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;
import CSCI5308.GroupFormationTool.Security.UserAuthHistoryRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static Logger logger = LoggerFactory.getLogger(User.class);
	private long id;
	private String password;
	private String bannerID;
	private String firstName;
	private String lastName;
	private String email;
	private IUserAuthHistoryRelationship userAuthHistory;

	public boolean addPasswordHistory(IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB) {
		logger.info("Adding the Password History having all the previous passwords");
		return userAuthHistory.addPasswordHistory(this, userAuthHistoryRelationshipDB);
	}

	public boolean isNotPreviousPassword(String password, PasswordPolicy uniqueInstance,
			IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB) {
		List<String> prevPasswords = userAuthHistory.getPreviousPasswords(this, uniqueInstance.getMinHistory(),
				userAuthHistoryRelationshipDB);
		logger.info("Retrieving the previous passwords from the Database");
		IPasswordEncryption passwordEncryption = SecurityConfig.instance().getPasswordEncryption();

		for (String pp : prevPasswords) {
			if (passwordEncryption.matches(password, pp)) {
				logger.info("Password matched the previous passwords. So it cannot be accessed");
				return false;
			}
		}
		return true;
	}

	public static boolean isPasswordValid(String password, PasswordPolicy uniqueInstance) {

		String temporaryPasswordVariable = "";
		int count = 0;
		if (uniqueInstance.isMinLengthFlag() && password.length() < uniqueInstance.getMinLength()) {
			logger.info("Checking the validation for password minimum length");
			return false;
		}
		if (uniqueInstance.isMaxLengthFlag() && password.length() > uniqueInstance.getMaxLength()) {
			logger.info("Checking the validation for password maximum length");
			return false;
		}
		if (uniqueInstance.isMinUpperLengthFlag()) {
			temporaryPasswordVariable = password;
			count = password.length() - temporaryPasswordVariable.replaceAll("[A-Z]", "").length();

			if (count < uniqueInstance.getMinUpperLength()) {
				logger.info("Checking if there is minimum one capital letter in the password");

				return false;
			}
		}
		if (uniqueInstance.isMinLowerLengthFlag()) {
			temporaryPasswordVariable = password;
			count = password.length() - temporaryPasswordVariable.replaceAll("[a-z]", "").length();

			if (count < uniqueInstance.getMinLowerLength()) {
				logger.info("Checking if there is minimum one lowercase letter in the password");

				return false;
			}
		}
		if (uniqueInstance.isMinSpecialFlag()) {
			temporaryPasswordVariable = password;
			count = password.length() - temporaryPasswordVariable.replaceAll("[^a-z|^A-Z]", "").length();

			if (count < uniqueInstance.getMinSpecial()) {
				return false;
			}
		}

		if (uniqueInstance.isNotAllowedFlag()) {
			for (char a : uniqueInstance.getNotAllowed().toCharArray()) {
				if (password.contains(String.valueOf(a))) {
					logger.info("The character which are not allowed in the password");
					return false;
				}
			}
		}
		return true;
	}

	public User() {
		setDefaults();
	}

	public User(long id, IUserPersistence persistence) {
		setDefaults();
		persistence.loadUserByID(id, this);
	}

	public User(String bannerID, IUserPersistence persistence) {
		setDefaults();
		persistence.loadUserByBannerID(bannerID, this);
		logger.info("Setting the values to default information and loading the user by Banner ID");
	}

	public void setDefaults() {
		id = -1;
		password = "";
		bannerID = "";
		firstName = "";
		lastName = "";
		email = "";
		userAuthHistory = new UserAuthHistoryRelationship();
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getID() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

	public String getBannerID() {
		return bannerID;
	}

	public String getBanner() {
		return bannerID;
	}

	public void setFirstName(String name) {
		firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String name) {
		lastName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public boolean isValidUser() {
		return id != -1;
	}

	public boolean createUser(IUserPersistence userDB, IPasswordEncryption passwordEncryption,
			IUserNotifications notification) {
		String rawPassword = password;
		this.password = passwordEncryption.encryptPassword(this.password);
		boolean success = userDB.createUser(this);
		if (success && (null != notification)) {
			notification.sendUserLoginCredentials(this, rawPassword);
		}
		logger.info("Creating the user is successful");
		return success;
	}

	public boolean updateUser(IUserPersistence userDB) {
		return userDB.updateUser(this);
	}

	private static boolean isStringNullOrEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	public static boolean isBannerIDValid(String bannerID) {
		return !isStringNullOrEmpty(bannerID);
	}

	public static boolean isFirstNameValid(String name) {
		return !isStringNullOrEmpty(name);
	}

	public static boolean isLastNameValid(String name) {
		return !isStringNullOrEmpty(name);
	}

	public static boolean isEmailValid(String email) {

		if (isStringNullOrEmpty(email)) {
			return false;
		}
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		logger.info("Validation of email successful");
		return matcher.matches();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		if (bannerID != null) {
			result = result + bannerID.hashCode();
		}
		logger.info("Generating hasdcode with the associated banner ID");
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bannerID == null) {
			if (other.bannerID != null)
				return false;
		} else if (!bannerID.equals(other.bannerID))
			return false;
		logger.info("Checking for no duplicate Banner ID");
		return true;
	}

}