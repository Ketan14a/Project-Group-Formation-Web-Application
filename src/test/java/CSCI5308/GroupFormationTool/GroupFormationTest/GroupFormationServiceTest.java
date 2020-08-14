package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.FormationAlgorithms.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.FormationAlgorithms.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicies;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationStudentResponsesPersistence;

public class GroupFormationServiceTest  {

	@Test
	public void createGroupstest() {
		int surveyID =1;
		List<IGroupFormationPolicies> questionPolicies = new ArrayList<>();
		IGroupFormationPolicies groupFormationPolicy = new GroupFormationPolicies();
		groupFormationPolicy.setGroupCount(2);
		groupFormationPolicy.setPolicyType("similiar");
		groupFormationPolicy.setQuestionID(1l);
		questionPolicies.add(groupFormationPolicy);
		IGroupFormationStudentResponsesPersistence groupFormResponseDb = new GroupFormationStudentResponsesDBMock();
		Map<String, Map<Long, String>> userMCCOResponses = groupFormResponseDb.loadMCCOResponses(surveyID);
		Map<String, Map<Long, String>> userFreeTextResponses =groupFormResponseDb.loadFreeTextResponses(surveyID);
		Map<String, Map<Long, Integer>> userNumericalResponses =groupFormResponseDb.loadNumericalResponses(surveyID);
		Map<String, Map<Long, List<String>>> userMCCMResponses =groupFormResponseDb.loadMCCMResponses(surveyID);
		
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setQuestionPolicies(questionPolicies);
		builder.setAlgorithm("random");
		builder.setUserMCCOResponses(userMCCOResponses);
		builder.setUserFreeTextResponses(userFreeTextResponses);
		builder.setUserNumericResponses(userNumericalResponses);
		builder.setUserMCCMResponses(userMCCMResponses);
		 
		IGroupFormationAlgorithm algorithm = builder.build();
		List<List<String>> groupList = algorithm.createGroups();
		assertNotNull(groupList);
		
	}

}
