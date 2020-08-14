package CSCI5308.GroupFormationTool.Questions;

import java.util.Map;

public class QuestionForm {
	private Long id;
	private String title;
	private String question;
	private String type;
	private String creationDate;
	private String createdBy;
	private Map<Integer, String> options;
	private String policyType;
	private int value;
	private String selected;

	public QuestionForm() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Map<Integer, String> getOptions() {
		return options;
	}

	public void setOptions(Map<Integer, String> options) {
		this.options = options;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public QuestionForm(AbstractQuestion question) {
		this.question = question.getQuestion();
		this.id = question.getId();
		this.title = question.getTitle();
		this.type = question.getType();
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

}
