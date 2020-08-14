package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.IRolePersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Controller
public class QuestionManagerController {

	private Logger logger = LoggerFactory.getLogger(QuestionManagerController.class);
	private static final String INSTRUCTOR_ID = "instructorID";
	private static final String ASC = "ASC";
	private static final String DESC = "DESC";
	public static final String MCCM = "MCCM";

	@GetMapping("/question/QuestionManager")
	public String process(Model model) {
		model.addAttribute("question", new QuestionForm());
		return "question/QuestionManager";
	}

	@PostMapping("/question/QuestionManager")
	public ModelAndView addQuestion(@ModelAttribute("question") QuestionForm questionForm, BindingResult bindingResult,
			Model model) {
		ModelAndView modelAndView = null;
		User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();

		if (AbstractQuestion.isTitleValid(questionForm.getTitle())
				&& AbstractQuestion.isQuestionValid(questionForm.getQuestion())) {
			IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
					.getQuestionAbstractFactory(questionForm.getType());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			AbstractQuestion question = iAbstractQuestionFactory.createQuestion(questionForm.getTitle(),
					questionForm.getQuestion(), questionForm.getType(), formatter.format(new Date()),
					String.valueOf(currentUser.getID()), questionForm.getOptions());

			if (AbstractQuestion.canCreateQuestion(questionForm.getType())) {
				logger.info("Persisting Question {} in Database", question);
				IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
				boolean saved = question.saveQuestion(questionDB);
				if (saved) {
					logger.info("Question successfully saved in Database");
					modelAndView = new ModelAndView("question/QuestionManager");
					modelAndView.addObject("message",
							"Question Entered Successfully! Please Continue with adding new question.");
				} else {
					logger.error("Question was not saved in Database");
					modelAndView = new ModelAndView("question/QuestionManager");
					modelAndView.addObject("message", "Question Error ! Please retry");
				}
			} else {
				logger.info("generate Question With Options for MultipleChoiceQuestions");
				question.generateQuestionWithOptions();
				modelAndView = new ModelAndView("question/CreationNext");
				modelAndView.addObject("question", question);
			}
		} else {
			modelAndView = new ModelAndView("question/QuestionManager");
			modelAndView.addObject("message", "Question Error ! Please retry");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/question/CreationNext", method = RequestMethod.POST, params = "action=AddOption")
	public ModelAndView addOptions(@ModelAttribute("question") QuestionForm questionForm) {
		ModelAndView modelAndView = new ModelAndView("question/CreationNext");
		IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
				.getQuestionAbstractFactory(questionForm.getType());
		AbstractQuestion question = iAbstractQuestionFactory.createQuestion(questionForm.getTitle(),
				questionForm.getQuestion(), questionForm.getType(), questionForm.getCreationDate(),
				questionForm.getCreatedBy(), questionForm.getOptions());
		question.addOption();
		modelAndView.addObject("question", question);
		return modelAndView;
	}

	@RequestMapping(value = "/question/CreationNext", method = RequestMethod.POST, params = "action=DeleteOption")
	public ModelAndView removeOptions(@ModelAttribute("question") QuestionForm questionForm) {
		ModelAndView modelAndView = new ModelAndView("question/CreationNext");
		IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
				.getQuestionAbstractFactory(questionForm.getType());
		AbstractQuestion question = iAbstractQuestionFactory.createQuestion(questionForm.getTitle(),
				questionForm.getQuestion(), questionForm.getType(), questionForm.getCreationDate(),
				questionForm.getCreatedBy(), questionForm.getOptions());
		question.removeOption();
		modelAndView.addObject("question", question);
		return modelAndView;
	}

	@RequestMapping(value = "/question/CreationNext", method = RequestMethod.POST, params = "action=CreateQuestion")
	public ModelAndView addQuestions(@ModelAttribute("question") QuestionForm questionForm, Model model) {
		ModelAndView modelAndView = new ModelAndView("question/CreationNext");
		IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider
				.getQuestionAbstractFactory(questionForm.getType());
		AbstractQuestion question = iAbstractQuestionFactory.createQuestion(questionForm.getTitle(),
				questionForm.getQuestion(), questionForm.getType(), questionForm.getCreationDate(),
				questionForm.getCreatedBy(), questionForm.getOptions());
		IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
		boolean saved = question.saveQuestion(questionDB);
		if (saved) {
			logger.info("MCQ Question {} successfully saved in Database", question);
			modelAndView = new ModelAndView("question/QuestionManager");
			modelAndView.addObject("message",
					"Question Entered Successfully! Please Continue with adding new question.");
		} else {
			logger.error("MCQ Question {} was not saved.", question);
			modelAndView = new ModelAndView("question/QuestionManager");
			modelAndView.addObject("message", "Question Error ! Please retry");
		}
		return modelAndView;
	}

	@GetMapping("/question/QuestionManagerMainPage")
	public String anotherProcess(Model model) {
		User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
		if (null == currentUser) {
			logger.info("AccessDenied, currentUser is null");
			return "accessdenied";
		}
		IRolePersistence roleDB = AccessControlConfig.instance().getRoleDB();
		List<String> roles = roleDB.loadRolesByUserID(currentUser.getID());
		if (roles.contains(Role.INSTRUCTOR.toString())) {
			model.addAttribute("instructorID", currentUser.getID());
			model.addAttribute("orderType", ASC);
			return "QuestionManagerMainPage";
		} else {
			return "accessdenied";
		}
	}

	@RequestMapping(value = "/question/delete", method = RequestMethod.POST)
	public String deleteOptions(Model model, @RequestParam(value = "action", required = true) long id) {
		logger.info("Deleting Question with Id {}", String.valueOf(id));
		IQuestionPersistence questionPersistence = QuestionConfig.instance().getQuestionDB();
		IAbstractQuestionFactory iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(MCCM);
		AbstractQuestion question = iAbstractQuestionFactory.createQuestion();;
		question.setId(id);
		IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
		question.deleteQuestion(questionDB);
		User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
		model.addAttribute("instructorID", currentUser.getID());
		model.addAttribute("orderType", ASC);
		return "QuestionManagerMainPage";

	}

	@GetMapping("/question/deleteQuestions")
	public String deleteQuestions(Model model, @RequestParam(name = INSTRUCTOR_ID) Integer instructorID,
			@RequestParam(name = "orderBy") String orderBy, @RequestParam(name = "orderType") String orderType) {

		IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
		List<AbstractQuestion> questions = questionDB.loadQuestionsByInstructorId(instructorID);
		AbstractQuestion.sortQuestionsList(questions, orderBy, orderType);
		model.addAttribute("questions", questions);
		model.addAttribute("instructorID", instructorID);
		if (orderType.equals(DESC)) {
			model.addAttribute("orderType", ASC);
		} else {
			model.addAttribute("orderType", DESC);
		}

		return "question/deleteQuestions";

	}

	@GetMapping("/question/listQuestions")
	public String listQuestions(Model model, @RequestParam(name = INSTRUCTOR_ID) Integer instructorID,
			@RequestParam(name = "orderBy") String orderBy, @RequestParam(name = "orderType") String orderType) {

		User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
		if (null == currentUser) {
			logger.info("AccessDenied, CurrentUser is null!");
			return "accessdenied";
		}
		IRolePersistence roleDB = AccessControlConfig.instance().getRoleDB();
		List<String> roles = roleDB.loadRolesByUserID(currentUser.getID());

		if (roles.contains(Role.INSTRUCTOR.toString())) {
			IQuestionPersistence questionDB = QuestionConfig.instance().getQuestionDB();
			List<AbstractQuestion> questions = questionDB.loadQuestionsByInstructorId(instructorID);
			AbstractQuestion.sortQuestionsList(questions, orderBy, orderType);
			model.addAttribute("questions", questions);
			model.addAttribute("instructorID", instructorID);
			if (orderType.equals(DESC)) {
				model.addAttribute("orderType", ASC);
			} else {
				model.addAttribute("orderType", DESC);
			}

			return "question/listQuestions";
		} else {
			logger.info("User {} is unauthorized to access listQuestions", currentUser);
			return "accessdenied";
		}
	}
}
