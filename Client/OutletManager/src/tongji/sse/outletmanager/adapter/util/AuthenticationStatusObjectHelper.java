package tongji.sse.outletmanager.adapter.util;

import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject;

public class AuthenticationStatusObjectHelper {
	public static AuthenticationStatusObject parse(String input) {
		JSONObject authenticationStatusJSONObject = null;
		AuthenticationStatusObject authenticationStatusObject = null;
		try {
			authenticationStatusJSONObject = new JSONObject(input);

			String username = authenticationStatusJSONObject
					.getString(AdapterConstant.USERNAME);
			String authority = authenticationStatusJSONObject
					.getString(AdapterConstant.AUTHORITY);

			authenticationStatusObject = new AuthenticationStatusObject(
					username, AuthorityEnumHelper.toAuthorityEnum(authority));
		} catch (JSONException e) {
			e.printStackTrace();
			return createErrorObject(e.getMessage());
		}

		return authenticationStatusObject;

	}

	public static AuthenticationStatusObject createErrorObject(
			String errorMessage) {
		return new AuthenticationStatusObject(errorMessage);
	}

}
