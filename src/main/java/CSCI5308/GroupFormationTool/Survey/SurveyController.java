package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.QuestionConfig;
import CSCI5308.GroupFormationTool.Questions.QuestionForm;
import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseSurveyPersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurvey;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurveyConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController {

	private Logger logger = LoggerFactory.getLogger(SurveyConfig.class);
	private static final String INSTRUCTORID = "instructorID";
	private static final String COURSE_ID = "course_id";
	private static final String SURVEY_ID = "survey_id";
	private static final String SELECTED = "selected";
	private static final String NOTSELECTED = "notSelected";
	private static final String COURSEID = "courseID";
	private static final String SURVEYFORM = "surveyForm";
	private static final String QUESTIONS = "questions";

	@GetMapping("/survey/createSurvey")
	public String loadQuestionsForSurvey(Model model, @RequestParam(name = COURSE_ID) long courseID,
			@RequestParam(name = INSTRUCTORID) Integer instructorID) {

		IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
		List<AbstractQuestion> questions = questionDB.loadQuestionsByInstructorId(instructorID);
		List<QuestionForm> surveyQuestions = new ArrayList<>();
		for (AbstractQuestion question : questions) {
			QuestionForm questionForm = new QuestionForm(question);
			surveyQuestions.add(questionForm);
		}
		SurveyForm surveyForm = new SurveyForm();
		surveyForm.setSurveyQuestions(surveyQuestions);
		logger.info("Loading the Questions for Survey");
		model.addAttribute(SURVEYFORM, surveyForm);
		model.addAttribute(QUESTIONS, questions);
		model.addAttribute(COURSEID, courseID);
		model.addAttribute(INSTRUCTORID, instructorID);
		return "survey/createSurvey";

	}

	@RequestMapping(value = "/survey/createSurvey", method = RequestMethod.POST)
	public String createSurvey(Model model, @RequestParam(name = COURSE_ID) Integer courseID,
			@RequestParam(name = INSTRUCTORID) Integer instructorID,
			@ModelAttribute(SURVEYFORM) SurveyForm surveyForm) {

		ISurveyPersistence surveyDB = SurveyConfig.instance().getSurveyDB();
		List<Long> questionIdList = new ArrayList<>();
		for (QuestionForm question : surveyForm.getSurveyQuestions()) {
			System.out.println(question.getQuestion());
			System.out.println(question.getSelected());
			if (question.getSelected().equalsIgnoreCase(SELECTED)) {
				questionIdList.add(question.getId());
			}
		}

		Survey survey = new Survey(surveyForm.getSurveyName(), courseID, instructorID, surveyDB);
		survey.setSurveyQuestions(questionIdList);
		survey.createSurveyQuestions(surveyDB);
		logger.info("Creating the survey for the selected course");
		return "survey/surveyCreationSuccess";
	}

	@GetMapping("/survey/updateSurvey")
	public String updateListQuestionsForSurvey(Model model, @RequestParam(name = COURSE_ID) long courseID,
			@RequestParam(name = INSTRUCTORID) Integer instructorID) {

		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		ResponseSurvey survey = new ResponseSurvey();
		responseSurveyDB.loadSurveyByCourseID(courseID, survey);
		List<AbstractQuestion> questions = responseSurveyDB.loadSurveyQuestionsBySurveyID(survey.getSurveyID());
		if (survey.isSurveyPublished()) {
			return "survey/surveyPublished";
		} else {
			List<QuestionForm> surveyQuestions = new ArrayList<>();
			for (AbstractQuestion question : questions) {
				QuestionForm questionForm = new QuestionForm(question);
				questionForm.setSelected(SELECTED);
				surveyQuestions.add(questionForm);
			}
			SurveyForm surveyForm = new SurveyForm();
			surveyForm.setSurveyQuestions(surveyQuestions);

			model.addAttribute(SURVEYFORM, surveyForm);
			model.addAttribute(QUESTIONS, questions);
			model.addAttribute(COURSEID, courseID);
			model.addAttribute(INSTRUCTORID, instructorID);
			model.addAttribute(SURVEY_ID, survey.getSurveyID());
			logger.info("Updating the Survey Information. Listing the Questions for updation process");
			return "survey/updateSurveyPage";
		}

	}

	@RequestMapping(value = "/survey/updateSurvey", method = RequestMethod.POST)
	public String updateSurvey(Model model, @RequestParam(name = SURVEY_ID) Integer surveyID,
			@ModelAttribute(SURVEYFORM) SurveyForm surveyForm) {

		ISurveyPersistence surveyDB = SurveyConfig.instance().getSurveyDB();
		List<Long> questionIdList = new ArrayList<>();
		for (QuestionForm question : surveyForm.getSurveyQuestions()) {
			System.out.println(question.getQuestion());
			System.out.println(question.getSelected());
			if (question.getSelected().equalsIgnoreCase(NOTSELECTED)) {
				questionIdList.add(question.getId());
			}
		}

		Survey survey = new Survey();
		survey.setSurveyID(surveyID);
		survey.setSurveyQuestions(questionIdList);
		survey.updateSurveyQuestions(surveyDB);
		logger.info("Update the Survey which is existing for the course");
		return "survey/surveyCreationSuccess";
	}

	@GetMapping("/survey/publishSurvey")
	public String publishSurvey(Model model, @RequestParam(name = COURSE_ID) long courseID) {
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		ResponseSurvey publishCheck = new ResponseSurvey();
		responseSurveyDB.loadSurveyByCourseID(courseID, publishCheck);
		if (publishCheck.isSurveyPublished()) {
			logger.info("List of Survey published");
			return "survey/surveyPublished";
		} else {
			ISurveyPersistence surveyDB = SurveyConfig.instance().getSurveyDB();
			Survey survey = new Survey();
			survey.setCourseID(courseID);
			survey.publish(surveyDB);
			logger.info("Publishing the survey successful");
			return "survey/surveyCreationSuccess";
		}

	}

}
