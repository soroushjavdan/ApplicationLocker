package net.soroushjavdan.lockapplicationsample.applicationlockerlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by TNP on 4/25/2016.
 */
public class DefaultLockerActivity extends BaseLockerActivity {

    private String password ;

    private TextView title ;
    private Button submitBtn ;
    private EditText passInput ;

    public static final String PASSWORD_EXTRA = "passExtra";
    public static final String PASS_TITLE_EXTRA = "titleExtra";
    public static final String FAIL_PASS_TITLE_EXTRA = "failTitleExtra";
    public static final String SUBMIT_BTN_TEXT = "submitBtnTxt";

    private String titleTxt ;
    private String wrongTxt ;
    private String submitTxt;

    private Animation shake ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.default_lock_activity);
        if( getIntent().getExtras().getString(PASSWORD_EXTRA) != null ){

            shake = AnimationUtils.loadAnimation(this, R.anim.passcode_shakeanim);

            password = getIntent().getExtras().getString(PASSWORD_EXTRA);

            title = (TextView) findViewById(R.id.activity_lock_title);
            passInput = (EditText) findViewById(R.id.activity_lock_pass_input);
            submitBtn = (Button) findViewById(R.id.activity_lock_submit);

            titleTxt = getString(R.string.lock_getting_pass_txt) ;
            wrongTxt = getString(R.string.wrong_lock_getting_pass_txt) ;
            submitTxt  = getString(R.string.submit_txt) ;

            if(getIntent().getExtras().getString(PASS_TITLE_EXTRA) != null){
                titleTxt = getIntent().getExtras().getString(PASS_TITLE_EXTRA);
            }
            if(getIntent().getExtras().getString(FAIL_PASS_TITLE_EXTRA) != null){
                wrongTxt = getIntent().getExtras().getString(FAIL_PASS_TITLE_EXTRA);
            }

            if(getIntent().getExtras().getString(SUBMIT_BTN_TEXT) != null){
                submitTxt = getIntent().getExtras().getString(SUBMIT_BTN_TEXT);
            }

            title.setText(titleTxt);
            submitBtn.setText(submitTxt);

            passInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if(password.equals(passInput.getText().toString())){
                            finish();
                        }else{
                            title.setTextColor(Color.parseColor("#ff7a7a"));
                            title.startAnimation(shake);
                            title.setText(wrongTxt);
                        }
                        return true;
                    }
                    return false;
                }
            });

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(password.equals(passInput.getText().toString())){
                        finish();
                    }else{
                        title.setTextColor(Color.parseColor("#ff7a7a"));
                        title.startAnimation(shake);
                        title.setText(wrongTxt);
                    }
                }
            });
        }else{
            finish();
        }
    }


}
