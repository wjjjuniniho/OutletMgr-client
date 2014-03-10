package tongji.sse.outletmanager.adapter.datamodel;

import android.graphics.drawable.Drawable;

public class MerchandiseCheckoutObject {
	private MerchandiseObject itemCheckoutObject = null;
	private int amount = 0;
	
	public MerchandiseCheckoutObject(MerchandiseObject itemObject, int amount) {
		this.itemCheckoutObject = itemObject;
		this.amount = amount;
	}

	/**
	 * @return the item
	 */
	public MerchandiseObject getItemObject() {
		return itemCheckoutObject;
	}

	/**
	 * @param itemObject the item to set
	 */
	public void setItemObject(MerchandiseObject itemObject) {
		this.itemCheckoutObject = itemObject;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#isSuccessful()
	 */
	public boolean isSuccessful() {
		return itemCheckoutObject.isSuccessful();
	}

	/**
	 * @param isSuccessful
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setSuccessful(boolean)
	 */
	public void setSuccessful(boolean isSuccessful) {
		itemCheckoutObject.setSuccessful(isSuccessful);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getErrorMessage()
	 */
	public String getErrorMessage() {
		return itemCheckoutObject.getErrorMessage();
	}

	/**
	 * @param errorMessage
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setErrorMessage(java.lang.String)
	 */
	public void setErrorMessage(String errorMessage) {
		itemCheckoutObject.setErrorMessage(errorMessage);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getBarcode()
	 */
	public String getBarcode() {
		return itemCheckoutObject.getBarcode();
	}

	/**
	 * @param barcode
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setBarcode(java.lang.String)
	 */
	public void setBarcode(String barcode) {
		itemCheckoutObject.setBarcode(barcode);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getName()
	 */
	public String getName() {
		return itemCheckoutObject.getName();
	}

	/**
	 * @param name
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setName(java.lang.String)
	 */
	public void setName(String name) {
		itemCheckoutObject.setName(name);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getModel()
	 */
	public String getModel() {
		return itemCheckoutObject.getModel();
	}

	/**
	 * @param model
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setModel(java.lang.String)
	 */
	public void setModel(String model) {
		itemCheckoutObject.setModel(model);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getUnit()
	 */
	public String getUnit() {
		return itemCheckoutObject.getUnit();
	}

	/**
	 * @param unit
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setUnit(java.lang.String)
	 */
	public void setUnit(String unit) {
		itemCheckoutObject.setUnit(unit);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPrice()
	 */
	public long getPrice() {
		return itemCheckoutObject.getPrice();
	}

	/**
	 * @param price
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPrice(long)
	 */
	public void setPrice(long price) {
		itemCheckoutObject.setPrice(price);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getCost()
	 */
	public long getCost() {
		return itemCheckoutObject.getCost();
	}

	/**
	 * @param cost
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setCost(long)
	 */
	public void setCost(long cost) {
		itemCheckoutObject.setCost(cost);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getStorage()
	 */
	public int getStorage() {
		return itemCheckoutObject.getStorage();
	}

	/**
	 * @param storage
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setStorage(int)
	 */
	public void setStorage(int storage) {
		itemCheckoutObject.setStorage(storage);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPreview()
	 */
	public Drawable getPreview() {
		return itemCheckoutObject.getPreview();
	}

	/**
	 * @param preview
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPreview(android.graphics.drawable.Drawable)
	 */
	public void setPreview(Drawable preview) {
		itemCheckoutObject.setPreview(preview);
	}
	
	
	
	
}
