package tongji.sse.outletmanager.ui.setting;

import tongji.sse.outletmanager.logic.data.Authority;

public class Setting {
	//permanently saved setting info
	private String serverIp = "192.168.1.103";
	private String serverPort = "8080";
	private String storeId = "store1";
	
	//temporarily saved setting info
	private String user = null;
	private Authority authority = null;
	
	private static Setting setting = null;
	
	private Setting() {
		
	}
	
	private Setting(String user, Authority authority) {
		this.user = user;
		this.authority = authority;
	}
	
	public static Setting getInstance() {
		if (setting == null) {
			setting = new Setting();
		}
		return setting;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the authority
	 */
	public Authority getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	
	
	
}
