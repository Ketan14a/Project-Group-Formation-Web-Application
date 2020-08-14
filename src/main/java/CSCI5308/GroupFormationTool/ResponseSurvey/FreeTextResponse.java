package CSCI5308.GroupFormationTool.ResponseSurvey;

public class FreeTextResponse implements Response {
	private long questionID;
	private String type;
	private String response;
	private long surveyID;
	private String bannerID;

	public FreeTextResponse(Object o, long qID, String responseType, long d_surveyID, String d_bannerID) {
		response = (String) o;
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

	public void setType(String type) {
		this.type = type;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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
