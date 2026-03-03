package com.example.lr9.util;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer {

    public static void playShort(Context context, int rawResId) {
        MediaPlayer mp = MediaPlayer.create(context, rawResId);
        if (mp == null) return;

        mp.setOnCompletionListener(player -> {
            player.reset();
            player.release();
        });

        mp.start();
    }
}