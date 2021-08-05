package com.terrengurule.openapplink;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Process;



public class OpenAppLink extends CordovaPlugin {
	
	@Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
	}

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
            
        if (action.equals("open")) {

			callbackContext.success("true");

            return true;

        } else {
			
			return false;
			
		}
    }
	
}