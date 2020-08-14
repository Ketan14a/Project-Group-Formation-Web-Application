package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreeTextQuestionFactoryTest {
    private static final String FREE_TEXT = "FREETEXT";
    private IAbstractQuestionFactory iAbstractQuestionFactory;

    @BeforeEach
    public void setUp(){
        iAbstractQuestionFactory = FactoryProvider.getQuestionAbstractFactory(FREE_TEXT);
    }

    @Test
    public void createQuestionTest(){
        AbstractQuestion question = iAbstractQuestionFactory.createQuestion("Backend Tech", "Which Backend technologies are you familiar with?", FREE_TEXT, "2020-06-16", null, null );
        assertTrue(question instanceof FreeTextQuestion);
        assertEquals("Backend Tech", question.getTitle());
        assertEquals("Which Backend technologies are you familiar with?", question.getQuestion());
        assertEquals(FREE_TEXT, question.getType());
    }
}
