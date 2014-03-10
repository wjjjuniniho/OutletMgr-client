package tongji.sse.outletmanager.adapter.datamodel;

import android.graphics.drawable.Drawable;

public class MerchandiseObject {
	private String barcode = null;
	private String name = null;
	private String model = null;
	private String unit = "$";
	private long price = 0l;
	private long cost = 0l;
	private int storage = 0;
	private Drawable preview = null;
	
	private StatusObject statusObject = null;

	public MerchandiseObject() {
		statusObject = new StatusObject();
	}
	
	public MerchandiseObject(String errorMessage) {
		statusObject = new StatusObject(errorMessage);
	}

	public MerchandiseObject(String barcode, String name, String model, String unit, long price, long cost, 
			int storage, Drawable preview) {
		this.barcode = barcode;
		this.name = name;
		this.model = model;
		this.unit = unit;
		this.price = price;
		this.cost = cost;
		this.storage = storage;
		this.preview = preview;
		this.statusObject = new StatusObject();
	}
	
	public boolean isSuccessful() {
		return statusObject.isSuccessful();
	}
	
	public void setSuccessful(boolean isSuccessful) {
		statusObject.setSuccessful(isSuccessful);
	}
	
	public String getErrorMessage() {
		return statusObject.getErrorMessage();
	}
	
	public void setErrorMessage(String errorMessage) {
		statusObject.setErrorMessage(errorMessage);
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public Drawable getPreview() {
		return preview;
	}

	public void setPreview(Drawable preview) {
		this.preview = preview;
	}

}
