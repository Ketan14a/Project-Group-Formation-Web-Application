package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicies;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;

public class GroupFormationPoliciesTest  {

	@Test
	public void getGroupCount() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setGroupCount(2);
		assertEquals(2,policies.getGroupCount());

	}

	@Test
	public void setGroupCount() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setGroupCount(2);
		assertEquals(2,policies.getGroupCount());
	}

	@Test
	public void getSurveyID() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setSurveyID(2);
		assertEquals(2,policies.getSurveyID());
	}

	@Test
	public void setSurveyID() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setSurveyID(2);
		assertEquals(2,policies.getSurveyID());
	}

	@Test
	public void getQuestionID() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setQuestionID(2);
		assertEquals(2,policies.getQuestionID());
	}

	@Test
	public void setQuestionID() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setQuestionID(2);
		assertEquals(2,policies.getQuestionID());
	}

	@Test
	public void getPolicyType() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setPolicyType("similar");
		assertEquals("similar",policies.getPolicyType());
	}

	@Test
	public void setPolicyType() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setPolicyType("similar");
		assertEquals("similar",policies.getPolicyType());
	}

	@Test
	public void getValue() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setValue(2);
		assertEquals(2,policies.getValue());
	}

	@Test
	public void setValue() {
		IGroupFormationPolicies policies = new GroupFormationPolicies();
		policies.setValue(2);
		assertEquals(2,policies.getValue());
	}

}
