package CSCI5308.GroupFormationTool.ResponseSurvey;

public class NumericResponseFactory implements ResponseFactory {

	@Override
	public Response createResponse(Object response, long qID, String type, long surveyID, String bannerID) {
		return new NumericResponse(response, qID, type, surveyID, bannerID);
	}

}
