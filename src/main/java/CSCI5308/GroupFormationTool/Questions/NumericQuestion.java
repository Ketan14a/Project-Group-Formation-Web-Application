package CSCI5308.GroupFormationTool.Questions;

public class NumericQuestion extends AbstractQuestion {
	public NumericQuestion() {
		setDefaults();
	}

	public boolean saveQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.createFreeTextNumericalQuestion(this);
	}

	public boolean deleteQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.deleteQuestion(this);
	}
}
