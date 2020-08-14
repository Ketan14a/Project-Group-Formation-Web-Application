package CSCI5308.GroupFormationTool.SurveyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;

public class SurveyTest {
	
	
	public void getSurveyID() {
		Survey survey = new Survey();
		survey.setSurveyID(1l);
		assertEquals(1l,survey.getSurveyID());
	}

	public void setSurveyID() {
		Survey survey = new Survey();
		survey.setSurveyID(1l);
		assertEquals(1l,survey.getSurveyID());
	}

	public void getSurveyName() {
		Survey survey = new Survey();
		survey.setSurveyName("Test");
		assertEquals("Test",survey.getSurveyName());
	}

	public void setSurveyName() {
		Survey survey = new Survey();
		survey.setSurveyName("Test");
		assertEquals("Test",survey.getSurveyName());
	}

	public void getCourseID() {
		Survey survey = new Survey();
		survey.setCourseID(1l);
		assertEquals(1l,survey.getCourseID());
	}

	public void setCourseID() {
		Survey survey = new Survey();
		survey.setCourseID(1l);
		assertEquals(1l,survey.getCourseID());
	}

	public void getCreatorID() {
		Survey survey = new Survey();
		survey.setCreatorID(1);
		assertEquals(1l,survey.getCreatorID());
	}

	public void setCreatorID() {
		Survey survey = new Survey();
		survey.setCreatorID(1);
		assertEquals(1l,survey.getCreatorID());
	}

	public void isPublished() {
		Survey survey = new Survey();
		survey.setPublished(true);
		assertTrue(survey.isPublished());
	}

	public void setPublished(boolean published) {
		Survey survey = new Survey();
		survey.setPublished(true);
		assertTrue(survey.isPublished());
	}

	public void createSurveyQuestions() {
		ISurveyPersistence surveyDB = new SurveyDBMock();
		Survey survey = new Survey();
		surveyDB.createSurveyQuestion(survey);
	}

	public void updateSurveyQuestions() {
		ISurveyPersistence surveyDB = new SurveyDBMock();
		Survey survey = new Survey();
		surveyDB.updateSurveyQuestion(survey);
	}

	public void publish() {
		ISurveyPersistence surveyDB = new SurveyDBMock();
		Survey survey = new Survey();
		surveyDB.publishSurvey(survey);
	}
}
