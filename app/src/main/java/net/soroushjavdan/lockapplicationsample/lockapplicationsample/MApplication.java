package net.soroushjavdan.lockapplicationsample.lockapplicationsample;

import android.app.Application;

import net.soroushjavdan.lockapplicationsample.applicationlockerlibrary.MyLifecycleHandler;

/**
 * Created by TNP on 4/25/2016.
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new MyLifecycleHandler("1234"));
    }

}
