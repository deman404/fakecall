package com.relapolires.ayaap;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.unity3d.services.banners.UnityBanners;

import java.io.IOException;
import java.util.List;


public class Activityacceptvideo extends Activity implements SurfaceHolder.Callback {
    VideoView mVideoView;
    RelativeLayout FinishCallRelative;
    Tools_Ads toolsAds;
    Camera maincamera;
    SurfaceView miniview;
    int cameraCount ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityacceptvideo);
        initializeAd();


        FinishCallRelative = findViewById(R.id.rela_finish);
        mVideoView = findViewById(R.id.video_play);
        initcam();
        VideoStart();

        FinishCallRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation(FinishCallRelative);





                if (toolsAds.selectnetwork.equals("unity"))
                {
                    UnityBanners.destroy();
                    startActivity(new Intent(getApplicationContext(), ActivityContacting.class));
                }else {
                    startActivity(new Intent(getApplicationContext(), ActivityContacting.class));
                    finish();

                }
                ShowInterstitial();





            }
        });
    }

    ToolsAds toolsAds_;
    private void initializeAd(){
        toolsAds_ =new ToolsAds (this);

        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
         }else {
            toolsAds.setBannerAd(view);

        }
    }
    public void ShowInterstitial(){
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.interunity(this, toolsAds.unity_inter_ads);
        }else {

            toolsAds = (Tools_Ads)getApplicationContext();
            toolsAds.showInterstitial(this);}
    }


    public void initcam(){
        miniview = findViewById(R.id.surfaceCam);
        miniview.getHolder().addCallback(this);
        miniview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        VideoCam();

    }
    public void VideoCam(){
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int FacingCam = 0; FacingCam < cameraCount; FacingCam++) {
            Camera.getCameraInfo(FacingCam, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    maincamera = Camera.open(FacingCam);
                } catch (RuntimeException e) {
                    //do nothing
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        this.finish();
        mVideoView.pause();
        mVideoView = null;
    }
    @Override
    protected void onResume() {
        initcam();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
        mVideoView.resume();
        super.onResume();

    }
    public void VideoStart(){

        mVideoView.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" + R.raw.videoone));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.setLooping(true);

            }
        });

        mVideoView.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try{
            Camera.Parameters params = maincamera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size selected = sizes.get(0);
            params.setPreviewSize(selected.width,selected.height);
            params.getSupportedPreviewSizes();
            maincamera.setParameters(params);
            maincamera.setDisplayOrientation(90);
            maincamera.setPreviewDisplay(holder);
            maincamera.startPreview();
        }catch (Exception e0){
            //error to Initialize Camera
        }

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (maincamera != null) {

            Camera.Parameters params = maincamera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size previewSize = sizes.get(0);

            params.setPreviewSize(previewSize.width,previewSize.height);
            maincamera.setParameters(params);
            maincamera.setDisplayOrientation(90);

            try {
                maincamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("motya","surfaceCreated");
            }

            // Important: Call startPreview() to start updating the preview
            // surface. Preview must be started before you can take a picture.
            maincamera.startPreview();
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (maincamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            maincamera.stopPreview();
        }
        Log.e("motya","surfaceDestroyed");
    }

    public void setAnimation(final View b ){
        b.setScaleX(0.9f);
        b.setScaleY(0.9f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setScaleX(1f);
                b.setScaleY(1f);
            }
        },80);
    }


    @Override
    public void onPause() {
        if (maincamera != null) {
            maincamera.stopPreview();
            maincamera.release();
            maincamera = null;
        }
        super.onPause();

    }


}





