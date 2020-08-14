package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceSingleOptionQuestionFactoryTest {
    private static final String MCCO = "MCCO";
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(MCCO);
    }

    @Test
    public void createQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Java");
        options.put(2, "Ruby");
        options.put(3, "Python");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Preferred Programming language", "Select one programming lang that you are most comfortable with?", MCCO, "2020-06-16", null, options );
        assertTrue(question instanceof MultipleChoiceSingleOptionQuestion);
        assertEquals("Preferred Programming language", question.getTitle());
        assertEquals("Select one programming lang that you are most comfortable with?", question.getQuestion());
        assertEquals(MCCO, question.getType());
    }
}
