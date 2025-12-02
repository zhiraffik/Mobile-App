package com.example.lr6;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DBHelper helper;
    private SQLiteDatabase database;
    private TextView text;
    private Button btn_eat, btn_clothes, btn_toys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        Button btnShowProducts = findViewById(R.id.btn_eat);
        Button btnShowClothes = findViewById(R.id.btn_clothes);
        Button btnShowToys = findViewById(R.id.btn_toys);
        text = findViewById(R.id.text);
        btnShowProducts.setOnClickListener(v -> showProductsByCategory("1"));
        btnShowClothes.setOnClickListener(v -> showProductsByCategory("2"));
        btnShowToys.setOnClickListener(v -> showProductsByCategory("3"));
    }

    private void showProductsByCategory(String category) {
        try {
            Cursor cursor = helper.getProductsByCategory(category);

            StringBuilder result = new StringBuilder();
            if (cursor != null && cursor.moveToFirst()) {
                do {

                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("product_name"));
                    @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                    result.append("🛒 ").append(name)
                            .append("\n💵 Цена: ").append(price).append(" руб.")
                            .append("\n📝 ").append(description)
                            .append("\n────────────────────\n");

                } while (cursor.moveToNext());
                cursor.close();

                text.setText(result.toString());
            } else {
                text.setText("❌ Товары не найдены для выбранной категории");
            }
        } catch (Exception e) {
            text.setText("⚠️ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}