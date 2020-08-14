package CSCI5308.GroupFormationTool.ResponseSurveyTest;

public class FreeTextResponseTestFactory implements ResponseTestFactory
{

	@Override
	public ResponseTest create() 
	{
		return new FreeTextResponseTest();
	}

}
