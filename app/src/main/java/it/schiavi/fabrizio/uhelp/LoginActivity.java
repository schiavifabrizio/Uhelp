package it.schiavi.fabrizio.uhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView2 = (TextView) findViewById(R.id.textIscriviti);
        textView2.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick (View v) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        }
    });

        Button button2 = (Button) findViewById(R.id.btnAccedi);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView textView3 = (TextView) findViewById(R.id.textScordata);
        textView3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });

    }
}
