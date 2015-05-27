package io.cir.demoapp;

import android.app.Application;

import io.cir.DeepLinkSupporterActivity;

/**
 * Created by joytomo on 2015/05/27.
 */
public class DemoAppApplication extends Application {

    @Override
    public void onCreate() {
        // 開発モードのON/OFF
        DeepLinkSupporterActivity.setDevelopmentMode(false);
        // ログ出力のON/OFF
        DeepLinkSupporterActivity.setLogEnabled(true);
    }

}