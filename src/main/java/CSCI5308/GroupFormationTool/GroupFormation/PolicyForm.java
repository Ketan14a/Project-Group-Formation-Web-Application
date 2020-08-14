package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;

import CSCI5308.GroupFormationTool.Questions.QuestionForm;

public class PolicyForm {
	List<QuestionForm> surveyQuestions;
	int groupCount;

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public List<QuestionForm> getSurveyQuestions() {
		return surveyQuestions;
	}

	public void setSurveyQuestions(List<QuestionForm> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
}
