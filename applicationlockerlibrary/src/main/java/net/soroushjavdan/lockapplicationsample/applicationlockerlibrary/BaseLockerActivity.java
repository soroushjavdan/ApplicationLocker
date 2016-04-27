package net.soroushjavdan.lockapplicationsample.applicationlockerlibrary;

import android.app.Activity;

/**
 * Created by TNP on 4/27/2016.
 */
public class BaseLockerActivity extends Activity {
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
