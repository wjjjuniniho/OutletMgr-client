package tongji.sse.outletmanager.adapter.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.ErrorMessage;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject.SalesInfo;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;

public class MerchandiseDetailObjectListHelper {
	
	//parse from JSON to the merchandise detail list
	public static ArrayList<MerchandiseDetailObject> parse(String input) {
		JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(input);
        } catch (JSONException e) {
            e.printStackTrace();
            return createErrorObjectList(e.getMessage());
        }
        
        ArrayList<MerchandiseDetailObject> itemDetailObjectList = new ArrayList<MerchandiseDetailObject>();
        if (jsonArray != null) {
        	if (jsonArray.length() == 0) { 
        		itemDetailObjectList = createErrorObjectList(ErrorMessage.ERROR_NO_ITEM);
        	}
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject itemDetailJSONObject = jsonArray.getJSONObject(i);
                    
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
                    
                    //create merchandiseDetailObject and add it to merchandiseDetailObject list
                    itemDetailObjectList.add(new MerchandiseDetailObject(itemObject, salesInfoList));
                    
                    
                } catch (JSONException e) {
                	e.printStackTrace();
                	return createErrorObjectList(e.getMessage());
                }
            }
        }
		
		return itemDetailObjectList;
	}
	
	/**
	 * create a one-object list to indicate that error occurs.The first(and the only) object in the list sets the error message.
	 * @param errorMessage
	 * @return a one-object list containing one error-object
	 */
	public static ArrayList<MerchandiseDetailObject> createErrorObjectList(String errorMessage) {
		ArrayList<MerchandiseDetailObject> itemDetailObjectList = new ArrayList<MerchandiseDetailObject>();
		itemDetailObjectList.add(new MerchandiseDetailObject(errorMessage));
		return itemDetailObjectList;
	}
}
