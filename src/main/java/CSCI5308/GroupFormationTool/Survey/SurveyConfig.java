package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Questions.QuestionDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyConfig {
	private static Logger logger = LoggerFactory.getLogger(SurveyConfig.class);
	private static SurveyConfig uniqueInstance = null;
	private ISurveyPersistence surveyDB;

	private SurveyConfig() {
		this.surveyDB = new SurveyDB();
	}

	public static SurveyConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new SurveyConfig();
		}else{
			logger.error("No unique instance created");
		}
		return uniqueInstance;
	}

	public ISurveyPersistence getSurveyDB() {
		return surveyDB;
	}

	public void setSurveyDB(ISurveyPersistence surveyDB) {
		this.surveyDB = surveyDB;
	}
}
