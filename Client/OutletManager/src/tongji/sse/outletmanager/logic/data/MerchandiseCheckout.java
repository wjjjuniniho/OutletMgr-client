package tongji.sse.outletmanager.logic.data;

import tongji.sse.outletmanager.adapter.datamodel.MerchandiseCheckoutObject;
import tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject;

public class MerchandiseCheckout {
	private MerchandiseCheckoutObject itemCheckoutObject = null;
	
	public MerchandiseCheckout(Merchandise item, int amount) {
		this.itemCheckoutObject = new MerchandiseCheckoutObject(item.getMerchandiseObject(), amount);
	}

	/**
	 * @return the item
	 */
	public MerchandiseObject getItemCheckoutObject() {
		return itemCheckoutObject.getItemObject();
	}

	/**
	 * @param item the item to set
	 */
	public void setItemCheckout(Merchandise item) {
		itemCheckoutObject.setItemObject(item.getMerchandiseObject());
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return itemCheckoutObject.getAmount();
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		itemCheckoutObject.setAmount(amount);
	}
	
	
}
