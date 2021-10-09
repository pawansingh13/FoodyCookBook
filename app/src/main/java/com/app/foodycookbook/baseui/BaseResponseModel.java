package com.app.foodycookbook.baseui;

/**
 * Created by Vinod Kumar on 27/5/19.
 */
public class BaseResponseModel<T> {
    public Integer statusCode;
    public String message;
    public String description;
    public T responseModel;
}
