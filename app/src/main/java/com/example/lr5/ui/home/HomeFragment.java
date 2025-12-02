package com.example.lr5.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lr5.R;
import com.example.lr5.databinding.FragmentHomeBinding;
import com.google.android.material.textfield.TextInputEditText;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Button btn_FIO;

    private TextInputEditText F,I,O,Email;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_FIO = root.findViewById(R.id.btn_FIO);
        F = root.findViewById(R.id.F);
        I = root.findViewById(R.id.I);
        O = root.findViewById(R.id.O);
        Email = root.findViewById(R.id.Email);

        btn_FIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback();
            }
        });

        return root;
    }

    private void feedback(){
        String F_full = F.getText().toString().trim();
        String I_full = I.getText().toString().trim();
        String O_full = O.getText().toString().trim();
        String Email_full = Email.getText().toString().trim();

        if (F_full.isEmpty() || I_full.isEmpty() || O_full.isEmpty() || Email_full.isEmpty()) {
            Toast.makeText(requireContext(),"Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(Email_full)) {
            Toast.makeText(requireContext(), "Введите корректный email адрес", Toast.LENGTH_SHORT).show();
            return;
        }

        clearFields();
        showSuccessDialog(F_full,I_full,O_full,Email_full);
    }

    private void clearFields(){
        F.setText("");
        I.setText("");
        O.setText("");
        Email.setText("");
    }

    private void showSuccessDialog(String F_full, String I_full, String O_full,String Email_full){
        String FullName = F_full + I_full + O_full;
        String message = "ФИО: " + FullName + "\nEmail: " + Email_full + "\n\n Заявка успешно отправлена!";

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("✅ Успешно")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }

    private boolean isValidEmail(String Email_full) {
        //  Есть ли @
        if (!Email_full.contains("@")) {
            return false;
        }
        // @ не первый и не последний
        if (Email_full.indexOf("@") == 0 || Email_full.indexOf("@") == Email_full.length() - 1) {
            return false;
        }
        // Проверка что есть точка после @
        String[] parts = Email_full.split("@");
        if (parts.length != 2 || !parts[1].contains(".")) {
            return false;
        }
        // Проверка что после последней точки есть хотя бы 2 символа
        String domain = parts[1];
        String[] domainParts = domain.split("\\.");
        if (domainParts.length < 2 || domainParts[domainParts.length - 1].length() < 2) {
            return false;
        }

        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

}