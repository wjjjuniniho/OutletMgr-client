package tongji.sse.outletmanager.logic;

import android.content.Context;
import tongji.sse.outletmanager.logic.data.Merchandise;

public interface OnItemCheckoutChangeListener {
	public void onItemCheckoutChange(Merchandise item);
	
	public void setRegister(Context register);
}
