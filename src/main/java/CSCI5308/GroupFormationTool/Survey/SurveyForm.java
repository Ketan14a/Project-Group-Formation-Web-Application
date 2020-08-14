package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.Questions.QuestionForm;

public class SurveyForm {
	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	String surveyName;
	List<QuestionForm> surveyQuestions;
	
	public List<QuestionForm> getSurveyQuestions() {
		return surveyQuestions;
	}

	public void setSurveyQuestions(List<QuestionForm> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

}
