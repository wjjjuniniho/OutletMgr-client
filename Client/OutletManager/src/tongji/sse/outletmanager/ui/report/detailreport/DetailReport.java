package tongji.sse.outletmanager.ui.report.detailreport;

import java.util.ArrayList;

import tongji.sse.outletmanager.logic.data.MerchandiseDetail;

public class DetailReport {

	private static DetailReport detailReport = null;
	private ArrayList<MerchandiseDetail> itemDetails = new ArrayList<MerchandiseDetail>();

	private DetailReport() {

	}

	public static DetailReport getInstance() {
		if (detailReport == null) {
			detailReport = new DetailReport();
		}
		return detailReport;
	}

	public MerchandiseDetail getItemDetail(int position) {
		return itemDetails.get(position);
	}
	
	public void setItemDetails(ArrayList<MerchandiseDetail> itemDetails) {
		this.itemDetails = itemDetails;
	}
	
	public int getItemDetailAmount() {
		return itemDetails.size();
	}
	
	
	public int getItemSales(int position) {
		int sales = 0;
		for (MerchandiseDetail.MerchandiseSalesInfo salesInfo : itemDetails.get(position).getSalesInfoList()) {
			sales += salesInfo.getSalesAmount();
		}
		return sales;
	}
	
	public long getItemTotalPrice(int position) {
		long price = itemDetails.get(position).getPrice();
		long totalPrice = 0;
		
		for (MerchandiseDetail.MerchandiseSalesInfo salesInfo : itemDetails.get(position).getSalesInfoList()) {
			totalPrice += salesInfo.getSalesAmount() * price;
		}
		
		return totalPrice;
	}
	
	public long getItemTotalCost(int position) {
		long cost = itemDetails.get(position).getCost();
		long totalCost = 0;
		
		for (MerchandiseDetail.MerchandiseSalesInfo salesInfo : itemDetails.get(position).getSalesInfoList()) {
			totalCost += salesInfo.getSalesAmount() * cost;
		}
		
		return totalCost;
	}
	
	
	public long getItemTotalProfit(int position) {
		long cost = itemDetails.get(position).getCost();
		long price = itemDetails.get(position).getPrice();
		long totalPrice = 0;
		long totalCost = 0;
		
		for (MerchandiseDetail.MerchandiseSalesInfo salesInfo : itemDetails.get(position).getSalesInfoList()) {
			totalPrice += salesInfo.getSalesAmount() * price;
			totalCost += salesInfo.getSalesAmount() * cost;
		}
		
		return (totalPrice - totalCost);
	}
	
	
	public String[] getItemRecordStrings(int position) {
		String[] valueNameList = new String[DetailReportConfig.COLUMN_NUMBER];
		MerchandiseDetail itemDetail = itemDetails.get(position);
		
		valueNameList[DetailReportConfig.NAME] = itemDetail.getName();
		valueNameList[DetailReportConfig.MODEL] = itemDetail.getModel();
		valueNameList[DetailReportConfig.SALES] = String.valueOf(getItemSales(position));
		valueNameList[DetailReportConfig.PRICE] = String.valueOf(itemDetail.getPrice());
		valueNameList[DetailReportConfig.COST] = String.valueOf(itemDetail.getCost());
		valueNameList[DetailReportConfig.TOTAL_PRICE] = String.valueOf(getItemTotalPrice(position));
		valueNameList[DetailReportConfig.TOTAL_COST] = String.valueOf(getItemTotalCost(position));
		valueNameList[DetailReportConfig.PROFIT] = String.valueOf(getItemTotalProfit(position));
		
		return valueNameList;
		
	}
	
	
	
}
