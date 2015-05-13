package io.cir.demoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by joytomo on 2015/05/13.
 */
public class BitmapUtil {

    public static Bitmap bitmapCreate(Context context,int imageId) {
        return BitmapFactory.decodeResource(context.getResources(), imageId);
    }
}
