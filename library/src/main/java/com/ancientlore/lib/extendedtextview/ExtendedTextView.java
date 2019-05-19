package com.ancientlore.lib.extendedtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
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

	private int leftPadding;
	private int topPadding;
	private int rightPadding;
	private int bottomPadding;

	private boolean packedDrawables;

	private final Rect textBounds = new Rect();

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
				packedDrawables = styles.getBoolean(R.styleable.ExtendedTextView_packedDrawables, false);
			} finally {
				styles.recycle();
			}
		}

		updateDrawablesSize();

		updateDrawablesTint();

		leftPadding = getPaddingLeft();
		topPadding = getPaddingTop();
		rightPadding = getPaddingRight();
		bottomPadding = getPaddingBottom();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		updatePadding(w, h);
	}

	@Override
	public void setText(CharSequence text, BufferType type)
	{
		super.setText(text, type);
		updatePadding();
	}

	@Override
	public void setPadding(int left, int top, int right, int bottom)
	{
		leftPadding = left;
		topPadding = top;
		rightPadding = right;
		bottomPadding = bottom;
		updatePadding();
	}

	@Override
	public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom)
	{
		super.setCompoundDrawables(left, top, right, bottom);
		updateDrawablesSize();
		updateDrawablesTint();
		updatePadding();
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
		updatePadding();
	}

	public void setTopDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
		updatePadding();
	}

	public void setRightDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
		updatePadding();
	}

	public void setBottomDrawable(@Nullable Drawable drawable)
	{
		Utils.setSize(drawable, drawableSize);
		Utils.setTint(drawable, drawableTint);
		Drawable[] drawables = getCompoundDrawables();
		super.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
		updatePadding();
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

	private void updatePadding()
	{
		updatePadding(getMeasuredWidth(), getMeasuredHeight());
	}

	private void updatePadding(int width, int height)
	{
		if (!packedDrawables
				|| width == 0 && height == 0
				|| !hasDrawables())
			return;

		Drawable[] drawables = getCompoundDrawables();
		Drawable ld = drawables[0];
		Drawable td = drawables[1];
		Drawable rd = drawables[2];
		Drawable bd = drawables[3];

		updateTextBounds();

		int textWidth = textBounds.width();
		int textHeight = textBounds.height();
		int iconPadding = getCompoundDrawablePadding();

		int paddingX;
		if (ld != null && rd != null)
			paddingX = (width - ld.getBounds().width() - rd.getBounds().width() - textWidth - iconPadding * 4) / 2;
		else if (ld != null)
			paddingX = (width - ld.getBounds().width() - textWidth - iconPadding * 2) / 2;
		else if (rd != null)
			paddingX = (width - rd.getBounds().width() - textWidth - iconPadding * 2) / 2;
		else paddingX = 0;

		int paddingY;
		if (td != null && bd != null)
			paddingY = (height - td.getBounds().height() - bd.getBounds().height() - textHeight - iconPadding * 4) / 2;
		else if (td != null)
			paddingY = (height - td.getBounds().height() - textHeight - iconPadding * 2) / 2;
		else if (bd != null)
			paddingY = (height - bd.getBounds().height() - textHeight - iconPadding * 2) / 2;
		else paddingY = 0;

		super.setPadding(
				Math.max(paddingX, leftPadding),
				Math.max(paddingY, topPadding),
				Math.max(paddingX, rightPadding),
				Math.max(paddingY, bottomPadding));
	}

	private boolean hasDrawables()
	{
		for (Drawable drawable : getCompoundDrawables())
			if (drawable != null)
				return true;

		return false;
	}

	private void updateTextBounds()
	{
		String text = getText().toString();
		getPaint().getTextBounds(text, 0, text.length(), textBounds);
	}
}