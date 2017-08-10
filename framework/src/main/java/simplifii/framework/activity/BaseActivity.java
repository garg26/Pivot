package simplifii.framework.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import simplifii.framework.R;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.fragments.TaskFragment;
import simplifii.framework.receivers.CartUpdateReceiver;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Util;
import simplifii.framework.widgets.CustomFontTextView;
import simplifii.framework.widgets.CustomRatingBar;


public class BaseActivity extends AppCompatActivity implements
        OnClickListener, TaskFragment.AsyncTaskListener, CartUpdateReceiver.CartAmountListener {

    protected ActionBar bar;
    protected View actionBarView;
    ProgressDialog dialog;
    protected boolean isCartIconNeeded = false;
    private TaskFragment taskFragment;
    protected Toolbar toolbar;

    protected final String TAG = getTag();

    public static boolean isInternetDialogVisible = false;
    private CartUpdateReceiver rec;
    private Dialog dialogInternet;

    public String getActionTitle() {
        return getResources().getString(R.string.app_name);
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            loadBundle(bundle);
        }
        taskFragment = new TaskFragment();
        getSupportFragmentManager().beginTransaction().add(taskFragment, "task").commit();
    }

    protected void loadBundle(Bundle bundle) {

    }

    public void registerReceiver(String... actions) {
        if (rec == null) {
            rec = new CartUpdateReceiver(this);
        }
        IntentFilter filter = new IntentFilter();
        for (String action : actions) {
            filter.addAction(action);
        }
        registerReceiver(rec, filter);
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
    }

    protected void initWindow() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.white);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void onActionItemClicked(View v) {
        Log.i(TAG, "onActionItemClicked");
        switch (v.getId()) {

            default:
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // do nothing
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setFullScreenWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams attrs = this.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        this.getWindow().setAttributes(attrs);
    }


    public void onBackIconClicked(View v) {
        super.onBackPressed();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int stringId) {
        showToast(getString(stringId));
    }

    protected void setRadioStatus(boolean b, int radioId) {
        ((RadioButton) findViewById(radioId)).setChecked(b);
    }

    protected boolean getRadioStatus(int radioId) {
        return ((RadioButton) findViewById(radioId)).isChecked();
    }


    public AsyncTask executeTask(int taskCode, Object... params) {
        if (Util.isConnectingToInternet(this)) {
            taskFragment.createAsyncTaskManagerObject(taskCode)
                    .executeOnExecutor(TaskFragment.AsyncManager.THREAD_POOL_EXECUTOR, params);
        } else {
            Log.i("Base Activity", "Not Connected to internet");
            onInternetException();
            showInternetDialog(taskCode, params);
        }
        return null;
    }


    protected void onInternetException() {
//        findViewById(R.id.frame_noInternet).setVisibility(View.VISIBLE);
    }

    public boolean isNetworkAvailable() {
        if (Util.isConnectingToInternet(this)) {
            return true;
        } else {
            Log.i("Base Activity", "Not Connected to internet");
            if (isInternetDialogVisible) {
                Util.createAlertDialog(this, "Please Connect to Internet",
                        "Not Connected to internet", false, "OK", "Cancel",
                        internetDialogListener).show();
                isInternetDialogVisible = true;
            }
            return false;
        }
    }

    public static Util.DialogListener internetDialogListener = new Util.DialogListener() {

        @Override
        public void onOKPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }

        @Override
        public void onCancelPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }
    };


    public void hideProgressBar() {
        Log.i(TAG + "Dialog", Thread.currentThread().getName());
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showProgressDialog() {
        showProgressDialog("Loading...");
    }

    public void showProgressDialog(String message) {
        if (dialog != null && dialog.isShowing()) {
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage(message);
            dialog.show();
            dialog.setCancelable(false);
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    protected void setCustomRating(int customRatingBarId, Integer hotelType) {
        if (hotelType != null) {
            CustomRatingBar customRatingBar = (CustomRatingBar) findViewById(customRatingBarId);
            customRatingBar.setRating(hotelType);
        }
    }


    protected void setImage(String imageUrl, int imageViewId, int placeHolderId) {
        ImageView imageView = (ImageView) findViewById(imageViewId);
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(this).load(imageUrl).placeholder(placeHolderId).into(imageView);
        } else {
            imageView.setImageResource(placeHolderId);
        }
    }

    protected void setImage(String imageUrl, int imageViewId, int placeHolderId, View convertView) {
        ImageView imageView = (ImageView) convertView.findViewById(imageViewId);
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(this).load(imageUrl).placeholder(placeHolderId).into(imageView);
        } else {
            imageView.setImageResource(placeHolderId);
        }
    }


    public void setText(String text, int textViewId) {
        TextView view = (TextView) findViewById(textViewId);
        view.setText(text);
    }

    protected String getTextView(int textViewId) {
        return ((TextView) findViewById(textViewId)).getText().toString().trim();
    }


    public void setText(String text, int textViewId, View v) {
        TextView view = (TextView) v.findViewById(textViewId);
        view.setText(text);
    }


    private int cartCount = 0;


    public void startNextActivity(Class<? extends Activity> activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    public void startNextActivity(Bundle bundle,
                                  Class<? extends Activity> activityClass) {

        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivity(i);
    }

    public void startNextActivityForResult(Bundle bundle,
                                           Class<? extends Activity> activityClass, int reqCode) {

        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivityForResult(i, reqCode);
    }


    protected void setOnClickListener(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setOnClickListener(this);
        }
    }

    protected String getEditText(int editTextId) {
        return ((EditText) findViewById(editTextId)).getText().toString()
                .trim();
    }

    @Override
    public void onPreExecute(int taskCode) {
        showProgressDialog();
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        hideProgressBar();
    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        hideProgressBar();
    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getHomeIcon());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setStatusBarColor(getResourceColor(R.color.color_primary_dark));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.color_title));
        Util.setToolbarFont(this, toolbar);

    }

    protected int getHomeIcon() {
        return 0;
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public void onRetryClicked(View view) {
        if (Util.isConnectingToInternet(this)) {
            findViewById(R.id.frame_noInternet).setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomePressed();
                return true;
        }
        return false;
    }

    protected void onHomePressed() {
        onBackPressed();
    }

    protected void onServerError() {
//        FrameLayout errorLayout = (FrameLayout) findViewById(R.id.frame_noInternet);
//        if (errorLayout != null) {
//            errorLayout.setVisibility(View.VISIBLE);
//            ImageView errorImage = (ImageView) errorLayout.findViewById(R.id.iv_error);
//            TextView errorMsg = (TextView) errorLayout.findViewById(R.id.tv_errorMsg);
//            TextView errorInfo = (TextView) errorLayout.findViewById(R.id.tv_errorInfo);
//
//            errorImage.setImageResource(R.drawable.icon_server_error);
//            errorMsg.setText("SERVER ERROR");
//            errorInfo.setText("Oops! Something went wrong...");
//        }
    }

    protected void hideVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.GONE);
        }
    }

    protected void showVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.VISIBLE);
        }
    }

    protected void showVisibility(View convertView, int... viewIds) {
        for (int i : viewIds) {
            convertView.findViewById(i).setVisibility(View.VISIBLE);
        }
    }

    protected int getResourceColor(int colorId) {
        return ContextCompat.getColor(this, colorId);
    }

    protected void changeFontOfTextView(int id, String font) {
        ((CustomFontTextView) findViewById(id)).setCustomFont(this, font);
    }

    protected void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void setError(int editTextId, String error) {
        EditText editText = (EditText) findViewById(editTextId);
        editText.setError(error);
    }

    private ArrayList<Integer> editTextList;
    private ArrayList<String> validationTypeList;
    private ArrayList<String> errorMessegeList;
    private ArrayList<String> fieldNames;

    protected void initialiseValidation() {
        editTextList = new ArrayList();
        validationTypeList = new ArrayList();
        errorMessegeList = new ArrayList();
        fieldNames = new ArrayList();
    }

    protected void setValidation(int editTextId, String validationType, String errorMessege) {
        editTextList.add(editTextId);
        validationTypeList.add(validationType);
        errorMessegeList.add(errorMessege);
        fieldNames.add("");
    }

    protected void setEmptyValidation(int editTextId, String fieldName) {
        editTextList.add(editTextId);
        validationTypeList.add(AppConstants.VALIDATIONS.EMPTY);
        errorMessegeList.add("");
        fieldNames.add(fieldName);
    }

    protected void setEmailValidation(int editTextId) {
        setEmptyValidation(editTextId, "Email");
        setValidation(editTextId, AppConstants.VALIDATIONS.EMAIL, "");
    }

    protected void setPhoneNumberValidation(int editTextId, String fieldName) {
        setEmptyValidation(editTextId, fieldName);
        setValidation(editTextId, AppConstants.VALIDATIONS.MOBILE, "");
    }

    protected boolean isValidate() {
        for (int x = 0; x < editTextList.size(); x++) {
            int id = editTextList.get(x);
            String validationType = validationTypeList.get(x);
            String errorMessage = errorMessegeList.get(x);

            EditText editText = (EditText) findViewById(id);

            switch (validationType) {
                case AppConstants.VALIDATIONS.EMPTY:
                    String text = editText.getText().toString();
                    if (TextUtils.isEmpty(text)) {
                        if ("".equals(errorMessage)) {
                            editText.setError(fieldNames.get(x) + " cannot be empty..!");
                        } else {
                            editText.setError(errorMessage);
                        }
                        return false;
                    }
                    break;
                case AppConstants.VALIDATIONS.EMAIL:
                    String email = editText.getText().toString();
                    if (!Util.isValidEmail(email)) {
                        if ("".equals(errorMessage))
                            editText.setError(getString(R.string.error_invalid_email));
                        else
                            editText.setError(errorMessage);
                        return false;
                    }
                    break;
                case AppConstants.VALIDATIONS.MOBILE:
                    String mobile = editText.getText().toString();
                    if (10 != mobile.length()) {
                        if ("".equals(errorMessage))
                            editText.setError(getString(R.string.error_invalid_mobile));
                        else
                            editText.setError(errorMessage);
                        return false;
                    }

                    break;
            }
        }
        return true;
    }

    @Override
    public void onReceive(Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    public void unregisterReceiver() {
        if (rec != null) {
            rec.removeListener(this);
            unregisterReceiver(rec);
        }
    }

    protected void showInternetDialog(final int taskCode, final Object[] params) {
        if (dialogInternet == null) {
            dialogInternet = new Dialog(this);
            dialogInternet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogInternet.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogInternet.setContentView(R.layout.dialog_interner_error);
        }
        if (!dialogInternet.isShowing()) {
            dialogInternet.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogInternet.dismiss();
                    executeTask(taskCode, params);
                }
            });
            dialogInternet.show();
        }
    }
}
