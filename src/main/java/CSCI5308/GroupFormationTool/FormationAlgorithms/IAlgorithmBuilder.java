package CSCI5308.GroupFormationTool.FormationAlgorithms;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;

public interface IAlgorithmBuilder {

	public void setUserMCCOResponses(Map<String, Map<Long, String>> userMCCOResponses);

	public void setUserNumericResponses(Map<String, Map<Long, Integer>> userNumericResponses);

	public void setUserFreeTextResponses(Map<String, Map<Long, String>> userFreeTextResponses);

	public void setUserMCCMResponses(Map<String, Map<Long, List<String>>> userMCCMResponses);

	public void setAlgorithm(String algorithm);

	public void setQuestionPolicies(List<IGroupFormationPolicies> questionPolicies);

	public IGroupFormationAlgorithm build();
}
