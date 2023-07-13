package com.brightminded.cordova.plugins;

import android.content.Context;
import android.media.AudioManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioFocus extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("requestFocus")) {
            this.requestFocus(callbackContext);
            return true;
        }
          if (action.equals("cancelFocus")) {
            this.cancelFocus(callbackContext);
            return true;
        }

        return false;
    }
    private AudioManager.OnAudioFocusChangeListener listener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            //nothing to do here
        }
    };
    private void requestFocus(CallbackContext callbackContext) {
        // get AudioManager
        AudioManager am = (AudioManager)this.cordova.getActivity()
                                    .getApplicationContext()
                                    .getSystemService(Context.AUDIO_SERVICE);

        // request audio focus
        int result = am.requestAudioFocus(listener,
                                        AudioManager.STREAM_MUSIC,
                                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        // return result
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            callbackContext.success("");
        } else {
            callbackContext.error("");
        }
    }

      private void cancelFocus(CallbackContext callbackContext) {

        // get AudioManager
        AudioManager am = (AudioManager)this.cordova.getActivity()
                                    .getApplicationContext()
                                    .getSystemService(Context.AUDIO_SERVICE);

                                    am.abandonAudioFocus(listener);

              // request audio focus
            //  int result = AudioManager.AUDIOFOCUS_NONE;
          /*  int result=  am.requestAudioFocus(listener,
                                              AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_NONE); */
              // return result
         /*  int result = am.requestAudioFocus(listener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
          if (result == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
            // Fokus dźwięku został przyznany
            // Wykonaj operacje na dźwięku

            // Anuluj fokus dźwięku
            am.abandonAudioFocus(listener);
            callbackContext.success("");
          }
          else
          {
            am.abandonAudioFocus(listener);
                    callbackContext.error("");
           } */
     }
}

