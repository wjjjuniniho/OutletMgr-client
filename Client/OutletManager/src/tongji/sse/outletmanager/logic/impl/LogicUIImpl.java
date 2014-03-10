package tongji.sse.outletmanager.logic.impl;

import java.util.ArrayList;

import tongji.sse.outletmanager.adapter.AdapterInterface;
import tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseCheckoutObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;
import tongji.sse.outletmanager.adapter.datamodel.StatusObject;
import tongji.sse.outletmanager.adapter.impl.AdapterImpl;
import tongji.sse.outletmanager.adapter.util.HttpHelper;
import tongji.sse.outletmanager.logic.LogicUIInterface;
import tongji.sse.outletmanager.logic.data.AuthenticationStatus;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.logic.data.MerchandiseCheckout;
import tongji.sse.outletmanager.logic.data.MerchandiseDetail;
import tongji.sse.outletmanager.logic.data.Status;

public class LogicUIImpl implements LogicUIInterface {
	AdapterInterface adapter = new AdapterImpl();
	
	@Override
	public ArrayList<MerchandiseDetail> getMerchandiseDetails(String storeId) {
		ArrayList<MerchandiseDetail> itemDetails = new ArrayList<MerchandiseDetail>();
		for (MerchandiseDetailObject itemDetailResponse : adapter
				.doGetMerchandiseDetails(storeId)) {
			itemDetails.add(new MerchandiseDetail(itemDetailResponse));
		}
		return itemDetails;
	}

	@Override
	public MerchandiseDetail getMerchandiseDetail(String storeId, String barcode) {
		MerchandiseDetailObject itemDetailResponse = adapter
				.doGetMerchandiseDetail(storeId, barcode);
		return new MerchandiseDetail(itemDetailResponse);
	}

	@Override
	public Merchandise getMerchandise(String storeId, String barcode) {
		MerchandiseObject itemResponse = adapter.doGetMerchandise(storeId,
				barcode);
		return new Merchandise(itemResponse);
	}

	@Override
	public Status checkoutMerchandises(String storeId,
			ArrayList<MerchandiseCheckout> itemCheckoutList, String year,
			String month, String day) {
		ArrayList<MerchandiseCheckoutObject> itemCheckoutObjectList = new ArrayList<MerchandiseCheckoutObject>();
		for (MerchandiseCheckout itemCheckout : itemCheckoutList) {
			itemCheckoutObjectList.add(new MerchandiseCheckoutObject(
					itemCheckout.getItemCheckoutObject(), itemCheckout
							.getAmount()));
		}

		StatusObject statusResponse = adapter.doCheckoutMerchandise(storeId,
				itemCheckoutObjectList, year, month, day);
		return new Status(statusResponse);
	}

	@Override
	public AuthenticationStatus authenticate(String storeId, String username,
			String password) {
		AuthenticationStatusObject authenticationStatusObject = adapter
				.doAuthenticate(storeId, username, password);
		return new AuthenticationStatus(authenticationStatusObject);
	}

	@Override
	public void setHttpBaseUrl(String serverIp, String serverPort) {
		HttpHelper.setBaseURL(serverIp, serverPort);
		
	}

}
