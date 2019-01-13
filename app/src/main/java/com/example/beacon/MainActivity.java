package com.example.beacon;



import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private Button distress;
    private Button contacts;
    private final int REQUEST_CODE = 1;
    public MainActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, PhoneService.class));


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


                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);

                            callIntent.setData(Uri.parse("tel:" + "1-650-695-2483"));
                            startActivity(callIntent);
                            moveTaskToBack(true);
                        } catch (SecurityException err) {
                            System.out.println(err);
                        }
                    }

                });



//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage("smsto:1-650-695-2483", null, "I'm in deep trouble. Call the police", null, null);
//                    }
//
//                });


//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        record_video(arg);
//                    }
//                });

                record_video(arg0);


                //new LongOperation().execute("");

            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) && (keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            System.out.println("I WANT A HENTAI GIRL");
        }
        return true;
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
    public class PhoneService extends Service {
        public PhoneService(){}
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }


        public void onCreate(){}

        public void onStartCommand() {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    Log.i("","HENTAI IS GAY");
                }

            });

        }
    }
}








