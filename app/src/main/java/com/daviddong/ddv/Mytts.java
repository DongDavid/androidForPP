package com.daviddong.ddv;

import android.content.Context;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;

public class Mytts {
    public static SpeechSynthesizer getSpeaker(Context context){
        SpeechSynthesizer mSpeechSynthesizer;
        MyListener listener;
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        listener = new MyListener();
        mSpeechSynthesizer.setContext(context); // this 是Context的之类，如Activity
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_ENCODE, SpeechSynthesizer.AUDIO_ENCODE_PCM);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_AUDIO_RATE, SpeechSynthesizer.AUDIO_BITRATE_PCM);
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);
        String AppId = "9763871";
        String AppKey = "lN6aK43BNnQvvlA5txKypvxH";
        String AppSecret = "263564954da7965364153051ebaee055";

        int result = mSpeechSynthesizer.setAppId(AppId);
        /*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/
        result = mSpeechSynthesizer.setApiKey(AppKey,AppSecret);

        mSpeechSynthesizer.auth(TtsMode.ONLINE);
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置发声的人声音，在线生效
        mSpeechSynthesizer.initTts(TtsMode.ONLINE); // 初始化离在线混合模式，如果只需要在线合成功能，使用 TtsMode.ONLINE
        return mSpeechSynthesizer;
    }
}
