package com.example.callwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {

    private static final int REQUEST_CALL = 1;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

//            if(ContextCompat.checkSelfPermission(context,
//                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity) context,
//                        new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//            } else {
//                String phoneNumber = String.valueOf(0);
//                Intent intent = new Intent(ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//                PendingIntent callPendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//                views.setOnClickPendingIntent(R.id.widget_btn, callPendingIntent);
//                views.setTextViewText(R.id.widget_text, "Phone: " + phoneNumber);
//                //views.setTextViewText(R.id.widget_id_value, "Widget ID: " + String.valueOf(appWidgetId));
//
//                try {
//                    String fileName = appWidgetId +".txt";
//                    FileInputStream fileInput = openFileInput(fileName);
//                    InputStreamReader reader = new InputStreamReader(fileInput);
//                    BufferedReader bufReader = new BufferedReader(reader);
//                    bufReader.readLine();
//
//                    Toast.makeText(context, fileName+" is saved",Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//        }


            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context, "Widget deleted", Toast.LENGTH_SHORT).show();
    }

}
