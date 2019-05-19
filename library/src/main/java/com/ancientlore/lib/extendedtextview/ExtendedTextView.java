package com.ancientlore.lib.extendedtextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

@SuppressWarnings("unused")
public class ExtendedTextView extends AppCompatTextView
{
	public ExtendedTextView(Context context)
	{
		super(context);
	}

	public ExtendedTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public ExtendedTextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
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
	}

	public void setLeftImage(@Nullable Drawable drawable)
	{
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
	}

	public void setTopImage(@Nullable Drawable drawable)
	{
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
	}

	public void setRightImage(@Nullable Drawable drawable)
	{
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
	}

	public void setBottomImage(@Nullable Drawable drawable)
	{
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
	}

	public void resetImages()
	{
		setCompoundDrawables(null, null, null, null);
	}
}