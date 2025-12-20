package com.example.lr8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText rusInput, mathInput;
    private TextView directionsText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rusInput = findViewById(R.id.rusInput);
        mathInput = findViewById(R.id.mathInput);
        directionsText = findViewById(R.id.directionsText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            if (validateInputs()) {
                int rusScore = Integer.parseInt(rusInput.getText().toString());
                int mathScore = Integer.parseInt(mathInput.getText().toString());
                new SendRequestTask().execute(rusScore, mathScore);
                directionsText.setText("Поиск направлений...");
            }
        });
    }

    private boolean validateInputs() {
        String rusText = rusInput.getText().toString().trim();
        String mathText = mathInput.getText().toString().trim();


        if (rusText.isEmpty() || mathText.isEmpty()) {
            Toast.makeText(this, "Введите баллы по всем предметам", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int rusScore = Integer.parseInt(rusText);
            int mathScore = Integer.parseInt(mathText);


            if (rusScore < 0 || rusScore > 100 || mathScore < 0 || mathScore > 100) {
                Toast.makeText(this, "Баллы должны быть от 0 до 100", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class SendRequestTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... scores) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://nti.urfu.ru/ege-calc/json");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                // Создание JSON запроса
                JSONObject json = new JSONObject();
                json.put("rus", scores[0]);      // Русский язык
                json.put("math", scores[1]);     // Математика
                json.put("inform", 100);         // Информатика
                json.put("social", 0);           // Обществознание
                json.put("chemistry", 0);        // Химия
                json.put("physics", 0);          // Физика
                json.put("eng", 0);              // Английский
                json.put("geo", 0);              // География

                // Отправка запроса
                OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes("UTF-8"));
                os.close();

                // Проверка ответа
                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    return "ERROR:" + responseCode;
                }

                // Чтение ответа
                InputStream inputStream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();

            } catch (Exception e) {
                return "ERROR:Ошибка подключения";
            } finally {
                if (conn != null) conn.disconnect();
                try { if (reader != null) reader.close(); } catch (Exception e) {}
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                directionsText.setText("Ошибка при получении данных");
                return;
            }

            if (result.startsWith("ERROR:")) {
                String error = result.substring(6);
                if (error.equals("Ошибка подключения")) {
                    directionsText.setText("Проверьте подключение к интернету");
                } else {
                    directionsText.setText("Ошибка сервера: " + error);
                }
                return;
            }

            try {
                JSONArray array = new JSONArray(result);

                if (array.length() == 0) {
                    directionsText.setText("❌ Не найдено направлений с такими баллами");
                    return;
                }

                StringBuilder names = new StringBuilder();
                names.append("✅ Найдено направлений: ").append(array.length()).append("\n\n");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String name = obj.optString("specialty_name", "Направление");
                    names.append("• ").append(name).append("\n\n");
                }

                directionsText.setText(names.toString().trim());

            } catch (Exception e) {
                directionsText.setText("Ошибка обработки данных");
            }
        }
    }
}