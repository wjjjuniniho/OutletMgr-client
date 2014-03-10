package tongji.sse.outletmanager.ui.trolley;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.ui.datamodel.util.MerchandiseWrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class TrolleyAdapter extends BaseAdapter {
	private Context context = null;

	public TrolleyAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return (Trolley.getInstance().getEntryAmount() + 1)
				* TrolleyConfig.COLUMN_NUMBER;
	}

	@Override
	public Object getItem(int position) {
		if (position < TrolleyConfig.COLUMN_NUMBER) {
			return null;
		}
		return Trolley.getInstance().getItem(
				position / TrolleyConfig.COLUMN_NUMBER - 1);
	}

	@Override
	public long getItemId(int position) {
		return position / TrolleyConfig.COLUMN_NUMBER - 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position < TrolleyConfig.COLUMN_NUMBER) {
			return getAttributeView(position);
		} else {
			return getValueView(position);
		}
	}

	private View getValueView(int position) {
		Merchandise item = Trolley.getInstance().getItem(
				(position - TrolleyConfig.COLUMN_NUMBER)
						/ TrolleyConfig.COLUMN_NUMBER);
		MerchandiseWrapper itemWrapper = new MerchandiseWrapper(item);
		int amount = Trolley.getInstance().getItemAmount(
				itemWrapper.getBarcode());
		long totalPrice = Trolley.getInstance().getItemTotalPrice(
				itemWrapper.getBarcode());
		View view = null;
		switch (position % TrolleyConfig.COLUMN_NUMBER) {
		case TrolleyConfig.PREVIEW:
			ImageView imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			Drawable preview = itemWrapper.getPreview();
			if (preview == null) {
				preview = context.getResources().getDrawable(R.drawable.blue_question);
			}
			imageView.setImageDrawable(preview);
			view = imageView;
			break;
		case TrolleyConfig.AMOUNT:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setText("   " + String.valueOf(amount) + "   ");
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.TOTAL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(String.valueOf(totalPrice));
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.NAME:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(itemWrapper.getName());
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(String.valueOf(itemWrapper.getPrice()));
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.MODEL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(itemWrapper.getModel());
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.STORAGE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(String.valueOf(itemWrapper.getStorage()));
			((TextView) view).setTextAppearance(context, R.style.TrolleyValueText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		}
		return view;
	}

	private View getAttributeView(int position) {
		View view = null;
		switch (position) {
		case TrolleyConfig.PREVIEW:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_preview));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.AMOUNT:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_amount));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.TOTAL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_total));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.NAME:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_name));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.PRICE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_price));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.MODEL:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_model));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		case TrolleyConfig.STORAGE:
			view = new TextView(context);
			view.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					TrolleyConfig.ITEM_HEIGHT));
			((TextView) view).setText(context.getResources().getString(
					R.string.str_trolley_storage));
			((TextView) view).setTextAppearance(context, R.style.TrolleyAttributeText);
			((TextView) view).setGravity(Gravity.CENTER);
			break;
		}
		return view;
	}

}
