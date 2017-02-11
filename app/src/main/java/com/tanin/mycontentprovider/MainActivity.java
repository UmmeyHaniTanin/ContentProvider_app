package com.tanin.mycontentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCall,btnSms,btnEmail,btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall=(Button)findViewById(R.id.btnCall);
        btnSms=(Button)findViewById(R.id.btnSms);
      btnEmail=(Button)findViewById(R.id.btnEmail);
        btnShare=(Button)findViewById(R.id.btnShare);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile="01672902634";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mobile));
                //  Log.i("test", mobile);

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

       btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="01672902634";
                String uriStr = "sms:"+number;
                Uri smsUri = Uri.parse(uriStr);
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsUri);

                startActivity(smsIntent);

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // when i need to use email id s dynamically
                // String email=getIntent().getExtras().getString("data"); this line will get email id that is
                // send to this file and save that data into email
                //String[] TO = {email};
                String[] TO = {"noor@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                //  emailIntent.setType("message/rfc822"); //reduce number of apps
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject : Test Email");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

       btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="Anik";
                String address="Agrabad,Chittagong";
                String number="01672902634";

                String content = "Name : " + name + "  \n" + "Address : " + address + " \n " + "Contact Number : " + number;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, content);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


    }
}
