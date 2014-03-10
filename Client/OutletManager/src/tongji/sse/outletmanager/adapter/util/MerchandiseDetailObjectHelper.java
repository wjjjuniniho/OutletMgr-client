package tongji.sse.outletmanager.adapter.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject.SalesInfo;

public class MerchandiseDetailObjectHelper {
	public static MerchandiseDetailObject parse(String input) {
		JSONObject itemDetailJSONObject = null;
		MerchandiseDetailObject itemDetailObject = null;
		try {
			itemDetailJSONObject = new JSONObject(input);
			
			String barcode = itemDetailJSONObject.getString(AdapterConstant.BARCODE);
            String name = itemDetailJSONObject.getString(AdapterConstant.NAME);
            String model = itemDetailJSONObject.getString(AdapterConstant.MODEL);
            long price = itemDetailJSONObject.getLong(AdapterConstant.PRICE);
            long cost = itemDetailJSONObject.getLong(AdapterConstant.COST);
            int storage = itemDetailJSONObject.getInt(AdapterConstant.STORAGE);
            
            //create merchandiseObject
            MerchandiseObject itemObject = new MerchandiseObject(barcode, name, model, "$", price, cost, storage, null);
            
            JSONArray salesInfoJSONArray = itemDetailJSONObject.getJSONArray(AdapterConstant.SALES_INFOS);
            ArrayList<SalesInfo> salesInfoList = new ArrayList<SalesInfo>();
            for (int j = 0; j < salesInfoJSONArray.length(); j++) {
            	JSONObject salesInfoJSONObject = salesInfoJSONArray.getJSONObject(j);
            	
            	String year = salesInfoJSONObject.getString(AdapterConstant.YEAR);
            	String month = salesInfoJSONObject.getString(AdapterConstant.MONTH);
            	int sales = salesInfoJSONObject.getInt(AdapterConstant.SALES);
            	
            	//create salesInfo and add it to salesInfoList
            	salesInfoList.add(new SalesInfo(sales, year, month));
            }
            
            itemDetailObject = new MerchandiseDetailObject(itemObject, salesInfoList);
		} catch (JSONException e) {
			e.printStackTrace();
			return createErrorObject(e.getMessage());
		}
		
		return itemDetailObject;
		
		
	}
	
	
	public static MerchandiseDetailObject createErrorObject(String errorMessage) {
		return new MerchandiseDetailObject(errorMessage);
	}
}
