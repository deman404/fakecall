package com.relapolires.ayaap;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;

import java.util.List;


public class ActivityChat extends AppCompatActivity implements View.OnClickListener {

    Button meessage1, meessage2, meessage3, meessage4, meessage5, meessage6
            , meessage7, meessage8, meessage9, meessage10, meessage11, meessage12, meessage13, meessage14, meessage15;


    ImageView herochating, back;
    ImageView callVOICE, Opencontect, aboutinfo, restartings;



    private AdView mAdView;
    ScrollView scview;
    Animation animate;
    String GooglePlay ="http://play.google.com/store/apps/details?id=";
    AlertDialog dialog;
    TextView myName ;
    LinearLayout first, second, third, chat4, chat5, chat6,
            chat7, chat8, chat9, chat10, chat11, chat12,
            lichat13, lichat14, lichat15;

    LinearLayout FLinearLayout_1 ,FLinearLayout_2,FLinearLayout_3,FLinearLayout_4,FLinearLayout_5 ,FLinearLayout_6 ,
            FLinearLayout_7 ,FLinearLayout_8,FLinearLayout_9,FLinearLayout_10,FLinearLayout_11 ,FLinearLayout_12,
            FLinearLayout_13,FLinearLayout_14 ,FLinearLayout_15;

    FrameLayout mFrameLayout1 ,mFrameLayout2 ,mFrameLayout3 ,mFrameLayout4 ,mFrameLayout5 ,mFrameLayout6 ,mFrameLayout7,
            mFrameLayout8 ,mFrameLayout9 ,mFrameLayout10 ,mFrameLayout11 ,mFrameLayout12 ,mFrameLayout13 ,
            mFrameLayout14 ,mFrameLayout15 ;
   ToolsAds toolsAds_;


    Tools_Ads toolsAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitychatiing);

        toolsAds_ =new ToolsAds (this);

         messagecreate();
        layouts();
        initframes();
initR();



        animate = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation);
        animate.setStartOffset(0);


        callVOICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call Voice Activity
                startActivity(new Intent(getApplicationContext(), Activitycallerwaiting.class));
              ///  ShowInterstitial();
            }
        });

        Opencontect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityContacting.class));
                ///   ShowInterstitial();
                finish();
            }
        });

        aboutinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ///    ShowInterstitial();
                ShowAboutUs();

            }
        });
        restartings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplayChat();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FLinearLayout_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         try { Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(localIntent);
                } catch (ActivityNotFoundException localActivityNotFoundException) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(GooglePlay+ getPackageName())));
                }

            }
        });

    }


    public void ShowInterstitial(){
        toolsAds = (Tools_Ads)getApplicationContext();

        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.interunity(this, toolsAds.unity_inter_ads);
        }else {

            toolsAds.showInterstitial(this);}

    }
public void initR(){
    herochating = findViewById(R.id.hero_chat);
    myName = findViewById(R.id.myName);

    callVOICE = findViewById(R.id.Call);
    Opencontect = findViewById(R.id.goContact);
    aboutinfo = findViewById(R.id.about_famous);

    restartings = findViewById(R.id.StartChatAgain);
    back = findViewById(R.id.back);
}
    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.mesg1 :
                first.setVisibility(View.VISIBLE);
                mFrameLayout1.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_1.setVisibility(View.VISIBLE);
                        FLinearLayout_1.startAnimation(animate);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);

                break;

            case R.id.mesg2 :

                second.setVisibility(View.VISIBLE);
                mFrameLayout2.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_2.setVisibility(View.VISIBLE);
                        FLinearLayout_2.startAnimation(animate);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);

                break;

            case R.id.mesg3 :
                third.setVisibility(View.VISIBLE);
                mFrameLayout3.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_3.setVisibility(View.VISIBLE);
                        FLinearLayout_3.startAnimation(animate);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);

                break;
            case R.id.mesg4 :
                chat4.setVisibility(View.VISIBLE);
                mFrameLayout4.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_4.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);

                break;

            case R.id.mesg5 :
                chat5.setVisibility(View.VISIBLE);
                mFrameLayout5.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_5.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);

                break;

            case R.id.mesg6 :
                chat6.setVisibility(View.VISIBLE);
                mFrameLayout6.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_6.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
            case R.id.mesg7 :
                chat7.setVisibility(View.VISIBLE);
                mFrameLayout7.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_7.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;

            case R.id.mesg8 :
                chat8.setVisibility(View.VISIBLE);
                mFrameLayout8.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_8.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
            case R.id.mesg9 :
                chat9.setVisibility(View.VISIBLE);
                mFrameLayout9.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_9.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;

            case R.id.mesg10 :
                chat10.setVisibility(View.VISIBLE);
                mFrameLayout10.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_10.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
            case R.id.mesg11 :
                chat11.setVisibility(View.VISIBLE);
                mFrameLayout11.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_11.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
            case R.id.mesg12 :
                chat12.setVisibility(View.VISIBLE);
                mFrameLayout12.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_12.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;

            case R.id.mesg13 :
                lichat13.setVisibility(View.VISIBLE);
                mFrameLayout13.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_13.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;

            case R.id.mesg14 :
                lichat14.setVisibility(View.VISIBLE);
                mFrameLayout14.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_14.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
            case R.id.mesg15 :
                lichat15.setVisibility(View.VISIBLE);
                mFrameLayout15.setVisibility(View.GONE);
                view.postDelayed(new Runnable() {
                    public void run() {
                        FLinearLayout_15.setVisibility(View.VISIBLE);
                        scrollDown();
                        SoundMsg();
                    }
                }, 500);
                break;
        }


      //scrollDown();
    }

    public void SoundMsg(){
        Tools_Ads.setBip(getApplicationContext(),R.raw.ringmessage);
    }


    public void messagecreate(){
        meessage1 = findViewById(R.id.mesg1);
        meessage2 = findViewById(R.id.mesg2);
        meessage3 = findViewById(R.id.mesg3);
        meessage4 = findViewById(R.id.mesg4);
        meessage5 = findViewById(R.id.mesg5);
        meessage6 = findViewById(R.id.mesg6);
        meessage7 = findViewById(R.id.mesg7);
        meessage8 = findViewById(R.id.mesg8);
        meessage9 = findViewById(R.id.mesg9);
        meessage10 = findViewById(R.id.mesg10);
        meessage11 = findViewById(R.id.mesg11);
        meessage12 = findViewById(R.id.mesg12);
        meessage13 = findViewById(R.id.mesg13);
        meessage14 = findViewById(R.id.mesg14);
        meessage15 = findViewById(R.id.mesg15);

        meessage1.setOnClickListener(this);
        meessage2.setOnClickListener(this);
        meessage3.setOnClickListener(this);
        meessage4.setOnClickListener(this);
        meessage5.setOnClickListener(this);
        meessage6.setOnClickListener(this);
        meessage7.setOnClickListener(this);
        meessage8.setOnClickListener(this);
        meessage9.setOnClickListener(this);
        meessage10.setOnClickListener(this);
        meessage11.setOnClickListener(this);
        meessage12.setOnClickListener(this);
        meessage13.setOnClickListener(this);
        meessage14.setOnClickListener(this);
        meessage15.setOnClickListener(this);


    }


    public void layouts(){
        FLinearLayout_1 = findViewById(R.id.fdiscussion1);
        first =findViewById(R.id.mdiscussion1);
        FLinearLayout_2 = findViewById(R.id.fdiscussion2);
        second =findViewById(R.id.mdiscussion2);
        FLinearLayout_3 = findViewById(R.id.fdiscussion3);
        third =findViewById(R.id.mdiscussion3);
        FLinearLayout_4 = findViewById(R.id.fdiscussion4);
        chat4 =findViewById(R.id.mdiscussion4);
        FLinearLayout_5 = findViewById(R.id.fdiscussion5);
        chat5 =findViewById(R.id.mdiscussion5);
        FLinearLayout_6 = findViewById(R.id.fdiscussion6);
        chat6 =findViewById(R.id.mdiscussion6);
        FLinearLayout_7 = findViewById(R.id.fdiscussion7);
        chat7 =findViewById(R.id.mdiscussion7);
        FLinearLayout_8 = findViewById(R.id.fdiscussion8);
        chat8 =findViewById(R.id.mdiscussion8);
        FLinearLayout_9 = findViewById(R.id.fdiscussion9);
        chat9 =findViewById(R.id.mdiscussion9);
        FLinearLayout_10 = findViewById(R.id.fdiscussion10);
        chat10 =findViewById(R.id.mdiscussion10);
        FLinearLayout_11 = findViewById(R.id.fdiscussion11);
        chat11 =findViewById(R.id.mdiscussion11);
        FLinearLayout_12= findViewById(R.id.fdiscussion12);
        chat12 =findViewById(R.id.mdiscussion12);
        FLinearLayout_13= findViewById(R.id.fdiscussion13);
        lichat13 =findViewById(R.id.mdiscussion13);
        FLinearLayout_14= findViewById(R.id.fdiscussion14);
        lichat14 =findViewById(R.id.mdiscussion14);
        FLinearLayout_15= findViewById(R.id.fdiscussion15);
        lichat15 =findViewById(R.id.mdiscussion15);
    }
    public void initframes(){
        mFrameLayout1 = findViewById(R.id.Frame1);
        mFrameLayout2 = findViewById(R.id.Frame2);
        mFrameLayout3 = findViewById(R.id.Frame3);
        mFrameLayout4 = findViewById(R.id.Frame4);
        mFrameLayout5 = findViewById(R.id.Frame5);
        mFrameLayout6 = findViewById(R.id.Frame6);
        mFrameLayout7 = findViewById(R.id.Frame7);
        mFrameLayout8 = findViewById(R.id.Frame8);
        mFrameLayout9 = findViewById(R.id.Frame9);
        mFrameLayout10 = findViewById(R.id.Frame10);
        mFrameLayout11 = findViewById(R.id.Frame11);
        mFrameLayout12 = findViewById(R.id.Frame12);
        mFrameLayout13 = findViewById(R.id.Frame13);
        mFrameLayout14 = findViewById(R.id.Frame14);
        mFrameLayout15 = findViewById(R.id.Frame15);

    }
    public void ShowAllButtonAgain(){
        mFrameLayout1.setVisibility(View.VISIBLE);
        mFrameLayout2.setVisibility(View.VISIBLE);
        mFrameLayout3.setVisibility(View.VISIBLE);
        mFrameLayout4.setVisibility(View.VISIBLE);
        mFrameLayout5.setVisibility(View.VISIBLE);
        mFrameLayout6.setVisibility(View.VISIBLE);
        mFrameLayout7.setVisibility(View.VISIBLE);
        mFrameLayout8.setVisibility(View.VISIBLE);
        mFrameLayout9.setVisibility(View.VISIBLE);
        mFrameLayout10.setVisibility(View.VISIBLE);
        mFrameLayout11.setVisibility(View.VISIBLE);
        mFrameLayout12.setVisibility(View.VISIBLE);
        mFrameLayout13.setVisibility(View.VISIBLE);
        mFrameLayout14.setVisibility(View.VISIBLE);
        mFrameLayout15.setVisibility(View.VISIBLE);




    }
    public void ClearRoomChat(){
        first.setVisibility(View.GONE);
        FLinearLayout_1.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
        FLinearLayout_2.setVisibility(View.GONE);
        third.setVisibility(View.GONE);
        FLinearLayout_3.setVisibility(View.GONE);
        chat4.setVisibility(View.GONE);
        FLinearLayout_4.setVisibility(View.GONE);
        chat5.setVisibility(View.GONE);
        FLinearLayout_5.setVisibility(View.GONE);
        chat6.setVisibility(View.GONE);
        FLinearLayout_6.setVisibility(View.GONE);
        chat7.setVisibility(View.GONE);
        FLinearLayout_7.setVisibility(View.GONE);
        chat8.setVisibility(View.GONE);
        FLinearLayout_8.setVisibility(View.GONE);
        chat9.setVisibility(View.GONE);
        FLinearLayout_9.setVisibility(View.GONE);
        chat10.setVisibility(View.GONE);
        FLinearLayout_10.setVisibility(View.GONE);
        chat11.setVisibility(View.GONE);
        FLinearLayout_11.setVisibility(View.GONE);
        chat12.setVisibility(View.GONE);
        FLinearLayout_12.setVisibility(View.GONE);
        lichat13.setVisibility(View.GONE);
        FLinearLayout_13.setVisibility(View.GONE);
        lichat14.setVisibility(View.GONE);
        FLinearLayout_14.setVisibility(View.GONE);
        lichat15.setVisibility(View.GONE);
        FLinearLayout_15.setVisibility(View.GONE);


    }



    public void ShowAboutUs() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder( ActivityChat.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = getLayoutInflater();
        View about_us = inflater.inflate(R.layout.infous, null);

        alertDialog.setView(about_us)
                .setCancelable(true);
        dialog = alertDialog.create();
        dialog.show();

        Button privacyPolicy = about_us.findViewById(R.id.about_privacy_poilicy);
        final TextView email_about = about_us.findViewById(R.id.feedback_email);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent consent_Privacy_URL = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.privacy_policy) ));
                if (isAvailable(consent_Privacy_URL)) {
                    startActivity(consent_Privacy_URL);
                }
                else {
                    Toast.makeText(getApplicationContext(), "There is no app availalbe for this task", Toast.LENGTH_SHORT).show();
                }


            }
        });

        email_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent email_about = new Intent(Intent.ACTION_SEND);
                email_about.setType("plain/text");
                email_about.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.Email)});
                email_about.putExtra(Intent.EXTRA_SUBJECT, "subject");
                email_about.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(email_about, ""));
                if (isAvailable(email_about)) {
                    startActivity(email_about);
                }
                else {
                    Toast.makeText(getApplicationContext(), "There is no app availalbe for this task", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }
    public boolean isAvailable(Intent intent) {
        final PackageManager mgr = getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    public void ReplayChat(){
        //Call Dialogue Confirm :
        AlertDialog.Builder builder = new AlertDialog.Builder( ActivityChat.this);
        builder.setMessage("Do you want to Replay Chat ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        ShowAllButtonAgain();
                        ClearRoomChat();
                        ShowInterstitial();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }
    public void scrollDown() {
        scview = findViewById(R.id.ScrollRoom);
        scview.postDelayed(new Runnable() {
            public void run() {
                scview.fullScroll(130);
            }
        }, 10);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Tools_Ads.stopSound();
    }



}
