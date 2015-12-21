/*
 * Copyright © 2015, 9x Media Pvt. Ltd.
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package sample.rs.storedoor.network;


import sample.rs.storedoor.constants.Constants;

public class RestError {

    private int code;
    private String message;

    public RestError(String message, int errorCode) {
        this.message = message;
        this.code = errorCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isNetworkError() {
        return this.code == Constants.NETWORK_ERROR_CODE;
    }
}
