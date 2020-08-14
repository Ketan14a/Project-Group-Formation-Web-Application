package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.AbstractQuestion;
import CSCI5308.GroupFormationTool.Questions.FactoryProvider;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.IAbstractQuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceSingleOptionQuestionTest {

    private static final String MCCO = "MCCO";
    private IQuestionPersistence questionPersistence;
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        questionPersistence = new QuestionDBMock();
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(MCCO);
    }

    @Test
    public void saveQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Java");
        options.put(2, "Ruby");
        options.put(3, "Python");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Preferred Programming language", "Select one programming lang that you are most comfortable with?", MCCO, "2020-06-16", null, options );
        assertTrue(question.saveQuestion(questionPersistence));
    }

    @Test
    public void deleteQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Java");
        options.put(2, "Ruby");
        options.put(3, "Python");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Preferred Programming language", "Select one programming lang that you are most comfortable with?", MCCO, "2020-06-16", null, options );
        assertTrue(question.deleteQuestion(questionPersistence));
    }
}
