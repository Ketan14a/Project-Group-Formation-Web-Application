package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicyDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FreeTextQuestionFactory implements IAbstractQuestionFactory {
	private Logger logger = LoggerFactory.getLogger(FreeTextQuestionFactory.class);
	@Override
	public AbstractQuestion createQuestion(String title, String question, String type, String creationDate,
			String createdBy, Map<Integer, String> options) {
		FreeTextQuestion freeTextQuestion = new FreeTextQuestion();
		freeTextQuestion.setTitle(title);
		freeTextQuestion.setQuestion(question);
		freeTextQuestion.setType(type);
		freeTextQuestion.setCreationDate(creationDate);
		freeTextQuestion.setCreatedBy(createdBy);
		freeTextQuestion.setOptions(options);
		logger.info("Creating Question for the Instructor");
		return freeTextQuestion;
	}

    @Override
    public AbstractQuestion createQuestion() {
        return new FreeTextQuestion();
    }
}
