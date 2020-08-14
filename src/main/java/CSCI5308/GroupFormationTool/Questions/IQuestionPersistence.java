package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IQuestionPersistence {
	public boolean createFreeTextNumericalQuestion(AbstractQuestion q);

	public boolean createMultipleOptionsSingleOptionQuestion(AbstractQuestion q);

	public boolean createMultipleOptionsMultipleOptionQuestion(AbstractQuestion q);

	public boolean deleteQuestion(AbstractQuestion q);

	public List<AbstractQuestion> loadQuestionsByInstructorId(Integer instructorId);
}
