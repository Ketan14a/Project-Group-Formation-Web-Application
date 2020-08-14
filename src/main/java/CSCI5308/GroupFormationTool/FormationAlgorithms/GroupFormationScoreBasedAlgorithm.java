package CSCI5308.GroupFormationTool.FormationAlgorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GroupFormationScoreBasedAlgorithm implements IGroupFormationAlgorithm {

	private Logger logger = LoggerFactory.getLogger(GroupFormationScoreBasedAlgorithm.class);
	List<IGroupFormationPolicies> questionPolicies;
	Map<String, Map<Long, String>> userMCCOResponses;
	Map<String, Map<Long, Integer>> userNumericResponses;
	Map<String, Map<Long, String>> userFreeTextResponses;
	Map<String, Map<Long, List<String>>> userMCCMResponses;
	List<List<String>> groups;

	GroupFormationScoreBasedAlgorithm(List<IGroupFormationPolicies> questionPolicies,
			Map<String, Map<Long, String>> userMCCOResponses, Map<String, Map<Long, Integer>> userNumericResponses,
			Map<String, Map<Long, String>> userFreeTextResponses,
			Map<String, Map<Long, List<String>>> userMCCMResponses) {
		this.questionPolicies = questionPolicies;
		this.userMCCOResponses = new HashMap<>(userMCCOResponses);
		this.userNumericResponses = new HashMap<>(userNumericResponses);
		this.userFreeTextResponses = new HashMap<>(userFreeTextResponses);
		this.userMCCMResponses = new HashMap<>(userMCCMResponses);
	}

	@Override
	public List<List<String>> createGroups() {
		groups = new LinkedList<>();
		int groupSize = this.questionPolicies.get(0).getGroupCount();
		while ((userMCCOResponses.size() + userNumericResponses.size() + userFreeTextResponses.size()
				+ userMCCMResponses.size()) != 0) {
			logger.info("Creating groups based on Group Size : {}",groupSize);
			findGroup(groupSize);
		}
		return groups;
	}

	private void findGroup(int groupSize) {
		String refUserId = userNumericResponses.keySet().stream().findFirst().get();
		Map<Long, String> refUserMCCOResponses = userMCCOResponses.get(refUserId);
		Map<Long, Integer> refUserNumericResponses = userNumericResponses.get(refUserId);
		Map<Long, String> refUserFreeTextResponses = userFreeTextResponses.get(refUserId);
		Map<Long, List<String>> reUserMCCMResponses = userMCCMResponses.get(refUserId);
		Map<String, Double> userScores = new HashMap<String, Double>();
		logger.info("Calculating all the scores for group formation");
		for (IGroupFormationPolicies policy : questionPolicies) {

			calculateMCCOScore(refUserId, policy, refUserMCCOResponses, userScores);
			calculateNumericScore(refUserId, policy, refUserNumericResponses, userScores);
			calculateFreeTextScore(refUserId, policy, refUserFreeTextResponses, userScores);
			calculateMCCMScore(refUserId, policy, reUserMCCMResponses, userScores);
		}

		List<String> group = new LinkedList<>();
		group.add(refUserId);
		logger.info("Sorting and Adding a groups in the list of Groups");
		if (userScores.size() > 0) {
			sortAndAdd(userScores, group, groupSize);
		}
		this.groups.add(group);

		for (String userId : group) {
			userMCCOResponses.remove(userId);
			userMCCMResponses.remove(userId);
			userNumericResponses.remove(userId);
			userFreeTextResponses.remove(userId);
		}
	}

	private void calculateMCCOScore(String refUserId, IGroupFormationPolicies policy,
			Map<Long, String> refUserMCCOResponses, Map<String, Double> userScores) {
		Double questionScore = 0.0;
		for (String userID : userMCCOResponses.keySet()) {
			if (userID.equalsIgnoreCase(refUserId) == false) {
				String otherResponse = userMCCOResponses.get(userID).get(policy.getQuestionID());
				String userResponse = refUserMCCOResponses.get(policy.getQuestionID());
				if (null == userResponse) {
					break;
				}
				questionScore = 0.0;
				if (policy.getPolicyType().equalsIgnoreCase("similar")) {
					if (otherResponse.equalsIgnoreCase(userResponse)) {
						questionScore = 4.0;
					}
				} else if (policy.getPolicyType().equalsIgnoreCase("dissimilar")) {
					if (otherResponse.equalsIgnoreCase(userResponse) == false) {
						questionScore = 4.0;
					}

				}
				Double score = userScores.getOrDefault(userID, 0.0);
				score += questionScore;
				logger.info("Calculating the MCCO Score : {} for group formation"+score);
				userScores.put(userID, score);
			}
		}
	}

	private void calculateNumericScore(String refUserId, IGroupFormationPolicies policy,
			Map<Long, Integer> refUserNumericResponses, Map<String, Double> userScores) {
		Double questionScore = 0.0;
		for (String userID : userNumericResponses.keySet()) {
			if (userID.equalsIgnoreCase(refUserId) == false) {
				Integer otherResponse = userNumericResponses.get(userID).get(policy.getQuestionID());
				Integer userResponse = refUserNumericResponses.get(policy.getQuestionID());
				if (null == userResponse) {
					break;
				}
				questionScore = 0.0;
				if (policy.getPolicyType().equalsIgnoreCase("similar")) {
					if (otherResponse == userResponse) {
						questionScore = 4.0;
					}
				} else if (policy.getPolicyType().equalsIgnoreCase("dissimilar")) {
					if (otherResponse != userResponse) {
						questionScore = 4.0;
					}
				}
				Double score = userScores.getOrDefault(userID, 0.0);
				score += questionScore;
				logger.info("Calculating the Numeric Score : {} for group formation"+score);
				userScores.put(userID, score);
			}
		}
	}

	private void calculateFreeTextScore(String refUserId, IGroupFormationPolicies policy,
			Map<Long, String> refUserFreeTextResponses, Map<String, Double> userScores) {
		Double questionScore = 0.0;
		for (String userID : userFreeTextResponses.keySet()) {
			if (userID.equalsIgnoreCase(refUserId) == false) {
				String otherResponse = userFreeTextResponses.get(userID).get(policy.getQuestionID());
				String userResponse = refUserFreeTextResponses.get(policy.getQuestionID());
				if (null == userResponse) {
					break;
				}
				questionScore = 0.0;
				if (policy.getPolicyType().equalsIgnoreCase("similar")) {
					if (otherResponse.equalsIgnoreCase(userResponse)) {
						questionScore = 4.0;
					}
				} else if (policy.getPolicyType().equalsIgnoreCase("dissimilar")) {
					if (!otherResponse.equalsIgnoreCase(userResponse)) {
						questionScore = 4.0;
					}

				}

				Double score = userScores.getOrDefault(userID, 0.0);
				score += questionScore;
				logger.info("Calculating the Free Text Score : {} for group formation"+score);
				userScores.put(userID, score);
			}
		}
	}

	private void calculateMCCMScore(String refUserId, IGroupFormationPolicies policy,
			Map<Long, List<String>> reUserMCCMResponses, Map<String, Double> userScores) {
		Double questionScore = 0.0;
		for (String userID : userMCCMResponses.keySet()) {
			if (userID.equalsIgnoreCase(refUserId) == false) {
				List<String> otherResponse = userMCCMResponses.get(userID).get(policy.getQuestionID());
				List<String> userResponse = reUserMCCMResponses.get(policy.getQuestionID());
				if (null == userResponse) {
					break;
				}
				Double optionScore = 4.0 / userResponse.size();
				questionScore = 0.0;
				for (String choice : userResponse) {
					if (policy.getPolicyType().equalsIgnoreCase("similar")) {
						if (otherResponse.contains(choice)) {
							questionScore += optionScore;
						}

					} else if (policy.getPolicyType().equalsIgnoreCase("dissimilar")) {
						if (!otherResponse.contains(choice)) {
							questionScore += optionScore;
						}
					}
				}

				Double score = userScores.getOrDefault(userID, 0.0);
				score += questionScore;
				logger.info("Calculating the MCCM Score : {} for group formation"+score);
				userScores.put(userID, score);
			}
		}
	}

	private void sortAndAdd(Map<String, Double> hashMap, List<String> group, int groupSize) {
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hashMap.entrySet());
		logger.info("Sorting the Groups");
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		int currentGroupSize;
		if (groupSize - 1 <= list.size()) {
			currentGroupSize = groupSize - 1;
		} else {
			currentGroupSize = list.size();
		}

		for (int i = 0; i < currentGroupSize; i++) {
			if (null != list.get(i)) {
				group.add(list.get(i).getKey());
			}
		}
		logger.info("Adding a group in the list of groups");
	}
}
