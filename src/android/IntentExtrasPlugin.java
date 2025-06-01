package com.stc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;


public class IntentExtrasPlugin extends CordovaPlugin {

    private final String pluginName = "IntentExtrasPlugin";
    private CallbackContext onNewIntentCallbackContext = null;

    /**
     * Generic plugin command executor
     *
     * @param action
     * @param data
     * @param callbackContext
     * @return
     */
    @Override
    public boolean execute(final String action, final JSONArray data, final CallbackContext callbackContext) {
        Log.d(pluginName, pluginName + " called with options: " + data);

        Class params[] = new Class[2];
        params[0] = JSONArray.class;
        params[1] = CallbackContext.class;

        try {
            Method method = this.getClass().getDeclaredMethod(action, params);
            method.invoke(this, data, callbackContext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    /**
     * Send a JSON representation of the cordova intent back to the caller
     *
     * @param data
     * @param context
     */
    public boolean getExtras (final JSONArray data, final CallbackContext context) {
        if(data.length() != 0) {
            context.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
            return false;
        }

        Intent intent = cordova.getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        try {
            JSONObject jsonObject = new JSONObject();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    jsonObject.put(key, bundle.get(key) != null ? bundle.get(key) : "NULL");
                }
            }
            context.sendPluginResult(new PluginResult(PluginResult.Status.OK, jsonObject));
        }
        catch(JSONException ex) {
            context.sendPluginResult(new PluginResult(PluginResult.Status.OK, "JSON_ERROR" + ex.getMessage()));
        }
        return true;
    }
}
