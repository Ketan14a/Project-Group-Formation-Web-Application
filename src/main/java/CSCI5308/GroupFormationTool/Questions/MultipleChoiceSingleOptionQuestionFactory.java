package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicyDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MultipleChoiceSingleOptionQuestionFactory implements IAbstractQuestionFactory {
	private Logger logger = LoggerFactory.getLogger(MultipleChoiceSingleOptionQuestionFactory.class);
	@Override
	public AbstractQuestion createQuestion(String title, String question, String type, String creationDate,
			String createdBy, Map<Integer, String> options) {
		MultipleChoiceSingleOptionQuestion multipleChoiceSingleOptionQuestion = new MultipleChoiceSingleOptionQuestion();
		multipleChoiceSingleOptionQuestion.setTitle(title);
		multipleChoiceSingleOptionQuestion.setQuestion(question);
		multipleChoiceSingleOptionQuestion.setType(type);
		multipleChoiceSingleOptionQuestion.setCreationDate(creationDate);
		multipleChoiceSingleOptionQuestion.setCreatedBy(createdBy);
		multipleChoiceSingleOptionQuestion.setOptions(options);
		logger.info("Adding MCCO Question for the Instructor");
		return multipleChoiceSingleOptionQuestion;
	}

    @Override
    public AbstractQuestion createQuestion() {
        return new MultipleChoiceSingleOptionQuestion();
    }
}
