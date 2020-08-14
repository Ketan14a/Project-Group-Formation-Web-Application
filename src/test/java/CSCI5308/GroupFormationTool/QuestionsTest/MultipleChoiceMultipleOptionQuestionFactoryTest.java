package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceMultipleOptionQuestionFactoryTest {

    private static final String MCCM = "MCCM";
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(MCCM);
    }

    @Test
    public void createQuestionTest(){
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "Spring");
        options.put(2, "ROR");
        options.put(3, "Django");
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Technologies", "Select Backend technologies that you familiar with from the following options?", MCCM, "2020-06-16", null, options );
        assertTrue(question instanceof MultipleChoiceMultipleOptionQuestion);
        assertEquals("Backend Technologies", question.getTitle());
        assertEquals("Select Backend technologies that you familiar with from the following options?", question.getQuestion());
        assertEquals(MCCM, question.getType());
    }
}
