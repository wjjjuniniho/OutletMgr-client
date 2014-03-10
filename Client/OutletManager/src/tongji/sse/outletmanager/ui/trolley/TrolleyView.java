package tongji.sse.outletmanager.ui.trolley;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.ui.global.Global;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class TrolleyView extends GridView {

	// Preview Name Model Price Amount Storage Total
	public TrolleyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setHapticFeedbackEnabled(true);
	}

}
