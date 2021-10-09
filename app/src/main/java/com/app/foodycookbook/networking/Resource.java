package com.app.foodycookbook.networking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/*A generic class that contains data and status about loading this data.*/

public class Resource<T> {
    @NonNull
    public final Status mStatus;
    @Nullable
    public final T mData;

    @Nullable
    public final String mMessage;
    @Nullable
    public final Throwable mError;


    public Resource(@NonNull Status status, @Nullable T data,
                    @Nullable String message, @Nullable Throwable error) {
        mStatus = status;
        mData = data;
        mMessage = message;
        mError = error;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null, null);
    }


    public static <T> Resource<T> errorData(Throwable error, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, null,error);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null, null);
    }

    public static <T> Resource<T> validation(@Nullable T data) {
        return new Resource<>(Status.VALIDATION, data, null, null);
    }

    public enum Status {SUCCESS, ERROR, LOADING, VALIDATION}




}
