package CSCI5308.GroupFormationTool.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager uniqueInstance = null;

	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	public ConnectionManager() {
		IDatabaseConfiguration config = DatabaseConfig.instance().getDatabaseConfiguration();
		dbURL = config.getDatabaseURL();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
	}

	public static ConnectionManager instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new ConnectionManager();
		} else {
			logger.error("The Connection Manager instance is not unique");
		}
		return uniqueInstance;
	}

	public Connection getDBConnection() throws SQLException {
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}

}
