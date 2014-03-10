package tongji.sse.outletmanager.adapter.datamodel;

public class StatusObject {
	private boolean isSuccessful = true;
	private String errorMessage = null;
	
	public StatusObject() {
		
	}
	
	public StatusObject(String errorMessage) {
		isSuccessful = false;
		this.errorMessage = errorMessage;
	}
	
	
	/**
	 * @return the isSuccessful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}
	/**
	 * @param isSuccessful the isSuccessful to set
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
