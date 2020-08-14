package CSCI5308.GroupFormationTool.FormationAlgorithms;

import java.util.List;
import java.util.Map;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AlgorithmBuilder implements IAlgorithmBuilder{

	private Logger logger = LoggerFactory.getLogger(AlgorithmBuilder.class);
	private static final String SCORE = "scoreBased";
	
	List <IGroupFormationPolicies> questionPolicies;
	String algorithm="";

	Map<String,Map<Long,String>> userMCCOResponses;
	Map<String,Map<Long,Integer>> userNumericResponses;
	Map<String,Map<Long,String>> userFreeTextResponses;
	Map<String,Map<Long,List<String>>> userMCCMResponses;
	
	
	public IGroupFormationAlgorithm build(){
		logger.info("Building a group formation based algorithm");
		if(null!=this.algorithm && this.algorithm.equalsIgnoreCase(SCORE)) {
			return new GroupFormationScoreBasedAlgorithm(this.questionPolicies,this.userMCCOResponses,this.userNumericResponses,this.userFreeTextResponses,this.userMCCMResponses);
		}else {
			return new GroupFormationScoreBasedAlgorithm(this.questionPolicies,this.userMCCOResponses,this.userNumericResponses,this.userFreeTextResponses,this.userMCCMResponses);

		}
	}

	public Map<String, Map<Long, String>> getUserMCCOResponses() {
		return userMCCOResponses;
	}

	public void setUserMCCOResponses(Map<String, Map<Long, String>> userMCCOResponses) {
		this.userMCCOResponses = userMCCOResponses;
	}

	public Map<String, Map<Long, Integer>> getUserNumericResponses() {
		return userNumericResponses;
	}

	public void setUserNumericResponses(Map<String, Map<Long, Integer>> userNumericResponses) {
		this.userNumericResponses = userNumericResponses;
	}

	public Map<String, Map<Long, String>> getUserFreeTextResponses() {
		return userFreeTextResponses;
	}

	public void setUserFreeTextResponses(Map<String, Map<Long, String>> userFreeTextResponses) {
		this.userFreeTextResponses = userFreeTextResponses;
	}

	public Map<String, Map<Long, List<String>>> getUserMCCMResponses() {
		return userMCCMResponses;
	}

	public void setUserMCCMResponses(Map<String, Map<Long, List<String>>> userMCCMResponses) {
		this.userMCCMResponses = userMCCMResponses;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public List<IGroupFormationPolicies> getQuestionPolicies() {
		return questionPolicies;
	}

	public void setQuestionPolicies(List<IGroupFormationPolicies> questionPolicies) {
		this.questionPolicies = questionPolicies;
	}

	public AlgorithmBuilder() {
	}
	

}
