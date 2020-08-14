package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicyDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryProvider {
	private static Logger logger = LoggerFactory.getLogger(FactoryProvider.class);

	public static final String MCCO = "MCCO";
	public static final String MCCM = "MCCM";
	public static final String FREE_TEXT = "FREETEXT";
	public static final String NUMERIC = "NUMERIC";

	public static IAbstractQuestionFactory getQuestionAbstractFactory(String type) {
		logger.info("Providing Question type to the user");
		if (type.matches(MCCO)) {
			return new MultipleChoiceSingleOptionQuestionFactory();
		} else if (type.matches(MCCM)) {
			return new MultipleChoiceMultipleOptionQuestionFactory();
		} else if (type.matches(FREE_TEXT)) {
			return new FreeTextQuestionFactory();
		} else if (type.matches(NUMERIC)) {
			return new NumericQuestionFactory();
		}
		return null;
	}
}
