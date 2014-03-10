package tongji.sse.outletmanager.ui.login;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.data.AuthenticationStatus;
import tongji.sse.outletmanager.logic.data.Authority;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.ui.global.Global;
import tongji.sse.outletmanager.ui.main.PortalActivity;
import tongji.sse.outletmanager.ui.report.SalesReportActivity;
import tongji.sse.outletmanager.ui.setting.OutletManagerSharedPreference;
import tongji.sse.outletmanager.ui.setting.Setting;
import tongji.sse.outletmanager.ui.trolley.TrolleyActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener,
		OnDismissListener {
	private PopupWindow popupWindow = null;
	private View popView = null;
	private EditText edt_username = null;
	private EditText edt_password = null;
	private EditText edt_storeId = null;
	private EditText edt_serverIp = null;
	private EditText edt_serverPort = null;
	private Button btn_save = null;
	private Button btn_login = null;
	private Button btn_reset = null;
	private ImageButton btn_setting = null;

	private LogicUIInterface logicUIInterface = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		init();
	}

	private void init() {
		OutletManagerSharedPreference.initSetting(LoginActivity.this);

		edt_username = (EditText) findViewById(R.id.username_edit);
		edt_password = (EditText) findViewById(R.id.password_edit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reset = (Button) findViewById(R.id.btn_reset);
		btn_setting = (ImageButton) findViewById(R.id.btn_setting);

		popupWindow = new PopupWindow(LoginActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(LoginActivity.this);

		popView = layoutInflater.inflate(R.layout.layout_setting, null);
		popupWindow.setContentView(popView);

		edt_storeId = (EditText) popView.findViewById(R.id.store_id_edt);
		edt_serverIp = (EditText) popView.findViewById(R.id.server_ip_edt);
		edt_serverPort = (EditText) popView.findViewById(R.id.server_port_edt);
		btn_save = (Button) popView.findViewById(R.id.btn_save);

		popupWindow.setFocusable(true);
		popupWindow.setWidth(300);
		popupWindow.setHeight(200);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background7));
		popupWindow.setOnDismissListener(this);

		btn_login.setOnClickListener(this);
		btn_reset.setOnClickListener(this);
		btn_setting.setOnClickListener(this);
		btn_save.setOnClickListener(this);

		logicUIInterface = new LogicUIImpl();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String username = edt_username.getText().toString();
			String password = edt_password.getText().toString();
			String storeId = Setting.getInstance().getStoreId();
			if (username.equals("") || password.equals("")) {
				showToast(getResources()
						.getString(R.string.str_login_not_empty));
			} else {
				// showToast(getResources().getString(R.string.str_login_process));
				AuthenticationStatus authenticationStatus = logicUIInterface
						.authenticate(storeId, username, password);
				if (authenticationStatus.isSuccessful()) {
					showToast(getResources().getString(
							R.string.str_login_success));
					Setting.getInstance().setUser(username);
					// Setting.getInstance().setAuthority(Authority.ADMIN);
					Setting.getInstance().setAuthority(
							authenticationStatus.getAuthority());
					Intent intent = new Intent(LoginActivity.this,
							PortalActivity.class);
					startActivity(intent);
				} else {
					showToast(authenticationStatus.getErrorMessage());
				}

			}
			break;
		case R.id.btn_reset:
			edt_username.setText("");
			edt_password.setText("");
			break;
		case R.id.btn_save:
			String settingStoreId = edt_storeId.getText().toString();
			String settingServerIp = edt_serverIp.getText().toString();
			String settingServerPort = edt_serverPort.getText().toString();

			if (settingStoreId.equals("") || settingServerIp.equals("")
					|| settingServerPort.equals("")) {
				showToast(getResources().getString(
						R.string.str_setting_not_empty));
			} else {
				Setting setting = Setting.getInstance();
				setting.setStoreId(settingStoreId);
				setting.setServerIp(settingServerIp);
				setting.setServerPort(settingServerPort);
				OutletManagerSharedPreference.saveSetting(LoginActivity.this);
				popupWindow.dismiss();
			}

			break;
		case R.id.btn_setting:
			findViewById(R.id.login).startAnimation(Global.outAnimation);
			popupWindow.showAtLocation(findViewById(R.id.login), Gravity.LEFT
					| Gravity.TOP, 0, btn_setting.getHeight());
			Setting setting = Setting.getInstance();
			edt_storeId.setText(setting.getStoreId());
			edt_serverIp.setText(setting.getServerIp());
			edt_serverPort.setText(setting.getServerPort());
			break;
		}

	}

	private void showToast(String text) {
		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	@Override
	public void onBackPressed() {
		onDestroy();
	}

	@Override
	public void onDismiss() {
		findViewById(R.id.login).startAnimation(Global.inAnimation);

	}

}