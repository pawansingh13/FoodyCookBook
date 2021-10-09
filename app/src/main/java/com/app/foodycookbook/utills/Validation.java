package com.app.foodycookbook.utills;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


/**
 * Created by Pawan.Singh on 6/14/2017.
 */

public class Validation {
    public String mPath;
    static SimpleDateFormat simpleDateFormat;
    boolean msg = false;
    static String pattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+[a-zA-Z]+";
    private static String patternDate = "([0]*[1-9]|[12][0-9]|3[01])[- /.]([0]*[1-9]|1[012])[- /.](19|20)([0-9][0-9])";
    public static File mScreenShort;
    public Bitmap mBitmap;
    private String filepath;


    public static boolean isValidEamil(String email) {
        if (email.matches(Patterns.EMAIL_ADDRESS.toString()))
            return true;
        else
            return false;
    }


    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8 && stringContainsNumber(pass))
            return true;
        else
            return false;
    }

    /**
     * @param s
     * @return
     */
    public static boolean stringContainsNumber(String s) {
        return checkString(s);
    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;

        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            if (numberFlag && capitalFlag)
                return true;
        }
        return false;
    }

    public static boolean isValidCorfirmPassword(String pass, String confirmPass) {
        if (pass.equals(confirmPass))
            return true;
        else
            return false;
    }

    public static boolean isValidServiceArea(String servicearea) {
        if (servicearea != null && servicearea.length() <= 20)
            return true;
        else
            return false;
    }

    public static boolean isValidFirstName(String name) {
        if (name != null && name.length() <= 25)
            return true;
        else
            return false;
    }

    public static boolean isValidLastName(String name) {
        if (name != null && name.length() <= 25)
            return true;
        else
            return false;
    }

    public static boolean isValidUsername(String username) {
        if (username != null && username.length() <= 20)
            return true;
        else
            return false;
    }

    public static boolean isValidDOB(String dob) {
        if (dob != null && dob.matches(patternDate))
            return true;
        else
            return false;
    }

    public static boolean isValidContactNumber(String number) {
        if (number != null && number.length() == 10)
            return true;
        else
            return false;
    }

    public static boolean isValidBusinessName(String businessName) {
        if (businessName != null && businessName.length() <= 50)
            return true;
        else
            return false;
    }

    public static boolean isValidTradingName(String tradingName) {
        if (tradingName != null && tradingName.length() <= 50)
            return true;
        else
            return false;
    }

    public static boolean isValidBusinessRegistraion(String businessRegistraion) {
        if (businessRegistraion != null && businessRegistraion.length() <= 20)
            return true;
        else
            return false;
    }

    public static boolean isValidMessage(String message) {
        if (message != null && message.length() <= 4000) {
            return true;
        }
        return false;
    }

    public static boolean isValidMobileNumber(String mobilenumber) {
        if (mobilenumber != null && mobilenumber.length() >= 8 && mobilenumber.length() <= 13) {
            return true;
        }
        return false;
    }

    public static boolean isOtpValidate(String mOtp) {
        if (mOtp != null && mOtp.length() == 4) {
            return true;
        }
        return false;
    }


    public static long secondToMilisecondsConverstion(long second) {
        if (second > 0) {
            return TimeUnit.SECONDS
                    .toMillis(second);
        }
        return 0;
    }


    public static long minutesToMilisecondsConverstion(long minutes) {
        if (minutes > 0) {
            return TimeUnit.MINUTES
                    .toMillis(minutes);
        }
        return 0;
    }


    public static long convert(String time) {
        if (time != null) {
            String[] Time = time.split(":");
            int min = Integer.valueOf(Time[0]);
            int sec = Integer.valueOf(Time[1]);
            return (((min * 60) + sec) * 1000);
        }
        return 0;
    }


    public static String stringDateToChangeTheFormate(String date, String inputDateFormate, String outPutDateFormate) {
        DateFormat input = new SimpleDateFormat(inputDateFormate);
        DateFormat output = new SimpleDateFormat(outPutDateFormate);
        try {
            return output.format(input.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); // the format of your date
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }


    public static String covertTimeToText(long time) {
        String convTime = null;
        String prefix = "";
        String suffix = "Ago";
        String daysSuffix = " Days ";
        Date pastTime = new Date(time);
        Date nowTime = new Date();
        long dateDiff = nowTime.getTime() - pastTime.getTime();
        long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
        long day = TimeUnit.MILLISECONDS.toDays(dateDiff);
        if (day <= 30) {
            if (day == 1) {
                convTime = "Today";
            } else if (day == 2) {
                convTime = "Yesterday";
            } else {
                convTime = day - 1 + daysSuffix + suffix;
            }
        } else {
            if (day > 360) {
                if ((day / 360) == 1) {
                    convTime = (day / 360) + " Year " + suffix;
                } else {
                    convTime = (day / 360) + " Years " + suffix;
                }
            } else if (day > 30) {
                if ((day / 30) == 1) {
                    convTime = (day / 30) + " Month " + suffix;
                } else {
                    convTime = (day / 30) + " Months " + suffix;
                }
            } else {
                if ((day / 7) == 1) {
                    convTime = (day / 7) + " Week " + suffix;
                } else {
                    convTime = (day / 7) + " Weeks " + suffix;
                }

            }

        }

        return convTime;
    }

    public static Date parseStringDateToDate(String date, String formate) {
        Date mDate = null;
        SimpleDateFormat format = new SimpleDateFormat(formate, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            mDate = format.parse(date);

        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
        return mDate;
    }

    public static String getCurrentDate(String dateFormat) {
        Date date = Calendar.getInstance().getTime();
        simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }


    public static long calculateDayBetweenToDates(long createdDate, long currentDate) {
        long diff = currentDate - createdDate;
        //    long diff1 = currentDate - createdDate;
//        Log.d("days", "createdDate " + new Date(createdDate));
//        Log.d("days", "currentDate " + new Date(createdDate));
//        Log.d("days", "==diff1==" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) +
//                "==diff1==" + TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS));

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static boolean isCalculateSameDay(Date currentDate, Date endDate) {
        long diff = currentDate.getTime() - endDate.getTime();
        long mDay = diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        String time = "";
        if (mDay == 0) {
            return true;

        }
        return false;
    }

    /**
     * here we are convert byte array to bitmap.
     *
     * @param buteArray
     * @return
     */
    public Bitmap ByteAarryToBitmap(byte[] buteArray) {
        return BitmapFactory.decodeByteArray(buteArray, 0, buteArray.length);
    }


    /**
     * here we are convert byte array to bitmap.
     *
     * @return
     */
    public Bitmap imageUriToBitmap() {
        byte[] img = Base64.decode(mPath, Base64.DEFAULT);
        Log.d("CheckBitmap", "mPath" + mPath);
        Bitmap mBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        Log.d("CheckBitmap", "imageUriToBitmap" + mBitmap);
        return mBitmap;
    }

    public byte[] getBytes() throws IOException {
        Log.d("CheckBitmap", "getBytes " + mPath);
        InputStream iStream = FoodyCookBookApplication.getApp().getContentResolver().openInputStream(Uri.parse(mPath));
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = iStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    /**
     * Here is the key method to apply the animation
     */
    public static void setAnimation(View viewToAnimate, int position, int lastPosition, Context mContext) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public static void overridePendingTransition(Context mContext) {
        ((Activity) mContext).overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


}
