package CSCI5308.GroupFormationTool.ResponseSurveyTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseStoragePersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.MCCOResponse;
import CSCI5308.GroupFormationTool.ResponseSurvey.MCCOResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseStorageHandler;

public class MCCOResponseTest implements ResponseTest 
{
	@Override
	@Test
	public void ResponseBehaviourTest()
	{
		ResponseFactory testObjFactory = new MCCOResponseFactory();
		MCCOResponse mock = (MCCOResponse) testObjFactory.createResponse(Long.MAX_VALUE, 1, "MCCO", 1, "B00334455");
		IResponseStoragePersistence iSTP = new ResponseStorageHandler();
		assertTrue(mock.getBannerID()=="B00334455");
		assertTrue(mock.getQuestionID()==1);
		assertTrue(mock.getResponse()==Long.MAX_VALUE);
		assertTrue(mock.getSurveyID()==1);
		assertTrue(mock.getType()=="MCCO");
		assertTrue(mock.storeResponse(iSTP));

	}

}
