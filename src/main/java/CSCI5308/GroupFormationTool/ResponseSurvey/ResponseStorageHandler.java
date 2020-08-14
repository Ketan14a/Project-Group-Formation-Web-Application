package CSCI5308.GroupFormationTool.ResponseSurvey;

import java.sql.SQLException;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseStorageHandler implements IResponseStoragePersistence {

	private Logger logger = LoggerFactory.getLogger(ResponseStorageHandler.class);

	@Override
	public boolean storeNumericResponse(NumericResponse numericResponse) {
		CallStoredProcedure proc = null;
		long surveyID = numericResponse.getSurveyID();
		String bannerID = numericResponse.getBannerID();
		long questionID = numericResponse.getQuestionID();
		Integer response = numericResponse.getResponse();

		try {
			proc = new CallStoredProcedure("spStoreNumericResponses(?,?,?,?)");
			proc.setParameter(1, surveyID);
			proc.setParameter(2, bannerID);
			proc.setParameter(3, questionID);
			proc.setParameter(4, response);
			proc.execute();
		}

		catch (SQLException sqe) {
			logger.error("SQLException occured in storeNumericResponse {}", sqe.getMessage());
			return false;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return true;
	}

	@Override
	public boolean storeFreeTextResponse(FreeTextResponse freeTextResponse) {
		CallStoredProcedure proc = null;
		long surveyID = freeTextResponse.getSurveyID();
		String bannerID = freeTextResponse.getBannerID();
		long questionID = freeTextResponse.getQuestionID();
		String response = freeTextResponse.getResponse();

		try {
			proc = new CallStoredProcedure("spStoreFreeTextResponses(?,?,?,?)");
			proc.setParameter(1, surveyID);
			proc.setParameter(2, bannerID);
			proc.setParameter(3, questionID);
			proc.setParameter(4, response);
			proc.execute();
		}

		catch (SQLException sqe) {
			logger.error("SQLException occured in storeFreeTextResponse {}", sqe.getMessage());
			return false;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return true;
	}

	@Override
	public boolean storeMCCOResponses(MCCOResponse mccoResponse) {
		CallStoredProcedure proc = null;
		long surveyID = mccoResponse.getSurveyID();
		String bannerID = mccoResponse.getBannerID();
		long questionID = mccoResponse.getQuestionID();
		long response = mccoResponse.getResponse();

		try {
			proc = new CallStoredProcedure("spStoreMCCOResponses(?,?,?,?)");
			proc.setParameter(1, surveyID);
			proc.setParameter(2, bannerID);
			proc.setParameter(3, questionID);
			proc.setParameter(4, response);
			proc.execute();
		}

		catch (SQLException sqe) {
			logger.error("SQLException occured in storeMCCOResponses {}", sqe.getMessage());
			return false;
		}

		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return true;
	}

	@Override
	public boolean storeMCCMResponses(MCCMResponse mccmResponse) {
		CallStoredProcedure proc = null;
		long surveyID = mccmResponse.getSurveyID();
		String bannerID = mccmResponse.getBannerID();
		long questionID = mccmResponse.getQuestionID();
		List<Long> responses = mccmResponse.getResponses();

		for (Long r : responses) {
			try {
				proc = new CallStoredProcedure("spStoreMCCMResponses(?,?,?,?)");
				proc.setParameter(1, surveyID);
				proc.setParameter(2, bannerID);
				proc.setParameter(3, questionID);
				proc.setParameter(4, r);
				proc.execute();
			}

			catch (SQLException sqe) {
				logger.error("SQLException occured in storeMCCMResponses {}", sqe.getMessage());
				return false;
			}

			finally {
				if (null != proc) {
					proc.cleanup();
				}
			}
		}

		return true;
	}

}
