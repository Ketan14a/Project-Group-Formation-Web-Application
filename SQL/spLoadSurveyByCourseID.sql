USE CSCI5308_24_DEVINT;
DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadSurveyByCourseID $$

CREATE PROCEDURE spLoadSurveyByCourseID (
	IN CourseID BIGINT
)

BEGIN
	SELECT name, published from Surveys WHERE Surveys.courseID = CourseID;
END $$

DELIMITER ;