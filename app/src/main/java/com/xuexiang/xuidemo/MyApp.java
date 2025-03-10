package com.xuexiang.xuidemo;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.google.android.cameraview.CameraView;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.mikepenz.iconics.Iconics;
import com.xuexiang.xui.XUI;
import com.xuexiang.xuidemo.utils.sdkinit.AutoCameraStrategy;
import com.xuexiang.xuidemo.utils.sdkinit.BuglyInit;
import com.xuexiang.xuidemo.utils.sdkinit.TbsInit;
import com.xuexiang.xuidemo.utils.sdkinit.UMengInit;
import com.xuexiang.xuidemo.utils.sdkinit.XBasicLibInit;
import com.xuexiang.xuidemo.utils.sdkinit.XUpdateInit;
import com.xuexiang.xuidemo.widget.iconfont.XUIIconFont;


/**
 * 应用初始化
 *
 * @author xuexiang
 * @since 2018/11/7 下午1:12
 */
public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决4.x运行崩溃的问题
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initUI();
        //初始化基础库
        XBasicLibInit.init(this);
        //三方SDK初始化
        XUpdateInit.init(this);
        TbsInit.init(this);
        //运营统计数据运行时不初始化
        if (!MyApp.isDebug()) {
            UMengInit.init(this);
            BuglyInit.init(this);
        }
    }

    /**
     * 初始化XUI 框架
     */
    private void initUI() {
        XUI.init(this);
        XUI.debug(MyApp.isDebug());
//        //设置默认字体为华文行楷
//        XUI.getInstance().initFontStyle("fonts/hwxk.ttf");
        PictureFileUtils.setAppName("xui");

        //字体图标库
        Iconics.init(this);
        //这是自己定义的图标库
        Iconics.registerFont(new XUIIconFont());

        CameraView.setICameraStrategy(new AutoCameraStrategy(1920 * 1080));
    }


    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }



}
