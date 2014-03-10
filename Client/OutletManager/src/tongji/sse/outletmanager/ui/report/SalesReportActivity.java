package tongji.sse.outletmanager.ui.report;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.data.MerchandiseDetail;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.service.ItemScanService;
import tongji.sse.outletmanager.ui.main.PortalActivity;
import tongji.sse.outletmanager.ui.report.detailreport.DetailReportActivity;
import tongji.sse.outletmanager.ui.setting.Setting;
import tongji.sse.outletmanager.ui.trolley.Trolley;
import tongji.sse.outletmanager.util.ThreadPoolUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class SalesReportActivity extends Activity implements OnClickListener {

	private final int SHOW_PROGRESSBAR = 0;
	private final int SHOW_CHART = 1;
	private final int SHOW_FAILURE = 2;
	private final int FETCH_PERIOD = 1000;

	private MerchandiseDetail itemDetailSearch = null;

	private LogicUIInterface logicUIInterface = null;
	private ScheduledExecutorService scheduledExecutorService = null;
	private ScheduledFuture<?> fetchMerchandiseDetailTask = null;

	private XYMultipleSeriesRenderer barChartRenderer = null;
	private XYMultipleSeriesDataset barChartDataset = null;
	private XYMultipleSeriesRenderer lineChartRenderer = null;
	private XYMultipleSeriesDataset lineChartDataset = null;

	private RelativeLayout layout1 = null;
	private ProgressBar progressBar = null;
	private ImageButton btn_detail = null;
	private ImageButton btn_search = null;
	private ImageButton btn_refresh = null;
	private EditText edt_search = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_report);
		init();
	}

	private void init() {
		initLogic();
		initView();
		handler.sendEmptyMessage(SHOW_PROGRESSBAR);
		// configureLineChart();
	}

	private void initLogic() {
		logicUIInterface = new LogicUIImpl();
		scheduledExecutorService = ThreadPoolUtil.getScheduledExecutorService();
		ItemScanService.registerOnItemCheckoutChangeListener(
				SalesReportActivity.this, Trolley.getInstance());

	}

	private void initView() {
		layout1 = (RelativeLayout) findViewById(R.id.layout_chart1);

		btn_detail = (ImageButton) findViewById(R.id.btn_detail);
		btn_search = (ImageButton) findViewById(R.id.btn_search);
		btn_refresh = (ImageButton) findViewById(R.id.btn_refresh);
		edt_search = (EditText) findViewById(R.id.search_edit);

		btn_detail.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		btn_refresh.setOnClickListener(this);
	}

	private void configAndShowBarChart() {

		layout1.removeAllViews();

		barChartRenderer = getBarRenderer();
		barChartDataset = getBarDataset();

		View chartView = ChartFactory.getBarChartView(this, barChartDataset,
				barChartRenderer, Type.DEFAULT);
		layout1.addView(chartView, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT));
	}

	private XYMultipleSeriesRenderer getBarRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		SimpleSeriesRenderer simpleRenderer = new SimpleSeriesRenderer();
		simpleRenderer.setColor(Color.RED);
		simpleRenderer.setDisplayChartValues(true);
		renderer.addSeriesRenderer(simpleRenderer);
		setBarChartSettings(renderer);
		return renderer;
	}

	private void setBarChartSettings(XYMultipleSeriesRenderer renderer) {
		renderer.setChartTitle(getResources().getString(
				R.string.str_bar_chart_title));
		renderer.setChartTitleTextSize(20);
		renderer.setXTitle(getResources().getString(
				R.string.str_bar_chart_x_title));
		renderer.setYTitle(getResources().getString(
				R.string.str_bar_chart_y_title));
		renderer.setXAxisMin(0.0);
		renderer.setXAxisMax(12.0);
		renderer.setYAxisMin(0.0);
		renderer.setYAxisMax(500);
		renderer.setShowGrid(true);
		renderer.setXLabels(0);

		renderer.addXTextLabel(1, getResources()
				.getString(R.string.str_january));
		renderer.addXTextLabel(2,
				getResources().getString(R.string.str_february));
		renderer.addXTextLabel(3, getResources().getString(R.string.str_march));
		renderer.addXTextLabel(4, getResources().getString(R.string.str_april));
		renderer.addXTextLabel(5, getResources().getString(R.string.str_may));
		renderer.addXTextLabel(6, getResources().getString(R.string.str_june));
		renderer.addXTextLabel(7, getResources().getString(R.string.str_july));
		renderer.addXTextLabel(8, getResources().getString(R.string.str_august));
		renderer.addXTextLabel(9,
				getResources().getString(R.string.str_september));
		renderer.addXTextLabel(10,
				getResources().getString(R.string.str_october));
		renderer.addXTextLabel(11,
				getResources().getString(R.string.str_november));
		renderer.addXTextLabel(12,
				getResources().getString(R.string.str_december));
		renderer.setBarSpacing(1);
	}

	private XYMultipleSeriesDataset getBarDataset() {
		ArrayList<MerchandiseDetail.MerchandiseSalesInfo> salesInfoList = itemDetailSearch
				.getSalesInfoList();
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		CategorySeries categorySeries = new CategorySeries(getResources()
				.getString(R.string.str_bar_chart_category_name)
				+ " "
				+ itemDetailSearch.getName()
				+ " "
				+ getResources().getString(R.string.str_bar_chart_in)
				+ " "
				+ salesInfoList.get(0).getYear());
		for (int i = 0; i < 12; i++) {
			categorySeries.add(salesInfoList.get(i).getSalesAmount());
		}
		dataset.addSeries(categorySeries.toXYSeries());
		return dataset;

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_detail:
			Intent intent = new Intent(SalesReportActivity.this,
					DetailReportActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_search:
			handler.sendEmptyMessage(SHOW_PROGRESSBAR);
			break;
		case R.id.btn_refresh:
			if (itemDetailSearch != null) {
				edt_search.setText(itemDetailSearch.getBarcode());
			}
			handler.sendEmptyMessage(SHOW_PROGRESSBAR);
			break;
		}

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_PROGRESSBAR:
				showProgressBar();
				break;
			case SHOW_CHART:
				showChart();
				break;
			case SHOW_FAILURE:
				showFailure();
				break;
			}
		}

	};

	private void showFailure() {
		stopFetchMerchandiseDetail();
		edt_search.setText("");
		layout1.removeAllViews();
		ViewGroup layoutParent = layout1;

		TextView tv_fail = new TextView(SalesReportActivity.this);
		tv_fail.setText(itemDetailSearch.getErrorMessage());
		tv_fail.setTextAppearance(SalesReportActivity.this,
				R.style.ErrorMessageText);
		RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		layoutParent.addView(tv_fail, textViewLayoutParams);

	}

	private void showChart() {
		stopFetchMerchandiseDetail();
		edt_search.setText("");
		configAndShowBarChart();

	}

	private void stopFetchMerchandiseDetail() {
		// UI level: hide progress bar
		if (progressBar != null && progressBar.getVisibility() != View.GONE) {
			progressBar.setVisibility(View.GONE);
		}
		// Logic level: cancel timer
		if (fetchMerchandiseDetailTask != null) {
			fetchMerchandiseDetailTask.cancel(false);
			fetchMerchandiseDetailTask = null;
		}

	}

	private void showProgressBar() {
		hideChart();
		ViewGroup layoutParent = layout1;
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

		if (fetchMerchandiseDetailTask == null) {
			fetchMerchandiseDetailTask = scheduledExecutorService.schedule(
					new fetchMerchandiseDetailRunnable(), 0,
					TimeUnit.MILLISECONDS);
		}

	}

	private void hideChart() {
		for (int i = 0; i < layout1.getChildCount(); i++) {
			layout1.getChildAt(i).setVisibility(View.INVISIBLE);
		}

	}

	private class fetchMerchandiseDetailRunnable implements Runnable {
		@Override
		public void run() {
			String barcodeSearch = edt_search.getText().toString();
			if (barcodeSearch.equals("")) {
				barcodeSearch = "barcodeAll";
			}
			itemDetailSearch = logicUIInterface.getMerchandiseDetail(Setting
					.getInstance().getStoreId(), barcodeSearch);
			// fetch merchandise here
			if (itemDetailSearch.isSuccessful()) {
				handler.sendEmptyMessage(SHOW_CHART);
			} else {
				handler.sendEmptyMessage(SHOW_FAILURE);
			}

		}
	};

	// for future use

	private void configureLineChart() {
		lineChartRenderer = getLineRenderer();
		lineChartDataset = getLineDataset();
	}

	private XYMultipleSeriesRenderer getLineRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		xyRenderer.setPointStyle(PointStyle.CIRCLE);
		xyRenderer.setColor(Color.GREEN);
		xyRenderer.setFillPoints(true);
		xyRenderer.setDisplayChartValues(true);
		renderer.addSeriesRenderer(xyRenderer);
		setLineChartSettings(renderer);
		return renderer;
	}

	private void setLineChartSettings(XYMultipleSeriesRenderer renderer) {
		renderer.setChartTitle(getResources().getString(
				R.string.str_bar_chart_title));
		renderer.setXTitle(getResources().getString(
				R.string.str_bar_chart_x_title));
		renderer.setYTitle(getResources().getString(
				R.string.str_bar_chart_y_title));
		renderer.setXAxisMin(0.0);
		renderer.setXAxisMax(12.0);
		renderer.setYAxisMin(0.0);
		renderer.setYAxisMax(500);
		renderer.setShowGrid(true);
		renderer.setXLabels(0);

		renderer.addXTextLabel(1, getResources()
				.getString(R.string.str_january));
		renderer.addXTextLabel(2,
				getResources().getString(R.string.str_february));
		renderer.addXTextLabel(3, getResources().getString(R.string.str_march));
		renderer.addXTextLabel(4, getResources().getString(R.string.str_april));
		renderer.addXTextLabel(5, getResources().getString(R.string.str_may));
		renderer.addXTextLabel(6, getResources().getString(R.string.str_june));
		renderer.addXTextLabel(7, getResources().getString(R.string.str_july));
		renderer.addXTextLabel(8, getResources().getString(R.string.str_august));
		renderer.addXTextLabel(9,
				getResources().getString(R.string.str_september));
		renderer.addXTextLabel(10,
				getResources().getString(R.string.str_october));
		renderer.addXTextLabel(11,
				getResources().getString(R.string.str_november));
		renderer.addXTextLabel(12,
				getResources().getString(R.string.str_december));
		// renderer.setBarSpacing(1);
		renderer.setFitLegend(true);
	}

	private XYMultipleSeriesDataset getLineDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		CategorySeries categorySeries = new CategorySeries(getResources()
				.getString(R.string.str_bar_chart_category_name));
		for (int i = 0; i < 12; i++) {
			categorySeries.add(100 + i * 10);
		}
		dataset.addSeries(categorySeries.toXYSeries());
		return dataset;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(SalesReportActivity.this,
				PortalActivity.class);
		startActivity(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (fetchMerchandiseDetailTask != null) {
			fetchMerchandiseDetailTask.cancel(false);
			fetchMerchandiseDetailTask = null;
		}
		ThreadPoolUtil.shutDownThreadPool();
	}

}
