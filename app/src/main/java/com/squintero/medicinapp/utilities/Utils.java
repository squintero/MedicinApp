package com.squintero.medicinapp.utilities;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.squintero.medicinapp.App;
import com.squintero.medicinapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public final class Utils {

    //Custom fonts
    private static HashMap<String, Typeface> cachedFonts = new HashMap<String, Typeface>();

    /********************/
    /** PUBLIC METHODS **/
    /********************/

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     * @return
     */
//    public static boolean isPlayServicesInstalled() {
//
//        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
//
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(App.getInstance().getApplicationContext());
//
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(
//                        (ActivityEdit) App.getInstance().getApplicationContext(),
//                        resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
//                        .show();
//
//            } else {
//                Log.i(TAG, "This device is not supported. Google Play services are not installed.");
////                activity.finish();
//            }
//            return false;
//        }
//        return true;
//    }
    public static boolean checkValidDateRange(String startDate, String endDate) {

        SimpleDateFormat curFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = curFormatter.parse(startDate);
            date2 = curFormatter.parse(endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // startDate <= endDate
        if (date1 != null && date2 != null && !date1.after(date2))
            return true;
        else
            return false;
    }

    public static boolean createAppDirectory() {

        File newDirectory = new File(Environment.getExternalStorageDirectory() + "/" + App.getInstance().getString(R.string.app_name));
        if (!newDirectory.exists()) {
            return newDirectory.mkdir();
        }
        return false;
    }

    public static boolean checkPhoneFormat(CharSequence target) {

        return target != null && !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches();
    }

    public static String getDateNow() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat curFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return curFormatter.format(currentTime);
    }

    public static String formatDateFromServer(String serverDateToFormat, boolean showTime) {

        SimpleDateFormat curFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat curFormatter  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat postFormatter; // = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateObj = null;
        String date = "";

        if (showTime)
            postFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        else
            postFormatter = new SimpleDateFormat("dd/MM/yyyy");

        if (serverDateToFormat == null || serverDateToFormat.equals(""))
            return date;

        try {
//            TimeZone tz = TimeZone.getDefault();
//            curFormatter.setTimeZone(tz);
            dateObj = curFormatter.parse(serverDateToFormat);

            if (dateObj != null)
                date = postFormatter.format(dateObj);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String formatDateToServer(String dateToFormat) {

        SimpleDateFormat curFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat postFormatter; // = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateObj = null;
        String date = "";

        postFormatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
//            TimeZone tz = TimeZone.getDefault();
//            curFormatter.setTimeZone(tz);
            dateObj = curFormatter.parse(dateToFormat);

            if (dateObj != null)
                date = postFormatter.format(dateObj);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }

        return result;
    }

    public static JSONObject getJSONObjectFromString(String json) {

        if (json == null || json.isEmpty()) {
            return new JSONObject();
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);

        } catch (JSONException e) {
            jsonObject = new JSONObject();
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static String generateRandomText(int length) {

        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(length);
    }

    public static String getAppVersion(Context context) {

        String versionCode = "0.1.0";
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getCurrentDateTime() {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrentTime() {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        return dateFormat.format(calendar.getTime());
    }

    public static String getDeviceLanguage() {

        String locale = Locale.getDefault().getLanguage();

        if (locale.length() > 2)
            locale = locale.substring(0, 2);
        Log.v("locale", locale);

        return locale;
    }

    public static String getDeviceId() {

        return Settings.Secure.getString(App.getInstance().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

//    public static String getDeviceImei(Context ctx) {
//
//        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
//        }
//        String imei = tm.getDeviceId();
//
//        return imei;
//    }

    public static Typeface getFont(Context ctx, String fontName) {

        Typeface outTypeFace = null;

        if (cachedFonts.containsKey(fontName)) {
            outTypeFace = cachedFonts.get(fontName);

        } else {
            outTypeFace = Typeface.createFromAsset(ctx.getAssets(), fontName);

            if (outTypeFace != null) {
                cachedFonts.put(fontName, outTypeFace);
            }
        }

        return outTypeFace;
    }

    public static String getPlatform(){
        return "ANDROID";
    }

    public static int getScreenDimension(Activity activity, int heightOrWidth){

        int size = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        if (heightOrWidth == 0) //Height
            size = displaymetrics.heightPixels;
        if (heightOrWidth == 1) //Width
            size = displaymetrics.widthPixels;

        return size;
    }

    public static float getPxFromInt(int value) {

        Resources r = App.getInstance().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
    }

    public static boolean hasInternet(Context ctx) {

        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();

        return i != null && i.isConnected() && i.isAvailable();

    }

    public static void hideKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no viewInit has focus:
        View view = activity.getCurrentFocus();
        if (view != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isMyServiceRunning(Activity activity, Class<?> serviceClass) {

        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void openKeyboardForView(View view){

        InputMethodManager imm = (InputMethodManager) App.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static String removeQuotationMarksAndSlashes(String text) {

        return text.replace("\"", "").replace("\\", "");   //Remove quotations marks and slashes.
    }

    /**
     * Round float to x decimal passed by parameter
     * @param value
     * @param numOfDecimal
     * @return
     */
    public static float roundFloatToXDecimal(float value, int numOfDecimal){

        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(numOfDecimal, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Function to convert String to double, round the value to one decimal and convert to string again.
     * @param valueStr
     * @return string with the value of the distance rounded to one decimal.
     */
    public static String roundStringToXDecimal(String valueStr, int numOfDecimal){

        if (valueStr == null || valueStr.equals(""))
            return "";

        double value = Double.parseDouble(valueStr);

        return String.format(Locale.US, "%." + numOfDecimal + "f", value);
    }

    public static String setToMD5(String s) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Logs.MessageLogs("setToMD5", "Error in the operation: " + e, "e");
        }
        return null;
    }

    public static void strikeThruTextView(TextView tv){
        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(App.getInstance().getColor(R.color.gray));
        }else{
            tv.setTextColor(App.getInstance().getResources().getColor(R.color.gray));
        }
    }

    public static Bitmap getBitmapFromPath(String path){
        return getBitmapFromPath(path, 2048);
    }

    public static Bitmap getBitmapFromPath(String path, int maxTargetSize){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        //Returns null, sizes are in the options variable
        BitmapFactory.decodeFile(path, options);
        int width = options.outWidth;
        int height = options.outHeight;
        int reduceFactor = 1;
        int maxImageSize = (width > height) ? width : height;

        while (maxImageSize > maxTargetSize){
            maxImageSize /= 2;
            reduceFactor *= 2;
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = reduceFactor;
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        //If you want, the MIME type will also be decoded (if possible)
        String type = options.outMimeType;

        //load from uri
        File auxFile = new File(path);

        Bitmap bm = null;

        try {
            bm = BitmapFactory.decodeStream(new FileInputStream(auxFile), null, options);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bm;
    }

    public static String getBase64StringForBitmap(Bitmap bmp){
        return getBase64StringForBitmapJPEGFormat(bmp);
    }

    public static String getBase64StringForBitmapJPEGFormat(Bitmap bmp){
        return getBase64StringForBitmap(bmp, true);
    }

    public static String getBase64StringForBitmapPNGFormat(Bitmap bmp){
        return getBase64StringForBitmap(bmp, false);
    }

    public static String getBase64StringForBitmap(Bitmap bmp, boolean JPEGFormat){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (JPEGFormat){
            //jpeg
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos);

        } else {
            //png
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        }

        byte[] imageBytes = baos.toByteArray();
        //Log.d("IMAGE SIZE", String.valueOf(imageBytes.length / 1024) + "KBytes");
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
