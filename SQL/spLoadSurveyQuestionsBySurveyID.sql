USE CSCI5308_24_DEVINT;
DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadSurveyQuestionsBySurveyID $$

CREATE PROCEDURE spLoadSurveyQuestionsBySurveyID (

	IN SurveyID BIGINT
)


BEGIN

  select distinct Questions.id,Questions.question, Questions.type from SurveyQuestions cross join Questions where SurveyQuestions.surveyID = SurveyID;

END $$

DELIMITER ;