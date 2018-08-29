package com.daviddong.ddv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;

import java.util.ArrayList;

// 引入百度语音sdk
public class SecondActivity extends AppCompatActivity {
    private Button btn2;
    private TextView tv1;
    private EditText et1;
    private SpeechSynthesizer mSpeechSynthesizer;
    private MyListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = findViewById(R.id.textView);
        btn2 = findViewById(R.id.button2);
        et1  =findViewById(R.id.editText);
        initPermission();
        showTip("初始化tts");
        InitVoice();
        showTip("初始化tts结束");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                text = et1.getText().toString();
                tv1.setText("输入的内容是: "+text);
//                Toast.makeText(SecondActivity.this,text,Toast.LENGTH_SHORT).show();
                mSpeechSynthesizer.speak(text);
                Toast.makeText(SecondActivity.this,"合成播放结束了",Toast.LENGTH_SHORT).show();
            }
        });
        Integer res = mSpeechSynthesizer.speak("百度一下");
        tv1.setText("合成结果为: "+res);
    }
    public void showTip(String text){
        tv1.setText(tv1.getText()+"\r\n"+text);
    }
    private void InitVoice(){
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        listener = new MyListener();
        mSpeechSynthesizer.setContext(this); // this 是Context的之类，如Activity
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_ENCODE, SpeechSynthesizer.AUDIO_ENCODE_PCM);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_RATE, SpeechSynthesizer.AUDIO_BITRATE_PCM);
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);
        String AppId = "9763871";
        String AppKey = "lN6aK43BNnQvvlA5txKypvxH";
        String AppSecret = "263564954da7965364153051ebaee055";

        int result = mSpeechSynthesizer.setAppId(AppId);
        showTip("appid设置"+result);
        /*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/
        result = mSpeechSynthesizer.setApiKey(AppKey,AppSecret);
        showTip("key校验结束"+result);

        mSpeechSynthesizer.auth(TtsMode.ONLINE);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置发声的人声音，在线生效
        mSpeechSynthesizer.initTts(TtsMode.MIX); // 初始化离在线混合模式，如果只需要在线合成功能，使用 TtsMode.ONLINE



    }
//    protected void handle(Message msg) {
//        int what = msg.what;
//        SpannableString colorfulText = new SpannableString(tv1.getText().toString());
//
//        colorfulText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, msg.arg1, Spannable
//                .SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv1.setText(colorfulText);
//    }
    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };
        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}
