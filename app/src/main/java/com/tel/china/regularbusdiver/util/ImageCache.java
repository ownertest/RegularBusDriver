package com.tel.china.regularbusdiver.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by wuyq on 14-7-30.
 */
public class ImageCache implements com.android.volley.toolbox.ImageLoader.ImageCache {
    /**
     * 默认缓存200张图片，在内存中
     */
    private static final int DEFAULT_MAX_SIZE = 200;
    private LruCache<String, Bitmap> lruCache;
    private Context context;

    public ImageCache(Context context, int maxSize) {
        lruCache = new LruCache<>(maxSize);
        this.context = context;
    }

    public ImageCache(Context context) {
        this(context, DEFAULT_MAX_SIZE);
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = lruCache.get(url);
        if (bitmap != null)
            return bitmap;
        //从文件中加载..
        String filename = Md5Util.encrypt(url);
        File file = new File(context.getCacheDir() + "/" + filename);
        if (file.exists()) {
            Log.d("img load from file# " + url);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmap != null) {
                lruCache.put(url, bitmap);
            }
            return bitmap;
        }
        Log.d("image_cache_info", "get bitmap and is null" + url);
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.d("image_cache_info", "put bitmap\t" + url);
        lruCache.put(url, bitmap);
        //保存到文件中
        try {
            String filename = Md5Util.encrypt(url);
            File file = new File(context.getCacheDir() + "/" + filename);
            FileOutputStream out = new FileOutputStream(file);
            if (url.contains("png") || url.contains("PNG")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            }
            out.flush();
            out.close();
            Log.d("img save to file# " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
