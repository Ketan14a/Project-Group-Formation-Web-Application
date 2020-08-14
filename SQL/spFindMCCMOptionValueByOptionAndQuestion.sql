DELIMITER $$

DROP PROCEDURE IF EXISTS spFindMCCMOptionValueByOptionAndQuestion $$

CREATE PROCEDURE spFindMCCMOptionValueByOptionAndQuestion (

	IN questionID BIGINT,
	IN optionChoice varchar(400)
)

BEGIN

SELECT value FROM MCCM WHERE id = questionID and choice = optionChoice;

END $$

DELIMITER ;