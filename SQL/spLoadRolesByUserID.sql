DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadRolesByUserID $$

CREATE PROCEDURE spLoadRolesByUserID (
	IN userID BIGINT
)
BEGIN
    SELECT role
    FROM Role
    INNER JOIN CourseRole on Role.id = CourseRole.roleID
    WHERE CourseRole.userID = userID;
END $$

DELIMITER ;

