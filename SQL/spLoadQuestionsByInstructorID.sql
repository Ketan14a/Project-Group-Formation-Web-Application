DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadQuestionsByInstructorID $$

CREATE PROCEDURE spLoadQuestionsByInstructorID (
    IN InstructorID BIGINT
)
BEGIN
	SELECT id, title, question, type, creationDate
    FROM Questions
    WHERE Questions.createdBy = InstructorID
    ORDER BY title ASC;
END $$

DELIMITER ;