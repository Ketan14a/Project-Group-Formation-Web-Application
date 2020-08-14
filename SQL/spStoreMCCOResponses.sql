DELIMITER $$

DROP PROCEDURE IF EXISTS spStoreMCCOResponses $$

CREATE PROCEDURE spStoreMCCOResponses (

	IN surveyID BIGINT,
	IN bannerID VARCHAR(20),
	IN questionID BIGINT,
	IN response INT(11)

)

BEGIN

INSERT INTO MCCOResponses VALUES (surveyID, bannerID, questionID, response);

END $$

DELIMITER ;