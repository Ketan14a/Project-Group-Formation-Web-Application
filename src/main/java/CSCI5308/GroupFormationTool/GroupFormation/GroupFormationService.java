package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.FormationAlgorithms.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.FormationAlgorithms.IGroupFormationAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupFormationService implements IGroupFormationService {

	private Logger logger = LoggerFactory.getLogger(GroupFormationService.class);
	private static final String ALGORITHM = System.getenv("ALGORITHM");

	public Map<Integer, Map<User, List<String>>> createGroups(List<IGroupFormationPolicies> questionPolicies,
			IGroupFormationStudentResponsesPersistence groupFormResponseDb, IUserPersistence userDb) {

		Long surveyID = questionPolicies.get(0).getSurveyID();
		Map<String, Map<Long, String>> userMCCOResponses = groupFormResponseDb.loadMCCOResponses(surveyID);
		Map<String, Map<Long, String>> userFreeTextResponses = groupFormResponseDb.loadFreeTextResponses(surveyID);
		Map<String, Map<Long, Integer>> userNumericalResponses = groupFormResponseDb.loadNumericalResponses(surveyID);
		Map<String, Map<Long, List<String>>> userMCCMResponses = groupFormResponseDb.loadMCCMResponses(surveyID);

		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setQuestionPolicies(questionPolicies);
		builder.setAlgorithm(ALGORITHM);
		builder.setUserMCCOResponses(userMCCOResponses);
		builder.setUserFreeTextResponses(userFreeTextResponses);
		builder.setUserNumericResponses(userNumericalResponses);
		builder.setUserMCCMResponses(userMCCMResponses);

		IGroupFormationAlgorithm algorithm = builder.build();
		List<List<String>> groupList = algorithm.createGroups();

		Map<Integer, Map<User, List<String>>> groupTable = createGroupTable(groupList, userDb, userMCCOResponses,
				userFreeTextResponses, userNumericalResponses, userMCCMResponses);
		logger.info("Creating groups and adding into the Group List");
		return groupTable;
	}

	private Map<Integer, Map<User, List<String>>> createGroupTable(List<List<String>> groupList,
			IUserPersistence userDb, Map<String, Map<Long, String>> userMCCOResponses,
			Map<String, Map<Long, String>> userFreeTextResponses,
			Map<String, Map<Long, Integer>> userNumericalResponses,
			Map<String, Map<Long, List<String>>> userMCCMResponses) {

		Map<Integer, Map<User, List<String>>> groupTable = new LinkedHashMap<>();
		for (int i = 0; i < groupList.size(); i++) {
			List<String> group = groupList.get(i);
			Map<User, List<String>> groupMap = new HashMap<User, List<String>>();
			for (String member : group) {
				User user = new User(member, userDb);
				List<String> responses = new ArrayList<>();
				if (null != userMCCOResponses.get(member)) {
					Map<Long, String> questionResponses = userMCCOResponses.get(member);
					for (String response : questionResponses.values()) {
						responses.add(response);
					}
				}
				if (null != userFreeTextResponses.get(member)) {
					Map<Long, String> questionResponses = userFreeTextResponses.get(member);
					for (String response : questionResponses.values()) {
						responses.add(response);
					}
				}
				if (null != userNumericalResponses.get(member)) {
					Map<Long, Integer> questionResponses = userNumericalResponses.get(member);
					for (Integer response : questionResponses.values()) {
						responses.add(response.toString());
					}
				}
				if (null != userMCCMResponses.get(member)) {
					Map<Long, List<String>> questionResponses = userMCCMResponses.get(member);
					for (List<String> responseList : questionResponses.values()) {
						String responseComma = "";
						for (String response : responseList) {
							responseComma = responseComma + response + ",";

						}
						responses.add(responseComma);
					}
				}
				groupMap.put(user, responses);
			}
			groupTable.put(i, groupMap);
		}
		logger.info("Creating the Group Table to store the groups");
		return groupTable;
	}
}
