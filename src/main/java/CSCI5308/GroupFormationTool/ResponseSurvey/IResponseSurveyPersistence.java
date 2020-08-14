package CSCI5308.GroupFormationTool.ResponseSurvey;
import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;

import java.util.List;
import java.util.Map;

public interface IResponseSurveyPersistence 
{
	public void loadSurveyByCourseID(long courseID, ResponseSurvey responseSurvey);
	public Map<Integer, String> parseRequest(Map<String,String> questionMapper);
	public List<AbstractQuestion> loadSurveyQuestionsBySurveyID(long SurveyID);
	public Boolean storeResponsesInDB(Map<Integer,String> responseMapper, List<AbstractQuestion> questionList);
}
