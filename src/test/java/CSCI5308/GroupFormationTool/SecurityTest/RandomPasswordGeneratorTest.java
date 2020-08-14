package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IRandomPasswordGenerator;
import CSCI5308.GroupFormationTool.Security.RandomPasswordGenerator;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomPasswordGeneratorTest {

	@Test
	public void generateTest() {
		IRandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
		String password = randomPasswordGenerator.generate();
		assertTrue(password.contains("@"));
		assertTrue(password.contains("#"));
		assertTrue(Character.isLowerCase(password.charAt(0)));
		assertTrue(Character.isDigit(password.charAt(password.indexOf("#") + 1)));
		assertTrue(Character.isUpperCase(password.charAt(password.length() - 1)));
	}

}
