package com.example.sanidhyagarg.qrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {
    Button scan;
    TextView text;
    public static final int PERMISSION_CODE=100;
    public static final int REQUEST_CODE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan=(Button) findViewById(R.id.scanbtn);
        text=(TextView) findViewById(R.id.result);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},PERMISSION_CODE);
        }
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ScanActivity.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    { if(requestcode==REQUEST_CODE && resultcode==RESULT_OK){
        Barcode barcode = data.getParcelableExtra("barcode");
        text.setText(barcode.displayValue);
    }
    else {
        super.onActivityResult(requestcode, resultcode, data);
    }

    }


}
