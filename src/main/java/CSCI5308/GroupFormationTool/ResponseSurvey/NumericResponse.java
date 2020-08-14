package CSCI5308.GroupFormationTool.ResponseSurvey;

public class NumericResponse implements Response {
	private long questionID;
	private String type;
	private Integer response;
	private long surveyID;
	private String bannerID;

	public NumericResponse(Object o, long qID, String responsetype, long d_surveyID, String d_bannerid) {
		response = (Integer) o;
		questionID = qID;
		type = responsetype;
		surveyID = d_surveyID;
		bannerID = d_bannerid;
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

	public Integer getResponse() {
		return response;
	}

	public void setResponse(Integer response) {
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
