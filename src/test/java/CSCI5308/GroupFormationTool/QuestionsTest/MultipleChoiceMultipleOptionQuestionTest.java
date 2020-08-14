package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceMultipleOptionQuestionTest {

    private static final String MCCM = "MCCM";
    private IQuestionPersistence questionPersistence;
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        questionPersistence = new QuestionDBMock();
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(MCCM);
    }

    @Test
    public void saveQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Spring");
        options.put(2, "ROR");
        options.put(3, "Django");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Technologies", "Select Backend technologies that you familiar with from the following options?", MCCM, "2020-06-16", null, options );
        assertTrue(question.saveQuestion(questionPersistence));
    }

    @Test
    public void deleteQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Spring");
        options.put(2, "ROR");
        options.put(3, "Django");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Technologies", "Select Backend technologies that you familiar with from the following options?", MCCM, "2020-06-16", null, options );
        assertTrue(question.deleteQuestion(questionPersistence));
    }
}
