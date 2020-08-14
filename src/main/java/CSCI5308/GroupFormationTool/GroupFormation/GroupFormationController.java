package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.QuestionForm;
import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseSurveyPersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurvey;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurveyConfig;

@Controller
public class GroupFormationController {

	private Logger logger = LoggerFactory.getLogger(GroupFormationController.class);
	private static final String ID = "id";

	@GetMapping("/course/GroupFormationPolicies")
	public String getQuestions(Model model, @RequestParam(name = ID) long courseID) {
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		ResponseSurvey responseSurvey = new ResponseSurvey();
		responseSurveyDB.loadSurveyByCourseID(courseID, responseSurvey);
		List<AbstractQuestion> questions = responseSurveyDB.loadSurveyQuestionsBySurveyID(responseSurvey.getSurveyID());
		List<QuestionForm> surveyQuestions = new ArrayList<>();
		logger.info("Get Survey question for Course ID : {}",courseID);
		for (AbstractQuestion question : questions) {
			QuestionForm questionForm = new QuestionForm(question);
			surveyQuestions.add(questionForm);
		}
		PolicyForm policyForm = new PolicyForm();
		policyForm.setSurveyQuestions(surveyQuestions);
		logger.info("Adding survey and policyForm Attribute to model");
		model.addAttribute("survey",responseSurvey);
		model.addAttribute("policyForm",policyForm);
		return "survey/policySetting.html";
		
	}

	@PostMapping("/survey/setRules")
	public String setPolicies(Model model,@ModelAttribute("policyForm") PolicyForm policyForm, @RequestParam(name = ID) long surveyID) {
		logger.info("Setting Policies for group formation");
		List <IGroupFormationPolicies> listOfGroupFormationPolicies = new ArrayList<IGroupFormationPolicies>();
		for(QuestionForm question : policyForm.getSurveyQuestions()) {
			IGroupFormationPolicies groupFormationPolicies = new GroupFormationPolicies(surveyID,policyForm.getGroupCount(),question);
		  listOfGroupFormationPolicies.add(groupFormationPolicies);
		  }
		GroupFormationPolicies.createPolicy(listOfGroupFormationPolicies,GroupFormationConfig.instance().getGroupFormationPolicyDB());
		Map<Integer,Map<User,List<String>>> groups = new GroupFormationService().createGroups(listOfGroupFormationPolicies,
				GroupFormationConfig.instance().getGroupFormationResponseDb(), AccessControlConfig.instance().getUserDB());
		logger.info("Adding the questions and groups to model for group formation");
		model.addAttribute("questions",policyForm.getSurveyQuestions());
		model.addAttribute("groups",groups);
		return "survey/showGroups.html";
	
	}
}
