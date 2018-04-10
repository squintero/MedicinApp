package com.squintero.medicinapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import com.squintero.medicinapp.App;
import com.squintero.medicinapp.Constants;
import com.squintero.medicinapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraManager {

    /** https://developer.android.com/training/camera/photobasics.html **/
    private final static String TAG = CameraManager.class.getSimpleName();
    private final static String PHOTO_PREFIX = "WIMG_";
    private final static String PHOTO_SUFFIX = ".jpeg";

    private File externalStorageFile;
    private String currentPhotoName;
    private String currentPhotoPath;
    private CameraManagerListener listener;

    //Singleton
    private static CameraManager cameraInstance;

    public static synchronized CameraManager getInstance(CameraManagerListener listener) {

        if (cameraInstance == null) {
            cameraInstance = new CameraManager(listener);
        }

        return cameraInstance;
    }

    public CameraManager(CameraManagerListener listener) {
        this.listener  = listener;
        this.externalStorageFile = Environment.getExternalStorageDirectory();
    }

    public String getCurrentPhotoName() {

        return currentPhotoName;
    }

    public void openCameraIntent(Object object, Context context) {

        File photoFile = null;

        try {
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//            currentPhotoName = PHOTO_PREFIX + timeStamp + PHOTO_SUFFIX;
//            photoFile        = new File(externalStorageFile.toString(), currentPhotoName);

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                photoFile = createImageFile(context);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri photoURI = FileProvider.getUriForFile(
                        context,
                        context.getPackageName() + ".fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            }

//            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (object instanceof AppCompatActivity) {
                ((AppCompatActivity) object).startActivityForResult(intent, Constants.REQUEST_CAMERA);

            } else if (object instanceof Fragment) {
                ((Fragment) object).startActivityForResult(intent, Constants.REQUEST_CAMERA);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onCameraResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.REQUEST_CAMERA) {

                try {
                    File file = new File(currentPhotoPath);
//                    File file = new File(externalStorageFile.toString(), currentPhotoName);

//                    for (File temp : file.listFiles()) {
//                        if (temp.getAbsolutePath().equals(currentPhotoName)) {
//                            file = temp;
//                            break;
//                        }
//                    }

                    decodeScaledImage(file, data);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void reset() {

        Logs.SystemLog(TAG + " - RESET");
        currentPhotoName = null;
        currentPhotoPath = null;
        cameraInstance   = null;
    }

    public static Bitmap rotateImageFromGallery(Bitmap source, final Uri selectedImageUri) {

        try {
            ExifInterface exif = new ExifInterface(selectedImageUri.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            int angle = 0;
            switch (orientation) {
                case ExifInterface.ORIENTATION_NORMAL:
                    angle = 0;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;

                default:
//                    angle = 0;
                    break;
            }

            Matrix mat = new Matrix();
            if (angle == 0 && source.getWidth() > source.getHeight())
                mat.postRotate(90);
            else
                mat.postRotate(angle);

            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), mat, true);

        } catch (IOException e) {
            Logs.MessageLogs("rotateImageFromGallery", "Error in setting image", "e");
        } catch (OutOfMemoryError oom) {
            Logs.MessageLogs("rotateImageFromGallery", "OOM Error in setting image", "e");
        }
        return null;
    }

    /*********************/
    /** PRIVATE METHODS **/
    /*********************/
    private File createImageFile(Context context) throws IOException {

        // Create an image file name
        String timeStamp     = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = PHOTO_PREFIX + timeStamp + "_";
        File storageDir      = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        String storageName   = externalStorageFile.toString() + "/" + App.getInstance().getString(R.string.app_name);
//        File storageDir      = context.getExternalFilesDir(storageName);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                PHOTO_SUFFIX,   /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        currentPhotoName = image.getName(); //imageFileName;

        return image;
    }

    public void decodeScaledImage(File file, Intent data) {

        Bitmap bm;

        try {
            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
            btmapOptions.inSampleSize = 4;  // downsizing image as it throws OutOfMemory Exception
            bm = BitmapFactory.decodeFile(file.getAbsolutePath(), btmapOptions);

            OutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);

            fOut.flush();
            fOut.close();

            //TODO Add to the gallery??
//            setPicToGallery();  //Add pic into gallery

            //Update view and data
            if (listener != null) {
                listener.cameraResultSuccess(file, bm, data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decodeScaledImageOld(File file, Intent data) {

        Bitmap bm;

        try {
            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
            btmapOptions.inSampleSize = 4;  // downsizing image as it throws OutOfMemory Exception
            bm = BitmapFactory.decodeFile(file.getAbsolutePath(), btmapOptions);

            String extStorageDirectory = externalStorageFile.toString() + "/" +
                    App.getInstance().getString(R.string.app_name);

            File folder = new File(extStorageDirectory);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            OutputStream fOut = null;
            File fileTemp = new File(extStorageDirectory,
                    String.valueOf(System.currentTimeMillis()) + ".png"); //file to send...

            fOut = new FileOutputStream(fileTemp);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);

            fOut.flush();
            fOut.close();

            //Update view and data
            if (listener != null) {
                listener.cameraResultSuccess(file, bm, data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean removeFile() {

        File file = new File(currentPhotoPath);

        return file.delete();
    }

    private void setPicToGallery() {

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        App.getInstance().getApplicationContext().sendBroadcast(mediaScanIntent);
    }

//    private void setPic(File file, Intent data) {
//        // Get the dimensions of the View
////        int targetW = mImageView.getWidth();
////        int targetH = mImageView.getHeight();
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
//        mImageView.setImageBitmap(bitmap);
//    }

    /** LISTENER **/
    public interface CameraManagerListener {

        void cameraResultSuccess(File file, Bitmap bitmap, Intent data);
    }
}
