package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
	private static CurrentUser uniqueInstance = null;
	private static Logger logger = LoggerFactory.getLogger(CurrentUser.class);
	
	private CurrentUser()
	{

	}

	public static CurrentUser instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CurrentUser();
		}

		logger.info("Creating a unique instance of the Current User logged in the portal");
		return uniqueInstance;
	}

	public User getCurrentAuthenticatedUser() {
		IUserPersistence userDB = AccessControlConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.isAuthenticated()) {
			String bannerID = authentication.getPrincipal().toString();
			User u = new User();
			userDB.loadUserByBannerID(bannerID, u);
			if (u.isValidUser()) {
				return u;
			}
		}


		logger.info("Getting the authorized information of the current user");
		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			User u = new User();
			userDB.loadUserByBannerID(bannerID, u);
			logger.info("Banner ID Retrived : {}",bannerID);
			if (u.isValidUser())
			{
				return u;
			}
		}

		return null;
	}
}
