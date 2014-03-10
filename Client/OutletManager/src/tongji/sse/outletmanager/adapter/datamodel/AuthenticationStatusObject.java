package tongji.sse.outletmanager.adapter.datamodel;


public class AuthenticationStatusObject extends StatusObject {
	private String username = null;
	private AuthorityEnum authority = AuthorityEnum.USER;
	
	public AuthenticationStatusObject(String username, AuthorityEnum authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	
	public AuthenticationStatusObject(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the authority
	 */
	public AuthorityEnum getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(AuthorityEnum authority) {
		this.authority = authority;
	}
	
	
}
