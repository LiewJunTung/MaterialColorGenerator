package org.pandawarrior.foodalbum;

import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.Executors;

/**
 * Created by kevintanhongann on 6/24/14.
 */
public class PicassoUtils {
    private static Picasso mPicasso;

    public static Picasso getPicasso(Context context) {
        if (mPicasso == null) {
            //File cacheDir = new File("tableappcache");
            File cacheDir = context.getCacheDir();
            Picasso.Builder picassoBuilder = new Picasso.Builder(context.getApplicationContext())
                    .executor(Executors
                            .newFixedThreadPool(10)).downloader(new OkHttpDownloader(cacheDir));
            mPicasso = picassoBuilder.build();
        }
        return mPicasso;
    }
}
