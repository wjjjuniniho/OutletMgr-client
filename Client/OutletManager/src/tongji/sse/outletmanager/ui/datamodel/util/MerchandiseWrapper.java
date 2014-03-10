package tongji.sse.outletmanager.ui.datamodel.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.data.Merchandise;

public class MerchandiseWrapper {
	private Merchandise merchandise = null;
	
	public MerchandiseWrapper(Merchandise merchandise) {
		this.merchandise = merchandise;
	}
	
	
	

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getBarcode()
	 */
	public String getBarcode() {
		return merchandise.getBarcode();
	}


	/**
	 * @param barcode
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setBarcode(java.lang.String)
	 */
	public void setBarcode(String barcode) {
		merchandise.setBarcode(barcode);
	}


	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getName()
	 */
	public String getName() {
		return merchandise.getName();
	}

	/**
	 * @param name
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setName(java.lang.String)
	 */
	public void setName(String name) {
		merchandise.setName(name);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getModel()
	 */
	public String getModel() {
		return merchandise.getModel();
	}

	/**
	 * @param model
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setModel(java.lang.String)
	 */
	public void setModel(String model) {
		merchandise.setModel(model);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getUnit()
	 */
	public String getUnit() {
		return merchandise.getUnit();
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPrice()
	 */
	public long getPrice() {
		return merchandise.getPrice();
	}

	/**
	 * @param price
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPrice(long)
	 */
	public void setPrice(long price) {
		merchandise.setPrice(price);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getStorage()
	 */
	public int getStorage() {
		return merchandise.getStorage();
	}

	/**
	 * @param storage
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setStorage(int)
	 */
	public void setStorage(int storage) {
		merchandise.setStorage(storage);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#getPreview()
	 */
	public Drawable getPreview() {
		return merchandise.getPreview();
	}

	/**
	 * @param preview
	 * @see tongji.sse.outletmanager.adapter.datamodel.MerchandiseObject#setPreview(android.widget.ImageView)
	 */
	public void setPreview(Drawable preview) {
		merchandise.setPreview(preview);
	}
	
	public Merchandise matchPreview(Context context) {
		String name = merchandise.getName();
		if (name.toLowerCase().equals(MerchandiseNameConstant.IPOD_NANO)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.ipod_nano));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IPOD_SHUFFLE)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.ipod_shuffle));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IPOD_TOUCH)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.ipod_touch));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IPHONE)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.iphone));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IPHONE4S)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.iphone4s));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IMAC)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.imac));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.IPAD)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.ipad));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.MAC_MINI)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.mac_mini));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.MACBOOK_PRO)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.mac_pro));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.MACBOOK_AIR)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.mac_air));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.KOBE)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.kobe));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.KAKA)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.kaka));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.JAMES)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.james));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.ROSE)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.rose));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.RONALDO)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.ronaldo));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.FOOTBALL)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.football));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.BASKETBALL)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.basketball));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.VOLLEYBALL)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.volleyball));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.TROPHY)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.trophy));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.TENNISBALL)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.tennis_ball));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.GOLFBALL)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.golf_ball));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.BRACKET)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.bracket));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.GOGGLE)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.goggle));
		} else if (name.toLowerCase().equals(MerchandiseNameConstant.BOXING_GLOVES)) {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.boxing_gloves));
		} else {
			merchandise.setPreview(context.getResources().getDrawable(R.drawable.blue_question));
		}
		
		
		
		
		
		
		
		
		
		

		return merchandise;
		
	}
	
	
}
