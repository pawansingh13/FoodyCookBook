package com.app.foodycookbook.apputils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.networking.RetrofitException;

import java.util.Map;

import io.reactivex.exceptions.CompositeException;

public class BaseUtils {

    private static final String TAG = "BaseUtils";
    public static boolean isKeyboardOpen = false;

    /**
     * This method will return true if network connection is available
     *
     * @param context
     * @return
     */
    public static boolean checkNetwork(Context context) {
        if (context != null) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }
        }

        return false;
    }

    /**
     * Method to open soft keyboard
     *
     * @param activity
     * @param editText
     */
    public static void openKeyBoard(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * Parse api error and return failure msg
     */
    public static String parseError(Throwable e) {
        String msg = FoodyCookBookApplication.getApp().getString(R.string.server_error);
        if (e instanceof RetrofitException) {
            RetrofitException error = (RetrofitException) e;
            if (error.getKind() == RetrofitException.Kind.NETWORK) {
                msg = FoodyCookBookApplication.getApp().getString(R.string.no_internet_connection);
            } else {
                try {
                    Object object = error.getErrorBodyAs(Object.class);
                    Map<String, Object> data = (Map<String, Object>) object;

                    String serverMsg = (String) data.get("Message");
                    msg = (!TextUtils.isEmpty(serverMsg) ? serverMsg :
                            FoodyCookBookApplication.getApp().getString(R.string.server_error));

                } catch (Exception castException) {
                    Log.v(TAG, e.getLocalizedMessage());
                }
            }

        } else if (e instanceof CompositeException) {
            CompositeException error = (CompositeException) e;
            msg = error.getMessage();
            return msg;
        }
        return msg;
    }


}