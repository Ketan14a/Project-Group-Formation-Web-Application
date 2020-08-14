package CSCI5308.GroupFormationTool.GroupFormation;

public interface IGroupFormationPolicies {
	public int getGroupCount();

	public void setGroupCount(int groupCount);

	public long getSurveyID();

	public void setSurveyID(long surveyID);

	public long getQuestionID();

	public void setQuestionID(long questionID);

	public String getPolicyType();

	public void setPolicyType(String similarOrGreaterPolicy);

	public int getValue();

	public void setValue(int value);
}
