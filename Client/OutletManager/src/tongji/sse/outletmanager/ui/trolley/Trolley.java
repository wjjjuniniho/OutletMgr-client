package tongji.sse.outletmanager.ui.trolley;

import java.util.ArrayList;

import android.content.Context;

import tongji.sse.outletmanager.R;
import tongji.sse.outletmanager.logic.OnItemCheckoutChangeListener;
import tongji.sse.outletmanager.logic.data.Merchandise;
import tongji.sse.outletmanager.ui.datamodel.util.MerchandiseWrapper;


public class Trolley implements OnItemCheckoutChangeListener{

	private class TrolleyEntry {
		private Merchandise item;
		private int amount;

		public TrolleyEntry(Merchandise item, int amount) {
			this.item = item;
			this.amount = amount;
		}

		public Merchandise getItem() {
			return item;
		}

		public void setItem(Merchandise item) {
			this.item = item;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public void addAmount() {
			amount++;
		}

	}

	private static Trolley trolley = null;
	private ArrayList<TrolleyEntry> trolleyEntries = new ArrayList<TrolleyEntry>();
	private Context register = null;

	private Trolley() {
	}

	public static Trolley getInstance() {
		if (trolley == null) {
			trolley = new Trolley();
		}
		return trolley;
	}
	
	public boolean isEmpty() {
		return trolleyEntries.isEmpty();
	}
	
	public int getEntryAmount() {
		return trolleyEntries.size();
	}

	public void addItem(Merchandise item) {
		for (int i = 0; i < trolleyEntries.size(); i++) {
			if (trolleyEntries.get(i).getItem().getBarcode()
					.equals(item.getBarcode())) {
				trolleyEntries.get(i).addAmount();
				return;
			}
		}
		TrolleyEntry trolleyEntry = new TrolleyEntry(item, 1);
		trolleyEntries.add(trolleyEntry);
	}

	public void removeItem(int position) {
		trolleyEntries.remove(position);
	}

	public void clearAllItems() {
		trolleyEntries.clear();
	}

	public ArrayList<TrolleyEntry> getAllItems() {
		return trolleyEntries;
	}

	public Merchandise getItem(int position) {
		return trolleyEntries.get(position).getItem();
	}

	public int getItemAmount(int position) {
		return trolleyEntries.get(position).getAmount();
	}
	
	public int getItemAmount(String barcode) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().getBarcode().equals(barcode)) {
				return trolleyEntry.getAmount();
			}
		}
		return 0;
	}

	public int getItemAmount(Merchandise item) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().equals(item)) {
				return trolleyEntry.getAmount();
			}
		}
		return 0;
	}
	
	public void setItemAmount(int position, int amount) {
		trolleyEntries.get(position).setAmount(amount);
	}
	
	public void setItemAmount(String barcode, int amount) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().getBarcode().equals(barcode)) {
				trolleyEntry.setAmount(amount);
			}
		}
	}
	
	public void setItemAmount(Merchandise item, int amount) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().equals(item)) {
				trolleyEntry.setAmount(amount);
			}
		}
	}

	public int getItemsTotalAmount() {
		int totalAmount = 0;
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			totalAmount += trolleyEntry.getAmount();
		}
		return totalAmount;
	}
	
	public long getItemTotalPrice(String barcode) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().getBarcode().equals(barcode)) {
				return trolleyEntry.getItem().getPrice() * trolleyEntry.getAmount();
			}
		}
		return 0l;
	}
	
	public long getItemTotalPrice(Merchandise item) {
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			if (trolleyEntry.getItem().equals(item)) {
				return item.getPrice() * trolleyEntry.getAmount();
			}
		}
		return 0l;
	}
	
	public long getItemsTotalPrice() {
		long totalPrice = 0l;
		for (TrolleyEntry trolleyEntry : trolleyEntries) {
			totalPrice += trolleyEntry.getItem().getPrice() * trolleyEntry.getAmount();
		}
		return totalPrice;
	}

	@Override
	public void onItemCheckoutChange(Merchandise item) {
		MerchandiseWrapper itemWrapper = new MerchandiseWrapper(item);
		item = itemWrapper.matchPreview(register);
		addItem(item);
	}

	@Override
	public void setRegister(Context register) {
		this.register = register;	
	}

}
