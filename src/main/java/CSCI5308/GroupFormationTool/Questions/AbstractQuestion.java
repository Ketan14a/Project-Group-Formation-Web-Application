package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicyDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractQuestion {
	private static Logger logger = LoggerFactory.getLogger(AbstractQuestion.class);
	private long id;
	private String title;
	private String question;
	private String type;
	private String creationDate;
	private String createdBy;
	private Map<Integer, String> options;
	public static final String MCCO = "MCCO";
	public static final String MCCM = "MCCM";
	private static final String ASC = "ASC";
	private static final String DESC = "DESC";

	public abstract boolean saveQuestion(IQuestionPersistence iQuestionPersistence);

	public abstract boolean deleteQuestion(IQuestionPersistence iQuestionPersistence);

	public void setDefaults() {
		id = -1;
		title = "";
		question = "";
		type = "";
		creationDate = "";
		createdBy = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setOptions(Map<Integer, String> option) {
		this.options = option;
	}

	public boolean addOption() {
		int index = this.getOptions().size();
		this.getOptions().put(index, "");
		return true;
	}

	public boolean removeOption() {
		int index = this.getOptions().size();
		if (index > 0) {
			this.getOptions().remove(index - 1);
		}
		return true;
	}

	private static boolean isStringNullOrEmpty(String s) {
		if (null == s) {
			return false;
		}

		if (s.isEmpty() == true) {
			return false;
		}

		return true;
	}

	public static boolean isTitleValid(String title) {
		return isStringNullOrEmpty(title);
	}

	public static boolean isQuestionValid(String question) {
		return isStringNullOrEmpty(question);
	}

	public static boolean canCreateQuestion(String type) {
		if (type.matches(MCCO) || type.matches(MCCM)) {
			return false;
		}
		return true;

	}

	public boolean generateQuestionWithOptions() {
		Map<Integer, String> options = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			options.put(i, "");
		}
		this.setOptions(options);
		logger.info("Generating question with options");
		return true;
	}

	public static void sortQuestionsList(List<AbstractQuestion> questions, String orderBy, String orderType) {
		logger.info("Sorting the Question List");
		Comparator<AbstractQuestion> sortByComparator = null;
		if (orderBy.toLowerCase().equals("title")) {
			sortByComparator = (AbstractQuestion q1, AbstractQuestion q2) -> q1.getTitle().compareTo(q2.getTitle());
		} else if (orderBy.toLowerCase().equals("date")) {
			sortByComparator = (AbstractQuestion q1, AbstractQuestion q2) -> q1.getCreationDate()
					.compareTo(q2.getCreationDate());
		}
		if (orderType.toUpperCase().equals(ASC)) {
			questions.sort(sortByComparator);
		} else if (orderType.toUpperCase().equals(DESC)) {
			questions.sort(sortByComparator.reversed());
		}
	}
}
