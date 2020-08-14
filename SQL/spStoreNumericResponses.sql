DELIMITER $$

DROP PROCEDURE IF EXISTS spStoreNumericResponses $$

CREATE PROCEDURE spStoreNumericResponses (

	IN surveyID BIGINT,
	IN bannerID VARCHAR(20),
	IN questionID BIGINT,
	IN response INT(11)

)

BEGIN

INSERT INTO NumericResponses VALUES (surveyID, bannerID, questionID, response);

END $$

DELIMITER ;