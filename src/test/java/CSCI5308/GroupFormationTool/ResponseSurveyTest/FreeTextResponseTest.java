package CSCI5308.GroupFormationTool.ResponseSurveyTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.ResponseSurvey.FreeTextResponse;
import CSCI5308.GroupFormationTool.ResponseSurvey.FreeTextResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseStoragePersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseStorageHandler;

public class FreeTextResponseTest implements ResponseTest
{
	@Override
	@Test
	public void ResponseBehaviourTest()
	{
		ResponseFactory testObjFactory = new FreeTextResponseFactory();
		FreeTextResponse mock = (FreeTextResponse) testObjFactory.createResponse("ABCDEFGHIJKL", 1, "FREETEXT", 1, "B00334455");
		IResponseStoragePersistence iSTP = new ResponseStorageHandler();
		assertTrue(mock.getBannerID()=="B00334455");
		assertTrue(mock.getQuestionID()==1);
		assertTrue(mock.getResponse()=="ABCDEFGHIJKL");
		assertTrue(mock.getSurveyID()==1);
		assertTrue(mock.getType()=="FREETEXT");
		assertTrue(mock.storeResponse(iSTP));
	}

}
