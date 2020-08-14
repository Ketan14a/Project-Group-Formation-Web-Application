package CSCI5308.GroupFormationTool.ResponseSurveyTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseStoragePersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.NumericResponse;
import CSCI5308.GroupFormationTool.ResponseSurvey.NumericResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseFactory;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseStorageHandler;

public class NumericResponseTest implements ResponseTest
{
	@Override
	@Test
	public void ResponseBehaviourTest() 
	{
		ResponseFactory testObjFactory = new NumericResponseFactory();
		NumericResponse mock = (NumericResponse) testObjFactory.createResponse(4,1,"NUMERIC",1,"B00334455");
		IResponseStoragePersistence iSTP = new ResponseStorageHandler();
		assertTrue(mock.getBannerID()=="B00334455");
		assertTrue(mock.getQuestionID()==1);
		assertTrue(mock.getResponse()==4);
		assertTrue(mock.getSurveyID()==1);
		assertTrue(mock.getType()=="NUMERIC");
		assertTrue(mock.storeResponse(iSTP));
	
	}

}
