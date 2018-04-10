package com.squintero.medicinapp.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.squintero.medicinapp.Constants;


public final class PermissionManager {

    public static boolean checkCameraPermission(Activity activity){

        int permissionCAM = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permissionCAM != PackageManager.PERMISSION_GRANTED)
        {
            Logs.MessageLogs("checkCameraPermission", "No permission granted", "v");
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(activity,
                    PERMISSIONS, Constants.REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
            return false;
        }

        Logs.MessageLogs("checkCameraPermission", "Permission granted", "v");
        return true;
    }

    public static boolean checkCameraAndReadExternalStoragePermission(Activity activity){

        int permissionRES = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCAM = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permissionRES != PackageManager.PERMISSION_GRANTED ||
            permissionCAM != PackageManager.PERMISSION_GRANTED)
        {
            Logs.MessageLogs("checkCameraAndReadExternalStoragePermission", "No permission granted", "v");
            String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(activity,
                    PERMISSIONS, Constants.REQUEST_CODE_ASK_PERMISSIONS_FILES);
            return false;
        }

        Logs.MessageLogs("checkCameraAndReadExternalStoragePermission", "Permission granted", "v");
        return true;
    }

    public static boolean checkFilesPermission(AppCompatActivity activity){

        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(activity.getApplicationContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            Logs.MessageLogs("checkFilesPermission", "No permission granted", "v");
            String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(activity, PERMISSIONS, Constants.REQUEST_CODE_ASK_PERMISSIONS_FILES);
            return false;
        }

        Logs.MessageLogs("checkFilesPermission", "Permission granted", "v");
        return true;
    }

    public static boolean checReadkWriteExternalStoragePermissions(Activity activity) {

        int permissionRES = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWES = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionRES != PackageManager.PERMISSION_GRANTED ||
            permissionWES != PackageManager.PERMISSION_GRANTED)
        {
            Logs.MessageLogs("checReadkWriteExternalStoragePermissions", "No permission granted", "v");
            String[] PERMISSIONS = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(activity,
                    PERMISSIONS, Constants.REQUEST_CODE_ASK_PERMISSIONS_FILES);
            return false;
        }

        Logs.MessageLogs("checReadkWriteExternalStoragePermissions", "Permission granted", "v");
        return true;
    }
}
