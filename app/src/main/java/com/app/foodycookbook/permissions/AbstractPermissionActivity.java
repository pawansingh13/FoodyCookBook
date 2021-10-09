package com.app.foodycookbook.permissions;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.app.foodycookbook.R;
import com.app.foodycookbook.apputils.BaseUtils;
import com.app.foodycookbook.baseui.BaseActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

import static com.app.foodycookbook.permissions.PermissionUtils.ACCESS_COARSE_LOCATION;
import static com.app.foodycookbook.permissions.PermissionUtils.WRITE_EXTERNAL_STORAGE;

public abstract class AbstractPermissionActivity extends BaseActivity {
    private ArrayList<String> mPermissionsList = new ArrayList<>();
    private PermissionResult mPermissionResultCallback = null;
    private Dialog showPermissionRationaleDialogBox;
    private ArrayList<Object> disGrantedPermissionsLists, rationalePermissionsList;

    public void requestPermission(String permission, PermissionResult permissionResult) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(permission);
        requestEach(arrayList, permissionResult);
    }


    public void showPermissionRationaleDialogBox(ArrayList<String> permissions) {
        if (BaseUtils.isKeyboardOpen) {
            hideKeyBoard();
        }
        if (showPermissionRationaleDialogBox == null) {
            showPermissionRationaleDialogBox = new Dialog(this, R.style.CustomDialogTheme);
            showPermissionRationaleDialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            showPermissionRationaleDialogBox.setContentView(R.layout.custom_show_permission_rationale_lauout);
            showPermissionRationaleDialogBox.setCanceledOnTouchOutside(false);
            showPermissionRationaleDialogBox.setCancelable(true);
        }


        LinearLayout mLlPermissionInfo = showPermissionRationaleDialogBox.findViewById(R.id.ll_permission_info);
        mLlPermissionInfo.removeAllViews();
        TextView mTvLocationInfo = null;
        for (int i = 0; i < permissions.size(); i++) {
            mTvLocationInfo = new TextView(this);
            mTvLocationInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            if (permissions.get(i).equals(WRITE_EXTERNAL_STORAGE)) {
                mTvLocationInfo.setText(getString(R.string.write_external_permission_detail));
            } else if (permissions.get(i).equals(ACCESS_COARSE_LOCATION)) {
                mTvLocationInfo.setText(getString(R.string.access_coarse_location_detail));
            }
            if (mTvLocationInfo != null) {
                mLlPermissionInfo.addView(mTvLocationInfo);
            }
        }
        TextView mTvRationaleCancel = showPermissionRationaleDialogBox.findViewById(R.id.btn_rationale_cancel);
        TextView mTvnRationaleOk = showPermissionRationaleDialogBox.findViewById(R.id.btn_rational_ok);
        mTvRationaleCancel.setOnClickListener(v -> {
            showPermissionRationaleDialogBox.dismiss();
            ActivityCompat.requestPermissions(this, disGrantedPermissionsLists.toArray(new String[0]),
                    PermissionUtils.REQUEST_CODE_PERMISSIONS);

        });
        mTvnRationaleOk.setOnClickListener(v -> {
            showPermissionRationaleDialogBox.dismiss();
            ActivityCompat.requestPermissions(this, disGrantedPermissionsLists.toArray(new String[0]),
                    PermissionUtils.REQUEST_CODE_PERMISSIONS);
        });
        /*
         * do not show if already showing
         */
        if (!showPermissionRationaleDialogBox.isShowing())
            showPermissionRationaleDialogBox.show();
    }

    public void requestEach(ArrayList<String> permissions, PermissionResult permissionResult) {
        if (permissions == null || permissions.size() == 0)
            return;

        mPermissionsList.clear();
        mPermissionsList = permissions;
        mPermissionResultCallback = permissionResult;

        disGrantedPermissionsLists = new ArrayList<>();
        rationalePermissionsList = new ArrayList<>();


        for (int i = 0; i < permissions.size(); i++) {
            if (ContextCompat.checkSelfPermission(this, permissions.get(i)) != PackageManager.PERMISSION_GRANTED)
                disGrantedPermissionsLists.add(permissions.get(i));
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions.get(i)))
                rationalePermissionsList.add(permissions.get(i));
        }

        if (disGrantedPermissionsLists.size() > 0) {

            showPermissionRationaleDialogBox(permissions);

//            ActivityCompat.requestPermissions(this, disGrantedPermissionsLists.toArray(new String[0]),
//                    PermissionUtils.REQUEST_CODE_PERMISSIONS);
            return;
        }

        sendPermissionResult(mPermissionsList, true, false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtils.REQUEST_CODE_PERMISSIONS:
                ArrayList<String> deniedPermissions = new ArrayList<>();
                ArrayList<String> neverAskAgainPermissions = new ArrayList<>();
                boolean mIsAllPermissionsGranted = true;

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        mIsAllPermissionsGranted = false;
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]))
                            deniedPermissions.add(permissions[i]);
                        else
                            neverAskAgainPermissions.add(permissions[i]);
                    }
                }

                if (!mIsAllPermissionsGranted) {
                    if (deniedPermissions.size() > 0)
                        showPermissionRationale(deniedPermissions);
                    else if (neverAskAgainPermissions.size() > 0) {
                        sendPermissionResult(mPermissionsList, false, true);
                        showOpenSettingsSnackBar(neverAskAgainPermissions);
                    }

                } else
                    sendPermissionResult(mPermissionsList, true, false);
                break;
            default:
                break;
        }

    }

    private void showPermissionRationale(ArrayList<String> deniedPermissions) {
        if (deniedPermissions != null && deniedPermissions.size() > 0) {

            showRationaleMessage(getDenialPermissionsMessage(deniedPermissions), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(AbstractPermissionActivity.this, deniedPermissions.toArray(new String[0])
                            , PermissionUtils.REQUEST_CODE_PERMISSIONS);
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendPermissionResult(mPermissionsList, false, false);
                }
            });
        }
    }

    private void showOpenSettingsSnackBar(ArrayList<String> neverAskAgainPermissions) {
        Snackbar.make(getLayoutRootView(),
                getDenialPermissionsMessage(neverAskAgainPermissions),
                Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
                .setAction("open settings", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSystemSettings();
                    }
                })
                .show();
    }

    /**
     * Method used to open System Settings
     */
    private void openSystemSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, PermissionUtils.REQUEST_CODE_SETTINGS_ACTIVITY);
    }

    public abstract View getLayoutRootView();

    private boolean checkIfAllPermissionsGranted(ArrayList<String> permissions) {
        boolean mIsAllPermissionsGranted = true;
        for (int i = 0; i < permissions.size(); i++)
            if (ContextCompat.checkSelfPermission(this, permissions.get(i)) != PackageManager.PERMISSION_GRANTED)
                mIsAllPermissionsGranted = false;
        return mIsAllPermissionsGranted;
    }

    private void showRationaleMessage(String message,
                                      DialogInterface.OnClickListener okClickListener,
                                      DialogInterface.OnClickListener cancelClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied");
        builder.setPositiveButton("Ok", okClickListener);
        builder.setNegativeButton("Cancel", cancelClickListener);
        builder.setMessage(message);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        //dialog.show();
    }

    private String getDenialPermissionsMessage(ArrayList<String> permissions) {

        String mRationaleMessage = "";

        if (PermissionUtils.isPermissionWithCustomMessage(permissions.get(0))) {
            mRationaleMessage = PermissionUtils.getPermissionForCustomMessage(permissions.get(0));
        } else if (!isSinglePermission()) {
            String text = "Please grant access to ";

            for (int i = 0; i < permissions.size(); i++) {
                String msg = PermissionUtils.getPermissionRationaleTag(permissions.get(i));
                if (!TextUtils.isEmpty(msg)) {
                    if (TextUtils.isEmpty(mRationaleMessage))
                        mRationaleMessage = text + PermissionUtils.getPermissionRationaleTag(permissions.get(i));
                    else
                        mRationaleMessage = mRationaleMessage + ", " + PermissionUtils.getPermissionRationaleTag(permissions.get(i));
                }

            }
        } else
            mRationaleMessage = PermissionUtils.getPermissionRationaleMessage(permissions.get(0));


        return mRationaleMessage;
    }


    public interface PermissionResult {
        void onPermissionResult(PermissionModel permissionResult);
    }

    public class PermissionModel {
        public boolean isPermissionGranted = false;
        public boolean isPermissionDenied = false;
        public boolean isNeverAskAgain = false;
        ArrayList<String> requestedPermissions = null;
    }

    private void sendPermissionResult(ArrayList<String> permissions, Boolean isGrant, boolean isNeverAskAgain) {
        PermissionModel permissionModel = new PermissionModel();
        if (isGrant) {
            permissionModel.isPermissionGranted = true;
            permissionModel.isPermissionDenied = false;
            permissionModel.isNeverAskAgain = false;
        } else {
            permissionModel.isPermissionGranted = false;
            if (isNeverAskAgain) {
                permissionModel.isPermissionDenied = false;
                permissionModel.isNeverAskAgain = true;
            } else {
                permissionModel.isPermissionDenied = true;
                permissionModel.isNeverAskAgain = false;
            }
        }

        permissionModel.requestedPermissions = permissions;
        mPermissionResultCallback.onPermissionResult(permissionModel);
    }

    private boolean isSinglePermission() {
        return mPermissionsList == null || mPermissionsList.size() <= 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PermissionUtils.REQUEST_CODE_SETTINGS_ACTIVITY:
                if (checkIfAllPermissionsGranted(mPermissionsList))
                    sendPermissionResult(mPermissionsList, true, false);
                else
                    sendPermissionResult(mPermissionsList, false, false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPermissionResultCallback = null;
    }
}
