package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationStudentResponsesPersistence;

public class GroupFormationStudentResponsesDBMock implements IGroupFormationStudentResponsesPersistence{
	private static final String MOCK = "Mock";
	private static final Long ONEL =1l;
	private static final String USERID ="B000000";
	
	@Override
	public Map<String, Map<Long, String>> loadMCCOResponses(long surveyId) {
		// TODO Auto-generated method stub
		Map<String, Map<Long, String>> userMCCOResponses =  new HashMap<String, Map<Long, String>>();
		Map<Long, String> MCCOresponses = new HashMap<>();
		MCCOresponses.put(ONEL, MOCK);
		userMCCOResponses.put(USERID, MCCOresponses);
		return userMCCOResponses;
	}

	@Override
	public Map<String, Map<Long, List<String>>> loadMCCMResponses(long surveyId) {
		Map<String, Map<Long, List<String>>> userMCCMResponses =  new HashMap<>();
		Map<Long, List<String>> MCCMresponses = new HashMap<>();
		List<String> responsesList = new ArrayList<>();
		responsesList.add(MOCK);
		MCCMresponses.put(ONEL, responsesList);
		userMCCMResponses.put(USERID, MCCMresponses);
		return userMCCMResponses;
	}

	@Override
	public Map<String, Map<Long, Integer>> loadNumericalResponses(long surveyId) {
		Map<String, Map<Long, Integer>> userNumericalResponses =  new HashMap<String, Map<Long, Integer>>();
		Map<Long, Integer> NumericalResponses = new HashMap<>();
		NumericalResponses.put(ONEL, 1);
		userNumericalResponses.put(USERID, NumericalResponses);
		return userNumericalResponses;
	}

	@Override
	public Map<String, Map<Long, String>> loadFreeTextResponses(long surveyId) {
		Map<String, Map<Long, String>> userFreeTextResponses =  new HashMap<String, Map<Long, String>>();
		Map<Long, String> FreeTextresponses = new HashMap<>();
		FreeTextresponses.put(ONEL, MOCK);
		userFreeTextResponses.put(USERID, FreeTextresponses);
		return userFreeTextResponses;
	}

}
