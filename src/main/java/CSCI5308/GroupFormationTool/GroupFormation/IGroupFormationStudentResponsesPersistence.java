package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;

public interface IGroupFormationStudentResponsesPersistence {
	public Map<String, Map<Long, String>> loadMCCOResponses(long surveyId);

	public Map<String, Map<Long, List<String>>> loadMCCMResponses(long surveyId);

	public Map<String, Map<Long, Integer>> loadNumericalResponses(long surveyId);

	public Map<String, Map<Long, String>> loadFreeTextResponses(long surveyId);
}
