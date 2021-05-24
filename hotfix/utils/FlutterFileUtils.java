package cn.weeget.youxuan.hotfix.utils;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlutterFileUtils {
    ///将文件拷贝到私有目录
    public static String copyLibAndWrite(Context context, String fileName){
        try {
            File dir = context.getDir("libs", Activity.MODE_PRIVATE);
            //String nativeLibraryDir =  context
                    //.getPackageManager()
                    //.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).nativeLibraryDir;
            File destFile = new File(dir.getPath() + File.separator + fileName);
            String path = context.getExternalFilesDir(null).getPath();
            Log.e("copyLibAndWrite=", "path = "+path);
            File targetFile = new File(path + File.separator + fileName);
            if(!targetFile.exists()){
                Log.e("copyLibAndWrite=", "!targetFile exists");
                return null;
            }
            Log.e("copyLibAndWrite=", "targetFile exists!!!");
            if (destFile.exists()) {
                destFile.delete();
            }
            if (!destFile.exists()){
                boolean res = destFile.createNewFile();
                if (res){
                    FileInputStream is = new FileInputStream(targetFile);
                    FileOutputStream fos = new FileOutputStream(destFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Log.e("copyLibAndWrite=", "suceess=");
                    return destFile.getAbsolutePath();
                }
            }
        }catch (Exception e){
            Log.e("copyLibAndWrite", "IOException=",e);
            e.printStackTrace();
        }
        return "";
    }

}