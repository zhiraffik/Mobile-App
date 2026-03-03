package com.example.lr9;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private MediaPlayer voicePlayer;

    // Таймеры (минимум 3)
    private RecipeTimer t1, t2, t3;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.applySavedLanguage(newBase));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // не играет голос в фоне
        if (voicePlayer != null && voicePlayer.isPlaying()) voicePlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (voicePlayer != null) {
            voicePlayer.release();
            voicePlayer = null;
        }
        if (t1 != null) t1.release();
        if (t2 != null) t2.release();
        if (t3 != null) t3.release();
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

        MaterialButton btnPlay = findViewById(R.id.btnPlay);
        MaterialButton btnPause = findViewById(R.id.btnPause);
        MaterialButton btnRestart = findViewById(R.id.btnRestart);

        MaterialCardView cardVideo = findViewById(R.id.cardVideo);
        VideoView videoView = findViewById(R.id.videoView);

        tvTitle.setText(recipe.getTitle(lang));
        tvMeta.setText(recipe.mealType.name() + " • " + recipe.cuisine.name());
        tvIngredients.setText(joinBullets(recipe.getIngredients(lang)));
        tvSteps.setText(numbered(recipe.getSteps(lang)));

        // ------- Voice  -------
        Integer voiceRes = recipe.getVoice(lang);
        if (voiceRes == null) {
            btnPlay.setEnabled(false);
            btnPause.setEnabled(false);
            btnRestart.setEnabled(false);
        } else {
            voicePlayer = MediaPlayer.create(this, voiceRes);

            btnPlay.setOnClickListener(v -> {
                if (voicePlayer != null && !voicePlayer.isPlaying()) voicePlayer.start();
            });

            btnPause.setOnClickListener(v -> {
                if (voicePlayer != null && voicePlayer.isPlaying()) voicePlayer.pause();
            });

            btnRestart.setOnClickListener(v -> {
                if (voicePlayer != null) {
                    voicePlayer.seekTo(0);
                    voicePlayer.start();
                }
            });

            voicePlayer.setOnCompletionListener(mp -> {
                try { mp.seekTo(0); } catch (Exception ignored) {}
            });
        }

        // ------- Video -------
        if (recipe.videoRes == null) {
            cardVideo.setVisibility(View.GONE);
        } else {
            cardVideo.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + recipe.videoRes);
            videoView.setVideoURI(uri);
            MediaController controller = new MediaController(this);
            controller.setAnchorView(videoView);
            videoView.setMediaController(controller);
        }

        // =========================
        // TIMER SECTION (главное)
        // =========================
        MaterialCardView cardTimers = findViewById(R.id.cardTimers);

        //
        boolean showTimers = "di_pizza".equals(recipe.id) || "di_kfc".equals(recipe.id);
        cardTimers.setVisibility(showTimers ? View.VISIBLE : View.GONE);
        if (!showTimers) return;

        // 1) Bake (editable) — 8 минут по умолчанию, звук 1
        TextView tvT1 = findViewById(R.id.tvT1);
        MaterialButton btnT1Start = findViewById(R.id.btnT1Start);
        MaterialButton btnT1Pause = findViewById(R.id.btnT1Pause);
        MaterialButton btnT1Reset = findViewById(R.id.btnT1Reset);
        EditText etT1Min = findViewById(R.id.etT1Min);
        EditText etT1Sec = findViewById(R.id.etT1Sec);
        MaterialButton btnT1Set = findViewById(R.id.btnT1Set);

        t1 = new RecipeTimer(this, tvT1, 8 * 60_000L, R.raw.timer_short);

        btnT1Set.setOnClickListener(v -> {
            long newMs = parseMs(etT1Min, etT1Sec);
            if (newMs >= 1_000L) { // минимум 1 сек
                t1.setInitialMillis(newMs); // разрешаем менять только когда таймер не запущен/после reset
            }
        });

        btnT1Start.setOnClickListener(v -> t1.start(btnT1Start));
        btnT1Pause.setOnClickListener(v -> t1.pause(btnT1Start));
        btnT1Reset.setOnClickListener(v -> t1.reset(btnT1Start));

        // 2) Dough rest — 40 минут, звук 2
        TextView tvT2 = findViewById(R.id.tvT2);
        MaterialButton btnT2Start = findViewById(R.id.btnT2Start);
        MaterialButton btnT2Pause = findViewById(R.id.btnT2Pause);
        MaterialButton btnT2Reset = findViewById(R.id.btnT2Reset);

        t2 = new RecipeTimer(this, tvT2, 40 * 60_000L, R.raw.timer_long1);

        btnT2Start.setOnClickListener(v -> t2.start(btnT2Start));
        btnT2Pause.setOnClickListener(v -> t2.pause(btnT2Start));
        btnT2Reset.setOnClickListener(v -> t2.reset(btnT2Start));

        // 3) Sauce simmer — 5 минут, звук 3
        TextView tvT3 = findViewById(R.id.tvT3);
        MaterialButton btnT3Start = findViewById(R.id.btnT3Start);
        MaterialButton btnT3Pause = findViewById(R.id.btnT3Pause);
        MaterialButton btnT3Reset = findViewById(R.id.btnT3Reset);
        btnT3Start.setOnClickListener(v -> t3.start(btnT3Start));
        btnT3Pause.setOnClickListener(v -> t3.pause(btnT3Start));
        btnT3Reset.setOnClickListener(v -> t3.reset(btnT3Start));
        t3 = new RecipeTimer(this, tvT3, 5 * 60_000L, R.raw.timer_long2);

        // Для KFC можно сделать 2 таймера, а третий скрыть:
        if ("di_kfc".equals(recipe.id)) {

            TextView lblT1 = findViewById(R.id.lblT1);
            TextView lblT2 = findViewById(R.id.lblT2);
            TextView lblT3 = findViewById(R.id.lblT3);

            // Меняем названия
            lblT1.setText(getString(R.string.timer_kfc_legs));
            lblT2.setText(getString(R.string.timer_kfc_wings));
            lblT3.setText(getString(R.string.timer_kfc_fillet));

            // Меняем время
            t1.setInitialMillis(15 * 60_000L);
            t2.setInitialMillis(7 * 60_000L);
            t3.setInitialMillis(5 * 60_000L);
        }

    }

    // ===== Helper: таймер =====
    private static class RecipeTimer {
        private final Context context;
        private final TextView tv;
        private final int soundRes;

        private long initialMs;
        private long remainingMs;

        private CountDownTimer timer;
        private boolean running = false;
        private boolean startedOnce = false; // чтобы не стартовать 2 раза без reset

        private final int normalColor;
        private final int warningColor = Color.parseColor("#D32F2F"); // красный

        RecipeTimer(Context context, TextView tv, long initialMs, int soundRes) {
            this.context = context;
            this.tv = tv;
            this.initialMs = initialMs;
            this.remainingMs = initialMs;
            this.soundRes = soundRes;
            this.normalColor = tv.getCurrentTextColor();
            updateText();
        }

        void setInitialMillis(long newMs) {
            // редактирование разрешаем только если не идёт отсчёт
            if (running) return;
            // если таймер уже запускали — требуем reset
            if (startedOnce) return;

            initialMs = newMs;
            remainingMs = newMs;
            updateText();
        }

        void start(MaterialButton btnStart) {
            // запрет двойного старта без reset
            if (startedOnce && !running && remainingMs == initialMs) {
                // после reset startedOnce станет false
            }
            if (running) return;
            if (startedOnce && remainingMs == initialMs) return;

            startedOnce = true;
            running = true;
            btnStart.setEnabled(false); // пока идёт — старт недоступен

            timer = new CountDownTimer(remainingMs, 250) {
                @Override public void onTick(long millisUntilFinished) {
                    remainingMs = millisUntilFinished;
                    updateText();
                }
                @Override public void onFinish() {
                    running = false;
                    remainingMs = 0;
                    updateText();
                    playSound();

                }
            }.start();
        }

        void pause(MaterialButton btnStart) {
            if (!running) return;
            running = false;
            if (timer != null) timer.cancel();
            timer = null;

            btnStart.setEnabled(true);
        }

        void reset(MaterialButton btnStart) {
            if (timer != null) timer.cancel();
            timer = null;
            running = false;
            startedOnce = false;
            remainingMs = initialMs;
            updateText();
            btnStart.setEnabled(true);
        }

        void release() {
            if (timer != null) timer.cancel();
            timer = null;
        }

        private void updateText() {
            tv.setText(formatMs(remainingMs));
            if (remainingMs > 0 && remainingMs < 60_000L) {
                tv.setTextColor(warningColor);
            } else {
                tv.setTextColor(normalColor);
            }
        }

        private void playSound() {
            MediaPlayer mp = MediaPlayer.create(context, soundRes);
            if (mp == null) return;
            mp.setOnCompletionListener(p -> {
                p.reset();
                p.release();
            });
            mp.start();
        }
    }

    private static long parseMs(EditText etMin, EditText etSec) {
        String mStr = etMin.getText() != null ? etMin.getText().toString().trim() : "";
        String sStr = etSec.getText() != null ? etSec.getText().toString().trim() : "";

        int m = 0, s = 0;
        if (!TextUtils.isEmpty(mStr)) m = Integer.parseInt(mStr);
        if (!TextUtils.isEmpty(sStr)) s = Integer.parseInt(sStr);
        if (s > 59) s = 59;

        return (m * 60L + s) * 1000L;
    }

    private static String formatMs(long ms) {
        long totalSec = Math.max(0, ms / 1000L);
        long min = totalSec / 60L;
        long sec = totalSec % 60L;
        return String.format(Locale.US, "%02d:%02d", min, sec);
    }

    private String joinBullets(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String s : lines) sb.append("• ").append(s).append("\n");
        return sb.toString().trim();
    }

    private String numbered(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) sb.append(i + 1).append(". ").append(lines.get(i)).append("\n");
        return sb.toString().trim();
    }
}
