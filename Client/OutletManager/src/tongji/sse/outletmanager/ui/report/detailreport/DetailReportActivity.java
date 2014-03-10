package tongji.sse.outletmanager.ui.report.detailreport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.csvreader.CsvWriter;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.data.MerchandiseDetail;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.service.ItemScanService;
import tongji.sse.outletmanager.ui.report.SalesReportActivity;
import tongji.sse.outletmanager.ui.setting.Setting;
import tongji.sse.outletmanager.ui.trolley.Trolley;
import tongji.sse.outletmanager.util.ThreadPoolUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailReportActivity extends Activity implements
		OnItemClickListener, OnClickListener {

	private final int SHOW_PROGRESSBAR = 0;
	private final int SHOW_DETAIL_REPORT = 1;
	private final int SHOW_FAILURE = 2;
	private final int FETCH_PERIOD = 1000;

	private LogicUIInterface logicUIInterface = null;
	private DetailReportView detailReportView = null;
	private RelativeLayout layout_reportView = null;
	private ProgressBar progressBar = null;
	private TextView tv_fail = null;
	private ImageButton btn_refresh = null;
	private ImageButton btn_export = null;

	private ScheduledExecutorService scheduledExecutorService = null;
	private ScheduledFuture<?> fetchMerchandiseDetailsTask = null;

	private ArrayList<MerchandiseDetail> itemDetailsSearch = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_detail_report);
		init();

	}

	private void loadItemDetail() {
		DetailReport.getInstance().setItemDetails(itemDetailsSearch);
		((DetailReportAdapter) detailReportView.getAdapter())
				.notifyDataSetChanged();

	}

	private void init() {
		layout_reportView = (RelativeLayout) findViewById(R.id.layout_reportview);

		detailReportView = (DetailReportView) findViewById(R.id.detail_report_view);
		detailReportView.setNumColumns(DetailReportConfig.COLUMN_NUMBER);
		detailReportView
				.setVerticalSpacing(DetailReportConfig.VERTICAL_SPACING);
		detailReportView
				.setHorizontalSpacing(DetailReportConfig.HORIZONTAL_SPACING);
		detailReportView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		DetailReportAdapter detailReportAdapter = new DetailReportAdapter(
				DetailReportActivity.this);
		detailReportView.setAdapter(detailReportAdapter);
		detailReportView.setOnItemClickListener(this);

		btn_refresh = (ImageButton) findViewById(R.id.btn_refresh);
		btn_export = (ImageButton) findViewById(R.id.btn_export);
		btn_refresh.setOnClickListener(this);
		btn_export.setOnClickListener(this);

		tv_fail = new TextView(DetailReportActivity.this);
		tv_fail.setTextAppearance(DetailReportActivity.this,
				R.style.ErrorMessageText);
		RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		layout_reportView.addView(tv_fail, textViewLayoutParams);

		logicUIInterface = new LogicUIImpl();
		scheduledExecutorService = ThreadPoolUtil.getScheduledExecutorService();
		ItemScanService.registerOnItemCheckoutChangeListener(
				DetailReportActivity.this, Trolley.getInstance());

		handler.sendEmptyMessage(SHOW_PROGRESSBAR);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_PROGRESSBAR:
				showProgressBar();
				break;
			case SHOW_DETAIL_REPORT:
				showDetailReport();
				break;
			case SHOW_FAILURE:
				showFailure();
				break;
			}
		}

	};

	private void showFailure() {
		stopFetchMerchandiseDetail();
		tv_fail.setText(itemDetailsSearch.get(0).getErrorMessage());
		tv_fail.setVisibility(View.VISIBLE);

	}

	private void showDetailReport() {
		stopFetchMerchandiseDetail();
		loadItemDetail();
		showReportView();

	}

	private void stopFetchMerchandiseDetail() {
		// UI level: hide progress bar
		if (progressBar != null && progressBar.getVisibility() != View.GONE) {
			progressBar.setVisibility(View.GONE);
		}
		// Logic level: cancel timer
		if (fetchMerchandiseDetailsTask != null) {
			fetchMerchandiseDetailsTask.cancel(false);
			fetchMerchandiseDetailsTask = null;
		}

	}

	private void showProgressBar() {
		hideReportView();
		ViewGroup layoutParent = layout_reportView;
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

		if (fetchMerchandiseDetailsTask == null) {
			fetchMerchandiseDetailsTask = scheduledExecutorService.schedule(
					new fetchMerchandiseDetailRunnable(), 0,
					TimeUnit.MILLISECONDS);
		}

	}

	private void hideReportView() {
		detailReportView.setVisibility(View.INVISIBLE);
		tv_fail.setVisibility(View.INVISIBLE);
	}

	private void showReportView() {
		detailReportView.setVisibility(View.VISIBLE);
	}

	private class fetchMerchandiseDetailRunnable implements Runnable {
		@Override
		public void run() {
			itemDetailsSearch = logicUIInterface.getMerchandiseDetails(Setting
					.getInstance().getStoreId());

			// fetch merchandise here
			if (itemDetailsSearch.get(0).isSuccessful()) {
				handler.sendEmptyMessage(SHOW_DETAIL_REPORT);
			} else {
				handler.sendEmptyMessage(SHOW_FAILURE);
			}

		}
	};

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(DetailReportActivity.this,
				SalesReportActivity.class);
		startActivity(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (fetchMerchandiseDetailsTask != null) {
			fetchMerchandiseDetailsTask.cancel(false);
			fetchMerchandiseDetailsTask = null;
		}
		ThreadPoolUtil.shutDownThreadPool();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_refresh:
			handler.sendEmptyMessage(SHOW_PROGRESSBAR);
			break;
		case R.id.btn_export:
			String path = getResources().getString(
					R.string.str_detail_report_export_path);
			exportToCSVFile(path);
			break;
		}

	}

	private boolean exportToCSVFile(String csvFilePath) {
		boolean isSuccessful = true;
		DetailReport detailReport = DetailReport.getInstance();
		String[] attributeNameList = {
				getResources().getString(R.string.str_detail_report_name),
				getResources().getString(R.string.str_detail_report_model),
				getResources().getString(R.string.str_detail_report_sales),
				getResources().getString(R.string.str_detail_report_price),
				getResources().getString(R.string.str_detail_report_cost),
				getResources()
						.getString(R.string.str_detail_report_total_price),
				getResources().getString(R.string.str_detail_report_total_cost),
				getResources().getString(R.string.str_detail_report_profit) };
		String[] valueNameList = null;

		CsvWriter writer = new CsvWriter(csvFilePath, ',',
				Charset.forName("UTF-8"));
		try {
			writer.writeRecord(attributeNameList, true);
			
			for (int i = 0; i < detailReport.getItemDetailAmount(); i++) {
				valueNameList = detailReport.getItemRecordStrings(i);
				writer.writeRecord(valueNameList, true);
			}
				
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			isSuccessful = false;
			return isSuccessful;
		} finally {
			writer.close();
		}
		return isSuccessful;
	}

}
