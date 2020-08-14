package CSCI5308.GroupFormationTool.ResponseSurvey;

public class MCCOResponseFactory implements ResponseFactory {

	@Override
	public Response createResponse(Object response, long qID, String type, long surveyID, String bannerID) {
		return new MCCOResponse(response, qID, type, surveyID, bannerID);
	}

}
