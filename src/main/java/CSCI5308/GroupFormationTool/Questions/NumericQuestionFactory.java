package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class NumericQuestionFactory implements IAbstractQuestionFactory {

	private Logger logger = LoggerFactory.getLogger(NumericQuestionFactory.class);

	@Override
	public AbstractQuestion createQuestion(String title, String question, String type, String creationDate,
			String createdBy, Map<Integer, String> options) {
		NumericQuestion numericQuestion = new NumericQuestion();
		numericQuestion.setTitle(title);
		numericQuestion.setQuestion(question);
		numericQuestion.setType(type);
		numericQuestion.setCreationDate(creationDate);
		numericQuestion.setCreatedBy(createdBy);
		numericQuestion.setOptions(options);
		logger.info("Adding the Numeric Question to the Question List");
		return numericQuestion;
	}

	@Override
	public AbstractQuestion createQuestion() {
		return new NumericQuestion();
	}

}
