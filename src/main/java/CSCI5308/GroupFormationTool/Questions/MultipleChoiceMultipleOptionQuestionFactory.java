package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicyDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MultipleChoiceMultipleOptionQuestionFactory implements IAbstractQuestionFactory {

	private Logger logger = LoggerFactory.getLogger(MultipleChoiceMultipleOptionQuestionFactory.class);

	@Override
	public AbstractQuestion createQuestion(String title, String question, String type, String creationDate,
			String createdBy, Map<Integer, String> options) {
		MultipleChoiceMultipleOptionQuestion multipleChoiceMultipleOptionQuestion = new MultipleChoiceMultipleOptionQuestion();
		multipleChoiceMultipleOptionQuestion.setTitle(title);
		multipleChoiceMultipleOptionQuestion.setQuestion(question);
		multipleChoiceMultipleOptionQuestion.setType(type);
		multipleChoiceMultipleOptionQuestion.setCreationDate(creationDate);
		multipleChoiceMultipleOptionQuestion.setCreatedBy(createdBy);
		multipleChoiceMultipleOptionQuestion.setOptions(options);
		logger.info("Adding MCCM Question for the Instructor");
		return multipleChoiceMultipleOptionQuestion;
	}

	@Override
	public AbstractQuestion createQuestion() {
		return new MultipleChoiceMultipleOptionQuestion();
	}

}
