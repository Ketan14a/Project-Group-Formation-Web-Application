DELIMITER $$

DROP PROCEDURE IF EXISTS spCreatePasswordHistory $$

CREATE PROCEDURE spCreatePasswordHistory (
	IN pass VARCHAR(76),
    IN bannerID VARCHAR(20)
)
BEGIN
	SELECT id,password
    INTO @userID,@pass
    FROM User
    WHERE bannerID = banner;

	INSERT INTO UserPasswordHistory
    (`userID`,`password`,`timestamp`) 
    VALUES
    (@userID,@pass,now());
    
    
END $$

DELIMITER ;