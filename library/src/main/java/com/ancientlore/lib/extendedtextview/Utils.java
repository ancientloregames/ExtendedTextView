package com.ancientlore.lib.extendedtextview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;

public class Utils
{
	private Utils() { }

	public static void setIntrinsicBounds(Drawable drawable)
	{
		if (drawable == null)
			return;

		drawable.setBounds(0, 0,  drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	}

	public static void setTint(Drawable drawable, @ColorInt int color)
	{
		if (drawable == null || color == Color.TRANSPARENT)
			return;

		DrawableCompat.setTint(drawable, color);
	}
}
