package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;

public class Survey {

	String surveyName;
	long courseID;
	int creatorID;
	boolean published;
	List<Long> surveyQuestions = new ArrayList<>();
	long surveyID;

	public Survey() {

	}

	public Survey(String surveyName, long courseID, int creatorID, ISurveyPersistence surveyDB) {
		this.surveyName = surveyName;
		this.courseID = courseID;
		this.creatorID = creatorID;
		this.published = false;
		this.surveyID = surveyDB.createSurvey(this);
	}

	public long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public List<Long> getSurveyQuestions() {
		return surveyQuestions;
	}

	public void setSurveyQuestions(List<Long> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

	public void createSurveyQuestions(ISurveyPersistence surveyDB) {
		surveyDB.createSurveyQuestion(this);
	}

	public void updateSurveyQuestions(ISurveyPersistence surveyDB) {
		surveyDB.updateSurveyQuestion(this);
	}

	public void publish(ISurveyPersistence surveyDB) {
		surveyDB.publishSurvey(this);
	}
}
