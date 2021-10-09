package com.app.foodycookbook.feature.meal;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.R;
import com.app.foodycookbook.database.DatabaseClient;
import com.app.foodycookbook.listeners.ItemClickListener;
import com.app.foodycookbook.utills.Util;
import com.app.foodycookbook.utills.Validation;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {
    private ItemClickListener mItemClickListener;
    Context mContext;
    ArrayList<Meals.Meal> mealsList;

    public MealsAdapter(Context mContext, ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        this.mContext = mContext;
        mealsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, final int position) {
        if (mealsList.get(position).getStrMeal() != null) {
            holder.mTvMeals.setText(mealsList.get(position).getStrMeal());
        }
        if (mealsList.get(position).getStrCategory() != null) {
            holder.mTvCategory.setText("Category:- " + mealsList.get(position).getStrCategory());
        }
        if (mealsList.get(position).getStrArea() != null) {
            holder.mTvArea.setText("Area:- " + mealsList.get(position).getStrArea());
        }
        if (mealsList.get(position).getStrMealThumb() != null) {
            Glide.with(mContext).load(mealsList.get(position).getStrMealThumb()).into(holder.mIvMeal);
        }
        if (mealsList.get(position).isFav()) {
            holder.mIvFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else holder.mIvFav.setImageResource(R.drawable.ic_un_favorite_24);

    }

    @Override
    public int getItemCount() {
        return mealsList != null ? mealsList.size() : 0;
    }

    public void updateList(ArrayList<Meals.Meal> list) {
        mealsList = list;
        notifyDataSetChanged();
    }


    public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView mCvOrdersItem;
        TextView mTvMeals, mTvCategory, mTvArea;
        ImageView mIvMeal, mIvFav;

        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mCvOrdersItem = itemView.findViewById(R.id.cv_meal_item);
            mTvMeals = itemView.findViewById(R.id.tv_meals);
            mTvCategory = itemView.findViewById(R.id.tv_category);
            mTvArea = itemView.findViewById(R.id.tv_area);
            mIvFav = itemView.findViewById(R.id.iv_fav);
            mIvMeal = itemView.findViewById(R.id.iv_meal);
            mCvOrdersItem.setOnClickListener(this);
            mIvFav.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cv_meal_item:
                    mItemClickListener.OnItemClick(v, getLayoutPosition());
                    break;
                case R.id.iv_fav:
                    mItemClickListener.OnItemClick(v, getLayoutPosition());
                    break;

            }
        }


    }


}
