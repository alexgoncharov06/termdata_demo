package com.github.alexwolfgoncharov.termdata.interfaces;

public enum ErrorStatus {
	WAIT (0),
	ESK1 (1),
	ESK2 (2),
	OK (3);
	private int statusCode;
	private ErrorStatus(int statusCode){
		this.statusCode = statusCode;
	}
	public int getStatusCode() {
		return statusCode;
	}

}
