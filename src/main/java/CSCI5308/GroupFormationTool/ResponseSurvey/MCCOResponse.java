package CSCI5308.GroupFormationTool.ResponseSurvey;

public class MCCOResponse implements Response {
	private long questionID;
	private String type;
	private Long response;
	private long surveyID;
	private String bannerID;

	public MCCOResponse(Object o, long qID, String responseType, long d_surveyID, String d_bannerID) {
		response = (Long) o;
		questionID = qID;
		type = responseType;
		surveyID = d_surveyID;
		bannerID = d_bannerID;
	}

	@Override
	public boolean storeResponse(IResponseStoragePersistence irp) {
		response = this.getResponse();
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

	public void setType(String type) {
		this.type = type;
	}

	public Long getResponse() {
		return response;
	}

	public void setResponse(Long response) {
		this.response = response;
	}

}
