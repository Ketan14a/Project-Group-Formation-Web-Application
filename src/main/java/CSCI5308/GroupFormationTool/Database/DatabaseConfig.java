package CSCI5308.GroupFormationTool.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConfig {
	private static DatabaseConfig uniqueInstance = null;

	private IDatabaseConfiguration databaseConfiguration;

	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	private DatabaseConfig() {
		databaseConfiguration = new DefaultDatabaseConfiguration();
	}

	public static DatabaseConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new DatabaseConfig();
		} else {
			logger.error("The Connection Manager instance is not unique");
		}

		return uniqueInstance;
	}

	public IDatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

}
