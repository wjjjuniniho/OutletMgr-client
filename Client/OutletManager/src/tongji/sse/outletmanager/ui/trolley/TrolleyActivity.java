package tongji.sse.outletmanager.ui.trolley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.OnItemCheckoutChangeListener;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.logic.data.MerchandiseCheckout;
import tongji.sse.outletmanager.logic.data.Status;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.service.ItemScanService;
import tongji.sse.outletmanager.ui.datamodel.util.MerchandiseWrapper;
import tongji.sse.outletmanager.ui.global.Global;
import tongji.sse.outletmanager.ui.main.PortalActivity;
import tongji.sse.outletmanager.ui.setting.Setting;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class TrolleyActivity extends Activity implements OnItemClickListener,
		OnDismissListener, OnClickListener, OnItemCheckoutChangeListener {
	private TrolleyView trolleyView = null;
	private PopupWindow popupWindow = null;
	private View popView = null;
	private ImageButton btn_ok = null;
	private ImageButton btn_cancel = null;
	private ImageButton btn_transact = null;
	private ImageButton btn_clear = null;
	private EditText edt_amount = null;
	private TextView tv_total;
	private int itemPosition = 0;

	private LogicUIInterface logicUIInterface = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_trolley);
		init();
	}

	private void init() {
		trolleyView = (TrolleyView) findViewById(R.id.trolley_view);
		trolleyView.setNumColumns(TrolleyConfig.COLUMN_NUMBER);
		trolleyView.setVerticalSpacing(TrolleyConfig.VERTICAL_SPACING);
		trolleyView.setHorizontalSpacing(TrolleyConfig.HORIZONTAL_SPACING);
		trolleyView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		TrolleyAdapter trolleyAdapter = new TrolleyAdapter(TrolleyActivity.this);
		trolleyView.setAdapter(trolleyAdapter);
		trolleyView.setOnItemClickListener(this);

		tv_total = (TextView) findViewById(R.id.total);
		tv_total.setText(String.valueOf(Trolley.getInstance()
				.getItemsTotalPrice()));
		btn_transact = (ImageButton) findViewById(R.id.btn_transact);
		btn_clear = (ImageButton) findViewById(R.id.btn_clear);
		btn_transact.setOnClickListener(this);
		btn_clear.setOnClickListener(this);

		popupWindow = new PopupWindow(TrolleyActivity.this);
		LayoutInflater layoutInflater = LayoutInflater
				.from(TrolleyActivity.this);

		popView = layoutInflater.inflate(R.layout.layout_edit_amount, null);
		popupWindow.setContentView(popView);

		edt_amount = (EditText) popView
				.findViewById(R.id.merchandise_amount_edt);
		btn_ok = (ImageButton) popView.findViewById(R.id.btn_ok);
		btn_cancel = (ImageButton) popView.findViewById(R.id.btn_cancel);
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);

		popupWindow.setFocusable(true);
		popupWindow.setWidth(200);
		popupWindow.setHeight(200);
		popupWindow.setOnDismissListener(this);

		logicUIInterface = new LogicUIImpl();
		ItemScanService.registerOnItemCheckoutChangeListener(
				TrolleyActivity.this, TrolleyActivity.this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		if (position >= TrolleyConfig.COLUMN_NUMBER
				&& position % TrolleyConfig.COLUMN_NUMBER == TrolleyConfig.AMOUNT) {
			btn_transact.setEnabled(false);
			itemPosition = position / TrolleyConfig.COLUMN_NUMBER - 1;
			findViewById(R.id.trolley).startAnimation(Global.outAnimation);
			popupWindow.showAtLocation(findViewById(R.id.trolley),
					Gravity.CENTER, 0, 0);
			edt_amount.setText(String.valueOf(Trolley.getInstance()
					.getItemAmount(itemPosition)));

		}

	}

	@Override
	public void onDismiss() {
		findViewById(R.id.trolley).startAnimation(Global.inAnimation);
		btn_transact.setEnabled(true);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_clear:
			showToast(getResources().getString(R.string.str_trolley_clear));
			Trolley.getInstance().clearAllItems();
			((TrolleyAdapter) trolleyView.getAdapter()).notifyDataSetChanged();
			tv_total.setText(String.valueOf(Trolley.getInstance()
					.getItemsTotalPrice()));
			break;
		case R.id.btn_transact:
			// checkout
			if (Trolley.getInstance().isEmpty()) {
				showToast(getResources().getString(R.string.str_trolley_empty));
				break;
			}
			Date date = new Date();
			String year = String.valueOf(date.getYear() + 1900);
			String month = String.valueOf(date.getMonth());
			String day = String.valueOf(date.getDate());
			Status status = logicUIInterface.checkoutMerchandises(Setting
					.getInstance().getStoreId(), getMerchandiseCheckoutList(),
					year, month, day);
			if (status.isSuccessful()) {
				showToast(getResources().getString(
						R.string.str_trolley_checkout_success));
				Trolley.getInstance().clearAllItems();
				((TrolleyAdapter) trolleyView.getAdapter())
						.notifyDataSetChanged();
				tv_total.setText(String.valueOf(Trolley.getInstance()
						.getItemsTotalPrice()));
			} else {
				showToast(status.getErrorMessage());
			}

			break;
		case R.id.btn_ok:
			int amount = Integer.valueOf(edt_amount.getText().toString());
			if (amount <= Trolley.getInstance().getItem(itemPosition)
					.getStorage()) {
				Trolley.getInstance().setItemAmount(itemPosition, amount);
				((TrolleyAdapter) trolleyView.getAdapter())
						.notifyDataSetChanged();
				tv_total.setText(String.valueOf(Trolley.getInstance()
						.getItemsTotalPrice()));
				popupWindow.dismiss();
			} else {
				showToast(getResources().getString(
						R.string.str_trolley_out_of_storage));
			}

			break;
		case R.id.btn_cancel:
			popupWindow.dismiss();
			break;
		}

	}

	private ArrayList<MerchandiseCheckout> getMerchandiseCheckoutList() {
		ArrayList<MerchandiseCheckout> itemCheckoutList = new ArrayList<MerchandiseCheckout>();
		Trolley trolley = Trolley.getInstance();
		for (int i = 0; i < trolley.getAllItems().size(); i++) {
			itemCheckoutList.add(new MerchandiseCheckout(trolley.getItem(i),
					trolley.getItemAmount(i)));
		}
		return itemCheckoutList;
	}

	private void showToast(String text) {
		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(TrolleyActivity.this, PortalActivity.class);
		startActivity(intent);
	}

	@Override
	public void onItemCheckoutChange(Merchandise item) {
		MerchandiseWrapper itemWrapper = new MerchandiseWrapper(item);
		item = itemWrapper.matchPreview(TrolleyActivity.this);
		itemCheckoutChangeHandler.sendMessage(Message.obtain(
				itemCheckoutChangeHandler, 0, item));

	}

	private Handler itemCheckoutChangeHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.obj instanceof Merchandise) {
				Trolley.getInstance().addItem((Merchandise) msg.obj);
				((TrolleyAdapter) trolleyView.getAdapter())
						.notifyDataSetChanged();
				tv_total.setText(String.valueOf(Trolley.getInstance()
						.getItemsTotalPrice()));
			}

		}
	};

	@Override
	public void setRegister(Context register) {
		// TODO Auto-generated method stub
		
	}

}
