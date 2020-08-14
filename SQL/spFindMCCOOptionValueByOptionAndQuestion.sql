DELIMITER $$

DROP PROCEDURE IF EXISTS spFindMCCOOptionValueByOptionAndQuestion $$

CREATE PROCEDURE spFindMCCOOptionValueByOptionAndQuestion (

	IN questionID BIGINT,
	IN optionChoice varchar(400)
)

BEGIN

SELECT value FROM MCCO WHERE id = questionID and choice = optionChoice;

END $$

DELIMITER ;