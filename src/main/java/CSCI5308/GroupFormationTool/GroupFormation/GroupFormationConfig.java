package CSCI5308.GroupFormationTool.GroupFormation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupFormationConfig {
	private static Logger logger = LoggerFactory.getLogger(GroupFormationConfig.class);
	private static GroupFormationConfig singletonInstance = null;

	private IGroupFormationPolicyPersistence groupFormationPolicyDB;
	private IGroupFormationStudentResponsesPersistence groupFormationResponseDb;

	private GroupFormationConfig() {
		groupFormationPolicyDB = new GroupFormationPolicyDB();
		setGroupFormationResponseDb(new GroupFormationResponsesDB());
	}

	public static GroupFormationConfig instance() {

		if (null == singletonInstance) {
			singletonInstance = new GroupFormationConfig();
		}else{
			logger.info("No Singelton instance formed");
		}
		return singletonInstance;
	}

	public IGroupFormationPolicyPersistence getGroupFormationPolicyDB() {
		return groupFormationPolicyDB;
	}

	public void setGroupFormationPolicyDB(IGroupFormationPolicyPersistence groupFormationPolicyDB) {
		this.groupFormationPolicyDB = groupFormationPolicyDB;
	}

	public IGroupFormationStudentResponsesPersistence getGroupFormationResponseDb() {
		return groupFormationResponseDb;
	}

	public void setGroupFormationResponseDb(IGroupFormationStudentResponsesPersistence groupFormResponseDb) {
		this.groupFormationResponseDb = groupFormResponseDb;
	}
}
