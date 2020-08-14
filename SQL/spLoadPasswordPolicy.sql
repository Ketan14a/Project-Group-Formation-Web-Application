DELIMITER $$

DROP procedure IF EXISTS `spLoadPasswordPolicy`;

CREATE PROCEDURE `spLoadPasswordPolicy` ()
BEGIN
SELECT * FROM PasswordValidations;
END$$

DELIMITER ;