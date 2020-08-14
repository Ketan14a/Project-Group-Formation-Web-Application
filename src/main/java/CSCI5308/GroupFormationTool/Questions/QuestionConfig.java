package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionConfig {
	private static Logger logger = LoggerFactory.getLogger(QuestionConfig.class);
	private static QuestionConfig uniqueInstance = null;

	private IQuestionPersistence questionDB;

	private QuestionConfig() {
		questionDB = new QuestionDB();
	}

	public static QuestionConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new QuestionConfig();
		}else{
			logger.error("No unique instance for Question Configuration");
		}

		return uniqueInstance;
	}

	public IQuestionPersistence getQuestionDB() {
		return questionDB;
	}

	public void setQuestionDB(IQuestionPersistence questionDB) {
		this.questionDB = questionDB;
	}

}
