package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractQuestionTest {

	private static final String FREE_TEXT = "FREETEXT";
	private static final String NUMERIC = "NUMERIC";
	private static final String MCCM = "MCCM";
	private IAbstractQuestionFactory iAbstractQuestionFactory;
	private AbstractQuestion freeTextQuestion;

	@BeforeEach
	public void setUp(){
		iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(FREE_TEXT);
		freeTextQuestion = iAbstractQuestionFactory.createQuestion("Backend Tech", "Which Backend technologies are you familiar with?", FREE_TEXT, "2020-06-16", "B00853", null );
	}


	public void getIdTest() {
		AbstractQuestion question = new FreeTextQuestion();
		question.setId(7);
		assertTrue(question.getId() == 7);
	}

	public void setIdTest(long id) {
		AbstractQuestion question = new FreeTextQuestion();
		question.setId(7);
		assertTrue(question.getId() == 7);
	}

	public void getTitleTest() {
		assertTrue(freeTextQuestion.getTitle() == "Backend Tech");
	}

	public void setTitleTest(String title) {
		freeTextQuestion.setTitle("Programming lang");
		assertTrue(freeTextQuestion.getTitle() == "Programming lang");

	}

	public void getQuestionTest() {
		assertTrue(freeTextQuestion.getQuestion() == "Which Backend technologies are you familiar with?");
	}

	public void setQuestionTest(String question) {
		freeTextQuestion.setQuestion("Which Programming lang are you familiar with?");
		assertTrue(freeTextQuestion.getQuestion() == "Which Programming lang are you familiar with?");
	}

	public void getTypeTest() {
		assertTrue(freeTextQuestion.getType() == FREE_TEXT);
	}

	public void setTypeTest(String type) {
		freeTextQuestion.setType(NUMERIC);
		assertTrue(freeTextQuestion.getType() == NUMERIC);
	}

	public void getCreationDateTest() {
		assertTrue(freeTextQuestion.getCreationDate() == "2020-06-16");
	}

	public void setCreationDateTest(String creationDate) {
		freeTextQuestion.setCreationDate("2020-06-15");
		assertTrue(freeTextQuestion.getCreationDate() == "2020-06-16");
	}

	public void getCreatedByTest() {

		assertTrue(freeTextQuestion.getCreatedBy() == "B00853");
	}

	public void setCreatedByTest(String createdBy) {
		freeTextQuestion.setCreatedBy("B00852");
		assertTrue(freeTextQuestion.getCreatedBy() == "B00852");
	}

	public void getOptionsTest() {
		assertTrue(freeTextQuestion.getOptions() == null);
	}

	public void setOptionsTest() {
		Map<Integer, String> options = new HashMap<Integer, String>();
		options.put(1, "Spring");
		options.put(2, "ROR");
		options.put(3, "Django");
		AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Technologies", "Select Backend technologies that you familiar with from the following options?", MCCM, "2020-06-16", null, options );
		options.put(4, "Javascript");
		question.setOptions(options);
		assertTrue(question.getOptions() == options);
	}

	@Test
	public void canCreateQuestionTest() {
		String type = "MCCO";
		assertFalse(AbstractQuestion.canCreateQuestion(type));
		type = "FREETEXT";
		assertTrue(AbstractQuestion.canCreateQuestion(type));
	}

	@Test
	public void deleteQuestionTest() {
		IQuestionPersistence questionDBMock = new QuestionDBMock();
		AbstractQuestion question = new FreeTextQuestion();
		question.setId(1);
		assertTrue(question.deleteQuestion(questionDBMock));
	}

	@Test
	public void generateQuestionWithOptionsTest() {
		AbstractQuestion question = new MultipleChoiceSingleOptionQuestion();
		assertNotNull(question.generateQuestionWithOptions());
	}

	@Test
	public void addOptionTest() {
		Map<Integer, String> options = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			options.put(i, "");
		}
		AbstractQuestion question = new MultipleChoiceMultipleOptionQuestion();
		question.setOptions(options);
		assertTrue(question.addOption());
	}

	@Test
	public void removeOptionTest() {
		Map<Integer, String> options = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			options.put(i, "");
		}
		AbstractQuestion question = new MultipleChoiceMultipleOptionQuestion();
		question.setOptions(options);
		assertTrue(question.removeOption());

	}

	@Test
	public void sortQuestionsListTest() {
		IQuestionPersistence questionDBMock = new QuestionDBMock();
		List<AbstractQuestion> questionsList = questionDBMock.loadQuestionsByInstructorId(4);
		assertEquals(3, questionsList.size());

		AbstractQuestion.sortQuestionsList(questionsList, "title", "DESC");
		assertEquals("Backend Tech", questionsList.get(2).getTitle());
		assertEquals("Frontend Tech", questionsList.get(1).getTitle());
		assertEquals("Work Experience", questionsList.get(0).getTitle());

		AbstractQuestion.sortQuestionsList(questionsList, "title", "ASC");
		assertEquals("Backend Tech", questionsList.get(0).getTitle());
		assertEquals("Frontend Tech", questionsList.get(1).getTitle());
		assertEquals("Work Experience", questionsList.get(2).getTitle());

		AbstractQuestion.sortQuestionsList(questionsList, "date", "DESC");
		assertEquals("2020-06-14", questionsList.get(2).getCreationDate());
		assertEquals("2020-06-15", questionsList.get(1).getCreationDate());
		assertEquals("2020-06-16", questionsList.get(0).getCreationDate());

		AbstractQuestion.sortQuestionsList(questionsList, "date", "ASC");
		assertEquals("2020-06-14", questionsList.get(0).getCreationDate());
		assertEquals("2020-06-15", questionsList.get(1).getCreationDate());
		assertEquals("2020-06-16", questionsList.get(2).getCreationDate());
	}

	@Test
	public void createQuestionTest() {
		IQuestionPersistence questionDBMock = new QuestionDBMock();
		AbstractQuestion question = new FreeTextQuestion();
		question.setType("FREETEXT");
		assertTrue(question.saveQuestion(questionDBMock));
	}

	@Test
	public void createQuestionWithOptions() {
		IQuestionPersistence questionDBMock = new QuestionDBMock();
		AbstractQuestion question = new MultipleChoiceSingleOptionQuestion();
		question.setType("MCCO");
		assertTrue(question.saveQuestion(questionDBMock));
	}

}
