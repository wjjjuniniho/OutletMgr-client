package tongji.sse.outletmanager.adapter.datamodel;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class MerchandiseDetailObject {
	static public class SalesInfo {
		private String year = null;
		private String month = null;
		private int salesAmount = 0;
		
		public SalesInfo(int salesAmount, String year, String month) {
			this.year = year;
			this.month = month;
			this.salesAmount = salesAmount;
		}
		/**
		 * @return the year
		 */
		public String getYear() {
			return year;
		}
		/**
		 * @param year the year to set
		 */
		public void setYear(String year) {
			this.year = year;
		}
		/**
		 * @return the month
		 */
		public String getMonth() {
			return month;
		}
		/**
		 * @param month the month to set
		 */
		public void setMonth(String month) {
			this.month = month;
		}
		/**
		 * @return the salesAmount
		 */
		public int getSalesAmount() {
			return salesAmount;
		}
		/**
		 * @param salesAmount the salesAmount to set
		 */
		public void setSalesAmount(int salesAmount) {
			this.salesAmount = salesAmount;
		}
		
		
	}
	
	
	private MerchandiseObject itemObject = null;
	private ArrayList<SalesInfo> salesInfoList = new ArrayList<SalesInfo>();
	
	
	public MerchandiseDetailObject(String errorMessage) {
		itemObject = new MerchandiseObject(errorMessage);
	}
	
	public MerchandiseDetailObject(MerchandiseObject itemObject, ArrayList<SalesInfo> salesInfoList) {
		this.itemObject = itemObject;
		this.salesInfoList = salesInfoList;
	}
	
	public boolean isSuccessful() {
		return itemObject.isSuccessful();
	}
	
	public void setSuccessful(boolean isSuccessful) {
		itemObject.setSuccessful(isSuccessful);
	}

	public String getErrorMessage() {
		return itemObject.getErrorMessage();
	}
	
	
	public void setErrorMessage(String errorMessage) {
		itemObject.setErrorMessage(errorMessage);
	}


	public ArrayList<SalesInfo> getSalesInfoList() {
		return salesInfoList;
	}





	public void setSalesInfoList(ArrayList<SalesInfo> salesInfoList) {
		this.salesInfoList = salesInfoList;
	}





	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getBarcode()
	 */
	public String getBarcode() {
		return itemObject.getBarcode();
	}



	/**
	 * @param barcode
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setBarcode(java.lang.String)
	 */
	public void setBarcode(String barcode) {
		itemObject.setBarcode(barcode);
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getName()
	 */
	public String getName() {
		return itemObject.getName();
	}



	/**
	 * @param name
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setName(java.lang.String)
	 */
	public void setName(String name) {
		itemObject.setName(name);
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getModel()
	 */
	public String getModel() {
		return itemObject.getModel();
	}



	/**
	 * @param model
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setModel(java.lang.String)
	 */
	public void setModel(String model) {
		itemObject.setModel(model);
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getUnit()
	 */
	public String getUnit() {
		return itemObject.getUnit();
	}



	/**
	 * @param unit
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setUnit(java.lang.String)
	 */
	public void setUnit(String unit) {
		itemObject.setUnit(unit);
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPrice()
	 */
	public long getPrice() {
		return itemObject.getPrice();
	}



	/**
	 * @param price
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPrice(long)
	 */
	public void setPrice(long price) {
		itemObject.setPrice(price);
	}


	public long getCost() {
		return itemObject.getCost();
	}
	
	
	public void setCost(long cost) {
		itemObject.setCost(cost);
	}
	
	
	
	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getStorage()
	 */
	public int getStorage() {
		return itemObject.getStorage();
	}



	/**
	 * @param storage
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setStorage(int)
	 */
	public void setStorage(int storage) {
		itemObject.setStorage(storage);
	}



	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPreview()
	 */
	public Drawable getPreview() {
		return itemObject.getPreview();
	}



	/**
	 * @param preview
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPreview(android.graphics.drawable.Drawable)
	 */
	public void setPreview(Drawable preview) {
		itemObject.setPreview(preview);
	}
	
	
	
	
}
