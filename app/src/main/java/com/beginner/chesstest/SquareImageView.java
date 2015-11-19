package com.beginner.chesstest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Subclass of ImageView that makes ImageView a square based on width.
 */
public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Overrides the on measure function and sets the height to the value of the width if they are not already the same.
     * @param widthMeasureSpec Width of View
     * @param heightMeasureSpec Height of View
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width <= height) {
            setMeasuredDimension(width, width);
        } else {
            setMeasuredDimension(height, height);
        }
    }

}
