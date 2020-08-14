package CSCI5308.GroupFormationTool.ResponseSurveyTest;

public class MCCMResponseFactoryTest implements ResponseTestFactory {

	@Override
	public ResponseTest create()
	{
		return new MCCMResponseTest();
	}

}
