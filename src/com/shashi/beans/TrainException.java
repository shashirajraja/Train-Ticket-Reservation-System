package com.shashi.beans;

import java.io.IOException;

import com.shashi.constant.ResponseCode;

public class TrainException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private int statusCode;

	public TrainException(ResponseCode errorCodes) {
		super(errorCodes.getMessage());
		this.statusCode = errorCodes.getCode();
		this.errorMessage = errorCodes.getMessage();
		this.setErrorCode(errorCodes.name());
	}

	public TrainException(String errorMessage) {
        super(errorMessage);
        this.errorCode = "BAD_REQUEST";
        this.setStatusCode(400);
        this.errorMessage = errorMessage;
    }
	
	public TrainException(int statusCode, String errorCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
