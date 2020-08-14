package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import CSCI5308.GroupFormationTool.Questions.QuestionForm;

public class GroupFormationPolicies implements IGroupFormationPolicies {
	long surveyID;
	long questionID;
	String policyType;
	int value;
	int groupCount;

	public GroupFormationPolicies() {
		
	}
	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public GroupFormationPolicies(long surveyId, int groupCount, QuestionForm question) {
		this.surveyID = surveyId;
		this.questionID = question.getId();
		this.policyType = question.getPolicyType();
		this.value = question.getValue();
		this.groupCount = groupCount;
	}

	public long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String similarOrGreaterPolicy) {
		this.policyType = similarOrGreaterPolicy;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void createPolicy(List<IGroupFormationPolicies> listOfGroupFormationPolicies,
			IGroupFormationPolicyPersistence groupFormationPolicyDB) {
		groupFormationPolicyDB.createSurveyPolicies(listOfGroupFormationPolicies);
	}
}
