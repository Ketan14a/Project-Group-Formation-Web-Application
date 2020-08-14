package CSCI5308.GroupFormationTool.Survey;

public interface ISurveyPersistence {
	public long createSurvey(Survey survey);

	public boolean createSurveyQuestion(Survey survey);

	public boolean updateSurveyQuestion(Survey survey);

	public boolean publishSurvey(Survey survey);

}
