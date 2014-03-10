package tongji.sse.outletmanager.adapter;

import java.util.ArrayList;

import tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseCheckoutObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;
import tongji.sse.outletmanager.adapter.datamodel.StatusObject;

public interface AdapterInterface {
	/**
	 * get merchandise details
	 * @return a list of merchandise details
	 */
	public ArrayList<MerchandiseDetailObject> doGetMerchandiseDetails(String storeId);
	
	/**
	 * get specified merchandise detail
	 * @return specified merchandise detail
	 */
	public MerchandiseDetailObject doGetMerchandiseDetail(String storeId, String barcode);
	
	/**
	 * get specified merchandise
	 * @return specified merchandise
	 */
	public MerchandiseObject doGetMerchandise(String storeId, String barcode);
	
	
	/**
	 * checkout merchandise
	 * @return status object
	 */
	public StatusObject doCheckoutMerchandise(String storeId, ArrayList<MerchandiseCheckoutObject> merchandiseCheckoutList, String year, String month, String day);
	
	/**
	 * 
	 * @param storeId
	 * @param username
	 * @param password
	 * @return authentication status object
	 */
	public AuthenticationStatusObject doAuthenticate(String storeId, String username, String password);
	
	
}
