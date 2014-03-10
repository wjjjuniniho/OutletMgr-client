package tongji.sse.outletmanager.ui.report.detailreport;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.data.MerchandiseDetail;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class DetailReportAdapter extends BaseAdapter {
	private Context context = null;

	public DetailReportAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return (DetailReport.getInstance().getItemDetailAmount() + 1)
				* DetailReportConfig.COLUMN_NUMBER;
	}

	@Override
	public Object getItem(int position) {
		if (position < DetailReportConfig.COLUMN_NUMBER) {
			return null;
		}
		return DetailReport.getInstance().getItemDetail(
				position / DetailReportConfig.COLUMN_NUMBER - 1);
	}

	@Override
	public long getItemId(int position) {
		return position / DetailReportConfig.COLUMN_NUMBER - 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position < DetailReportConfig.COLUMN_NUMBER) {
			return getAttributeView(position);
		} else {
			return getValueView(position);
		}
	}

	private View getValueView(int position) {
		MerchandiseDetail itemDetail = DetailReport.getInstance().getItemDetail(position / DetailReportConfig.COLUMN_NUMBER - 1);
		int sales = DetailReport.getInstance().getItemSales(position / DetailReportConfig.COLUMN_NUMBER - 1);
		long totalPrice = DetailReport.getInstance().getItemTotalPrice(position / DetailReportConfig.COLUMN_NUMBER - 1);
		long totalCost = DetailReport.getInstance().getItemTotalCost(position / DetailReportConfig.COLUMN_NUMBER - 1);
		long totalProfit = DetailReport.getInstance().getItemTotalProfit(position / DetailReportConfig.COLUMN_NUMBER - 1);
		View view = null;
		switch (position % DetailReportConfig.COLUMN_NUMBER) {
		case DetailReportConfig.TOTAL_PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(totalPrice));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.TOTAL_COST:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(totalCost));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.PROFIT:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(totalProfit));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.NAME:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(itemDetail.getName());
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(itemDetail.getPrice()));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.MODEL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(itemDetail.getModel());
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.COST:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(itemDetail.getCost()));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.SALES:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setSingleLine(true);
			((TextView) view).setText(String.valueOf(sales));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		}
		return view;
	}

	private View getAttributeView(int position) {
		View view = null;
		switch (position) {
		case DetailReportConfig.TOTAL_PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_total_price));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.TOTAL_COST:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_total_cost));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.PROFIT:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_profit));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.NAME:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_name));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_price));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.MODEL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_model));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.COST:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_cost));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case DetailReportConfig.SALES:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_detail_report_sales));
			((TextView) view).setTextAppearance(context,
					R.style.DetailReportAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		}
		return view;
	}

}
