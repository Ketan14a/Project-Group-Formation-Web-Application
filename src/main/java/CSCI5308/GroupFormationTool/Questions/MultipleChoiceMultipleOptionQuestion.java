package CSCI5308.GroupFormationTool.Questions;

public class MultipleChoiceMultipleOptionQuestion extends AbstractQuestion {

	public MultipleChoiceMultipleOptionQuestion() {
		setDefaults();
	}

	public boolean saveQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.createMultipleOptionsMultipleOptionQuestion(this);
	}

	public boolean deleteQuestion(IQuestionPersistence iQuestionPersistence) {
		return iQuestionPersistence.deleteQuestion(this);
	}

}
