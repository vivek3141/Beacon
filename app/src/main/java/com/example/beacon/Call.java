package com.example.beacon;



import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaRecorder;

import java.io.File;

public class Call extends AppCompatActivity implements SurfaceHolder.Callback{

    private Button distress;
    private Button contacts;
    private final int REQUEST_CODE = 1;

    private static final String TAG = Call.class.getSimpleName();

    public static SurfaceView mSurfaceView;
    public static SurfaceHolder mSurfaceHolder;
    public static android.hardware.Camera mCamera;
    public static boolean mPreviewRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        //moveTaskToBack(true);
        makeService();


        contacts = findViewById(R.id.contactsButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Call.this, scenarios.class);
                startActivity(intent);
            }
        });



        distress = findViewById(R.id.distressButton);
        distress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final View arg = arg0;


                Intent intent2 = new Intent(Call.this, message.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(intent2);
                moveTaskToBack(true);



//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try {
//                            Intent callIntent = new Intent(Intent.ACTION_CALL);
//
//                            callIntent.setData(Uri.parse("tel:" + "1-650-695-2483"));
//                            startActivity(callIntent);
//                            //moveTaskToBack(true);
//                        } catch (SecurityException err) {
//                            System.out.println(err);
//                        }
//                    }
//
//                });



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

        Button btnStop = (Button) findViewById(R.id.distressButton);
        btnStop.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopService(new Intent(Call.this, message.class));
            }
        });


    }



    public void record_video(View v) {

//        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//
//        File video_file = getFile();
//
//        Uri uri = Uri.fromFile(video_file);
//
//        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        startActivityForResult(i, 27);

//        myButton.setPressed(true); //you won't click the button
//        myButton.invalidate();
//        myButton.postDelayed(new Runnable() {
//            public void run() {
//                myButton.setPressed(false);
//                myButton.invalidate();
//                releaseCamera();   //release camera from preview before MediaRecorder starts
//                if(!prepareMediaRecorder()){
//                    Toast.makeText(AndroidVideoCapture.this,"could not prepare MediaRecorder",Toast.LENGTH_LONG).show();
//                    finish();
//                }
//                mediaRecorder.start();
//            }
//        },5000);
    }

    public File getFile() {

        File folder = new File("dangerVids");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File video_file = new File(folder, "video.mp4");
        return video_file;
    }


    public void makeService(){
        Intent serv = new Intent(this, message.class);
        startService(serv);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


}








