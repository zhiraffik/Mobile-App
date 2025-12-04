package com.example.lr7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lr7.R;

public class ResultActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = findViewById(R.id.imageView);
        tvDescription = findViewById(R.id.tvDescription);

        String qrText = getIntent().getStringExtra("qr_text");

        if (qrText != null) {
            showContentForQR(qrText);
        }
    }

    private void showContentForQR(String qrText) {

        String text = qrText.toLowerCase();

        switch (text) {
            case "house":
                imageView.setImageResource(R.drawable.house);
                tvDescription.setText("Это дом");
                break;

            case "cat":
                imageView.setImageResource(R.drawable.cat);
                tvDescription.setText("Это кошка");
                break;

            case "car":
                imageView.setImageResource(R.drawable.car);
                tvDescription.setText("Это машина");
                break;

            default:
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
                tvDescription.setText("Неизвестный QR-код: " + text);
                break;
        }
    }
}
