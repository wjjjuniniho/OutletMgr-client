package tongji.sse.outletmanager.adapter.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseCheckoutObject;
import tongji.sse.outletmanager.adapter.datamodel.StatusObject;

public class MerchandiseCheckoutObjectListHelper {
	public static JSONObject toJSONObject(String storeId,
			ArrayList<MerchandiseCheckoutObject> merchandiseCheckoutList,
			String year, String month, String day) {
		JSONObject itemCheckoutInfoJSONObject = new JSONObject();
		try {
			JSONArray itemCheckoutJSONArray = new JSONArray();
			//checkout list
			for (MerchandiseCheckoutObject itemCheckoutObject : merchandiseCheckoutList) {
				String barcode = itemCheckoutObject.getBarcode();
				int amount = itemCheckoutObject.getAmount();

				JSONObject itemCheckoutJSONObject = new JSONObject();
				itemCheckoutJSONObject.put(AdapterConstant.BARCODE, barcode);
				itemCheckoutJSONObject.put(AdapterConstant.AMOUNT, amount);
				
				itemCheckoutJSONArray.put(itemCheckoutJSONObject);
			}
			
			itemCheckoutInfoJSONObject.put(AdapterConstant.ITEM_CHECKOUT_LIST, itemCheckoutJSONArray);
			itemCheckoutInfoJSONObject.put(AdapterConstant.STOREID, storeId);
			itemCheckoutInfoJSONObject.put(AdapterConstant.YEAR, year);
			itemCheckoutInfoJSONObject.put(AdapterConstant.MONTH, month);
			itemCheckoutInfoJSONObject.put(AdapterConstant.DAY, day);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return itemCheckoutInfoJSONObject;
	}
	
	public static StatusObject createErrorObject(String errorMessage) {
		return new StatusObject(errorMessage);
	}
	
	
}
