package com.example.lr9;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lr9.data.Recipe;
import com.example.lr9.util.LocaleHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private MediaPlayer voicePlayer;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(LocaleHelper.applySavedLanguage(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseVoicePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // чтобы звук не продолжал играть в фоне
        if (voicePlayer != null && voicePlayer.isPlaying()) {
            voicePlayer.pause();
        }
    }

    private void releaseVoicePlayer() {
        try {
            if (voicePlayer != null) {
                voicePlayer.release();
                voicePlayer = null;
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        String lang = getIntent().getStringExtra("lang");
        if (lang == null) lang = "en";
        if (recipe == null) return;

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvMeta = findViewById(R.id.tvMeta);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvSteps = findViewById(R.id.tvSteps);

        // Кнопки озвучки
        MaterialButton btnPlay = findViewById(R.id.btnPlay);
        MaterialButton btnPause = findViewById(R.id.btnPause);
        MaterialButton btnRestart = findViewById(R.id.btnRestart);

        // Видео
        MaterialCardView cardVideo = findViewById(R.id.cardVideo);
        VideoView videoView = findViewById(R.id.videoView);

        // Заполнение текста
        tvTitle.setText(recipe.getTitle(lang));
        tvMeta.setText(recipe.mealType.name() + " • " + recipe.cuisine.name());
        tvIngredients.setText(joinBullets(recipe.getIngredients(lang)));
        tvSteps.setText(numbered(recipe.getSteps(lang)));

        // ---------- Озвучка ----------
        Integer voiceRes = recipe.getVoice(lang);

        if (voiceRes == null) {
            btnPlay.setEnabled(false);
            btnPause.setEnabled(false);
            btnRestart.setEnabled(false);

            // если хочешь подсказку:
            // btnPlay.setText("🔇 No voice");
        } else {
            // создаём плеер один раз
            voicePlayer = MediaPlayer.create(this, voiceRes);

            btnPlay.setOnClickListener(v -> {
                if (voicePlayer != null && !voicePlayer.isPlaying()) {
                    voicePlayer.start();
                }
            });

            btnPause.setOnClickListener(v -> {
                if (voicePlayer != null && voicePlayer.isPlaying()) {
                    voicePlayer.pause();
                }
            });

            btnRestart.setOnClickListener(v -> {
                if (voicePlayer != null) {
                    voicePlayer.seekTo(0);
                    voicePlayer.start();
                }
            });

            voicePlayer.setOnCompletionListener(mp -> {
                // после окончания вернём в начало
                try { mp.seekTo(0); } catch (Exception ignored) {}
            });
        }

        // ---------- Видео ----------
        if (recipe.videoRes == null) {
            cardVideo.setVisibility(View.GONE);
        } else {
            cardVideo.setVisibility(View.VISIBLE);

            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + recipe.videoRes);
            videoView.setVideoURI(uri);

            MediaController controller = new MediaController(this);
            controller.setAnchorView(videoView);
            videoView.setMediaController(controller);
            // не автозапускаем — пользователь нажмёт play сам
        }
    }

    private String joinBullets(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String s : lines) {
            sb.append("• ").append(s).append("\n");
        }
        return sb.toString().trim();
    }

    private String numbered(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(i + 1).append(". ").append(lines.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}