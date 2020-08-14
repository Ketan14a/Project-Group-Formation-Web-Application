package CSCI5308.GroupFormationTool.ResponseSurvey;

public class MCCMResponseFactory implements ResponseFactory {

	@Override
	public Response createResponse(Object response, long qID, String type, long surveyID, String bannerID) {
		return new MCCMResponse(response, qID, type, surveyID, bannerID);
	}

}
