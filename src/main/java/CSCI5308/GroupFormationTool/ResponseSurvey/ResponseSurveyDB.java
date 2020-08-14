package CSCI5308.GroupFormationTool.ResponseSurvey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.FactoryProvider;
import CSCI5308.GroupFormationTool.Questions.IAbstractQuestionFactory;
import org.apache.commons.collections4.map.HashedMap;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseSurveyDB implements IResponseSurveyPersistence {

	private Logger logger = LoggerFactory.getLogger(ResponseSurveyDB.class);
	private long currentSurveyID;
	public static final String MCCO = "MCCO";
	public static final String MCCM = "MCCM";
	public static final String FREETEXT = "FREETEXT";
	public static final String NUMERIC = "NUMERIC";
	public static final String SPLITRE_DELIMITER = "@@";
	public static final String QUES_NO_DELIMITER = "X@Y";

	@Override
	public void loadSurveyByCourseID(long courseID, ResponseSurvey responseSurvey) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadSurveyByCourseID(?)");
			proc.setParameter(1, courseID);
			ResultSet results = proc.executeWithResults();

			if (results != null) {
				while (results.next()) {
					responseSurvey.setSurveyID(results.getLong(1));
					currentSurveyID = results.getLong(1);
					responseSurvey.setName(results.getString(2));
					responseSurvey.setCourseID(results.getLong(3));
					responseSurvey.setCourseID(results.getLong(4));
					responseSurvey.setPublished(results.getInt(5));
				}
			}

		}

		catch (SQLException e) {
			logger.error("SQLException {} came in loadSurveyByCourseID", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

	}

	@Override
	public List<AbstractQuestion> loadSurveyQuestionsBySurveyID(long SurveyID) {
		List<AbstractQuestion> surveyQuestions = new ArrayList<>();
		CallStoredProcedure proc = null;
		long questionID;
		String question_statement;
		String question_type;
		String question_title;
		Map<Integer, String> options;
		try {
			proc = new CallStoredProcedure("spLoadSurveyQuestionsBySurveyID(?)");
			proc.setParameter(1, SurveyID);
			ResultSet results = proc.executeWithResults();

			if (results != null) {
				while (results.next()) {
					questionID = results.getLong(1);
					question_statement = results.getString(2);
					question_type = results.getString(3);
					question_title = results.getString(4);
					IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
							.getQuestionAbstractFactory(question_type);
					AbstractQuestion current_question = iAbstractQuestionFactory.createQuestion(question_title,
							question_statement, question_type, null, null, null);
					current_question.setId(questionID);
					current_question.setQuestion(question_statement);
					current_question.setType(question_type);
					current_question.setTitle(question_title);

					if (question_type.contains(MCCM) || question_type.contains(MCCO)) {
						if (question_type.contains(MCCM)) {
							options = getMCCMOptions(questionID);
						}

						else {
							options = getMCCOOptions(questionID);
						}

						current_question.setOptions(options);
					}

					surveyQuestions.add(current_question);
				}

			}
		}

		catch (SQLException e) {
			logger.error("SQLException {} came in loadSurveyQuestionsBySurveyID", e.getMessage());
			return null;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return surveyQuestions;

	}

	public Map<Integer, String> getMCCMOptions(long questionID) {
		Map<Integer, String> options = new HashedMap<Integer, String>();
		CallStoredProcedure proc = null;
		String option_name;
		Integer option_value;
		try {
			proc = new CallStoredProcedure("spLoadMCCMOptionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();

			if (results != null) {
				while (results.next()) {
					option_value = results.getInt(1);
					option_name = results.getString(2);
					options.put(option_value, option_name);
				}

			}
		} catch (SQLException e) {
			logger.error("SQLException {} came in getMCCMOptions", e.getMessage());
			return null;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return options;
	}

	public Map<Integer, String> getMCCOOptions(long questionID) {
		Map<Integer, String> options = new HashedMap<Integer, String>();
		CallStoredProcedure proc = null;
		String option_name;
		Integer option_value;
		try {
			proc = new CallStoredProcedure("spLoadMCCOOptionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();

			if (results != null) {
				while (results.next()) {
					option_value = results.getInt(1);
					option_name = results.getString(2);
					options.put(option_value, option_name);
				}

			}
		} catch (SQLException e) {
			logger.error("SQLException {} came in getMCCOOptions", e.getMessage());
			return null;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return options;
	}

	@Override
	public Map<Integer, String> parseRequest(Map<String, String> questionMapper) {
		Map<Integer, String> mappedQuestions = new HashMap<Integer, String>();
		List<Integer> questionNumbers = new ArrayList<>();
		Integer questionNumber = 0;
		String responseValue = null;
		String[] slices = null;
		for (String key : questionMapper.keySet()) {
			if (key.contains(QUES_NO_DELIMITER) == false) {
				questionNumber = Integer.parseInt(key);
				responseValue = questionMapper.get(key);
				mappedQuestions.put(questionNumber, responseValue);
			}

			else {
				slices = key.split(QUES_NO_DELIMITER);
				questionNumber = Integer.parseInt(slices[0]);

				if (questionNumbers.contains(questionNumber)) {
					responseValue = mappedQuestions.get(questionNumber) + SPLITRE_DELIMITER + slices[1];
					mappedQuestions.replace(questionNumber, responseValue);

				}

				else {
					questionNumbers.add(questionNumber);
					responseValue = slices[1];
					mappedQuestions.put(questionNumber, responseValue);
				}
			}
		}

		return mappedQuestions;
	}

	@Override
	public Boolean storeResponsesInDB(Map<Integer, String> responseMapper, List<AbstractQuestion> questionList) {
		ResponseFactory responseFactory;
		Response questionResponse;
		List<Response> studentResponses = new ArrayList<Response>();
		List<String> responseTypes = new ArrayList<String>();
		int i;
		User currentStudent = CurrentUser.instance().getCurrentAuthenticatedUser();
		String bannerID = currentStudent.getBanner();
		long currentQuestionID;
		String currentQuestionType;
		String currentResponse;
		Object response;
		String numericString = NUMERIC;
		String freeTextString = FREETEXT;
		String MCCMString = MCCM;
		String MCCOString = MCCO;
		String splitRE = SPLITRE_DELIMITER;
		Boolean result = true;
		IResponseStoragePersistence responseStorageDB = ResponseSurveyConfig.instance().getResponseStorageDB();

		for (i = 0; i < questionList.size(); i++) {
			currentQuestionID = questionList.get(i).getId();
			currentQuestionType = questionList.get(i).getType();
			currentResponse = responseMapper.get(i + 1);

			if (currentQuestionType.contains(numericString)) {
				response = Integer.parseInt(currentResponse);
				responseFactory = new NumericResponseFactory();
				questionResponse = responseFactory.createResponse(response, currentQuestionID, numericString,
						currentSurveyID, bannerID);
				studentResponses.add(questionResponse);
				responseTypes.add(currentQuestionType);

			} else if (currentQuestionType.contains(freeTextString)) {
				response = currentResponse;
				responseFactory = new FreeTextResponseFactory();
				questionResponse = responseFactory.createResponse(response, currentQuestionID, freeTextString,
						currentSurveyID, bannerID);
				studentResponses.add(questionResponse);
				responseTypes.add(currentQuestionType);
			} else if (currentQuestionType.contains(MCCOString)) {
				response = this.fetchMCCOOptionValue(currentQuestionID, currentResponse);
				responseFactory = new MCCOResponseFactory();
				questionResponse = responseFactory.createResponse(response, currentQuestionID, MCCOString,
						currentSurveyID, bannerID);
				studentResponses.add(questionResponse);
				responseTypes.add(currentQuestionType);
			} else if (currentQuestionType.contains(MCCMString)) {
				String[] responseSlices = currentResponse.split(splitRE);
				List<Long> MCCMresponses = new ArrayList<>();

				for (String s : responseSlices) {
					response = this.fetchMCCMOptionValue(currentQuestionID, s);
					MCCMresponses.add((Long) response);
				}
				responseFactory = new MCCMResponseFactory();
				questionResponse = responseFactory.createResponse(MCCMresponses, currentQuestionID, MCCMString,
						currentSurveyID, bannerID);
				studentResponses.add(questionResponse);
				responseTypes.add(currentQuestionType);
			}

		}

		for (i = 0; i < studentResponses.size(); i++) {

			if (responseTypes.get(i).contains(numericString)) {
				result = responseStorageDB.storeNumericResponse((NumericResponse) studentResponses.get(i));
			}

			else if (responseTypes.get(i).contains(freeTextString)) {
				result = responseStorageDB.storeFreeTextResponse((FreeTextResponse) studentResponses.get(i));
			}

			else if (responseTypes.get(i).contains(MCCOString)) {
				result = responseStorageDB.storeMCCOResponses((MCCOResponse) studentResponses.get(i));
			}

			else if (responseTypes.get(i).contains(MCCMString)) {
				result = responseStorageDB.storeMCCMResponses((MCCMResponse) studentResponses.get(i));
			}

			if (result == false) {
				return false;
			}

		}

		return true;
	}

	public Long fetchMCCMOptionValue(long questionID, String choice) {
		long optionValue = 0;
		CallStoredProcedure proc = null;

		try {
			proc = new CallStoredProcedure("spFindMCCMOptionValueByOptionAndQuestion(?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, choice);
			ResultSet results = proc.executeWithResults();

			while (results.next()) {
				optionValue = results.getLong(1);
			}
		} catch (SQLException sqe) {
			logger.error("SQLException {} came in fetchMCCMOptionValue", sqe.getMessage());
			return null;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return optionValue;
	}

	public Long fetchMCCOOptionValue(long questionID, String choice) {
		long optionValue = 0;
		CallStoredProcedure proc = null;

		try {
			proc = new CallStoredProcedure("spFindMCCOOptionValueByOptionAndQuestion(?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, choice);
			ResultSet results = proc.executeWithResults();

			while (results.next()) {
				optionValue = results.getLong(1);
			}
		} catch (SQLException sqe) {
			logger.error("SQLException {} came in fetchMCCOOptionValue", sqe.getMessage());
			return null;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return optionValue;
	}

}
