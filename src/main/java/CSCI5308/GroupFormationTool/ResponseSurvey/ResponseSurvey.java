package CSCI5308.GroupFormationTool.ResponseSurvey;

public class ResponseSurvey {
	private long surveyID;
	private String name;
	private long courseID;
	private long creatorID;
	private int published;

	public ResponseSurvey() {
		setDefaults();
	}

	public void setDefaults() {
		surveyID = -1;
		name = "";
		courseID = -1;
		creatorID = -1;
		published = 0;
	}

	public long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}

	public long getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(long creatorID) {
		this.creatorID = creatorID;
	}

	public int getPublished() {
		return published;
	}

	public void setPublished(int published) {
		this.published = published;
	}

	public boolean isSurveyPublished() {
		if (this.published == 1) {
			return true;
		} else {
			return false;
		}
	}

}
