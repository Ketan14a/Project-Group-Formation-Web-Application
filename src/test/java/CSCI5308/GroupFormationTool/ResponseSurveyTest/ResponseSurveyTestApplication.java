package CSCI5308.GroupFormationTool.ResponseSurveyTest;


public class ResponseSurveyTestApplication
{
	ResponseTestFactory testNumericFactory = new NumericResponseTestFactory();
	ResponseTest testNumericObj = testNumericFactory.create();
	
	ResponseTestFactory testFreeTextFactory = new FreeTextResponseTestFactory();
	ResponseTest testFreeTextObj = testFreeTextFactory.create();
	
	ResponseTestFactory testMCCOFactory = new MCCOResponseTestFactory();
	ResponseTest testMCCOObj = testMCCOFactory.create();
	
	ResponseTestFactory testMCCMFactory = new MCCMResponseFactoryTest();
	ResponseTest testMCCMObj = testMCCMFactory.create();

}
