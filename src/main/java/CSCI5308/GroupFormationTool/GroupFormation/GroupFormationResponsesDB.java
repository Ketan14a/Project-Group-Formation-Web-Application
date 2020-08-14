package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupFormationResponsesDB implements IGroupFormationStudentResponsesPersistence {

	private Logger logger = LoggerFactory.getLogger(GroupFormationResponsesDB.class);
	@Override
	public Map<String, Map<Long, String>> loadMCCOResponses(long surveyId) {
		CallStoredProcedure proc = null;
		Map<String, Map<Long, String>> userResponses = null;
		try {
			userResponses = new HashMap<>();
			proc = new CallStoredProcedure("spLoadMCCOresponsesBySurveyID(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String userId = results.getString(1);
					long questionId = results.getLong(2);
					String response = results.getString(3);
					if (null != userResponses.get(userId)) {
						userResponses.get(userId).put(questionId, response);
					} else {
						Map<Long, String> question = new HashMap<>();
						question.put(questionId, response);
						userResponses.put(userId, question);
					}
				}
			}
			logger.info("Loaded the MCCO Responses");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}

		return userResponses;
	}

	@Override
	public Map<String, Map<Long, List<String>>> loadMCCMResponses(long surveyId) {
		CallStoredProcedure proc = null;
		Map<String, Map<Long, List<String>>> userResponses = null;
		try {
			userResponses = new HashMap<>();
			proc = new CallStoredProcedure("spLoadMCCMresponsesBySurveyID(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String userId = results.getString(1);
					long questionId = results.getLong(2);
					String response = results.getString(3);
					if (null != userResponses.get(userId)) {
						if (null != userResponses.get(userId).get(questionId)) {
							userResponses.get(userId).get(questionId).add(response);
						} else {
							List<String> responses = new ArrayList<>();
							responses.add(response);
							userResponses.get(userId).put(questionId, responses);
						}
					} else {
						List<String> responses = new ArrayList<>();
						responses.add(response);
						Map<Long, List<String>> question = new HashMap<>();
						question.put(questionId, responses);
						userResponses.put(userId, question);
					}
				}
			}
			logger.info("Loaded the MCCM Responses");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return userResponses;
	}

	@Override
	public Map<String, Map<Long, Integer>> loadNumericalResponses(long surveyId) {
		CallStoredProcedure proc = null;
		Map<String, Map<Long, Integer>> userResponses = null;
		try {
			userResponses = new HashMap<>();
			proc = new CallStoredProcedure("spLoadNumericalresponsesBySurveyID(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String userId = results.getString(1);
					long questionId = results.getLong(2);
					int response = results.getInt(3);
					if (null != userResponses.get(userId)) {
						userResponses.get(userId).put(questionId, response);
					} else {
						Map<Long, Integer> question = new HashMap<>();
						question.put(questionId, response);
						userResponses.put(userId, question);
					}
				}
			}
			logger.info("Loaded the Numerical Responses");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return userResponses;
	}

	@Override
	public Map<String, Map<Long, String>> loadFreeTextResponses(long surveyId) {
		CallStoredProcedure proc = null;
		Map<String, Map<Long, String>> userResponses = null;
		try {
			userResponses = new HashMap<>();
			proc = new CallStoredProcedure("spLoadFreeTextresponsesBySurveyID(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String userId = results.getString(1);
					long questionId = results.getLong(2);
					String response = results.getString(3);
					if (null != userResponses.get(userId)) {
						userResponses.get(userId).put(questionId, response);
					} else {
						Map<Long, String> question = new HashMap<>();
						question.put(questionId, response);
						userResponses.put(userId, question);
					}
				}
			}
			logger.info("Loaded the Free Text Responses");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return userResponses;
	}

	public List<String> loadMCCOResponseText(long surveyId, String bannerID) {
		CallStoredProcedure proc = null;
		List<String> userResponses = null;
		try {
			userResponses = new ArrayList<>();
			proc = new CallStoredProcedure("spLoadFreeTextresponsesBySurveyID(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					userResponses.add(results.getString(1));
				}
			}
			logger.info("Loaded the MCCO Responses Value as text");
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}

		return userResponses;
	}

}
