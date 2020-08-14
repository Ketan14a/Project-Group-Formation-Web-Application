package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessControlConfig
{
	private static AccessControlConfig uniqueInstance = null;
	private static Logger logger = LoggerFactory.getLogger(AccessControlConfig.class);
	private IUserPersistence userDB;
	private INotificationConfig defaultNotificationConfig;
	private IUserNotifications userNotification;
	private IRolePersistence roleDB;

	private AccessControlConfig() {
		userDB = new UserDB();
		defaultNotificationConfig = new DefaultNotificationConfig();
		userNotification = new UserNotification();
		roleDB = new RoleDB();
		logger.info("Assigning The configuration setting to Databases");
	}

	public static AccessControlConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new AccessControlConfig();
		}

		logger.info("Creating a unique instance of all the configurations set up");
		return uniqueInstance;
	}

	public IUserPersistence getUserDB() {
		return userDB;
	}

	public void setUserDB(IUserPersistence userDB) {
		this.userDB = userDB;
	}

	public INotificationConfig getDefaultNotificationConfig() {
		return defaultNotificationConfig;
	}

	public void setDefaultNotificationConfig(INotificationConfig defaultNotificationConfig) {
		this.defaultNotificationConfig = defaultNotificationConfig;
	}

	public IUserNotifications getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(IUserNotifications userNotification) {
		this.userNotification = userNotification;
	}

	public IRolePersistence getRoleDB() {
		return roleDB;
	}

	public void setRoleDB(IRolePersistence roleDB) {
		this.roleDB = roleDB;
	}

}
