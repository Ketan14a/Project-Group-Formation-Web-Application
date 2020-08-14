package CSCI5308.GroupFormationTool.ResponseSurvey;

public class FreeTextResponseFactory implements ResponseFactory {

	@Override
	public Response createResponse(Object response, long qID, String type, long surveyID, String bannerID) {
		return new FreeTextResponse(response, qID, type, surveyID, bannerID);
	}

}
