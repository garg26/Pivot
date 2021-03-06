package simplifii.framework.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import me.grantland.widget.AutofitTextView;
import simplifii.framework.R;
import simplifii.framework.utility.AppConstants;


public class CustomFontAutofitTextView extends AutofitTextView {

    private static final String TAG = "CustomFont";

    public CustomFontAutofitTextView(Context context) {
        super(context);
    }

    public CustomFontAutofitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomFontAutofitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.CustomFontTxtView);
        String customFont = a
                .getString(R.styleable.CustomFontTxtView_customFont);

        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            if (asset == null || "".equals(asset)) {
                asset = "fonts/"+ AppConstants.DEF_REGULAR_FONT;
            }
            tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/"+asset);
        } catch (Exception e) {
            Log.e(TAG, "Error to get typeface: " + e.getMessage());
            return false;
        }
        setTypeface(tf);
        return true;
    }

}