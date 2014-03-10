package tongji.sse.outletmanager.ui.setting;

import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import android.content.Context;
import android.content.SharedPreferences;

public class OutletManagerSharedPreference {

	private static final String PREF_NAME = "tongji.sse.outletmanager.setting";
	private static final String STORE_ID = "storeId";
	private static final String SERVER_IP = "serverIp";
	private static final String SERVER_PORT = "serverPort";
	
	private static final String VALUE_DEFAULT_STORE_ID = "store1";
	private static final String VALUE_DEFAULT_SERVER_IP = "192.168.1.103";
	private static final String VALUE_DEFAULT_SERVER_PORT = "8080";

	
	public static void initSetting(Context context) {
		if (context == null) {
			return;
		}
		
		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
		String storeId = prefs.getString(STORE_ID, VALUE_DEFAULT_STORE_ID);
		String serverIp = prefs.getString(SERVER_IP, VALUE_DEFAULT_SERVER_IP);
		String serverPort = prefs.getString(SERVER_PORT, VALUE_DEFAULT_SERVER_PORT);
		
		Setting setting = Setting.getInstance();
		setting.setStoreId(storeId);
		setting.setServerIp(serverIp);
		setting.setServerPort(serverPort);
		
		LogicUIInterface logicUIInterface = new LogicUIImpl();
		logicUIInterface.setHttpBaseUrl(serverIp, serverPort);
		
	}
	
	public static void saveSetting(Context context) {
		if (context == null) {
			return;
		}
		
		Setting setting = Setting.getInstance();
		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putString(STORE_ID, setting.getStoreId());
		editor.putString(SERVER_IP, setting.getServerIp());
		editor.putString(SERVER_PORT, setting.getServerPort());
		editor.commit();
		
		LogicUIInterface logicUIInterface = new LogicUIImpl();
		logicUIInterface.setHttpBaseUrl(setting.getServerIp(), setting.getServerPort());
		
	}
	
}
