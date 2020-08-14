package CSCI5308.GroupFormationTool.AccessControl;

public class DefaultNotificationConfig implements INotificationConfig {

	private static final String HOST = "smtp.gmail.com";
	private static final String FROM = "hariarunachalam27@gmail.com";
	private static final String PASSWORD = "ynwmrwprdnbhihwh";

	public String getHost() {
		return HOST;
	}

	public String getFrom() {
		return FROM;
	}

	public String getPassword() {
		return PASSWORD;
	}

}
