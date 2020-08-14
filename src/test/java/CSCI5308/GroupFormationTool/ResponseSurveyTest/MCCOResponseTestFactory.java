package CSCI5308.GroupFormationTool.ResponseSurveyTest;

public class MCCOResponseTestFactory implements ResponseTestFactory
{

	@Override
	public ResponseTest create()
	{
		return new MCCOResponseTest();
	}

}
