package tongji.sse.outletmanager.logic.data;

import android.graphics.drawable.Drawable;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;

public class Merchandise {
	private MerchandiseObject itemObject = null;
	
	public Merchandise() {
		itemObject = new MerchandiseObject();
	}
	
	public Merchandise(String errorMessage) {
		itemObject = new MerchandiseObject(errorMessage);
	}

	public Merchandise(String barcode, String name, String model, String unit, long price, long cost,
			int storage, Drawable preview) {
		itemObject = new MerchandiseObject(barcode, name, model, unit, price, cost, storage, preview);
	}
	
	public Merchandise(MerchandiseObject item) {
		this.itemObject = item;
	}

	
	
	/**
	 * @return the item
	 */
	public MerchandiseObject getMerchandiseObject() {
		return itemObject;
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
	
	
	public long getCost() {
		return itemObject.getCost();
	}
	
	public void setCost(long cost) {
		itemObject.setCost(cost);
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
	
	
}
