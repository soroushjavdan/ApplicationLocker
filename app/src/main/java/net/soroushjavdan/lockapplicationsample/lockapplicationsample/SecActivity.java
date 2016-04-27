package net.soroushjavdan.lockapplicationsample.lockapplicationsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TNP on 4/25/2016.
 */
public class SecActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.activity_text);
        textView.setText("Second Activity");

        Button button = (Button) findViewById(R.id.activity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecActivity.this, ThirdActivity.class));
            }
        });
    }
}
