USE CSCI5308_24_DEVINT;

CREATE TABLE Surveys (
	
	surveyID BIGINT PRIMARY KEY AUTO_INCREMENT,
	name varchar(60),
	courseID BIGINT,
	creatorID BIGINT
	published BOOLEAN  

);

CREATE TABLE SurveyQuestions (

	surveyID BIGINT,
	questionID BIGINT
);

CREATE TABLE NumericResponses (
	
	surveyID BIGINT,
	bannerID VARCHAR(20),
	questionID BIGINT,
	response INT

);

CREATE TABLE FreeTextResponses (
	
	surveyID BIGINT,
	bannerID VARCHAR(20),
	questionID BIGINT,
	response VARCHAR(120)
);

CREATE TABLE MCCOResponses (
	
	surveyID BIGINT,
	bannerID VARCHAR(20),
	questionID BIGINT,
	response INT

);


CREATE TABLE MCCMResponses (
	
	surveyID BIGINT,
	bannerID VARCHAR(20),
	questionID BIGINT,
	response INT

);
