package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.FactoryProvider;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.IAbstractQuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreeTextQuestionTest {

    private static final String FREE_TEXT = "FREETEXT";
    private IQuestionPersistence questionPersistence;
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        questionPersistence = new QuestionDBMock();
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(FREE_TEXT);
    }

    @Test
    public void saveQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Tech", "Which Backend technologies are you familiar with?", FREE_TEXT, "2020-06-16", null, null );
        assertTrue(question.saveQuestion(questionPersistence));
    }

    @Test
    public void deleteQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Tech", "Which Backend technologies are you familiar with?", FREE_TEXT, "2020-06-16", null, null );
        assertTrue(question.deleteQuestion(questionPersistence));
    }

}
