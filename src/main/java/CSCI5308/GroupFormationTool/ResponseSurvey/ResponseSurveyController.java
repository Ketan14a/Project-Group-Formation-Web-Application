package CSCI5308.GroupFormationTool.ResponseSurvey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Questions.*;
import java.util.List;
import java.util.Map;

@Controller
public class ResponseSurveyController {
	private Logger logger = LoggerFactory.getLogger(ResponseSurveyController.class);
	private static final String ID = "id";
	private List<AbstractQuestion> questions_list;

	@GetMapping("/survey/surveyInstructions")
	public String processSurveyInstructionsPage(Model model, @RequestParam(name = ID) long courseID) {
		logger.info("Processing SurveyInstructionsPage for courseID: {}", courseID);
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		ResponseSurvey responseSurvey = new ResponseSurvey();
		responseSurveyDB.loadSurveyByCourseID(courseID, responseSurvey);
		model.addAttribute("survey", responseSurvey);
		return "survey/surveyInstructions";
	}

	@RequestMapping(value = "/survey/studentSurvey", method = RequestMethod.POST)
	public String processStudentSurvey(Model model, @RequestParam(name = ID) long surveyID) {
		logger.info("Processing Student survey for surveyId: {}", surveyID);
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		List<AbstractQuestion> surveyQuestions = responseSurveyDB.loadSurveyQuestionsBySurveyID(surveyID);
		questions_list = surveyQuestions;
		model.addAttribute("surveyQuestions", surveyQuestions);
		return "survey/studentSurvey";
	}

	@GetMapping("/survey/studentSurvey")
	public String storeResponses(@RequestParam Map<String, String> searchParams) {
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		Map<Integer, String> responseMapper = responseSurveyDB.parseRequest(searchParams);

		Boolean result = responseSurveyDB.storeResponsesInDB(responseMapper, questions_list);

		if (result == false) {
			logger.error("Database Down Exception occured while storing response for searchParams: {}", searchParams);
			return "dbDownError";
		}
		return "index";
	}

}
