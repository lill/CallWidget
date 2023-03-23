package com.example.callwidget;

import static android.content.Intent.ACTION_CALL;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class WidgetConfig extends Activity {

    private int appWidgetId;
    private EditText editWidgetName;
    private EditText editWidgetPhone;
    private Context thisContext;
    private AppWidgetManager appWidgetManager;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_config);

        setResult(RESULT_CANCELED);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            // If the intent doesn’t have a widget ID, then call finish()//
            if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                finish();
            }
        }

        editWidgetName = (EditText)findViewById(R.id.configWidgetName);
        editWidgetPhone = (EditText)findViewById(R.id.configWidgetPhone);
        thisContext = this.getApplicationContext();
        appWidgetManager = AppWidgetManager.getInstance(thisContext);
        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.widget);


        // added start
        Button setupWidget = (Button) findViewById(R.id.configWidgetBtn);
        setupWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = editWidgetPhone.getText().toString();
                if(ContextCompat.checkSelfPermission(thisContext,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WidgetConfig.this,
                            new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    Intent intent = new Intent(ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    PendingIntent callPendingIntent = PendingIntent.getActivity(thisContext, 0, intent, 0);
                    views.setTextViewText(R.id.widget_name, editWidgetName.getText().toString());
                    views.setTextViewText(R.id.widget_phone, editWidgetPhone.getText().toString());
//                    views.setTextViewText(R.id.widget_id_value, "Widget ID: " + String.valueOf(appWidgetId));
                    views.setOnClickPendingIntent(R.id.widget_btn, callPendingIntent);

//                    try {
//                        String fileName = appWidgetId +".txt";
//                        FileOutputStream fileOutput = openFileOutput(fileName, MODE_PRIVATE);
//                        fileOutput.write(Integer.parseInt(phoneNumber));
//                        fileOutput.close();
//
//                        Toast.makeText(thisContext, fileName+" is saved with number "+phoneNumber,Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(thisContext, e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }

                }

                // added end

                appWidgetManager.updateAppWidget(appWidgetId, views);

                //Toast.makeText(thisContext, "Widget "+appWidgetId+" is saved with number "+phoneNumber,Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }

}

