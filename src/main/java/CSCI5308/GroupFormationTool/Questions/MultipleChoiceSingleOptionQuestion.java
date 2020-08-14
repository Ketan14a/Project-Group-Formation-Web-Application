package CSCI5308.GroupFormationTool.Questions;

public class MultipleChoiceSingleOptionQuestion extends AbstractQuestion {

	public MultipleChoiceSingleOptionQuestion() {
		setDefaults();
	}

	public boolean saveQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.createMultipleOptionsSingleOptionQuestion(this);
	}

	public boolean deleteQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.deleteQuestion(this);
	}

}
