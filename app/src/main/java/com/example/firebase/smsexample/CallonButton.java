package com.example.firebase.smsexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallonButton extends AppCompatActivity {

    EditText edittext1;
    Button button1;

    private final static int REQUESTCODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callon_button);

        edittext1=(EditText)findViewById(R.id.editText1);
        button1=(Button)findViewById(R.id.button1);


        if(checkPermission(Manifest.permission.CALL_PHONE))
        {
            button1.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(CallonButton.this, new String[]{Manifest.permission.CALL_PHONE},REQUESTCODE);

        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String number=edittext1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);

            }
        });



    }

    private boolean checkPermission(String permission)
    {
        int checkPermission = ContextCompat.checkSelfPermission(CallonButton.this,permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
