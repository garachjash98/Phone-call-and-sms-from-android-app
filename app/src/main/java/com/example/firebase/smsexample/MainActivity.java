package com.example.firebase.smsexample;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    private final static int REQUESTCODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);


        b1.setEnabled(false);



        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            b1.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},REQUESTCODE);

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                send_SMS();
            }
        });




    }


    public void send_SMS()
    {
        String sms=e1.getText().toString();
        String mess=e2.getText().toString();


        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sms,null,mess,null,null);
            Toast.makeText(MainActivity.this,"MESSAGE SENDED",Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
        }
        else
        {
            Toast.makeText(MainActivity.this,"NOT SENDED",Toast.LENGTH_LONG).show();
        }

    }
    private boolean checkPermission(String permission)
    {
        int checkPermission = ContextCompat.checkSelfPermission(MainActivity.this,permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
