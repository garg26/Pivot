package simplifii.framework.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import simplifii.framework.R;
import simplifii.framework.utility.AppConstants;


public class CustomRatingBar extends LinearLayout {
    private int rating;

    private static final String TAG = "CustomRatingBar";
    private int startSize;

    public CustomRatingBar(Context context) {
        super(context);
    }

    public CustomRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomRatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.CustomRatingBar);
        rating = a
                .getInt(R.styleable.CustomRatingBar_rating, 0);
        startSize = a.getDimensionPixelSize(R.styleable.CustomRatingBar_starSize, 32);
        a.recycle();
        initView();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        initView();
    }

    private void initView() {
        removeAllViews();
        setWeightSum(rating);
        if (rating >5){
            rating = 5;
        }
        for (int x = 0; x < 5; x++) {
            ImageView imageView = new ImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(startSize, startSize);
            layoutParams.setMargins(1, 1, 1, 1);
            imageView.setLayoutParams(layoutParams);
            if(x<rating){
                imageView.setImageResource(R.drawable.rating_star);
            }else {
                imageView.setImageResource(R.drawable.star_unselect);
            }
            addView(imageView);
        }
    }
}