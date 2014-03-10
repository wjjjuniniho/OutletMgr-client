package tongji.sse.outletmanager.logic.data;

import java.util.ArrayList;

import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject.SalesInfo;
import android.graphics.drawable.Drawable;

public class MerchandiseDetail {
	public class MerchandiseSalesInfo {
		MerchandiseDetailObject.SalesInfo salesInfo = null;
		public MerchandiseSalesInfo(MerchandiseDetailObject.SalesInfo salesInfo) {
			this.salesInfo = salesInfo;
		}
		public String getYear() {
			return salesInfo.getYear();
		}
		public void setYear(String year) {
			salesInfo.setYear(year);
		}
		public String getMonth() {
			return salesInfo.getMonth();
		}
		public void setMonth(String month) {
			salesInfo.setMonth(month);
		}
		public int getSalesAmount() {
			return salesInfo.getSalesAmount();
		}
		public void setSalesAmount(int salesAmount) {
			salesInfo.setSalesAmount(salesAmount);
		}
		
		
	}
	
	private MerchandiseDetailObject itemDetailObject = null;
	
	public MerchandiseDetail(MerchandiseDetailObject itemInfo) {
		this.itemDetailObject = itemInfo;
	}

	
	
	public ArrayList<MerchandiseSalesInfo> getSalesInfoList() {
		ArrayList<MerchandiseSalesInfo> merchandiseSalesInfos = new ArrayList<MerchandiseDetail.MerchandiseSalesInfo>();
		for (MerchandiseDetailObject.SalesInfo salesInfo : itemDetailObject.getSalesInfoList()) {
			merchandiseSalesInfos.add(new MerchandiseSalesInfo(salesInfo));
		}
		return merchandiseSalesInfos;
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getBarcode()
	 */
	public String getBarcode() {
		return itemDetailObject.getBarcode();
	}

	/**
	 * @param barcode
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setBarcode(java.lang.String)
	 */
	public void setBarcode(String barcode) {
		itemDetailObject.setBarcode(barcode);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getName()
	 */
	public String getName() {
		return itemDetailObject.getName();
	}

	/**
	 * @param name
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setName(java.lang.String)
	 */
	public void setName(String name) {
		itemDetailObject.setName(name);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getModel()
	 */
	public String getModel() {
		return itemDetailObject.getModel();
	}

	/**
	 * @param model
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setModel(java.lang.String)
	 */
	public void setModel(String model) {
		itemDetailObject.setModel(model);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getUnit()
	 */
	public String getUnit() {
		return itemDetailObject.getUnit();
	}

	/**
	 * @param unit
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setUnit(java.lang.String)
	 */
	public void setUnit(String unit) {
		itemDetailObject.setUnit(unit);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getPrice()
	 */
	public long getPrice() {
		return itemDetailObject.getPrice();
	}

	/**
	 * @param price
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setPrice(long)
	 */
	public void setPrice(long price) {
		itemDetailObject.setPrice(price);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getStorage()
	 */
	public int getStorage() {
		return itemDetailObject.getStorage();
	}

	/**
	 * @param storage
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setStorage(int)
	 */
	public void setStorage(int storage) {
		itemDetailObject.setStorage(storage);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#getPreview()
	 */
	public Drawable getPreview() {
		return itemDetailObject.getPreview();
	}

	/**
	 * @param preview
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseDetailObject#setPreview(android.graphics.drawable.Drawable)
	 */
	public void setPreview(Drawable preview) {
		itemDetailObject.setPreview(preview);
	}
	
	public long getCost() {
		return itemDetailObject.getCost();
	}
	
	public void setCost(long cost) {
		itemDetailObject.setCost(cost);
	}
	
	public boolean isSuccessful() {
		return itemDetailObject.isSuccessful();
	}
	
	public void setSuccessful(boolean isSuccessful) {
		itemDetailObject.setSuccessful(isSuccessful);
	}

	public String getErrorMessage() {
		return itemDetailObject.getErrorMessage();
	}
	
	
	public void setErrorMessage(String errorMessage) {
		itemDetailObject.setErrorMessage(errorMessage);
	}
	
}
