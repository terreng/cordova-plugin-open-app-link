package com.terrengurule.openapplink;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Process;

import android.os.Build;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.content.Intent;

import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Set;
import java.util.List;
import java.util.HashSet;


public class OpenAppLink extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        Context context = this.cordova.getContext();
        String url = data.getString(0);
            
        if (launchUri(context, Uri.parse(url))) {

			callbackContext.success("true");

            return true;

        } else {

            callbackContext.success("false");
			
			return true;
			
		}
    }

    private static boolean launchNativeBeforeApi30(Context context, Uri uri) {
        PackageManager pm = context.getPackageManager();

        // Get all Apps that resolve a generic url
        Intent browserActivityIntent = new Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setData(Uri.fromParts("http", "", null));
        Set<String> genericResolvedList = extractPackageNames(
                pm.queryIntentActivities(browserActivityIntent, 0));

        // Get all apps that resolve the specific Url
        Intent specializedActivityIntent = new Intent(Intent.ACTION_VIEW, uri)
                .addCategory(Intent.CATEGORY_BROWSABLE);
        Set<String> resolvedSpecializedList = extractPackageNames(
                pm.queryIntentActivities(specializedActivityIntent, 0));

        // Keep only the Urls that resolve the specific, but not the generic
        // urls.
        resolvedSpecializedList.removeAll(genericResolvedList);

        // If the list is empty, no native app handlers were found.
        if (resolvedSpecializedList.isEmpty()) {
            return false;
        }

        // We found native handlers. Launch the Intent.
        specializedActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(specializedActivityIntent);
        return true;
    }

    public static Set<String> extractPackageNames(List<ResolveInfo> resolveInfos) {
        Set<String> packageNameSet = new HashSet<>();
        for (ResolveInfo ri: resolveInfos) {
            packageNameSet.add(ri.activityInfo.packageName);
        }
        return packageNameSet;
    }

    static boolean launchUri(Context context, Uri uri) {
        boolean launched = Build.VERSION.SDK_INT >= 30 ?
                launchNativeApi30(context, uri) :
                launchNativeBeforeApi30(context, uri);

        return launched;
    }
    
    static boolean launchNativeApi30(Context context, Uri uri) {
        Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW, uri)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER);
        try {
            context.startActivity(nativeAppIntent);
            return true;
        } catch (ActivityNotFoundException ex) {
            return false;
        }
    }
	
}