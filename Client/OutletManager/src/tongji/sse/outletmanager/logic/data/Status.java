package tongji.sse.outletmanager.logic.data;

import tongji.sse.outletmanager.adapter.datamodel.StatusObject;

public class Status {
	private StatusObject statusObject = null;
	
	public Status() {
		this.statusObject = new StatusObject();
	}
	
	public Status(StatusObject status) {
		this.statusObject = status;
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#isSuccessful()
	 */
	public boolean isSuccessful() {
		return statusObject.isSuccessful();
	}

	/**
	 * @param isSuccessful
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#setSuccessful(boolean)
	 */
	public void setSuccessful(boolean isSuccessful) {
		statusObject.setSuccessful(isSuccessful);
	}

	/**
	 * @return
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#getErrorMessage()
	 */
	public String getErrorMessage() {
		return statusObject.getErrorMessage();
	}

	/**
	 * @param errorMessage
	 * @see tongji.sse.outletmanager.adapter.datamodel.StatusObject#setErrorMessage(java.lang.String)
	 */
	public void setErrorMessage(String errorMessage) {
		statusObject.setErrorMessage(errorMessage);
	}
	
	
}
