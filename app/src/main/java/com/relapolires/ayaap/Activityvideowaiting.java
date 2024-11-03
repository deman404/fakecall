package com.relapolires.ayaap;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.services.banners.UnityBanners;

import java.io.IOException;
import java.util.List;


public class Activityvideowaiting extends AppCompatActivity implements SurfaceHolder.Callback{
    RelativeLayout clickvideo, cancelvideo;
    Tools_Ads toolsAds;

    Camera camrea;
    SurfaceView videomini;
    int camnumberscount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitywaitvideos);
        toolsAds = (Tools_Ads)getApplicationContext();
        initads();
        initcamera();
        playRing(R.raw.ringvideos);

        clickvideo = findViewById(R.id.video_accpt);
        cancelvideo = findViewById(R.id.video_refus);

        clickvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (toolsAds.selectnetwork.equals("unity")){
                    UnityBanners.destroy();
                }
                toolsAds.stopSound();
                startActivity(new Intent(getApplicationContext(), Activityacceptvideo.class));
               // ShowInterstitial();

                finish();
            }
        });

        cancelvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activitytime.class));
                toolsAds.stopSound();
                throwinter();
                finish();
            }
        });

    }
    ToolsAds toolsAds_;

    private void initads(){
        toolsAds_ =new ToolsAds (this);
        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
         }else {
            toolsAds.setBannerAd(view);

        }

    }
    public void throwinter(){
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
            toolsAds_.interunity(this, toolsAds.unity_inter_ads);
        }else {

            toolsAds = (Tools_Ads)getApplicationContext();
            toolsAds.showInterstitial(this);}

    }


    public void playRing(@RawRes final  int mRaw){
        toolsAds.setsoundavatar(getApplicationContext() ,mRaw);

    }

    public void initcamera(){
        videomini = findViewById(R.id.surfaceVideo);
        videomini.getHolder().addCallback(this);
        videomini.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        facecam();

    }


    public void facecam(){
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        camnumberscount = Camera.getNumberOfCameras();
        for (int FacingCam = 0; FacingCam < camnumberscount; FacingCam++) {
            Camera.getCameraInfo(FacingCam, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    camrea = Camera.open(FacingCam);
                } catch (RuntimeException e) {

                }
            }
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camrea != null) {

            Camera.Parameters params = camrea.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size previewSize = sizes.get(0);

            params.setPreviewSize(previewSize.width,previewSize.height);
            camrea.setParameters(params);
            camrea.setDisplayOrientation(90);

            try {
                camrea.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("motya","surfaceCreated");
            }


            camrea.startPreview();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        try{
            Camera.Parameters params = camrea.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size selected = sizes.get(0);
            params.setPreviewSize(selected.width,selected.height);
            params.getSupportedPreviewSizes();
            camrea.setParameters(params);
            camrea.setDisplayOrientation(90);
            camrea.setPreviewDisplay(holder);
            camrea.startPreview();
        }catch (Exception e0){

        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        toolsAds.stopSound();
    }

    @Override
    protected void onResume() {
        initcamera();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
        super.onResume();

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camrea != null) {
             camrea.stopPreview();
        }

    }


}

