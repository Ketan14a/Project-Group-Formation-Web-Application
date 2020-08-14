package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;

public class SurveyDBMock implements ISurveyPersistence {

	@Override
	public long createSurvey(Survey survey) {
		return 1;
	}

	@Override
	public boolean createSurveyQuestion(Survey survey) {
		return true;
	}

	@Override
	public boolean updateSurveyQuestion(Survey survey) {
		return true;
	}

	@Override
	public boolean publishSurvey(Survey survey) {
		return true;
	}

}
