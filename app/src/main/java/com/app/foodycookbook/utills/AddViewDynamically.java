package com.app.foodycookbook.utills;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.foodycookbook.R;
import com.app.foodycookbook.feature.mealdetails.Ingredients;

import java.util.ArrayList;

public class AddViewDynamically {

    public  static void setKeywords(Context context, LinearLayout llKeyword, ArrayList<Ingredients> keywords) {

        llKeyword.setVisibility(View.VISIBLE);
        llKeyword.removeAllViews();
        ArrayList<Ingredients> keywordsList = keywords;

        int width = 0;
        LinearLayout parent = null;
        for (int i = 0; i < keywordsList.size(); i++) {

            if (width == 0) {
                parent = new LinearLayout(context);
                parent.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                parent.setOrientation(LinearLayout.HORIZONTAL);
                parent.setPadding(15, 0, 15, 0);
                measureView(parent);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            params.setMargins(0, 5, 15, 5);

            View someLayoutView = LayoutInflater.from(context).inflate(
                    R.layout.view_textview, null);
            final TextView tvKeyword = (TextView) someLayoutView.findViewById(R.id.tv_ingredients);
            tvKeyword.setText(keywordsList.get(i).getIngredients() + ":- " + keywordsList.get(i).getQuantity());
            tvKeyword.setLayoutParams(params);

            measureView(tvKeyword);

            width += tvKeyword.getMeasuredWidth() + 5;

            Log.e("", "textview width " + tvKeyword.getMeasuredWidth()
                    + " width " + width);
            if (width < getWidth(context))
                parent.addView(tvKeyword);
            else {
                llKeyword.addView(parent);
                width = 0;
                parent = new LinearLayout(context);
                parent.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                parent.setOrientation(LinearLayout.HORIZONTAL);
                measureView(parent);

                width += tvKeyword.getMeasuredWidth();
                parent.addView(tvKeyword);
            }

            if (i == keywordsList.size() - 1)
                llKeyword.addView(parent);
        }

    }

    protected static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Log.e("", "Width " + display.getWidth());
        return display.getWidth() - 80;
    }

    protected static void measureView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int childWidth = ViewGroup.getChildMeasureSpec(0, view.getPaddingLeft()
                + view.getPaddingRight(), params.width);
        int childHeight = ViewGroup.getChildMeasureSpec(0,
                view.getPaddingBottom() + view.getPaddingTop(), params.height);
        view.measure(childWidth, childHeight);
    }
}
