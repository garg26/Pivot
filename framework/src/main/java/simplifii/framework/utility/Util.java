package simplifii.framework.utility;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.provider.UserDictionary;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import simplifii.framework.activity.BaseActivity;

public class Util {

    public static String CHECK_IN_TIME_API_FORMAT = "HH:mm";
    public static String CHECK_IN_TIME_UI_FORMAT = "hh:mm a";
    public static final String REQUEST_DASHBOARD_DELIVERY_TIME_UI_FORMAT = "hh:mm a, dd MMM";
    public static final String LATE_CHECKOUT_DATE_FORMAT = "dd MMM yyyy";
    public static final String API_DATE_FORMAT = "dd-MM-yyyy";


    public static float randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static final String JAVA_DATE_PATTERN = "E MMM dd HH:mm:ss Z yyyy";
    public static final String REQUIRE_DATE_PATTERN = "MMM dd, HH:mm";
    public static final String PARSE_CREATED_AT_DATE_PATTERN = "MMM dd, yyyy, HH:mm";
    public static final String DISCVER_SERVER_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DISCVER_UI_ORDER_STATUS_DATE_PATTERN = "HH:mm a,\ndd MMM";
    public static final String DISCVER_DELIVERY_TIME_DATE_PATTERN = "HH:mm a, dd MMM";
    public static final String DELIVERY_TIME_DATE_PATTERN = "dd-MM-yyyy HH:mm";
    public static final String CHECKOUT_DATE_TIME = "dd-MM-yyyy HH:mm";

    public static final Pattern VERY_STRONG_PASSWORD =
            Pattern.compile(
                    "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
            );

    public static final Pattern STRONG_PASSWORD =
            Pattern.compile(
                    "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})"
            );

    public static String getParseRangeQuery(String startDate, String endDate) {
        return String
                .format("where={'createdAt':{'$gte':{'__type':'DateFragment','iso':'%s'},'$lte':{'__type':'DateFragment','iso':'%s'}}}",
                        startDate, endDate);
    }

    public static Bitmap getBitmapFromUri(Context ctx, Uri imageUri) {
        try {
            return MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static byte[] getBytesFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static String setDate(int year, int month, int dayOfMonth) {
        String m,d;
        if(month<10){
            m="0"+month;
        }else {
            m= String.valueOf(month);
        }
        if(dayOfMonth<10){
            d="0"+dayOfMonth;
        }else {
            d= String.valueOf(dayOfMonth);
        }
        return year+"-"+m+"-"+d;
    }

    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static void addToLocalDictionary(Context context,String fullWord,String hint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // On JellyBean & above, you can provide a shortcut and an explicit Locale
            UserDictionary.Words.addWord(context, fullWord, 100, hint, Locale.getDefault());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            UserDictionary.Words.addWord(context, fullWord, 100, UserDictionary.Words.LOCALE_TYPE_CURRENT);
        }
    }


    public interface DialogListener {
        public void onOKPressed(DialogInterface dialog, int which);

        public void onCancelPressed(DialogInterface dialog, int which);
    }

    public static int getQuantityFromEditText(EditText etQty) {
        int qty = 0;
        try {
            qty = Integer.parseInt(etQty.getText().toString());

        } catch (Exception e) {

        }
        return qty;
    }

    public static AlertDialog createAlertDialog(Context context,
                                                String message, String title, boolean isCancelable, String okText,
                                                String cancelText, final DialogListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(isCancelable);
        builder.setPositiveButton(okText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        listener.onOKPressed(dialog, which);
                    }
                });
        builder.setNegativeButton(cancelText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        listener.onCancelPressed(dialog, which);
                    }
                });

        return builder.create();
    }

    public static boolean isConnectingToInternet(Context ctx) {

        boolean NetConnected = false;
        try {
            ConnectivityManager connectivity = (ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                Logger.info("tag", "couldn't get connectivity manager");
                NetConnected = false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            NetConnected = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            Logger.error("Connectivity Exception",
                    "Exception AT isInternetConnection");
            NetConnected = false;
        }
        return NetConnected;

    }

    public static boolean ifGreaterThnCheckOut(long selectedTime, long checkoutTime){
        if (selectedTime < checkoutTime){
            return true;
        }
        return false;
    }

    public static String getStringFromInputStream(InputStream is) {
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader buReader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 50000);

            String line;

            while ((line = buReader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response.toString();

    }

    public static void startItemActivity(Context ctx, Class activityClass) {
        Intent i = new Intent(ctx, activityClass);
        ctx.startActivity(i);
    }

    public static String getStringFromHTMLContent(String s) {
        String str = s.replaceAll("<br />", "<br /><br />").replaceAll(
                "&nbsp;", "<br /><br />");
        Log.e("String After", str);
        return str;
    }

    public static String convertDateFormat(String currentDate,
                                           String reqDateFormat) {
        SimpleDateFormat currentDateFormat = new SimpleDateFormat(
                JAVA_DATE_PATTERN);
        SimpleDateFormat format = new SimpleDateFormat(reqDateFormat);
        try {
            Date d = currentDateFormat.parse(currentDate);
            return format.format(d);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String convertDateFormat(String currentDate,
                                           String currentDateFormatString, String reqDateFormat) {
        SimpleDateFormat currentDateFormat = new SimpleDateFormat(
                currentDateFormatString);
        SimpleDateFormat format = new SimpleDateFormat(reqDateFormat);
        try {
            Date d = currentDateFormat.parse(currentDate);
            return format.format(d).toUpperCase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentDate.toUpperCase();
    }

    public static long convertStringToLong(String dateFormat, String dateValue){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try{
            Date date = formatter.parse(dateValue);
            return date.getTime();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static Object getColumnObject(Cursor c, String columnName) {
        int colIndex = c.getColumnIndex(columnName);
        switch (c.getType(colIndex)) {

            case Cursor.FIELD_TYPE_BLOB:
                return c.getBlob(colIndex);
            case Cursor.FIELD_TYPE_STRING:
                return c.getString(colIndex);
            case Cursor.FIELD_TYPE_FLOAT:
                return c.getFloat(colIndex);
            case Cursor.FIELD_TYPE_INTEGER:
                return c.getInt(colIndex);
            case Cursor.FIELD_TYPE_NULL:
                return null;
        }
        return null;

    }

    public static String getCombinedString(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    public static String DBL_FMT = "%.2f";

    public static Date convertStringToDate(String dateString, String dateFormat)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.parse(dateString);
    }

    public static Date convertDateFormat(Date date, String reqDateFormat,
                                         String currentDateFormat) {
        String formattedDateString = convertDateFormat(date.toString(),
                currentDateFormat, reqDateFormat);
        SimpleDateFormat format = new SimpleDateFormat(reqDateFormat);
        try {
            return format.parse(formattedDateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date).toUpperCase();
    }

    public static File getFile(Bitmap bMap, String folderName) throws Exception {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/" + folderName;
        File dir = new File(file_path);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir, System.currentTimeMillis() + ".png");
        FileOutputStream fOut = new FileOutputStream(file);

        bMap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
        return file;
    }

    public static void callUs(Context context, String customerCareNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + customerCareNumber));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static SpannableString getSppnnableString(String wholeText,
                                                     String spannedText, int colorId) {
        SpannableString spanString = new SpannableString(wholeText);
        try {
            int index = wholeText.indexOf(spannedText);
            if (index == -1) {
                return spanString;
            }
            spanString.setSpan(new ForegroundColorSpan(colorId), index, index
                    + spannedText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {

        }
        return spanString;
    }

    public static Bitmap getResizeBitmap(Bitmap bitmap, int maxSize) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            float bitmapRatio = (float) width / (float) height;
            if (bitmapRatio > 0) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            return Bitmap.createScaledBitmap(bitmap, width, height, true);
        } catch (Exception e) {

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getAndroidId(Context ctx) {
        String identifier = null;
        TelephonyManager tm = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null)
            identifier = tm.getDeviceId();
        if (identifier == null || identifier.length() == 0)
            identifier = Secure.getString(ctx.getContentResolver(),
                    Secure.ANDROID_ID);
        return identifier;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String number){
        Pattern pattern = Patterns.PHONE;
        return pattern.matcher(number).matches();
    }

    public static boolean isStrongPassword(String password){
        Pattern pattern = STRONG_PASSWORD;
        return pattern.matcher(password).matches();
    }

    public static void setBackground(View view, String color) {
        if (TextUtils.isEmpty(color)) {
            setBackground(view, Color.RED);
        } else {
            setBackground(view, Color.parseColor(color));
        }
    }

    public static void setBackground(View view, int color) {
//        Log.d(TAG, "H:" + view.getHeight() + ", W:" + view.getWidth());
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(color);
//        view.setBackground(shape);
    }

    public static String getFirstCharacter(String title) {
        if (TextUtils.isEmpty(title)) return "";
        return title.trim().charAt(0) + "";
    }

    public static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "SocialEvening");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".png");

        return mediaFile;
    }

    public static void performCrop(Activity ctx, Uri picUri, int reqCode) {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/png");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate <span id="IL_AD11" class="IL_AD">output</span> X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // <span id="IL_AD5" class="IL_AD">retrieve data</span> on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            ctx.startActivityForResult(cropIntent, reqCode);
        }
        // respond to users whose devices do <span id="IL_AD4" class="IL_AD">not support</span> the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(ctx, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

//            try {
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return null;
//            }

            bundle.putString("idFacebook", id);
            String firstName = "", lastName = "";
            if (object.has("first_name")) {
                Preferences.saveData("first_name", object.getString("first_name"));
                bundle.putString("name", object.getString("first_name"));
                firstName = object.getString("first_name");
            }
            if (object.has("last_name")) {
                bundle.putString("last_name", object.getString("last_name"));
                Preferences.saveData("last_name", object.getString("last_name"));
                lastName = object.getString("last_name");
            }

            Preferences.saveData("name", firstName + " " + lastName);

            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));
                Preferences.saveData("email", object.getString("email"));
            }
            if (object.has("gender")) {
                bundle.putString("gender", object.getString("gender"));
                String gender = object.getString("gender");
                if ("male".equalsIgnoreCase(gender)) {
                    Preferences.saveData("gender", "M");
                } else if ("female".equalsIgnoreCase("gender")) {
                    Preferences.saveData("gender", "F");
                }
            }
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (Exception e) {

        }
        return null;
    }


    public static void setSpannableColor(TextView view, String fulltext, String subtext, int color) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        if (i < 0) {
            return;
        }
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        view.setText(str);
    }


    public static String getAppendedString(String s1, String sep, String s2) {
        if (TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2)) {
            return "";
        } else if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
            return s1 + sep + s2;
        } else if (TextUtils.isEmpty(s1)) {
            return s2;
        } else {
            return s1;
        }
    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(new Date(milliSeconds)).toUpperCase();
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String getFirstNameAndLastName(String displayName, boolean isFirstName){
        int length = displayName.trim().length();
        int mid;
        String firstName, lastName;

        if (displayName.contains(" ")) {
            mid = displayName.indexOf(" ");
            firstName = displayName.substring(0, mid - 1);
            lastName = displayName.substring(mid + 1, length - 1);
        } else if (length == 1) {
            firstName = displayName;
            lastName = displayName;
        } else {
            mid = length / 2;
            firstName = displayName.substring(0, mid - 1);
            lastName = displayName.substring(mid, length - 1);
        }

        if(isFirstName){
            return firstName;
        } else{
            return lastName;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static<T> String formatDecimal(T value, String format){
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setToolbarFont(Context context, Toolbar toolbar){
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }
    public static void changeTabsFont(TabLayout tabLayout) {
        Typeface font = Typeface.createFromAsset(tabLayout.getContext().getAssets(), "fonts/"+ AppConstants.DEF_REGULAR_FONT);
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(font);
                    ((TextView) tabViewChild).setTextSize(15);
                }
            }
        }
    }



}

