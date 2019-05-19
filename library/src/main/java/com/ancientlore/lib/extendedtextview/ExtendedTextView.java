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

	private int drawableSize = -1;

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
				drawableSize = styles.getDimensionPixelSize(R.styleable.ExtendedTextView_drawableSize, -1);
			} finally {
				styles.recycle();
			}
		}

		updateDrawablesSize();

		updateDrawablesTint();
	}

	@Override
	public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		super.setCompoundDrawables(left, top, right, bottom);
		updateDrawablesSize();
		updateDrawablesTint();
	}

	@Override
	public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		Utils.setIntrinsicBounds(left);
		Utils.setIntrinsicBounds(top);
		Utils.setIntrinsicBounds(right);
		Utils.setIntrinsicBounds(bottom);
		setCompoundDrawables(left, top, right, bottom);
	}

	public void setLeftDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
	}

	public void setTopDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
	}

	public void setRightDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
	}

	public void setBottomDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
	}

	public void resetDrawables()
	{
		super.setCompoundDrawables(null, null, null, null);
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

	private void updateDrawablesSize()
	{
		Drawable[] drawables = getCompoundDrawables();
		Utils.setSize(drawables[0], drawableSize);
		Utils.setSize(drawables[1], drawableSize);
		Utils.setSize(drawables[2], drawableSize);
		Utils.setSize(drawables[3], drawableSize);
	}
}