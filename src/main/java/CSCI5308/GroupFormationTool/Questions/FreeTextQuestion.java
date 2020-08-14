package CSCI5308.GroupFormationTool.Questions;

public class FreeTextQuestion extends AbstractQuestion {

	public FreeTextQuestion() {
		setDefaults();
	}

	public boolean saveQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.createFreeTextNumericalQuestion(this);
	}

	public boolean deleteQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.deleteQuestion(this);
	}
}
