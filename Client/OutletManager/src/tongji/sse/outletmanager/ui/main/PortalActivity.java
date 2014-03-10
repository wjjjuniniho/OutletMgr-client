package tongji.sse.outletmanager.ui.main;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.LogicUIConstant;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.data.Authority;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.service.ItemScanService;
import tongji.sse.outletmanager.ui.datamodel.util.MerchandiseWrapper;
import tongji.sse.outletmanager.ui.global.Global;
import tongji.sse.outletmanager.ui.login.LoginActivity;
import tongji.sse.outletmanager.ui.report.SalesReportActivity;
import tongji.sse.outletmanager.ui.setting.Setting;
import tongji.sse.outletmanager.ui.trolley.Trolley;
import tongji.sse.outletmanager.ui.trolley.TrolleyActivity;
import tongji.sse.outletmanager.util.ThreadPoolUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class PortalActivity extends Activity implements OnClickListener,
		OnDismissListener, AnimationListener {
	private final int SHOW_PROGRESSBAR = 0;
	private final int SHOW_MERCHANDISE_INFO = 1;
	private final int SHOW_FAILURE = 2;
	private final int FETCH_PERIOD = 10000;

	private Merchandise itemSearch = null;

	private RelativeLayout layout_search_result = null;
	private ProgressBar progressBar = null;
	private ImageButton btn_trolley = null;
	private ImageButton btn_sales_report = null;
	private ImageButton btn_search = null;
	private ImageButton btn_trolley_input = null;
	private EditText edt_search = null;
	private PopupWindow popupWindow = null;
	private View popView = null;
	private Animation outIntoTrolleyAnimation = null;
	private TextView tv_name = null;
	private TextView tv_model = null;
	private TextView tv_price = null;
	private TextView tv_storage = null;
	private TextView tv_fail = null;
	private TextView tv_username = null;
	private ScrollView sv_merchandiseInfo = null;
	private ImageView img_merchandise = null;

	private ScheduledExecutorService scheduledExecutorService = null;
	private ScheduledFuture<?> fetchMerchandiseTask = null;

	private LogicUIInterface logicUIInterface = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_portal);
		init();
	}

	private void init() {
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_username.setText(Setting.getInstance().getUser());

		btn_trolley = (ImageButton) findViewById(R.id.btn_trolley);
		btn_sales_report = (ImageButton) findViewById(R.id.btn_sales_report);
		btn_search = (ImageButton) findViewById(R.id.btn_search);

		edt_search = (EditText) findViewById(R.id.search_edit);

		if (Setting.getInstance().getAuthority().equals(Authority.USER)) {
			btn_sales_report.setVisibility(View.GONE);
		}

		btn_trolley.setOnClickListener(this);
		btn_sales_report.setOnClickListener(this);
		btn_search.setOnClickListener(this);

		popupWindow = new PopupWindow(PortalActivity.this);
		LayoutInflater layoutInflater = LayoutInflater
				.from(PortalActivity.this);

		popView = layoutInflater.inflate(R.layout.layout_search_result, null);
		popupWindow.setContentView(popView);

		layout_search_result = (RelativeLayout) popView
				.findViewById(R.id.search_result);

		img_merchandise = (ImageView) popView
				.findViewById(R.id.merchandise_img);
		sv_merchandiseInfo = (ScrollView) popView
				.findViewById(R.id.merchandise_scroll);
		tv_name = (TextView) popView.findViewById(R.id.merchandise_name);
		tv_model = (TextView) popView.findViewById(R.id.merchandise_model);
		tv_price = (TextView) popView.findViewById(R.id.merchandise_price);
		tv_storage = (TextView) popView.findViewById(R.id.merchandise_storage);

		tv_fail = new TextView(PortalActivity.this);
		tv_fail.setTextAppearance(PortalActivity.this,
				R.style.ErrorMessageSmallText);
		RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		layout_search_result.addView(tv_fail, textViewLayoutParams);

		btn_trolley_input = (ImageButton) popView
				.findViewById(R.id.btn_trolley_input);
		btn_trolley_input.setOnClickListener(this);

		popupWindow.setFocusable(true);
		popupWindow.setWidth(250);
		popupWindow.setHeight(300);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background4));
		popupWindow.setOnDismissListener(this);

		outIntoTrolleyAnimation = AnimationUtils.loadAnimation(
				PortalActivity.this, R.anim.anim_input_trolley);
		outIntoTrolleyAnimation.setAnimationListener(this);

		scheduledExecutorService = ThreadPoolUtil.getScheduledExecutorService();

		logicUIInterface = new LogicUIImpl();

		ItemScanService.registerOnItemCheckoutChangeListener(
				PortalActivity.this, Trolley.getInstance());

		Intent intent = new Intent(PortalActivity.this, ItemScanService.class);
		intent.putExtra(LogicUIConstant.STOREID, Setting.getInstance()
				.getStoreId());
		startService(intent);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_PROGRESSBAR:
				showProgressBar();
				break;
			case SHOW_MERCHANDISE_INFO:
				showMerchandiseInfo();
				break;
			case SHOW_FAILURE:
				showFailure();
				break;
			}
		}
	};

	private class fetchMerchandiseRunnable implements Runnable {
		@Override
		public void run() {
			String barcodeSearch = edt_search.getText().toString();
			edt_search.setText("");
			itemSearch = logicUIInterface.getMerchandise(Setting.getInstance()
					.getStoreId(), barcodeSearch);
			// fetch merchandise here
			if (itemSearch.isSuccessful()) {
				MerchandiseWrapper itemWrapper = new MerchandiseWrapper(itemSearch);
				itemSearch = itemWrapper.matchPreview(PortalActivity.this);
				handler.sendEmptyMessage(SHOW_MERCHANDISE_INFO);
			} else {
				handler.sendEmptyMessage(SHOW_FAILURE);
			}

		}
	};

	private void showFailure() {
		stopFetchMerchandise();
		tv_fail.setText(itemSearch.getErrorMessage());
		tv_fail.setVisibility(View.VISIBLE);

	}

	private void showMerchandiseInfo() {
		stopFetchMerchandise();
		img_merchandise.setImageDrawable(itemSearch.getPreview());
		tv_name.setText(itemSearch.getName());
		tv_model.setText(itemSearch.getModel());
		tv_price.setText(String.valueOf(itemSearch.getPrice()));
		tv_storage.setText(String.valueOf(itemSearch.getStorage()));
		showSearchResult();
	}

	private void showProgressBar() {
		hideSearchResult();
		ViewGroup layoutParent = layout_search_result;
		if (progressBar != null) {
			layoutParent.removeView(progressBar);
			progressBar.clearAnimation();
		}
		progressBar = new ProgressBar(this);
		progressBar.setIndeterminate(true);
		RelativeLayout.LayoutParams progressLayoutParams = new RelativeLayout.LayoutParams(
				50, 50);
		progressLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		layoutParent.addView(progressBar, progressLayoutParams);

		if (fetchMerchandiseTask == null) {
			try {
				fetchMerchandiseTask = scheduledExecutorService.schedule(
						new fetchMerchandiseRunnable(), 0,
						TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void stopFetchMerchandise() {
		// UI level: hide progress bar
		if (progressBar != null && progressBar.getVisibility() != View.GONE) {
			progressBar.setVisibility(View.GONE);
		}
		// Logic level: cancel timer
		if (fetchMerchandiseTask != null) {
			fetchMerchandiseTask.cancel(false);
			fetchMerchandiseTask = null;
		}
	}

	private void hideSearchResult() {
		sv_merchandiseInfo.setVisibility(View.INVISIBLE);
		img_merchandise.setVisibility(View.INVISIBLE);
		btn_trolley_input.setVisibility(View.INVISIBLE);
		tv_fail.setVisibility(View.INVISIBLE);

	}

	private void showSearchResult() {
		sv_merchandiseInfo.setVisibility(View.VISIBLE);
		img_merchandise.setVisibility(View.VISIBLE);
		if (itemSearch.getStorage() > 0) {
			btn_trolley_input.setVisibility(View.VISIBLE);
		} else {
			btn_trolley_input.setVisibility(View.INVISIBLE);
		}

	}

	private void showToast(String text) {
		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_trolley:
			Intent intentTrolley = new Intent(PortalActivity.this,
					TrolleyActivity.class);
			startActivity(intentTrolley);
			break;
		case R.id.btn_sales_report:
			Intent intentReport = new Intent(PortalActivity.this,
					SalesReportActivity.class);
			startActivity(intentReport);
			break;
		case R.id.btn_search:
			if (edt_search.getText().toString().equals("")) {
				showToast(getResources().getString(
						R.string.str_search_not_empty));
				return;
			}
			btn_trolley.setEnabled(false);
			btn_sales_report.setEnabled(false);
			btn_search.setEnabled(false);

			findViewById(R.id.portal).startAnimation(Global.outAnimation);
			popupWindow.showAtLocation(findViewById(R.id.portal),
					Gravity.CENTER, 0, 0);
			handler.sendEmptyMessage(SHOW_PROGRESSBAR);
			break;
		case R.id.btn_trolley_input:
			popView.startAnimation(outIntoTrolleyAnimation);
			Trolley.getInstance().addItem(itemSearch);
			break;
		}

	}

	@Override
	public void onDismiss() {
		findViewById(R.id.portal).startAnimation(Global.inAnimation);
		btn_trolley.setEnabled(true);
		btn_sales_report.setEnabled(true);
		btn_search.setEnabled(true);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		btn_trolley_input.setEnabled(true);
		popupWindow.dismiss();

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		btn_trolley_input.setEnabled(false);

	}

	@Override
	public void onBackPressed() {
		showDialog(0);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			return new AlertDialog.Builder(this)
					.setTitle(R.string.str_confirm_logout)
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									stopService(new Intent(PortalActivity.this,
											ItemScanService.class));
									Trolley.getInstance().clearAllItems();
									Intent intent = new Intent(
											PortalActivity.this,
											LoginActivity.class);
									startActivity(intent);
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							})
					.setOnCancelListener(
							new DialogInterface.OnCancelListener() {
								@Override
								public void onCancel(DialogInterface dialog) {

								}
							}).create();
		default:
			break;
		}
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (fetchMerchandiseTask != null) {
			fetchMerchandiseTask.cancel(false);
			fetchMerchandiseTask = null;
		}
		ThreadPoolUtil.shutDownThreadPool();
	}

}
