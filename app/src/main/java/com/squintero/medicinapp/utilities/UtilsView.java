package com.squintero.medicinapp.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;


public class UtilsView {

    public static int convertDpToPx(Context ctx, int dp) {

        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int convertPxToDp(Context ctx, int px) {

        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Bitmap convertTextToBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width      = (int) (paint.measureText(text) + 0.0f); // round
        int height     = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image   = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    public static Uri getLocalBitmapFromUri(Context context, String shareImage, Bitmap bmp) {

        // Extract Bitmap from ImageView drawable
//        Drawable drawable = imageView.getDrawable();
//        Bitmap bmp = null;
//        if (drawable instanceof BitmapDrawable){
//            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        } else {
//            return null;
//        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
            File file =  new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES),
                    shareImage + "_" + System.currentTimeMillis() + ".jpeg");

            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();

            // **Warning:** This will fail for API >= 24, use a FileProvider as shown below instead.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // wrap File object into a content provider
                bmpUri = FileProvider.getUriForFile(
                        context,
                        context.getPackageName() + ".fileprovider", file);

            } else {
                bmpUri = Uri.fromFile(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
