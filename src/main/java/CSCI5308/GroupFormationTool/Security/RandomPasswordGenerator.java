package CSCI5308.GroupFormationTool.Security;

import java.util.Random;

public class RandomPasswordGenerator implements IRandomPasswordGenerator {
	@Override
	public String generate() {
		Random random = new Random();
		int lengthOfPassword = random.nextInt(8) + 9;
		int lengthOfLowerCase = lengthOfPassword / 3;
		int lengthOfUpperCase = lengthOfPassword - lengthOfLowerCase - 4;
		String generatedLowercaseString = random.ints(97, 122).limit(lengthOfLowerCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		String generatedUpperCaseString = random.ints(65, 90).limit(lengthOfUpperCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		String generatedNumberString = random.ints(48, 57).limit(2)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedLowercaseString + "#" + generatedNumberString + "@" + generatedUpperCaseString;
	}

}