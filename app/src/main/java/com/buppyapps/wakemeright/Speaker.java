package com.buppyapps.wakemeright;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by JorgeMunoz on 2/25/2016.
 */
public class Speaker implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    public Speaker(Context context){
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.US);
        }
    }

    public void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null,"id" );
        } else {
            HashMap<String, String> hash = new HashMap<>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_NOTIFICATION));
            tts.speak(text, TextToSpeech.QUEUE_ADD, hash);
        }
    }
}