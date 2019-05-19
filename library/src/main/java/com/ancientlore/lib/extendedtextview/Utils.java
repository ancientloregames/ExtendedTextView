package com.ancientlore.lib.extendedtextview;

import android.graphics.drawable.Drawable;

public class Utils
{
	private Utils() { }

	public static void setIntrinsicBounds(Drawable drawable)
	{
		if (drawable == null)
			return;

		drawable.setBounds(0, 0,  drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	}
}
