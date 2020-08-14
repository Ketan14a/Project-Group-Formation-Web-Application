DELIMITER $$

DROP PROCEDURE IF EXISTS spStoreMCCMResponses $$

CREATE PROCEDURE spStoreMCCMResponses (

	IN surveyID BIGINT,
	IN bannerID VARCHAR(20),
	IN questionID BIGINT,
	IN response INT(11)

)

BEGIN

INSERT INTO MCCMResponses VALUES (surveyID, bannerID, questionID, response);

END $$

DELIMITER ;