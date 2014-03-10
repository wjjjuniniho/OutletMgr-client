package tongji.sse.outletmanager.logic;

import java.util.ArrayList;

import tongji.sse.outletmanager.logic.data.AuthenticationStatus;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.logic.data.MerchandiseCheckout;
import tongji.sse.outletmanager.logic.data.MerchandiseDetail;
import tongji.sse.outletmanager.logic.data.Status;

public interface LogicUIInterface {
	
	/**
	 * 
	 * @param serverIp
	 * @param serverPort
	 */
	public void setHttpBaseUrl(String serverIp, String serverPort);
	
	
	/**
	 * get merchandise details for report
	 * @param storeId
	 * @return detail list
	 */
	public ArrayList<MerchandiseDetail> getMerchandiseDetails(String storeId);
	
	/**
	 * get specified merchandise detail for chart
	 * @param storeId
	 * @param barcode
	 * @return specified merchandise detail
	 */
	public MerchandiseDetail getMerchandiseDetail(String storeId, String barcode);
	
	/**
	 * get specified merchandise for query
	 * @param storeId
	 * @param barcode
	 * @return specified merchandise
	 */
	public Merchandise getMerchandise(String storeId, String barcode);
	
	/**
	 * checkout merchandise
	 * @param storeId
	 * @param merchandiseCheckoutList
	 * @param year
	 * @param month
	 * @return status
	 */
	public Status checkoutMerchandises(String storeId, ArrayList<MerchandiseCheckout> itemCheckoutList, String year, String month, String day);
	
	/**
	 * authentication process
	 * @param storeId
	 * @param username
	 * @param password
	 * @return authentication status
	 */
	public AuthenticationStatus authenticate(String storeId, String username, String password);
	
	
	
	
}
