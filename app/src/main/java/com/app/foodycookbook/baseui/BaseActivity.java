package com.app.foodycookbook.baseui;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.apputils.AppConstants;
import com.app.foodycookbook.apputils.BaseUtils;
import com.app.foodycookbook.apputils.FileUtils;
import com.app.foodycookbook.feature.meal.MealFragment;
import com.app.foodycookbook.preference.Prefs;
import com.app.foodycookbook.preference.SPKeys;
import com.app.foodycookbook.utills.AddOnScrollListener;
import com.app.foodycookbook.utills.Const;
import com.app.foodycookbook.utills.CountDownTimerListener;
import com.app.foodycookbook.utills.Validation;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/*Base activity class for all the activity used throughout application to share common methods...*/
public class BaseActivity extends AppCompatActivity implements AppConstants {
    public String mValue;
    public static String imgeButeArray;
    public DrawerLayout mDlMenuItem;
    public Geocoder mGeocoder;
    public List<Address> mAddresses = null;
    public Dialog mAlertDialog, mChangeValueDialog;
    private AlertDialog mChooseImageAlert;
    private final int REQUEST_CODE_TAKE_PICTURE = 3000;
    private final int REQUEST_CODE_GALLERY = 3001;
    private Uri mImageCaptureUri;
    private OnCapturedListener mCapturedListener;
    private File mFile;
    private OnChangeValueListener mChangeValueListener;
    private ProgressDialog pDialog;
    private boolean isScrolling;
    private int mVisibleItem;
    private int mCurrentItem;
    private int mTotlaItem;


    public boolean isRunning = false;

    AddOnScrollListener mAddOnScrollListener;

    private CountDownTimer mCountDownTimer;
    public ImageView mIvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);

    }


    public String setTwoDecimalFormat(float value) {
        return String.format("%.2f", value);

    }

    /**
     * this method used for removing the current fragment;
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentById(R.id.fl_container);
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            return fragments.get(fragments.size() - 1);
//            for (Fragment fragment : fragments) {
//                if (fragment != null && fragment.isVisible())
//                    return fragment;
//            }
        }
        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Const.CELL_PHONE_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

            }
        }
    }

    /**
     * here we are the start the countdown timer.
     *
     * @param countDownTime
     * @param countDownTimerListener
     * @param mTvGeekResponceInfo
     */
    public void countDownTimerStart(long countDownTime, CountDownTimerListener countDownTimerListener, TextView mTvGeekResponceInfo) {
        mCountDownTimer = new CountDownTimer(countDownTime, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                countDownTimerListener.CountDownTimerListener(millisUntilFinished, mTvGeekResponceInfo);
            }

            public void onFinish() {
                countDownTimerListener.countDownFinsh(mTvGeekResponceInfo);
            }
        }.start();
    }

    public void cancelCountDownTimer() {
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }


    /**
     * * here we are  set the View resources of the two button option layoput.
     *
     * @param isFlagChecker
     * @param mLlOptionOne
     * @param mLlOptionTwo
     * @param mTvOptionOne
     * @param mTvOptionTwo
     */
    public void setClickResources(boolean isFlagChecker, LinearLayout mLlOptionOne, LinearLayout mLlOptionTwo, TextView mTvOptionOne, TextView mTvOptionTwo) {
        mLlOptionOne.setSelected(isFlagChecker ? true : false);
        mLlOptionTwo.setSelected(isFlagChecker ? false : true);
        mTvOptionOne.setText(isFlagChecker ? getString(R.string.map_view) : getString(R.string.map_view));
        mTvOptionTwo.setText(isFlagChecker ? getString(R.string.list_view) : getString(R.string.list_view));
        mTvOptionOne.setCompoundDrawablesWithIntrinsicBounds(isFlagChecker ? R.mipmap.ic_tab_mapview_active : R.mipmap.ic_tab_mapview, 0, 0, 0);
        mTvOptionTwo.setCompoundDrawablesWithIntrinsicBounds(isFlagChecker ? R.mipmap.ic_tab_listview : R.mipmap.ic_tab_listview_active, 0, 0, 0);
        mTvOptionOne.setTextColor(isFlagChecker ? getResources().getColor(R.color.black) : getResources().getColor(R.color.password_color));
        mTvOptionTwo.setTextColor(isFlagChecker ? getResources().getColor(R.color.password_color) : getResources().getColor(R.color.black));

    }


    /**
     * used for pagination when scroll the mRecyclerView
     *
     * @param manager
     * @param mRecyclerView
     */

    public void addOnScrollListener(LinearLayoutManager manager, RecyclerView mRecyclerView) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mVisibleItem = manager.findFirstVisibleItemPosition();
                    mCurrentItem = manager.getChildCount();
                    mTotlaItem = manager.getItemCount();
                    if (isScrolling && (mVisibleItem + mCurrentItem == mTotlaItem)) {
                        isScrolling = false;
                        mAddOnScrollListener.hitApiListener();
                    }
                }
            }

        });
    }

    /**
     * This method is used to initialize the AddOnScrollListener
     *
     * @param listener
     */
    public void setoNAddOnScrollListener(AddOnScrollListener listener) {
        mAddOnScrollListener = listener;
    }


    /**
     * here we are doing  show and hide the password.
     *
     * @param mCheckBox
     * @param mEditText
     */

    public void setOnCheckedChangeListener(CheckBox mCheckBox, EditText mEditText) {
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    /**
     * here we are doing  show and hide the password.
     *
     * @param mCheckBox
     * @param mTextView
     */

    public void setOnCheckedChangeListener(CheckBox mCheckBox, TextView mTextView) {
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTextView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mTextView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


//    public void doSessionExpired(String errorMessage) {
//        if (errorMessage.equalsIgnoreCase(Const.user_not_authenticated)
//                || errorMessage.equalsIgnoreCase(Const.authenticated_failed)) {
//            logout(SPKeys.IS_LOGIN);
//            View.OnClickListener onOkClickListener = view -> {
//                dismissMessageAlertDialog();
//                switchActivity(LoginActivity.class);
//                Validation.overridePendingTransition(BaseActivity.this);
//                finish();
//            };
//            showAlertMessageDialog(getString(R.string.ALERT_MESAAGE), Const.session_expired_please_login_again,
//                    onOkClickListener, getString(R.string.OK), null, null);
//        } else {
//            showAlertMessageDialog(getString(R.string.ALERT_MESAAGE), errorMessage,
//                    null, getString(R.string.OK), null, null);
//        }
//    }

    public void customToast(String message) {
        View toastView = getLayoutInflater().inflate(R.layout.toast_view, null);
        // Initiate the Toast instance.
        TextView tvMesaage = toastView.findViewById(R.id.tv_message);
        tvMesaage.setText(message);
        toastView.setBackgroundResource(R.color.transparent);
        Toast toast = new Toast(this);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public void customSnack(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setBackgroundResource(R.color.transparent);
        // Hide the text
        TextView textView = layout.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate our custom view
        View snackView = inflater.inflate(R.layout.snackbar_view, null);
        TextView tvMesaage = snackView.findViewById(R.id.tv_message);
        tvMesaage.setText(message);
        snackView.setBackgroundResource(R.color.transparent);
        layout.addView(snackView, 0);
        snackbar.show();
    }


    /**
     * change the image size.
     *
     * @param iconResID
     */
    public Drawable changeImageSize(int iconResID) {
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(iconResID);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        Drawable drawable = new BitmapDrawable(getResources(), smallMarker);
        return drawable;
    }


    public void setEditTextView(TextView dummyText, EditText editText, TextInputLayout textInputLayout,
                                String hint, int icon, int inActiveIcon, View layout) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus) {
                new Handler().postDelayed(() -> {
                    textInputLayout.setHint(null);
                    dummyText.setVisibility(View.VISIBLE);
                    editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
                    layout.setBackground(getDrawable(R.drawable.bg_selected_et));
                }, 100);
            } else {
                if (editText.getText().toString().length() > 0) {
                    textInputLayout.setHint(null);
                    dummyText.setVisibility(View.VISIBLE);
                    // layout.setBackground(getDrawable(R.drawable.bg_edittext));
                } else {
                    dummyText.setVisibility(View.INVISIBLE);
                    textInputLayout.setHint(hint);
                    layout.setBackground(getDrawable(R.drawable.bg_un_selected_et));
                    editText.setCompoundDrawablesWithIntrinsicBounds(inActiveIcon, 0, 0, 0);
                }
            }
        });
    }


    private void showPicTypeDialog(Uri uri) {
        try {

            mFile = FileUtils.getFileToKeepImage(BaseActivity.this);
            InputStream inputStream = getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(mFile);
            FileUtils.copyStream(inputStream, fileOutputStream);
            fileOutputStream.close();
            if (inputStream != null)
                inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mCapturedListener != null) {
            mCapturedListener.onCaptured(Uri.fromFile(mFile));
        }
    }


    public void dismissUpdateFieldDailogBox() {
        mAlertDialog.dismiss();
    }


    /**
     * this method is used to show Post dialog
     */
    public void showPicDialog() {
        String[] items = getResources().getStringArray(R.array.upload_photo);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.upload_image));
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals(items[0])) {
                startCamera();
            } else if (items[item].equals(items[1])) {
                openGallery();
            } else if (items[item].equals(items[2])) {
                mChooseImageAlert.dismiss();
            }
        });
        mChooseImageAlert = builder.create();


        if (!mChooseImageAlert.isShowing())
            mChooseImageAlert.show();
    }


    /**
     * This method is used to start camera
     */
    public void startCamera() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String BASE_DIR = getExternalCacheDir().getPath();

            File file = new File(BASE_DIR, "tmp_" + System.currentTimeMillis() + ".jpg");

            mImageCaptureUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);

            photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                photoPickerIntent.setClipData(ClipData.newRawUri("", mImageCaptureUri));
                photoPickerIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            startActivityForResult(photoPickerIntent, REQUEST_CODE_TAKE_PICTURE);

        } else {
            Log.v(getClass().getSimpleName(), "Media not mounted.");
        }
    }

    /**
     * This method is used to take picture from gallery
     */
    public void openGallery() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(
                    Intent.createChooser(photoPickerIntent, "Select Picture"),
                    REQUEST_CODE_GALLERY);
        } else {
            Log.v(getClass().getSimpleName(), "Media not mounted.");
        }
    }

    /**
     * This method is used to initialize the OnPicCapturedListener
     *
     * @param listener
     */
    public void setOnCapturedListener(OnCapturedListener listener) {
        mCapturedListener = listener;
    }

    /**
     * This interface is used to catch the photo bitmap from camera or gallery
     * and navigate, on where this interface is implemented
     */
    public interface OnCapturedListener {
        void onCaptured(Uri uri);
    }

    /**
     * Method used to switch from current activity to other
     *
     * @param destinationActivity activity to open
     */
    public void switchActivity(Class<?> destinationActivity) {
        startActivity(new Intent(this, destinationActivity));
    }

    /**
     * Method used to switch from current activity to other with data
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     */
    public void switchActivity(Class<?> destinationActivity, Bundle bundle) {
        Intent intent = new Intent(this, destinationActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * method used to starting another activity for result
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     * @param requestCode         result code
     */
    public void switchActivityForResult(Class<?> destinationActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, destinationActivity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * method used to starting another activity for result
     *
     * @param destinationActivity activity to open
     * @param requestCode         result code
     */
    public void switchActivityForResult(Class<?> destinationActivity, int requestCode) {
        Intent intent = new Intent(this, destinationActivity);
        startActivityForResult(intent, requestCode);
    }

    /**
     * This method displays provided message on SnackBar
     *
     * @param view
     * @param message
     */
    public void showSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, 3500);
        final View snackBarView = snackbar.getView();
        snackBarView.setPadding(0, (int) getResources().getDimension(R.dimen.padding_minus_8),
                0, (int) getResources().getDimension(R.dimen.padding_minus_8));

        snackBarView.setBackgroundColor(getResources().getColor(android.R.color.black));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setMaxLines(5);
        snackbar.show();
    }

    /**
     * Method used to display short duration toast
     *
     * @param resId resource id of the message string to be displayed
     */
    public void showSnackBar(View view, int resId) {
        showSnackBar(view, getString(resId));
    }


    /**
     * here we are get the address using location
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public List<Address> getAddressFromTheLocation(double latitude, double longitude) {
        try {
            mAddresses = mGeocoder.getFromLocation(
                    latitude,
                    longitude,
                    // In this sample, get just a single address.
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mAddresses;
    }


    /**
     * Method to hide soft keyboard
     */
    public void hideKeyBoard() {
// Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showProgressDialog(String message) {
//        if (mDialog == null) {
//            mDialog = new ACProgressFlower.Builder(this)
//                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                    .themeColor(Color.WHITE)
//                    .bgColor(this.getResources().getColor(R.color.green))
//                    .text(message)
//                    .fadeColor(Color.DKGRAY).build();
//            mDialog.show();
//        }
        if (pDialog == null)
            pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait");
        pDialog.setCancelable(false);

        if (!pDialog.isShowing() && !isFinishing())
            pDialog.show();
    }

    public void dismissProgressDialog() {
        if (pDialog.isShowing() && !isFinishing())
            pDialog.dismiss();

    }


    /**
     * Show image between a textview
     *
     * @param textView
     * @param message
     * @param ic_cap
     */
    public void showImageBetweenText(TextView textView, String message, int left, int top, int right, int bottom, int ic_cap) {
        int $index = message.indexOf("$");
        Spannable span = Spannable.Factory.getInstance().newSpannable(message);
        Drawable drawable = getResources().getDrawable(ic_cap);
        drawable.setBounds(left, top, right, bottom);
        span.setSpan(new ImageSpan(drawable),
                $index, $index + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(span);
    }

    /**
     * here we are set the  mBottomNavigationView visibility or not.
     *
     * @param visibleOrNotFlag
     */
    public void mViewVisibleOrNot(boolean visibleOrNotFlag, View mView) {
        mView.setVisibility(visibleOrNotFlag ? View.VISIBLE : View.GONE);
    }

    /**
     * here we are implement the close Drawer fucntionality.
     */
    public void closeDrawer() {
        mDlMenuItem.closeDrawer(GravityCompat.START, false);
    }

    public void setHeader(String title) {
        ((TextView) findViewById(R.id.tv_header)).setText(title);
    }


    public void setUpToolbar(boolean isBack, boolean isDrawer, boolean isEditTextSearch, boolean isSearch) {
        mIvBack = findViewById(R.id.iv_back);
        ImageView ivDrawer = findViewById(R.id.img_drawer);
        EditText mEtSearch = findViewById(R.id.et_search);
        ImageView mIvSearch = findViewById(R.id.iv_search);
        ivDrawer.setOnClickListener(v -> openDrawer());
        mIvBack.setOnClickListener(v -> {
            onBackPressed();
            Validation.overridePendingTransition(this);
        });
        if (isBack) {
            mIvBack.setVisibility(View.VISIBLE);
        } else {
            mIvBack.setVisibility(View.INVISIBLE);
        }
        if (isDrawer) {
            ivDrawer.setVisibility(View.VISIBLE);
        } else {
            ivDrawer.setVisibility(View.INVISIBLE);
        }
        if (isEditTextSearch) {
            mEtSearch.setVisibility(View.VISIBLE);
        } else {
            mEtSearch.setVisibility(View.INVISIBLE);
        }
        if (isSearch) {
            mIvSearch.setVisibility(View.VISIBLE);
        } else {
            mIvSearch.setVisibility(View.INVISIBLE);
        }

    }


    public void openDrawer() {
        mDlMenuItem.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onSpannableClick(String message, TextView view, View.OnClickListener
            spanClickListener, String splitString) {
        SpannableString ss = new SpannableString(message);

        ClickableSpan signUp = new ClickableSpan() {
            @Override
            public void onClick(@NotNull View view) {
                if (spanClickListener != null)
                    view.setOnClickListener(spanClickListener);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(signUp, message.indexOf(splitString), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 0, message.indexOf(splitString), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), message.indexOf(splitString) + 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(ss);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * this method used for text bold, size increase and colcor change bitween the text
     *
     * @param textView
     * @param startindex
     * @param endIndex
     * @param text
     */
    public void makeTextBoldAndSizeChange(TextView textView, int startindex,
                                          int endIndex, String text) {
        SpannableString mSpannableString = new SpannableString(textView.getText().toString().trim());
        mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), startindex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSpannableString.setSpan(new StyleSpan(Typeface.BOLD), startindex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSpannableString.setSpan(new RelativeSizeSpan(1.3f), startindex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(mSpannableString);
    }


    /**
     * Show internet error
     */
    public void showInternetError() {
        Toast.makeText(this, getResources().getString(R.string.internet_error), Toast.LENGTH_SHORT).show();
    }


    public interface OnChangeValueListener {
        void onChangeValue(String value);

    }

    public void setChangeValueListener(OnChangeValueListener listener) {
        mChangeValueListener = listener;
    }


    public void showAlertMessageDialog(String alertHeader, String message,
                                       View.OnClickListener positiveButtonListener, String positiveButtonText,
                                       String negativeButtonText, View.OnClickListener negativeButtonListener) {
        if (BaseUtils.isKeyboardOpen) {
            hideKeyBoard();
        }
        /*
         * no need to re-initialized every worktime
         */
        if (mAlertDialog == null) {
            mAlertDialog = new Dialog(BaseActivity.this, R.style.CustomDialogTheme);
            mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mAlertDialog.setContentView(R.layout.alert_dialog_with_both_btns);
            mAlertDialog.setCanceledOnTouchOutside(false);
            mAlertDialog.setCancelable(false);
        }
        View view = mAlertDialog.findViewById(R.id.center_divider);
        TextView header = mAlertDialog.findViewById(R.id.header);
        if (alertHeader != null)
            header.setText(alertHeader);
        else
            header.setVisibility(View.GONE);

        TextView title = mAlertDialog.findViewById(R.id.message);
        if (message != null)
            title.setText(message);
        TextView ok = mAlertDialog.findViewById(R.id.positive_btn);
        ok.setText(positiveButtonText);

        if (positiveButtonText != null) {
            if (positiveButtonListener == null) {
                ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mAlertDialog.dismiss();
                    }
                });
            } else
                ok.setOnClickListener(positiveButtonListener);
        }

        TextView cancel = mAlertDialog.findViewById(R.id.negative_btn);
        cancel.setText(negativeButtonText);

        if (negativeButtonText != null) {
            cancel.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        } else {
            cancel.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }


        if (negativeButtonListener == null) {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAlertDialog.dismiss();
                }
            });
        } else
            cancel.setOnClickListener(negativeButtonListener);

        cancel.setVisibility(negativeButtonText == null ? View.GONE : View.VISIBLE);

        /*
         * do not show if already showing
         */
        if (!mAlertDialog.isShowing())
            mAlertDialog.show();
    }

    public void dismissMessageAlertDialog() {
        if (mAlertDialog != null)
            mAlertDialog.dismiss();
    }

    public void copyTextUsingClipBoard(String referrallink) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", referrallink);
        clipboard.setPrimaryClip(clip);
    }

    /**
     * here we are load the Fragment.
     */
    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            if (fragment instanceof MealFragment) {
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).addToBackStack(null).commit();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).addToBackStack(fragment.getClass().getName()).commit();
            }
            overridePendingTransition(0, 0);
            closeDrawer();

        }
    }

    private void removeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = manager.beginTransaction();
        mFragmentTransaction.remove(getCurrentFragment());
        mFragmentTransaction.commit();
    }
}
