package tongji.sse.outletmanager.service;

import java.util.Random;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import tongji.sse.outletmanager.logic.LogicUIConstant;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.OnItemCheckoutChangeListener;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.logic.impl.LogicUIImpl;
import tongji.sse.outletmanager.util.ThreadPoolUtil;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ItemScanService extends Service {
	private ScheduledExecutorService serviceScheduledExecutorService = null;
	private ScheduledFuture fetchItemCheckoutTask = null;
	private LogicUIInterface logicUIInterface = null;
	private static OnItemCheckoutChangeListener onItemCheckoutChangeListener = null;

	private int DELAY_PERIOD = 10000;

	@Override
	public void onCreate() {
		logicUIInterface = new LogicUIImpl();
		serviceScheduledExecutorService = ThreadPoolUtil
				.getServiceScheduledExecutorService();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		String storeId = intent.getStringExtra(LogicUIConstant.STOREID);
		fetchItemCheckoutTask = serviceScheduledExecutorService
				.scheduleWithFixedDelay(new FetchItemCheckoutRunnable(storeId),
						0, DELAY_PERIOD, TimeUnit.MILLISECONDS);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private class FetchItemCheckoutRunnable implements Runnable {
		private String storeId;
		private int loop = 0;

		public FetchItemCheckoutRunnable(String storeId) {
			this.storeId = storeId;
		}

		@Override
		public void run() {
			if (storeId == null || onItemCheckoutChangeListener == null) {
				return;
			}

			loop = (loop <= 100 ? ++loop : 0);
			Merchandise item = logicUIInterface.getMerchandise(storeId,
					"barcode" + String.valueOf(loop));
			if (onItemCheckoutChangeListener != null && item.isSuccessful()
					&& item.getStorage() > 0) {
				onItemCheckoutChangeListener.onItemCheckoutChange(item);
			}
		}

	};

	public static void registerOnItemCheckoutChangeListener(Context register,
			OnItemCheckoutChangeListener listener) {
		onItemCheckoutChangeListener = listener;
		if (onItemCheckoutChangeListener != null) {
			onItemCheckoutChangeListener.setRegister(register);
		}
	}

	@Override
	public void onDestroy() {
		onItemCheckoutChangeListener = null;
		if (fetchItemCheckoutTask != null) {
			fetchItemCheckoutTask.cancel(false);
			fetchItemCheckoutTask = null;
		}
		ThreadPoolUtil.shutDownServiceThreadPool();
	}

}
