DELIMITER $$

DROP PROCEDURE IF EXISTS spStoreFreeTextResponses $$

CREATE PROCEDURE spStoreFreeTextResponses  (

	IN surveyID BIGINT,
	IN bannerID VARCHAR(20),
	IN questionID BIGINT,
	IN response VARCHAR(120)

)

BEGIN

INSERT INTO FreeTextResponses VALUES (surveyID, bannerID, questionID, response);

END $$

DELIMITER ;