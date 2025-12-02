package com.example.lr5.ui.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lr5.R;
import com.example.lr5.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private Button btn_test;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_test = root.findViewById(R.id.btn_test);


        btn_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String[] animals = {"Корова","Лев","Лошадь","Волк","Кролик"};

                    final boolean[] checkItems = {false,false,false,false,false};

                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Выберите травоядных животных")
                            .setIcon(R.drawable.free_icon_app_13895628)
                            .setMultiChoiceItems(animals, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    checkItems [which] = isChecked;
                                }
                            })
                            .setPositiveButton("Проверить", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean correctCow = checkItems[0]; // +
                                    boolean correctHorse = checkItems[2]; // +
                                    boolean correctRabbit = checkItems[4]; // +
                                    boolean correctWolf  = checkItems[3]; // -
                                    boolean correctLion = checkItems[1]; // -

                                    if (correctCow && correctHorse && correctRabbit && !correctLion && !correctWolf){
                                        // ???
                                        Toast.makeText(requireContext(), "Все ответы Правильные!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {

                                        Toast.makeText(requireContext(), "Неверно, попробуй ещё раз", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(requireContext(), "Отмена", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                }
            });





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}