package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericQuestionFactoryTest {

    private static final String NUMERIC = "NUMERIC";
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(NUMERIC);
    }

    @Test
    public void createQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Spring rating", "Rate yourself in a scale of 1 to 10 on Spring?", NUMERIC, "2020-06-15", null, null );
        assertTrue(question instanceof NumericQuestion);
        assertEquals("Spring rating", question.getTitle());
        assertEquals("Rate yourself in a scale of 1 to 10 on Spring?", question.getQuestion());
        assertEquals(NUMERIC, question.getType());
    }
}

