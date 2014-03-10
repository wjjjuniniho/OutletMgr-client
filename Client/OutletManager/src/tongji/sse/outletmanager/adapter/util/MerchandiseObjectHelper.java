package tongji.sse.outletmanager.adapter.util;

import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;

public class MerchandiseObjectHelper {
	public static MerchandiseObject parse(String input) {
		JSONObject itemJSONObject = null;
		MerchandiseObject itemObject = null;
		try {
			itemJSONObject = new JSONObject(input);

			String barcode = itemJSONObject.getString(AdapterConstant.BARCODE);
			String name = itemJSONObject.getString(AdapterConstant.NAME);
			String model = itemJSONObject.getString(AdapterConstant.MODEL);
			long price = itemJSONObject.getLong(AdapterConstant.PRICE);
			long cost = itemJSONObject.getLong(AdapterConstant.COST);
			int storage = itemJSONObject.getInt(AdapterConstant.STORAGE);

			// create merchandiseObject
			itemObject = new MerchandiseObject(barcode, name, model, "$",
					price, cost, storage, null);

		} catch (JSONException e) {
			e.printStackTrace();
			return createErrorObject(e.getMessage());
		}

		return itemObject;

	}

	public static MerchandiseObject createErrorObject(String errorMessage) {
		return new MerchandiseObject(errorMessage);
	}
}
