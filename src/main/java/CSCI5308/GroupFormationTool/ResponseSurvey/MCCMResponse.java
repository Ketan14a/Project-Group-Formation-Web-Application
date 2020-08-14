package CSCI5308.GroupFormationTool.ResponseSurvey;

import java.util.List;

public class MCCMResponse implements Response {
	private long questionID;
	private String type;
	private List<Long> responses;
	private long surveyID;
	private String bannerID;

	public MCCMResponse(Object o, long qID, String responseType, long d_surveyID, String d_bannerID) {
		responses = (List<Long>) o;
		questionID = qID;
		type = responseType;
		surveyID = d_surveyID;
		bannerID = d_bannerID;
	}

	@Override
	public boolean storeResponse(IResponseStoragePersistence irp) {
		responses = this.getResponses();
		questionID = this.getQuestionID();
		type = this.getType();
		surveyID = this.getSurveyID();
		bannerID = this.getBannerID();
		return true;

	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Long> getResponses() {
		return responses;
	}

	public void setResponses(List<Long> responses) {
		this.responses = responses;
	}

	public long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	public String getBannerID() {
		return bannerID;
	}

	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

}
