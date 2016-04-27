package net.soroushjavdan.lockapplicationsample.applicationlockerlibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by SoroushJavdan on 1/25/2016.
 */
public class MyLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private static final String PREF = "LockerPref";
    private static final String IS_APP_LEAVED = "isAppLeaved";

    private Intent lockActivityIntent = null ;
    private String password ;
    private String enterPassCodeTitle ;
    private String wrongPassCodeText ;
    private String submitButtonText;

    // to using custom passcode activity
    public MyLifecycleHandler (Intent lockActivityIntent) {
        this.lockActivityIntent = lockActivityIntent ;
    }

    public MyLifecycleHandler(String password){
        this.password = password ;
    }

    public MyLifecycleHandler (String password , String enterPassCodeTitle , String wrongPassCodeText , String submitButtonText ) {
        this.password = password ;
        this.enterPassCodeTitle = enterPassCodeTitle ;
        this.wrongPassCodeText = wrongPassCodeText ;
        this.submitButtonText= submitButtonText;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;

        //  check if activity start from background state
        SharedPreferences pref =   activity.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        boolean isAppLeaved = pref.getBoolean(IS_APP_LEAVED , false);
        if(isAppLeaved){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(IS_APP_LEAVED, false);
            editor.commit();
            // send user to passcode activity to get password
            Intent intent ;
            if(lockActivityIntent == null ){
                intent = new Intent(activity , DefaultLockerActivity.class );
                intent.putExtra(DefaultLockerActivity.PASSWORD_EXTRA , password ) ;
                if(enterPassCodeTitle != null){
                    intent.putExtra(DefaultLockerActivity.FAIL_PASS_TITLE_EXTRA , wrongPassCodeText ) ;
                    intent.putExtra(DefaultLockerActivity.PASS_TITLE_EXTRA , enterPassCodeTitle ) ;
                    intent.putExtra(DefaultLockerActivity.SUBMIT_BTN_TEXT , submitButtonText ) ;
                }
            }else{
                intent = lockActivityIntent ;
            }
            activity.startActivity(intent);
        }

    }


    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
        if(!isApplicationVisible()){
            // here you store the application background state in preferences
            SharedPreferences pref =  activity.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(IS_APP_LEAVED, true);
            editor.commit();
        }
    }

    private static int started;
    private static int stopped;

    public static boolean isApplicationVisible() {
        return started > stopped;
    }


}