package CSCI5308.GroupFormationTool.Questions;

import java.util.Map;

public interface IAbstractQuestionFactory {

     public AbstractQuestion createQuestion(String title, String question, String type, String creationDate, String createdBy, Map<Integer, String> options);
     public AbstractQuestion createQuestion();
}
