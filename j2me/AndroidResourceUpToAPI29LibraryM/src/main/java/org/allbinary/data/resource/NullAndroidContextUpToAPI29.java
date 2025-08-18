/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
package org.allbinary.data.resource;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author User
 */
public class NullAndroidContextUpToAPI29 extends Context {
 
    private static final Context NULL_ANDROID_CONTEXT = new NullAndroidContextUpToAPI29();
    
    public static final Context getInstance() {
        return NULL_ANDROID_CONTEXT;
    }
    
    @Override
    public AssetManager getAssets(){throw new RuntimeException();}

    @Override
    public Resources getResources(){throw new RuntimeException();}

    @Override
    public PackageManager getPackageManager(){throw new RuntimeException();}

    @Override
    public ContentResolver getContentResolver(){throw new RuntimeException();}

    @Override
    public Looper getMainLooper(){throw new RuntimeException();}

    @Override
    public Context getApplicationContext(){throw new RuntimeException();}

    @Override
    public void setTheme(int resid){throw new RuntimeException();}

    @Override
    public Theme getTheme(){throw new RuntimeException();}

    @Override
    public ClassLoader getClassLoader(){throw new RuntimeException();}

    @Override
    public String getPackageName(){throw new RuntimeException();}

    @Override
    public SharedPreferences getSharedPreferences(String name,
            int mode){throw new RuntimeException();}

    @Override
    public FileInputStream openFileInput(String name)
        throws FileNotFoundException{throw new RuntimeException();}

    @Override
    public FileOutputStream openFileOutput(String name, int mode)
        throws FileNotFoundException{throw new RuntimeException();}

    @Override
    public boolean deleteFile(String name){throw new RuntimeException();}

    @Override
    public File getFileStreamPath(String name){throw new RuntimeException();}

    @Override
    public File getFilesDir(){throw new RuntimeException();}

    @Override
    public File getCacheDir(){throw new RuntimeException();}

    @Override
    public String[] fileList(){throw new RuntimeException();}

    @Override
    public File getDir(String name, int mode){throw new RuntimeException();}

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name,
            int mode, CursorFactory factory){throw new RuntimeException();}

    @Override
    public boolean deleteDatabase(String name){throw new RuntimeException();}

    @Override
    public File getDatabasePath(String name){throw new RuntimeException();}

    @Override
    public String[] databaseList(){throw new RuntimeException();}

    @Override
    public Drawable getWallpaper(){throw new RuntimeException();}

    @Override
    public Drawable peekWallpaper(){throw new RuntimeException();}

    @Override
    public int getWallpaperDesiredMinimumWidth(){throw new RuntimeException();}

    @Override
    public int getWallpaperDesiredMinimumHeight(){throw new RuntimeException();}

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException{throw new RuntimeException();}

    @Override
    public void setWallpaper(InputStream data) throws IOException{throw new RuntimeException();}

    @Override
    public void clearWallpaper() throws IOException{throw new RuntimeException();}

    @Override
    public void startActivity(Intent intent){throw new RuntimeException();}

    public void startActivities(Intent[] intents){throw new RuntimeException();}

    @Override
    public void sendBroadcast(Intent intent){throw new RuntimeException();}

    @Override
    public void sendBroadcast(Intent intent,
            String receiverPermission){throw new RuntimeException();}

    @Override
    public void sendOrderedBroadcast(Intent intent,
            String receiverPermission){throw new RuntimeException();}

    @Override
    public void sendOrderedBroadcast(Intent intent,
            String receiverPermission, BroadcastReceiver resultReceiver,
            Handler scheduler, int initialCode, String initialData,
            Bundle initialExtras){throw new RuntimeException();}

    @Override
    public void sendStickyBroadcast(Intent intent){throw new RuntimeException();}

    @Override
    public void removeStickyBroadcast(Intent intent){throw new RuntimeException();}

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
                                            IntentFilter filter){throw new RuntimeException();}

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
                                            IntentFilter filter,
                                            String broadcastPermission,
                                            Handler scheduler){throw new RuntimeException();}

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver){throw new RuntimeException();}

    @Override
    public ComponentName startService(Intent service){throw new RuntimeException();}

    @Override
    public boolean stopService(Intent service){throw new RuntimeException();}

    @Override
    public boolean bindService(Intent service, ServiceConnection conn,
            int flags){throw new RuntimeException();}

    @Override
    public void unbindService(ServiceConnection conn){throw new RuntimeException();}

    @Override
    public boolean startInstrumentation(ComponentName className,
            String profileFile, Bundle arguments){throw new RuntimeException();}

    @Override
    public Object getSystemService(String name){throw new RuntimeException();}

    @Override
    public int checkPermission(String permission, int pid, int uid){throw new RuntimeException();}

    @Override
    public int checkCallingPermission(String permission){throw new RuntimeException();}

    @Override
    public int checkCallingOrSelfPermission(String permission){throw new RuntimeException();}

    @Override
    public void enforcePermission(
            String permission, int pid, int uid, String message){throw new RuntimeException();}

    @Override
    public void enforceCallingPermission(
            String permission, String message){throw new RuntimeException();}

    @Override
    public void enforceCallingOrSelfPermission(
            String permission, String message){throw new RuntimeException();}

    @Override
    public void grantUriPermission(String toPackage, Uri uri,
            int modeFlags){throw new RuntimeException();}

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags){throw new RuntimeException();}

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags){throw new RuntimeException();}

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags){throw new RuntimeException();}

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags){throw new RuntimeException();}

    @Override
    public int checkUriPermission(Uri uri, String readPermission,
            String writePermission, int pid, int uid, int modeFlags){throw new RuntimeException();}

    @Override
    public void enforceUriPermission(
            Uri uri, int pid, int uid, int modeFlags, String message){throw new RuntimeException();}

    @Override
    public void enforceCallingUriPermission(
            Uri uri, int modeFlags, String message){throw new RuntimeException();}
    
    @Override
    public void enforceCallingOrSelfUriPermission(
            Uri uri, int modeFlags, String message){throw new RuntimeException();}

    @Override
    public void enforceUriPermission(
            Uri uri, String readPermission, String writePermission,
            int pid, int uid, int modeFlags, String message){throw new RuntimeException();}

    @Override
    public Context createPackageContext(String packageName,
            int flags) throws NameNotFoundException{throw new RuntimeException();}
    
}
