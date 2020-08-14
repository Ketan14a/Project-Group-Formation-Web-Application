package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyDB implements ISurveyPersistence {
	private Logger logger = LoggerFactory.getLogger(SurveyDB.class);

	@Override
	public long createSurvey(Survey survey) {

		CallStoredProcedure procedure1 = null;
		CallStoredProcedure procedure2 = null;

		long surveyID = 0;
		try {
			procedure1 = new CallStoredProcedure("spCreateSurvey(?,?,?,?)");
			procedure1.setParameter(1, survey.getSurveyName());
			procedure1.setParameter(2, survey.getCourseID());
			procedure1.setParameter(3, survey.getCreatorID());
			procedure1.setParameter(4, survey.isPublished());
			procedure1.execute();
			procedure2 = new CallStoredProcedure("spFindSurveyIDByCourseID(?)");
			procedure2.setParameter(1, survey.getCourseID());
			ResultSet results = procedure2.executeWithResults();
			if (results.next()) {
				surveyID = results.getLong(1);
			}
			logger.info("{} survey created successfully",survey.getSurveyName());
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure1) {
				procedure1.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
			if (null != procedure2) {
				procedure2.cleanup();
				logger.info("The procedure has been cleaned up.");
			}

		}
		return surveyID;
	}

	@Override
	public boolean createSurveyQuestion(Survey survey) {
		CallStoredProcedure procedure = null;
		int length = 0;
		try {
			procedure = new CallStoredProcedure("spCreateSurveyQuestions(?,?)");
			length = survey.getSurveyQuestions().size();
			for (int index = 0; index < length; index++) {
				long questionID = survey.getSurveyQuestions().get(index);
				procedure.setParameter(1, survey.getSurveyID());
				procedure.setParameter(2, questionID);
				procedure.execute();
			}
			logger.info("Survey Questions Created for {}",survey.getSurveyName());
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} catch (Exception exception) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", exception.getMessage());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return true;
	}

	@Override
	public boolean updateSurveyQuestion(Survey survey) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spDeleteSurveyQuestionBySurveyId(?,?)");
			for (long id : survey.getSurveyQuestions())
				procedure.setParameter(1, id);
			procedure.setParameter(2, survey.getSurveyID());
			procedure.execute();
			logger.info("Survey Question updated for {}",survey.getSurveyName());
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return true;
	}

	@Override
	public boolean publishSurvey(Survey survey) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spPublishSurvey(?)");
			procedure.setParameter(1, survey.getCourseID());
			procedure.execute();
			logger.info("{} survey is published",survey.getSurveyName());
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return true;
	}

}
