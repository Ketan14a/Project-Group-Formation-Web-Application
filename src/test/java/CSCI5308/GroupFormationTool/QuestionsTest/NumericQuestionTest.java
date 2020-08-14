package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.FactoryProvider;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.IAbstractQuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericQuestionTest {

    private static final String NUMERIC = "NUMERIC";
    private IQuestionPersistence questionPersistence;
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        questionPersistence = new QuestionDBMock();
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(NUMERIC);
    }

    @Test
    public void saveQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Spring rating", "Rate yourself in a scale of 1 to 10 on Spring?", NUMERIC, "2020-06-15", null, null );
        assertTrue(question.saveQuestion(questionPersistence));
    }

    @Test
    public void deleteQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Spring rating", "Rate yourself in a scale of 1 to 10 on Spring?", NUMERIC, "2020-06-15", null, null );
        assertTrue(question.deleteQuestion(questionPersistence));
    }
}
