package CSCI5308.GroupFormationTool.ResponseSurvey;

public interface IResponseStoragePersistence {
	public boolean storeNumericResponse(NumericResponse numericResponse);

	public boolean storeFreeTextResponse(FreeTextResponse freeTextResponse);

	public boolean storeMCCOResponses(MCCOResponse mccoResponse);

	public boolean storeMCCMResponses(MCCMResponse mccmResponse);

}
