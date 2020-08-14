package CSCI5308.GroupFormationTool.ResponseSurvey;

public interface ResponseFactory {
	public Response createResponse(Object response, long qID, String type, long surveyID, String bannerID);
}
