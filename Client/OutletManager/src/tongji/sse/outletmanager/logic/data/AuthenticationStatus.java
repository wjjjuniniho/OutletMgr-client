package tongji.sse.outletmanager.logic.data;

import tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject;
import tongji.sse.outletmanager.adapter.datamodel.AuthorityEnum;

public class AuthenticationStatus  {
	private AuthenticationStatusObject authenticationStatusObject = null;
	
	public AuthenticationStatus(AuthenticationStatusObject authenticationStatusObject) {
		this.authenticationStatusObject = authenticationStatusObject;
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#isSuccessful()
	 */
	public boolean isSuccessful() {
		return authenticationStatusObject.isSuccessful();
	}

	/**
	 * @param isSuccessful
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#setSuccessful(boolean)
	 */
	public void setSuccessful(boolean isSuccessful) {
		authenticationStatusObject.setSuccessful(isSuccessful);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject#getUsername()
	 */
	public String getUsername() {
		return authenticationStatusObject.getUsername();
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#getErrorMessage()
	 */
	public String getErrorMessage() {
		return authenticationStatusObject.getErrorMessage();
	}

	/**
	 * @param username
	 * @see tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject#setUsername(java.lang.String)
	 */
	public void setUsername(String username) {
		authenticationStatusObject.setUsername(username);
	}

	/**
	 * @param errorMessage
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#setErrorMessage(java.lang.String)
	 */
	public void setErrorMessage(String errorMessage) {
		authenticationStatusObject.setErrorMessage(errorMessage);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject#getAuthority()
	 */
	public Authority getAuthority() {
		Authority authority = Authority.USER;
		if (authenticationStatusObject.getAuthority().equals(AuthorityEnum.ADMIN)) {
			authority = Authority.ADMIN;
		} else if (authenticationStatusObject.getAuthority().equals(AuthorityEnum.USER)) {
			authority = Authority.USER;
		}
		return authority;
	}

	/**
	 * @param authority
	 * @see tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject#setAuthority(tongji.sse.outletmanager.adapter.datamodel.AuthorityEnum)
	 */
	public void setAuthority(Authority authority) {
		AuthorityEnum authorityEnum = AuthorityEnum.USER;
		if (authority.equals(Authority.ADMIN)) {
			authorityEnum = AuthorityEnum.ADMIN;
		} else if (authority.equals(Authority.USER)) {
			authorityEnum = AuthorityEnum.USER;
		}
		authenticationStatusObject.setAuthority(authorityEnum);
	}
	
	
}
