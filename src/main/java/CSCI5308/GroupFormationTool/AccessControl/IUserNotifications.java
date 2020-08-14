package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserNotifications {
	public boolean sendUserLoginCredentials(User user, String rawPassword);
}
