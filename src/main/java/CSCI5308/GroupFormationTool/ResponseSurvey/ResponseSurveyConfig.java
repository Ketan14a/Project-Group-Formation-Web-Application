package CSCI5308.GroupFormationTool.ResponseSurvey;

public class ResponseSurveyConfig {
	private static ResponseSurveyConfig uniqueInstance = null;

	private IResponseSurveyPersistence responseSurveyDB;
	private IResponseStoragePersistence responseStorageDB;

	private ResponseSurveyConfig() {
		responseSurveyDB = new ResponseSurveyDB();
		responseStorageDB = new ResponseStorageHandler();
	}

	public static ResponseSurveyConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new ResponseSurveyConfig();
		}

		return uniqueInstance;
	}

	public IResponseSurveyPersistence getResponseSurveyDB() {
		return responseSurveyDB;
	}

	public void setResponseSurveyDB(IResponseSurveyPersistence responseSurveyDB) {
		this.responseSurveyDB = responseSurveyDB;
	}

	public IResponseStoragePersistence getResponseStorageDB() {
		return responseStorageDB;
	}

	public void setResponseStorageDB(IResponseStoragePersistence responseStorageDB) {
		this.responseStorageDB = responseStorageDB;
	}

}
