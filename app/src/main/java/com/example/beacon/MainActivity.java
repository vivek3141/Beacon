package com.example.beacon;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button distress;
    private Button contacts;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contacts = findViewById(R.id.contactsButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, scenarios.class);
                startActivity(intent);
            }
        });

        distress = findViewById(R.id.distressButton);
        distress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final View arg = arg0;
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());





                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("smsto:1-650-695-2483", null, "I'm in deep trouble. Call the police", null, null);

                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "1-650-695-2483"));
                    startActivity(callIntent);

                } catch (SecurityException err) {
                    Log.i("", err.toString());
                }

                record_video(arg);


                //new LongOperation().execute("");

            }
        });


    }

    public void record_video(View v) {

        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        File video_file = getFile();

        Uri uri = Uri.fromFile(video_file);

        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(i, REQUEST_CODE);
    }

    public File getFile() {

        File folder = new File("dangerVids");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File video_file = new File(folder, "video.mp4");
        return video_file;
    }
}


//    private class LongOperation extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//
//                callIntent.setData(Uri.parse("tel:" + "1-650-695-2483"));
//                startActivity(callIntent);
//            } catch (SecurityException err) {
//                System.out.println(err);
//            }
//
//            return null;
//        }
//
//
//        protected void onPostExecute(Void... values) {
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//    }
//}






