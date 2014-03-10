package tongji.sse.outletmanager.adapter.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tongji.sse.outletmanager.adapter.AdapterConstant;
import tongji.sse.outletmanager.adapter.AdapterInterface;
import tongji.sse.outletmanager.adapter.ErrorMessage;
import tongji.sse.outletmanager.adapter.datamodel.AuthenticationStatusObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseCheckoutObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;
import tongji.sse.outletmanager.adapter.datamodel.StatusObject;
import tongji.sse.outletmanager.adapter.util.AuthenticationStatusObjectHelper;
import tongji.sse.outletmanager.adapter.util.HttpHelper;
import tongji.sse.outletmanager.adapter.util.HttpService;
import tongji.sse.outletmanager.adapter.util.MerchandiseCheckoutObjectListHelper;
import tongji.sse.outletmanager.adapter.util.MerchandiseDetailObjectHelper;
import tongji.sse.outletmanager.adapter.util.MerchandiseDetailObjectListHelper;
import tongji.sse.outletmanager.adapter.util.MerchandiseObjectHelper;

public class AdapterImpl implements AdapterInterface {

	@Override
	public ArrayList<MerchandiseDetailObject> doGetMerchandiseDetails(
			String storeId) {
		List<NameValuePair> query = new ArrayList<NameValuePair>();
		query.add(new BasicNameValuePair(AdapterConstant.STOREID, storeId));

		String requestURL = HttpHelper.buildURL(HttpHelper.getBaseURL()
				+ AdapterConstant.PATH_ITEM_DETAIL_LIST,
				URLEncodedUtils.format(query, HTTP.UTF_8));
		HttpGet httpGet = new HttpGet(requestURL);
		httpGet.setHeader(HttpHelper.HEADER_ACCEPT, HttpHelper.JSON_TYPE);
		HttpService httpService = HttpService.getInstance();
		try {
			HttpResponse response = httpService.execute(httpGet, null);
			if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
				String responseText = httpService.getResponseText(response);
				return MerchandiseDetailObjectListHelper.parse(responseText);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return MerchandiseDetailObjectListHelper
					.createErrorObjectList(ErrorMessage.ERROR_IOEXCEPTION);
		} finally {
			httpService.shutdown();
		}
		return MerchandiseDetailObjectListHelper
				.createErrorObjectList(ErrorMessage.ERROR_UNKNOWN);

	}

	@Override
	public MerchandiseDetailObject doGetMerchandiseDetail(String storeId,
			String barcode) {
		List<NameValuePair> query = new ArrayList<NameValuePair>();
		query.add(new BasicNameValuePair(AdapterConstant.STOREID, storeId));
		query.add(new BasicNameValuePair(AdapterConstant.BARCODE, barcode));
		String requestURL = HttpHelper.buildURL(HttpHelper.getBaseURL()
				+ AdapterConstant.PATH_ITEM_DETAIL,
				URLEncodedUtils.format(query, HTTP.UTF_8));

		HttpGet httpGet = new HttpGet(requestURL);
		httpGet.setHeader(HttpHelper.HEADER_ACCEPT, HttpHelper.JSON_TYPE);

		HttpService httpService = HttpService.getInstance();
		try {
			HttpResponse response = httpService.execute(httpGet, null);
			if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
				String responseText = httpService.getResponseText(response);
				return MerchandiseDetailObjectHelper.parse(responseText);
			} else if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND)) {
				return MerchandiseDetailObjectHelper
						.createErrorObject(ErrorMessage.ERROR_ITEM_NOT_FOUND);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return MerchandiseDetailObjectHelper
					.createErrorObject(ErrorMessage.ERROR_IOEXCEPTION);
		} finally {
			httpService.shutdown();
		}
		return MerchandiseDetailObjectHelper
				.createErrorObject(ErrorMessage.ERROR_UNKNOWN);
	}

	@Override
	public MerchandiseObject doGetMerchandise(String storeId, String barcode) {
		List<NameValuePair> query = new ArrayList<NameValuePair>();
		query.add(new BasicNameValuePair(AdapterConstant.STOREID, storeId));
		query.add(new BasicNameValuePair(AdapterConstant.BARCODE, barcode));
		String requestURL = HttpHelper.buildURL(HttpHelper.getBaseURL()
				+ AdapterConstant.PATH_ITEM,
				URLEncodedUtils.format(query, HTTP.UTF_8));

		HttpGet httpGet = new HttpGet(requestURL);
		httpGet.setHeader(HttpHelper.HEADER_ACCEPT, HttpHelper.JSON_TYPE);

		HttpService httpService = HttpService.getInstance();
		try {
			HttpResponse response = httpService.execute(httpGet, null);
			if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
				String responseText = httpService.getResponseText(response);
				return MerchandiseObjectHelper.parse(responseText);
			} else if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND)) {
				return MerchandiseObjectHelper
						.createErrorObject(ErrorMessage.ERROR_ITEM_NOT_FOUND);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return MerchandiseObjectHelper
					.createErrorObject(ErrorMessage.ERROR_IOEXCEPTION);
		} finally {
			httpService.shutdown();
		}
		return MerchandiseObjectHelper
				.createErrorObject(ErrorMessage.ERROR_UNKNOWN);
	}

	@Override
	public StatusObject doCheckoutMerchandise(String storeId,
			ArrayList<MerchandiseCheckoutObject> merchandiseCheckoutList,
			String year, String month, String day) {

		String requestURL = HttpHelper.buildURL(HttpHelper.getBaseURL()
				+ AdapterConstant.PATH_ITEM_CHECKOUT_LIST, null);

		HttpPost httpPost = new HttpPost(requestURL);
		httpPost.setHeader(HttpHelper.HEADER_CONTENT_TYPE, HttpHelper.JSON_TYPE);

		JSONObject itemCheckoutJSONObject = MerchandiseCheckoutObjectListHelper
				.toJSONObject(storeId, merchandiseCheckoutList, year, month, day);
		try {
			httpPost.setEntity(new StringEntity(itemCheckoutJSONObject
					.toString(), HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			return MerchandiseCheckoutObjectListHelper.createErrorObject(e
					.getMessage());
		}

		HttpService httpService = HttpService.getInstance();
		try {
			HttpResponse response = httpService.execute(httpPost, null);
			if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED)) {
				return new StatusObject();
			} else if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)) {
				return MerchandiseCheckoutObjectListHelper
						.createErrorObject(ErrorMessage.ERROR_CHECKOUT_FAILED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return MerchandiseCheckoutObjectListHelper
					.createErrorObject(ErrorMessage.ERROR_IOEXCEPTION);
		} finally {
			httpService.shutdown();
		}
		return MerchandiseCheckoutObjectListHelper
				.createErrorObject(ErrorMessage.ERROR_UNKNOWN);
	}

	@Override
	public AuthenticationStatusObject doAuthenticate(String storeId,
			String username, String password) {
		List<NameValuePair> query = new ArrayList<NameValuePair>();
		query.add(new BasicNameValuePair(AdapterConstant.STOREID, storeId));
		query.add(new BasicNameValuePair(AdapterConstant.USERNAME, username));
		query.add(new BasicNameValuePair(AdapterConstant.PASSWORD, password));
		String requestURL = HttpHelper.buildURL(HttpHelper.getBaseURL()
				+ AdapterConstant.PATH_AUTHENTICATION,
				URLEncodedUtils.format(query, HTTP.UTF_8));

		HttpGet httpGet = new HttpGet(requestURL);
		httpGet.setHeader(HttpHelper.HEADER_ACCEPT, HttpHelper.JSON_TYPE);

		HttpService httpService = HttpService.getInstance();
		try {
			HttpResponse response = httpService.execute(httpGet, null);
			if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
				String responseText = httpService.getResponseText(response);
				return AuthenticationStatusObjectHelper.parse(responseText);
			} else if ((response != null)
					&& (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND)) {
				return AuthenticationStatusObjectHelper
						.createErrorObject(ErrorMessage.ERROR_AUTHENTICATION_FAILED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return AuthenticationStatusObjectHelper
					.createErrorObject(ErrorMessage.ERROR_IOEXCEPTION);
		} finally {
			httpService.shutdown();
		}
		return AuthenticationStatusObjectHelper
				.createErrorObject(ErrorMessage.ERROR_UNKNOWN);
	}

	public JSONArray toJSON() {
		JSONArray itemDetailJSONArray = new JSONArray();
		for (int i = 0; i < 50; i++) {

			JSONArray salesInfoJSONArray = new JSONArray();
			for (int j = 1; j <= 12; j++) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put(AdapterConstant.YEAR, "2012");
					jsonObject.put(AdapterConstant.MONTH, String.valueOf(j));
					jsonObject.put(AdapterConstant.SALES, j * 20);

					salesInfoJSONArray.put(jsonObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			JSONObject itemJSON = new JSONObject();
			try {
				itemJSON.put(AdapterConstant.BARCODE, "barcode");
				itemJSON.put(AdapterConstant.NAME, "Ipod");
				itemJSON.put(AdapterConstant.MODEL, "nano");
				itemJSON.put(AdapterConstant.STORAGE, 100);
				itemJSON.put(AdapterConstant.PRICE, 1000);
				itemJSON.put(AdapterConstant.COST, 500);
				itemJSON.put(AdapterConstant.SALES_INFOS, salesInfoJSONArray);

				itemDetailJSONArray.put(itemJSON);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return itemDetailJSONArray;
	}

}
