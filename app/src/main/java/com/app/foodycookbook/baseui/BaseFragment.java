package com.app.foodycookbook.baseui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.apputils.AppConstants;
import com.app.foodycookbook.utills.Const;


public class BaseFragment extends Fragment implements AppConstants {
    public Dialog mRatingDialog;
    private int mRating;


    /**
     * Method used to switch from current activity to other
     *
     * @param destinationActivity activity to open
     */
    public void startActivity(Class<?> destinationActivity) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivity(destinationActivity);
        }

    }

    public void openDrawer() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).openDrawer();
        }

    }


    public void doSessionExpired(String errorMessage) {
        if (getActivity() != null) {
          //  ((BaseActivity) getActivity()).doSessionExpired(errorMessage);
        }

    }

    public Fragment getCurrentFragment() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getCurrentFragment();
        }
        return null;
    }

    /**
     * Method used to switch  view Visible or Gone.
     *
     * @param visibleOrNotFlag
     * @param mView
     */
    public void mViewVisibleOrNot(boolean visibleOrNotFlag, View mView) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).mViewVisibleOrNot(visibleOrNotFlag, mView);
        }

    }

    public void loadFragment(Fragment fragment) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).loadFragment(fragment);
        }
    }

    /**
     * used for pagination when scroll the mRecyclerView
     *
     * @param manager
     * @param mRecyclerView
     */

    public void addOnScrollListener(LinearLayoutManager manager, RecyclerView mRecyclerView) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).addOnScrollListener(manager, mRecyclerView);
        }
    }


    public void customSnack(View view, String message) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).customSnack(view, message);
        }
    }


    /**
     * here we are doing  show and hide the password.
     *
     * @param mCheckBox
     * @param mEditText
     */
    public void setOnCheckedChangeListener(CheckBox mCheckBox, EditText mEditText) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).setOnCheckedChangeListener(mCheckBox, mEditText);
        }
    }

    /**
     * here we are doing  show and hide the password.
     *
     * @param mCheckBox
     * @param mTextView
     */
    public void setOnCheckedChangeListener(CheckBox mCheckBox, TextView mTextView) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).setOnCheckedChangeListener(mCheckBox, mTextView);
        }
    }

    /**
     * Method used to switch from current activity to other with data
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     */
    public void startActivity(Class<?> destinationActivity, Bundle bundle) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivity(destinationActivity, bundle);
        }
    }

    public void setHeader(String title) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).setHeader(title);
        }
    }

    /**
     * Method used to start another activity for result
     *
     * @param destinationActivity activity to open
     * @param requestCode         requestCode
     */
    public void switchActivityForResult(Class<?> destinationActivity, Bundle bundle, int requestCode) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivityForResult(destinationActivity, bundle, requestCode);
        }
    }

    public void switchActivityForResult(Class<?> destinationActivity, int requestCode) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivityForResult(destinationActivity, requestCode);
        }
    }


    public void hideKeyBoard() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).hideKeyBoard();
        }
    }


    /**
     * This method is used to initialize the OnPicCapturedListener
     *
     * @param listener
     */
    public void setOnCapturedListener(BaseActivity.OnCapturedListener listener) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).setOnCapturedListener(listener);
        }
    }


    public void showProgressDialog() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showProgressDialog(Const.PLEASE_WAIT);
        }
    }


    public void dismissProgressDialog() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).dismissProgressDialog();
        }
    }

    public void dismissMessageAlertDialog() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).dismissMessageAlertDialog();
        }
    }

    public void showAlertMessageDialog(String alert, String string, View.OnClickListener onOkClickListener, String ok, String cancel, View.OnClickListener onCancelClickListener) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showAlertMessageDialog(alert, string, onOkClickListener, ok, cancel, onCancelClickListener);
        }
    }

    public void showInternetError() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showInternetError();
        }
    }

    public void showImageBetweenText(TextView textView, String message, int left, int top, int right, int bottom, int ic_cap) {
        ((BaseActivity) getActivity()).showImageBetweenText(textView, message, left, top, right, bottom, ic_cap);
    }


}
