package com.ancientlore.lib.extendedtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

@SuppressWarnings("unused")
public class ExtendedTextView extends AppCompatTextView
{
	@ColorInt
	private int drawableTint = Color.TRANSPARENT;

	public ExtendedTextView(Context context)
	{
		super(context);
		init(context, null);
	}

	public ExtendedTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	public ExtendedTextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		if (attrs != null)
		{
			TypedArray styles = context.obtainStyledAttributes(attrs, R.styleable.ExtendedTextView);

			try {
				drawableTint = styles.getColor(R.styleable.ExtendedTextView_drawableTint, Color.TRANSPARENT);
			} finally {
				styles.recycle();
			}
		}

		updateDrawablesTint();
	}

	@Override
	public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		setImages(left, top, right, bottom);
	}

	@Override
	public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		Utils.setIntrinsicBounds(left);
		Utils.setIntrinsicBounds(top);
		Utils.setIntrinsicBounds(right);
		Utils.setIntrinsicBounds(bottom);
		setImages(left, top, right, bottom);
	}

	public void setImages(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		super.setCompoundDrawables(left, top, right, bottom);
		updateDrawablesTint();
	}

	public void setLeftImage(@Nullable Drawable drawable)
	{
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
	}

	public void setTopImage(@Nullable Drawable drawable)
	{
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
	}

	public void setRightImage(@Nullable Drawable drawable)
	{
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
	}

	public void setBottomImage(@Nullable Drawable drawable)
	{
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
	}

	public void resetImages()
	{
		setCompoundDrawables(null, null, null, null);
	}

	public void setDrawableTint(@ColorInt int color)
	{
		drawableTint = color;
		updateDrawablesTint();
	}

	private void updateDrawablesTint()
	{
		Drawable[] drawables = getCompoundDrawables();
		Utils.setTint(drawables[0], drawableTint);
		Utils.setTint(drawables[1], drawableTint);
		Utils.setTint(drawables[2], drawableTint);
		Utils.setTint(drawables[3], drawableTint);
	}
}