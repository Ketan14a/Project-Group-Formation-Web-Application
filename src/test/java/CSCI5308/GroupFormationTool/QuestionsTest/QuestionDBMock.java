package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class QuestionDBMock implements IQuestionPersistence {

	@Override
	public boolean createFreeTextNumericalQuestion(AbstractQuestion q) {

		try {
			System.out.println("Question title:" + q.getTitle());
			System.out.println("Question date:" + q.getCreationDate());
			System.out.println("Question:" + q.getQuestion());
		}

		catch (Exception e) {
			return false;
		}

		return true;

	}

	@Override
	public boolean deleteQuestion(AbstractQuestion q) {
		return true;
	}

	@Override
	public List<AbstractQuestion> loadQuestionsByInstructorId(Integer instructorId) {
		List<AbstractQuestion> questions = new ArrayList<>();
		Map<Integer, String> options = new HashMap<Integer, String>();
		AbstractQuestion q1 = new FreeTextQuestion();
		q1.setId(1);
		q1.setTitle("Backend Tech");
		q1.setCreationDate("2020-06-16");
		AbstractQuestion q2 = new MultipleChoiceMultipleOptionQuestion();
		q2.setId(2);
		q2.setTitle("Work Experience");
		q2.setCreationDate("2020-06-15");
		q2.setType("MCCM");
		options.put(1, "O1");
		options.put(2, "O2");
		options.put(3, "O3");
		q2.setOptions(options);
		AbstractQuestion q3 = new MultipleChoiceMultipleOptionQuestion();
		q3.setId(3);
		q3.setTitle("Frontend Tech");
		q3.setCreationDate("2020-06-14");
		q2.setOptions(options);
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		return questions;
	}

	@Override
	public boolean createMultipleOptionsSingleOptionQuestion(AbstractQuestion q) {
		try {
			System.out.println("Question title:" + q.getTitle());
			System.out.println("Question date:" + q.getCreationDate());
			System.out.println("Question:" + q.getQuestion());
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean createMultipleOptionsMultipleOptionQuestion(AbstractQuestion q) {
		try {
			System.out.println("Question title:" + q.getTitle());
			System.out.println("Question date:" + q.getCreationDate());
			System.out.println("Question:" + q.getQuestion());
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

}
