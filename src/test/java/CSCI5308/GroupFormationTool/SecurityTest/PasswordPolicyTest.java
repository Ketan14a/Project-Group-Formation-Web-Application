package CSCI5308.GroupFormationTool.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;

public class PasswordPolicyTest {

	@Test
	public void ContstructorTests() {

		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);

		assertEquals(1, passwordPolicy.getMinLength());
		assertEquals(10, passwordPolicy.getMaxLength());
		assertEquals(1, passwordPolicy.getMinLowerLength());
		assertEquals(1, passwordPolicy.getMinUpperLength());
		assertEquals(1, passwordPolicy.getMinSpecial());
		assertEquals("$", passwordPolicy.getNotAllowed());
		assertEquals(3, passwordPolicy.getMinHistory());

		assertTrue(passwordPolicy.isMinLengthFlag());
		assertTrue(passwordPolicy.isMaxLengthFlag());
		assertTrue(passwordPolicy.isMinLowerLengthFlag());
		assertTrue(passwordPolicy.isMinUpperLengthFlag());
		assertTrue(passwordPolicy.isMinSpecialFlag());
		assertTrue(passwordPolicy.isNotAllowedFlag());
		assertTrue(passwordPolicy.isMinHistoryFlag());
	}

	@Test
	public void getMinLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLength(1);
		assertEquals(1, passwordPolicy.getMinLength());
	}

	@Test
	public void getMaxLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMaxLength(1);
		assertEquals(1, passwordPolicy.getMaxLength());
	}

	@Test
	public void getMinLowerLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLowerLength(1);
		assertEquals(1, passwordPolicy.getMinLowerLength());
	}

	@Test
	public void getMinUpperLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinUpperLength(1);
		assertEquals(1, passwordPolicy.getMinUpperLength());
	}

	@Test
	public void getMinSpecialTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinSpecial(1);
		assertEquals(1, passwordPolicy.getMinSpecial());
	}

	@Test
	public void getNotAllowedTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setNotAllowed("$");
		assertEquals("$", passwordPolicy.getNotAllowed());
	}

	@Test
	public void getMinHistoryTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinHistory(1);
		assertEquals(1, passwordPolicy.getMinHistory());
	}

	@Test
	public void isMinLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLengthFlag(true);
		assertTrue(passwordPolicy.isMaxLengthFlag());
	}

	@Test
	public void isMaxLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMaxLengthFlag(true);
		assertTrue(passwordPolicy.isMaxLengthFlag());
	}

	@Test
	public void isMinLowerLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLowerLengthFlag(true);
		assertTrue(passwordPolicy.isMinLowerLengthFlag());
	}

	@Test
	public void isMinUpperLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinUpperLengthFlag(true);
		assertTrue(passwordPolicy.isMinUpperLengthFlag());
	}

	@Test
	public void isMinSpecialFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinSpecialFlag(true);
		assertTrue(passwordPolicy.isMinSpecialFlag());
	}

	@Test
	public void isNotAllowedFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setNotAllowedFlag(true);
		assertTrue(passwordPolicy.isNotAllowedFlag());
	}

	@Test
	public void isMinHistoryFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinHistoryFlag(true);
		assertTrue(passwordPolicy.isMinHistoryFlag());
	}

	@Test
	public void setMinLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLength(1);
		assertEquals(1, passwordPolicy.getMinLength());
	}

	@Test
	public void setMaxLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMaxLength(1);
		assertEquals(1, passwordPolicy.getMaxLength());
	}

	@Test
	public void setMinLowerLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLowerLength(1);
		assertEquals(1, passwordPolicy.getMinLowerLength());
	}

	@Test
	public void setMinUpperLengthTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinUpperLength(1);
		assertEquals(1, passwordPolicy.getMinUpperLength());
	}

	@Test
	public void setMinSpecialTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinSpecial(1);
		assertEquals(1, passwordPolicy.getMinSpecial());
	}

	@Test
	public void setNotAllowedTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setNotAllowed("$");
		assertEquals("$", passwordPolicy.getNotAllowed());
	}

	@Test
	public void setMinHistoryTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinHistory(1);
		assertEquals(1, passwordPolicy.getMinHistory());
	}

	@Test
	public void setMinLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLengthFlag(true);
		assertTrue(passwordPolicy.isMaxLengthFlag());
	}

	@Test
	public void setMaxLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMaxLengthFlag(true);
		assertTrue(passwordPolicy.isMaxLengthFlag());
	}

	@Test
	public void setMinLowerLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinLowerLengthFlag(true);
		assertTrue(passwordPolicy.isMinLowerLengthFlag());
	}

	@Test
	public void setMinUpperLengthFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinUpperLengthFlag(true);
		assertTrue(passwordPolicy.isMinUpperLengthFlag());
	}

	@Test
	public void setMinSpecialFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinSpecialFlag(true);
		assertTrue(passwordPolicy.isMinSpecialFlag());
	}

	@Test
	public void setNotAllowedFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setNotAllowedFlag(true);
		assertTrue(passwordPolicy.isNotAllowedFlag());
	}

	@Test
	public void setMinHistoryFlagTest() {
		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy passwordPolicy = PasswordPolicy.getInstance(persistence);
		passwordPolicy.setMinHistoryFlag(true);
		assertTrue(passwordPolicy.isMinHistoryFlag());
	}

	@Test
	public void policiesTest() {

		IPasswordPolicyPersistence persistence = new PasswordPolicyDBMock();
		PasswordPolicy.getInstance(persistence);
		assertNotNull(PasswordPolicy.getPolicies());

	}

}
