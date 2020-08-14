package CSCI5308.GroupFormationTool.ResponseSurveyTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseStoragePersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.MCCMResponse;
import CSCI5308.GroupFormationTool.ResponseSurvey.MCCMResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseStorageHandler;

public class MCCMResponseTest implements ResponseTest
{

	@Override
	@Test
	public void ResponseBehaviourTest()
	{
		ResponseFactory testObjFactory = new MCCMResponseFactory();
		List<Long> responseList = new ArrayList<Long>();
		responseList.add(Long.MAX_VALUE);
		responseList.add(Long.MIN_VALUE);
		IResponseStoragePersistence iSTP = new ResponseStorageHandler();
		MCCMResponse mock = (MCCMResponse) testObjFactory.createResponse(responseList, 1, "MCCM", 1, "B00334455");
		assertTrue(mock.getBannerID()=="B00334455");
		assertTrue(mock.getQuestionID()==1);
		assertTrue(mock.getSurveyID()==1);
		assertTrue(mock.getType()=="MCCM");
		assertTrue(mock.storeResponse(iSTP));
	}

}
