package CSCI5308.GroupFormationTool.ResponseSurveyTest;

public class NumericResponseTestFactory implements ResponseTestFactory
{

	@Override
	public ResponseTest create() 
	{
		return new NumericResponseTest();
	}

}
