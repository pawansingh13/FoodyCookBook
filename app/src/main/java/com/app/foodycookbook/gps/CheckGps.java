package com.app.foodycookbook.gps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import java.util.List;

public class CheckGps {

    Context mContext;


    public CheckGps(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isGpsEnable() {
        final LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(mContext)) {
            return true;
        }
        return false;
    }

    private boolean hasGPSDevice(Context mContext) {
        final LocationManager mgr = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("GPS Not Enabled");
        alertDialog.setMessage("Do you wants to turn On GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });


        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            showAlertDialog();
             dialog.cancel();

            }
        });


        alertDialog.show();
    }


    public void showAlertDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("Location Services Not Active");

        alertDialog.setMessage("Please enable Location Services and GPS.This Service is used to improve location accuracy and  location based services.");


        alertDialog.setPositiveButton("Goto Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });


        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ((Activity)mContext).finish();

            }
        });


        alertDialog.show();
    }

}
