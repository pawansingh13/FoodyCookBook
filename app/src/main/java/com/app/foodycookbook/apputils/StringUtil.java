package com.app.foodycookbook.apputils;

import android.text.TextUtils;

/*Simple utility class for string data. Write your string manipulation code here...*/
public class StringUtil {
    /**
     *  Func returns the string or empty string but not the null value.
     * */
    public static String returnValidString(String str) {

        return !TextUtils.isEmpty(str) ?
                str :
                "";
    }


    /**
     *  Func returns the string of proper Name
     * */
    public static String getFullName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName)&&!TextUtils.isEmpty(lastName)) {
            return firstName+" "+lastName;
        } else if(TextUtils.isEmpty(firstName)&&!TextUtils.isEmpty(lastName)){
            return lastName;
        }else if (!TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)){
            return firstName;
        }else {
            return "";
        }
    }
}
