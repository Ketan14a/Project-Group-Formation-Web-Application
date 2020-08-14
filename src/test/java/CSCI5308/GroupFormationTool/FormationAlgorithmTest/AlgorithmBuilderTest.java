package CSCI5308.GroupFormationTool.FormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.FormationAlgorithms.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicies;

public class AlgorithmBuilderTest {

	@Test
	public void getUserMCCOResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserMCCOResponses(new HashMap<String, Map<Long, String>>());
		assertNotNull(builder.getUserMCCOResponses());
	}
	
	@Test
	public void setUserMCCOResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserMCCOResponses(new HashMap<String, Map<Long, String>>());
		assertNotNull(builder.getUserMCCOResponses());
	}
	
	@Test
	public void getUserNumericResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserNumericResponses(new HashMap<String, Map<Long, Integer>>());
		assertNotNull(builder.getUserNumericResponses());
	}
	
	@Test
	public void setUserNumericResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserNumericResponses(new HashMap<String, Map<Long, Integer>>());
		assertNotNull(builder.getUserNumericResponses());
	}
	
	@Test
	public void getUserFreeTextResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserFreeTextResponses(new HashMap<String, Map<Long, String>>());
		assertNotNull(builder.getUserFreeTextResponses());
	}

	@Test
	public void setUserFreeTextResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserFreeTextResponses(new HashMap<String, Map<Long, String>>());
		assertNotNull(builder.getUserFreeTextResponses());
	}
	
	@Test
	public void getUserMCCMResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserMCCMResponses(new HashMap<String, Map<Long, List<String>>>());
		assertNotNull(builder.getUserMCCMResponses());
	}
	
	@Test
	public void setUserMCCMResponses() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setUserMCCMResponses(new HashMap<String, Map<Long, List<String>>>());
		assertNotNull(builder.getUserMCCMResponses());
	}
	
	@Test
	public void getAlgorithm() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setAlgorithm("random");
		assertEquals("random",builder.getAlgorithm());
	}
	
	@Test
	public void setAlgorithm() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setAlgorithm("random");
		assertEquals("random",builder.getAlgorithm());
	}
	
	@Test
	public void getQuestionPolicies() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setQuestionPolicies(new ArrayList<IGroupFormationPolicies>());
		assertNotNull(builder.getQuestionPolicies());
	}
	
	@Test
	public void setQuestionPolicies() {
		AlgorithmBuilder builder = new AlgorithmBuilder();
		builder.setQuestionPolicies(new ArrayList<IGroupFormationPolicies>());
		assertNotNull(builder.getQuestionPolicies());
	}
	
	
}
