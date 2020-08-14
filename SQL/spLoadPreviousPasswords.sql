DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadPreviousPasswords $$

CREATE PROCEDURE spLoadPreviousPasswords (
	IN uID BIGINT,
    IN histLimit INT
)
BEGIN
	SELECT password
    FROM UserPasswordHistory
    WHERE userID = uID ORDER BY timestamp DESC LIMIT histLimit;
END $$

DELIMITER ;