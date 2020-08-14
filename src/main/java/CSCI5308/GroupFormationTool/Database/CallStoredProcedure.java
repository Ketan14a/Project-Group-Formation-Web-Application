package CSCI5308.GroupFormationTool.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CallStoredProcedure {
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	private Logger logger = LoggerFactory.getLogger(CallStoredProcedure.class);

	public CallStoredProcedure(String storedProcedureName) throws SQLException {
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}

	private void createStatement() throws SQLException {
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}

	private void openConnection() throws SQLException {
		connection = ConnectionManager.instance().getDBConnection();
	}

	public void cleanup() {
		try {
			if (null != statement) {
				statement.close();
				logger.info("Statement is closed");
			}
			if (null != connection) {
				if (!connection.isClosed()) {
					connection.close();
					logger.info("Connection is closed");
				}
			}
		} catch (Exception exception) {
			logger.info("Error Message : {}", exception.getMessage());
		}
	}

	public void setParameter(int paramIndex, String value) throws SQLException {
		statement.setString(paramIndex, value);
	}

	public void setParameter(int paramIndex, Date date) throws SQLException {
		statement.setDate(paramIndex, date);
	}

	public void registerOutputParameterString(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}

	public void setParameter(int paramIndex, long value) throws SQLException {
		statement.setLong(paramIndex, value);
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}

	public ResultSet executeWithResults() throws SQLException {
		if (statement.execute()) {
			logger.info("returning the ResultSet");
			return statement.getResultSet();
		}
		return null;
	}

	public void execute() throws SQLException {
		statement.execute();
	}

	public long getOutputParameter(int index) throws SQLException {
		return statement.getLong(index);

	}

	public void setParameter(int paramIndex, boolean published) throws SQLException {
		statement.setBoolean(paramIndex, published);
	}
}
