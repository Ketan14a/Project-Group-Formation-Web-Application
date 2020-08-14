package CSCI5308.GroupFormationTool.Questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionDB implements IQuestionPersistence {

	private Logger logger = LoggerFactory.getLogger(QuestionDB.class);

	@Override
	public boolean createFreeTextNumericalQuestion(AbstractQuestion abstractQuestion) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?, ?, ?)");
			procedure.setParameter(1, abstractQuestion.getTitle());
			procedure.setParameter(2, abstractQuestion.getQuestion());
			procedure.setParameter(3, abstractQuestion.getType());
			procedure.setParameter(4, abstractQuestion.getCreationDate());
			procedure.setParameter(5, abstractQuestion.getCreatedBy());
			procedure.registerOutputParameterLong(6);
			procedure.execute();
			logger.info("Creating Free Text Numeric Question");
		}

		catch (SQLException sqlException) {
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
	public boolean createMultipleOptionsSingleOptionQuestion(AbstractQuestion abstractQuestion) {
		CallStoredProcedure procedure = null;
		long questionID;
		try {
			procedure = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?, ?, ?)");
			procedure.setParameter(1, abstractQuestion.getTitle());
			procedure.setParameter(2, abstractQuestion.getQuestion());
			procedure.setParameter(3, abstractQuestion.getType());
			procedure.setParameter(4, abstractQuestion.getCreationDate());
			procedure.setParameter(5, abstractQuestion.getCreatedBy());
			procedure.registerOutputParameterLong(6);
			procedure.execute();
			questionID = procedure.getOutputParameter(6);
			storeSingleChoiceOptions(abstractQuestion, questionID);
			logger.info("Creating MCCO Question");
		}

		catch (SQLException sqlException) {
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

	private void storeSingleChoiceOptions(AbstractQuestion abstractQuestion, long questionID) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateMCCOOptions(?, ?, ?)");

			for (Map.Entry<Integer, String> entry : abstractQuestion.getOptions().entrySet()) {
				proc = new CallStoredProcedure("spCreateMCCOOptions(?, ?, ?)");
				proc.setParameter(1, questionID);
				proc.setParameter(2, entry.getValue());
				proc.setParameter(3, entry.getKey());
				proc.execute();
				logger.info("Storing Single Choice options");
			}
		}
		catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} catch (Exception exception) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", exception.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}

	}

	@Override
	public boolean createMultipleOptionsMultipleOptionQuestion(AbstractQuestion abstractQuestion) {
		CallStoredProcedure procedure = null;
		long questionID;
		try {
			procedure = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?, ?, ?)");
			procedure.setParameter(1, abstractQuestion.getTitle());
			procedure.setParameter(2, abstractQuestion.getQuestion());
			procedure.setParameter(3, abstractQuestion.getType());
			procedure.setParameter(4, abstractQuestion.getCreationDate());
			procedure.setParameter(5, abstractQuestion.getCreatedBy());
			procedure.registerOutputParameterLong(6);
			procedure.execute();
			questionID = procedure.getOutputParameter(6);
			storeMultipleChoiceOptions(abstractQuestion, questionID);
			logger.info("Creating MCCM question");
		}
		catch (SQLException sqlException) {
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

	private void storeMultipleChoiceOptions(AbstractQuestion abstractQuestion, long questionID) {
		CallStoredProcedure procedure = null;
		try {

			procedure = new CallStoredProcedure("spCreateMCCMOptions(?, ?, ?)");
			for (Map.Entry<Integer, String> entry : abstractQuestion.getOptions().entrySet()) {

				procedure.setParameter(1, questionID);
				procedure.setParameter(2, entry.getValue());
				procedure.setParameter(3, entry.getKey());
				procedure.execute();
				logger.info("Store Multiple Choice options");
			}
		}
		catch (SQLException sqlException) {
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

	}

	@Override
	public boolean deleteQuestion(AbstractQuestion abstractQuestion) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spDeleteQuestion(?)");
			procedure.setParameter(1, abstractQuestion.getId());
			procedure.execute();
			logger.info("Delete the Question Selected");
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
	public List<AbstractQuestion> loadQuestionsByInstructorId(Integer instructorId) {
		List<AbstractQuestion> questions = new ArrayList<>();
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure("spLoadQuestionsByInstructorID(?)");
			procedure.setParameter(1, instructorId);
			ResultSet results = procedure.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					String quest = results.getString(3);
					String type = results.getString(4);
					String creationDate = results.getString(5);
					IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
							.getQuestionAbstractFactory(type);
					AbstractQuestion question = iAbstractQuestionFactory.createQuestion(title, quest, type,
							creationDate, null, null);
					question.setId(id);
					questions.add(question);
				}
			}
			logger.info("Load Questions by Instructor ID : {}",instructorId);
		} catch (SQLException sqlException) {
			logger.error("A database error has occured!!! Probably the database is down.");
			logger.error("Error Message : {} Error Code : {}", sqlException.getMessage(), sqlException.getErrorCode());
		} finally {
			if (null != procedure) {
				procedure.cleanup();
				logger.info("The procedure has been cleaned up.");
			}
		}
		return questions;
	}
}
